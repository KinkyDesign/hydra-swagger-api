/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.providers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import static java.lang.System.out;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
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

    public JsonldProvider() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        Reflections reflectedWriters = new Reflections("org.kinkydesign.hydra.swagger.api.dto");
        Set<Class<?>> acceptedClasses = reflectedWriters.getTypesAnnotatedWith(JsonldType.class);
        Boolean val= false;
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
//        JsonObject json = new JsonObject();
        ObjectNode node = objectMapper.valueToTree(t);
        System.out.println(node);
        if (type.equals(ErrorReport.class)) {
            ErrorReport err = (ErrorReport) t;
            err.setErrorMessage("ErrorMessage json ld");

            objectMapper.writeValue(entityStream, err);
        }
        entityStream.flush();
    }

}
