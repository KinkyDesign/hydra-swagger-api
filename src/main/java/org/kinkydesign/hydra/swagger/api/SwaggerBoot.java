/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api;

import com.fasterxml.jackson.databind.util.Annotations;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import io.swagger.models.auth.OAuth2Definition;
import io.swagger.models.properties.Property;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldType;
import org.kinkydesign.hydra.swagger.api.dto.ConvertedValue;
import org.kinkydesign.hydra.swagger.api.dto.ErrorReport;
import org.kinkydesign.hydra.swagger.api.dto.Value;
import org.kinkydesign.hydra.swagger.api.dto.swagger.AddLdModels;
import org.kinkydesign.hydra.swagger.api.dto.swagger.PropertyExtended;
import org.kinkydesign.hydra.swagger.api.dto.swagger.SwaggerModelExtended;
import org.kinkydesign.hydra.swagger.api.dto.swagger.SwaggerProperty;
import org.reflections.Reflections;

/**
 *
 * @author pantelispanka
 */
public class SwaggerBoot extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger();

        swagger.externalDocs(new ExternalDocs("Find out more about Swagger", "http://swagger.io"));
        swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
//        swagger.securityDefinition("petstore_auth",
//                new OAuth2Definition()
//                        .implicit("http://petstore.swagger.io/api/oauth/dialog")
//                        .scope("read:pets", "read your pets")
//                        .scope("write:pets", "modify pets in your account"));
//        swagger.tag(new Tag()
//                .name("pet")
//                .description("Everything about your Pets")
//                .externalDocs(new ExternalDocs("Find out more", "http://swagger.io")));
//        swagger.tag(new Tag()
//                .name("store")
//                .description("Access to Petstore orders"));
//        swagger.tag(new Tag()
//                .name("user")
//                .description("Operations about user")
//                .externalDocs(new ExternalDocs("Find out more about our store", "http://swagger.io")));

        List<Class> classes = new ArrayList<>();
        classes.add(ErrorReport.class);
        classes.add(ConvertedValue.class);
        classes.add(Value.class);

        List<Model> models = new ArrayList<>();

        for (Class c : classes) {

            AddLdModels model = new AddLdModels();
            Map<String, Property> props = new HashMap<>();
            Map<String, Object> vent = new HashMap<>();

            if (c.isAnnotationPresent(JsonldType.class)) {
                JsonldType jld = (JsonldType) c.getAnnotation(JsonldType.class);
                PropertyExtended p = new SwaggerProperty();
                p.setName("@type");
                p.setDefault(jld.value());
                p.setDescription(jld.value());
                props.put("@type", p);
                PropertyExtended pId = new SwaggerProperty();
                pId.setDescription(URI.class.getSimpleName());
                props.put("@id", pId);
            }

            for (Field f : c.getDeclaredFields()) {

                PropertyExtended p = new SwaggerProperty(f);
                if (f.isAnnotationPresent(JsonldProperty.class)) {

                    JsonldProperty jld = (JsonldProperty) f.getAnnotation(JsonldProperty.class);
                    Map<String, Object> ventP = new HashMap<>();
                    ventP.put(f.getName(), jld.value());
                    vent.put(f.getName(), jld.value());
                    p.setVendorExtentions(ventP);
                }

                props.put(f.getName(), p);

                model.setProperties(props);
            }

            if (vent.size() > 0) {
                PropertyExtended p = new SwaggerProperty();
                p.setContext(vent);
                props.put("@context", p);
                model.setProperties(props);
            }

            swagger.setVendorExtensions(vent);
            model.setTitle(c.getSimpleName() + "_ld");
            models.add(model);
            swagger.model(c.getSimpleName() + "_ld", model);

        }

        for (Model m : models) {
            swagger.model(m.getTitle(), m);
        }

        new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
    }

}
