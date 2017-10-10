package org.kinkydesign.hydra.swagger.api.resources;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.ReaderListener;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import io.swagger.models.properties.Property;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldType;
import org.kinkydesign.hydra.swagger.api.dto.ConvertedValue;
import org.kinkydesign.hydra.swagger.api.dto.ErrorReport;
import org.kinkydesign.hydra.swagger.api.dto.Value;
import org.kinkydesign.hydra.swagger.api.dto.swagger.AddLdModels;
import org.kinkydesign.hydra.swagger.api.dto.swagger.PropertyExtended;
import org.kinkydesign.hydra.swagger.api.dto.swagger.SwaggerProperty;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SwaggerDefinition
public class SwaggerModifier implements ReaderListener {
    @Override
    public void beforeScan(Reader reader, Swagger swagger) {
    }

    @Override
    public void afterScan(Reader reader, Swagger swagger) {
    swagger.externalDocs(new ExternalDocs("Find out more about Swagger", "http://swagger.io"));
    swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
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

        model.setTitle(c.getSimpleName() + "_ld");
        models.add(model);
        swagger.model(c.getSimpleName() + "_ld", model);
    }

    for (Model m : models) {
        swagger.model(m.getTitle(), m);
    }
    }
}

