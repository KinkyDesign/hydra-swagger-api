/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api;

import io.swagger.models.Contact;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.auth.OAuth2Definition;
import io.swagger.models.properties.Property;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author pantelispanka
 */
public class SwaggerBoot extends HttpServlet{
       
    @Override
    public void init(ServletConfig config) throws ServletException{
        
         Info info = new Info()
                .title("Affie api")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact()
                        .email("burnyourpc@gmail.com"));
         
        
        
        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger()
                .info(info);
        swagger.securityDefinition("petstore_auth",
                new OAuth2Definition()
                        .implicit("http://localhost:8002/oauth/dialog")
        );
        
        context.setAttribute("swagger", swagger);
        
        
    }
    
    
}
