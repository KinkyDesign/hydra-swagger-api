/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.dto.swagger;

import io.swagger.models.Model;
import java.util.Map;

/**
 *
 * @author pantelispanka
 */
public interface SwaggerModelExtended extends Model{
    
    public void setVendorExtentions(Map<String, Object> vendorExtensions);
    
}
