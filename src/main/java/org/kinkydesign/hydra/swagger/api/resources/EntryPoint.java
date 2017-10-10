/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author pantelispanka
 */
@Path("/entrypoint")
@Consumes({"application/json", "application/ld+json"})
@Produces({"application/json", "application/ld+json"})
@Api(value = "/EntryPoint", tags = "EntryPoint")
public class EntryPoint {
    
    @GET
    @ApiOperation(value = "Returns the Json Ld as declared from the Hydra core")
    public Response getEntryLd(){
        return Response.ok().build();
    }
}
