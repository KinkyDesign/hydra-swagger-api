/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.dto.swagger;

import io.swagger.models.Xml;
import io.swagger.models.properties.Property;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author pantelispanka
 */
public class SwaggerProperty implements Property{

    private String _title;
    private String _description;
    private String _type;
    private String _format;
    private Boolean _allowEmptyValue;
    private String _name;
    private Boolean _required;
    private Boolean _readOnly;
    private Object _examble;
    private Xml _xml;
    private String _default;
    private int _possition;
    private String _access;
    private Map<String, Object> _vendorExtensions;
    
    
    public SwaggerProperty(){}
    
    public SwaggerProperty(Field f){
        if(f.getName() != null){
            this._name = f.getName();
        }
        this._type = f.getType().getSimpleName();
        
    }
    
    
    @Override
    public Property title(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Property description(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType() {
        return this._type;
    }
        
    public void setType(String type){
        this._type = type;
    }

    @Override
    public String getFormat() {
        return this._format;
    }

    @Override
    public String getTitle() {
        return this._title;
    }

    @Override
    public void setTitle(String title) {
        this._title = title;
    }

    @Override
    public String getDescription() {
        return this._description;
    }

    @Override
    public void setDescription(String descr) {
        this._description = descr;
    }

    @Override
    public Boolean getAllowEmptyValue() {
        return this._allowEmptyValue;
    }

    @Override
    public void setAllowEmptyValue(Boolean allow) {
        this._allowEmptyValue = allow;
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public void setName(String name) {
        this._name = name;
    }

    @Override
    public boolean getRequired() {
        return this._required;
    }

    @Override
    public void setRequired(boolean req) {
        this._required = req;
    }

    @Override
    public Object getExample() {
        return this._examble;
    }

    @Override
    public void setExample(Object ex) {
        this._examble = ex;
    }

    @Override
    public Boolean getReadOnly() {
        return this._readOnly;
    }

    @Override
    public void setReadOnly(Boolean readOn) {
        this._readOnly = readOn;
    }

    @Override
    public Integer getPosition() {
        return this._possition;
    }

    @Override
    public void setPosition(Integer pos) {
        this._possition = pos;
    }

    @Override
    public Xml getXml() {
        return this._xml;
    }

    @Override
    public void setXml(Xml xml) {
        this._xml = xml;
    }

    @Override
    public void setDefault(String def) {
        this._default = def;
    }

    @Override
    public String getAccess() {
        return this._access;
    }

    @Override
    public void setAccess(String acc) {
        this._access = acc;
    }

    @Override
    public Map<String, Object> getVendorExtensions() {
        return this._vendorExtensions;
    }

    @Override
    public Property rename(String newName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setExample(String example) {
        this._examble = example;
    }
    
}
