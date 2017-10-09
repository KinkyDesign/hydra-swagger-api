/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.kinkydesign.hydra.swagger.api.dto.ConvertedValue;
import org.kinkydesign.hydra.swagger.api.dto.ErrorReport;
import org.kinkydesign.hydra.swagger.api.dto.Value;

/**
 *
 * @author pantelispanka
 */
@Path("/cas")
@Consumes({"application/json", "application/ld+json"})
@Produces({"application/json", "application/ld+json"})
@Api(value = "/Cas", tags = "Cas")
public class Cas {
    
    @GET
    @Path("/to/smiles/{cas}")
    @ApiOperation(value="converts cas to smiles")
    @Consumes({"application/json", "application/ld+json"})
    @Produces({"application/json", "application/ld+json"})
    @ApiResponses( value = {@ApiResponse(code = 200 , message = "", response = ConvertedValue.class),
            @ApiResponse(code = 500, message = "Unkown Error", response = ErrorReport.class)})
    public Response smilesToCas(@PathParam("cas") String cas){
        ConvertedValue convVal = new ConvertedValue();
        return Response.ok(convVal).build();
    } 
    
    
    @POST
    @Path("/to/smiles")
    @Consumes({"application/json", "application/ld+json"})
    @Produces({"application/json", "application/ld+json"})
    @ApiOperation(value="converts cas to smiles")
    @ApiResponses( value = {@ApiResponse(code = 200 , message = "", response = ConvertedValue.class),
            @ApiResponse(code = 500, message = "Unkown Error", response = ErrorReport.class)})
    public Response smileToCas(Value cas){
        ConvertedValue convVal = new ConvertedValue();
        return Response.ok(convVal).build();
    }
    
}
