/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.dto;

import org.kinkydesign.hydra.swagger.api.annotations.JsonldProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldType;

/**
 *
 * @author pantelispanka
 */
@JsonldType("http://schema.org/object")
public class Value {
    
    @JsonldProperty("http://schema.org/value")
    private String value;

    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
            
}
