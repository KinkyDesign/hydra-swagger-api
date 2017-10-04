/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.kinkydesign.hydra.swagger.api.resources.Cas;
import org.kinkydesign.hydra.swagger.api.resources.Smiles;

/**
 *
 * @author pantelispanka
 */
@ApplicationPath("/api")
public class ApplicationHydra extends Application{
    
    public ApplicationHydra(){
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setDescription("A first try to document a rest api with hydra annotations and json ld through swagger tooling");
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/HydraApi/api");
        beanConfig.setResourcePackage("org.kinkydesign.hydra.swagger.api.resources");
        beanConfig.setScan(true);
        
    }
    
    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new HashSet();
        
        resources.add(Cas.class);
        resources.add(Smiles.class);
        
        resources.add(SwaggerSerializers.class);
        resources.add(ApiListingResource.class);
        
        
        return resources;
    }
    
    
}
