package org.kinkydesign.hydra.swagger.api.resources;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.ReaderListener;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldType;
import org.kinkydesign.hydra.swagger.api.dto.ConvertedValue;
import org.kinkydesign.hydra.swagger.api.dto.ConvertedValueExtension;
import org.kinkydesign.hydra.swagger.api.dto.ErrorReport;
import org.kinkydesign.hydra.swagger.api.dto.Value;

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
        /**
         * Populate classes that we want to dynamically create Json-LD documentation
         */
        List<Class> classes = new ArrayList<>();
        classes.add(ErrorReport.class);
        classes.add(ConvertedValue.class);
        classes.add(ConvertedValueExtension.class);

        List<Model> models = new ArrayList<>();
        for (Class c : classes) {
            Model model = new ModelImpl();
            Map<String, Property> props = new HashMap<>();
            Map<String, Object> vent = new HashMap<>();
            /**
             * Find JsonldType annotation and populate @type and @id fields
             */
            if (c.isAnnotationPresent(JsonldType.class)) {
                JsonldType jld = (JsonldType) c.getAnnotation(JsonldType.class);
                Property p = new StringProperty();
                p.setDescription(jld.value());
                props.put("@type", p);
                Property pId = new StringProperty();
                pId.setDescription(URI.class.getSimpleName());
                props.put("@id", pId);
            }
            /**
             * Find JsonldProperty annotations and populate @context field
             * We check them recursively to include parent fields
             */

            Field[] fields = c.getDeclaredFields();
            Class tempClass = c;
            do {
                for (Field f : fields) {
                    if (f.isAnnotationPresent(JsonldProperty.class)) {
                        JsonldProperty jld = (JsonldProperty) f.getAnnotation(JsonldProperty.class);
                        vent.put(f.getName(), jld.value());
                    }
                }
                if (tempClass.getSuperclass()!=null) {
                    fields = tempClass.getSuperclass().getDeclaredFields();
                    tempClass = c.getSuperclass();
                }
                else
                    fields = new Field[]{};
            } while (fields.length!=0);

            if (vent.size() > 0) {
                Property p = new StringProperty();
                p.setDescription(vent.toString());
                props.put("@context", p);
                model.setProperties(props);
            }
            /**
             * Set title of model, it's {initialModel}_ld
             */
            model.setTitle(c.getSimpleName() + "_ld");
            models.add(model);
            swagger.model(c.getSimpleName() + "_ld", model);
        }
        for (Model m : models) {
            swagger.model(m.getTitle(), m);
        }
    }
}

