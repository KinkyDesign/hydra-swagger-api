/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.dto.swagger;

import io.swagger.models.properties.Property;
import java.util.Map;

/**
 *
 * @author pantelispanka
 */
public interface PropertyExtended extends Property{
    
    public void setVendorExtentions(Map<String, Object> extentions);
    
    public Map<String, Object> getContext();
    public void setContext(Map<String, Object> context);
    
}
