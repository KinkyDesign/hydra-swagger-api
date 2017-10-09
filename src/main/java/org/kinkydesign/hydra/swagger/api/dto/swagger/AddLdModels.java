/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.dto.swagger;

import io.swagger.models.ExternalDocs;
import io.swagger.models.Model;
import io.swagger.models.properties.Property;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pantelispanka
 */
public class AddLdModels implements Model {

    private String title;
    private String description;
    private Map<String, Property> properties;
    private Object example;
    private ExternalDocs extDocs;
    private String reference;
    private Map<String, Object> vendorExtensions;

    public AddLdModels(){
        
    }
    
    public AddLdModels(Class T) {
        this.title = T.getSimpleName();
        this.description = T.getCanonicalName();
        Field[] fs = T.getDeclaredFields();
        for (Field f : fs) {
            
        }
    }

    public Map<String, Property> getClassProperties(Field[] fs) {
        Map<String, Property> mapSp = new HashMap<>();
        for (Field f : fs) {
            Property p = new SwaggerProperty(f);
            mapSp.put(f.toGenericString(), p);
        }
        return mapSp;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String arg0) {
        this.title = arg0;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String arg0) {
        this.description = arg0;
    }

    @Override
    public Map<String, Property> getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Map<String, Property> props) {
        this.properties = props;
    }

    @Override
    public Object getExample() {
        return this.example;
    }

    @Override
    public void setExample(Object examp) {
        this.example = examp;
    }

    @Override
    public ExternalDocs getExternalDocs() {
        return this.extDocs;
    }

    @Override
    public String getReference() {
        return this.reference;
    }

    @Override
    public void setReference(String ref) {
        this.reference = ref;
    }

    @Override
    public Map<String, Object> getVendorExtensions() {
        return this.vendorExtensions;
    }

    @Override
    public Object clone() {
        Object o = new Object();
        return o;
    }

//    @Override
//    public void setVendorExtentions(Map<String, Object> vendorExtensions) {
//        this.vendorExtensions = vendorExtensions;
//    }

}
