/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.providers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import static java.lang.System.out;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldType;
import org.kinkydesign.hydra.swagger.api.dto.ErrorReport;
import org.reflections.Reflections;

/**
 *
 * @author pantelispanka
 */
@Provider
@Produces("application/ld+json")
@Consumes("application/ld+json")
@ApplicationScoped
public class JsonldProvider implements MessageBodyWriter<Object> {

    private final ObjectMapper objectMapper;

    @Context
    UriInfo uriInfo;
    
    
    public JsonldProvider() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        Reflections reflectedWriters = new Reflections("org.kinkydesign.hydra.swagger.api.dto");
        Set<Class<?>> acceptedClasses = reflectedWriters.getTypesAnnotatedWith(JsonldType.class);
        Boolean val = false;
        if (acceptedClasses.contains(type)) {
            val = true;
        }
        return val;
    }

    @Override
    public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Object t, Class<?> type,
            Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException {

        ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
        Map<String, Object> context = new HashMap<>();

        try {
            
            for (Field f : type.getDeclaredFields()) {
                /**
                 * Add the context of the jsonld from JsonldProperties
                 */
                JsonldProperty jldProp = f.getAnnotation(JsonldProperty.class);
                String contextProp = f.getName();
                context.put(contextProp, jldProp.value());
            }

            node.putPOJO("@context", context);
            /**
             * Add the type of json from JsonldType
             */
            JsonldType jldType = type.getAnnotation(JsonldType.class);
            node.put("@type", jldType.value());
            
            
            /**
             * Add the @id for the json ld
             */
            String uri = uriInfo.getAbsolutePath().toString();
            node.put("@id", uri);
            
            for(Field f : type.getDeclaredFields()){
                f.setAccessible(true);
                Object ob = f.get(t);
                node.putPOJO(f.getName(), ob);
            }
            
            
        } catch (NullPointerException e) {
            ErrorReport err = new ErrorReport();
            err.setStatus(416);
            err.setErrorMessage("Mediatype unsupported");
            err.setDevMessage(e.getMessage());
            
            objectMapper.writeValue(entityStream, err);
            entityStream.flush();
        } catch (IllegalArgumentException | IllegalAccessException ex) { 
            Logger.getLogger(JsonldProvider.class.getName()).log(Level.SEVERE, null, ex);
            ErrorReport err = new ErrorReport();
            err.setStatus(500);
            err.setErrorMessage("Internall Server Error");
            err.setDevMessage(ex.getMessage());
            objectMapper.writeValue(entityStream, err);
            entityStream.flush();
        }
        


        objectMapper.writeValue(entityStream, node);
        entityStream.flush();
    }

}
