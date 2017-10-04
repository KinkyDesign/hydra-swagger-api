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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.kinkydesign.hydra.swagger.api.dto.ErrorReport;

/**
 *
 * @author pantelispanka
 */
@Path("/cas")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "/Cas", tags = "Cas")
public class Cas {
    
    @GET
    @Path("/to/smiles")
    @ApiOperation(value="converts cas to smiles")
    @ApiResponses( value = {@ApiResponse(code = 200 , message = ""),
            @ApiResponse(code = 500, message = "Unkown Error", response = ErrorReport.class)})
    public Response smilesToCas(){
        return Response.ok().build();
    } 
    
    
    @POST
    @Path("/to/smiles")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value="converts cas to smiles")
    @ApiResponses( value = {@ApiResponse(code = 200 , message = ""),
            @ApiResponse(code = 500, message = "Unkown Error", response = ErrorReport.class)})
    public Response smileToCas(){
        return Response.ok().build();
    }
    
}
