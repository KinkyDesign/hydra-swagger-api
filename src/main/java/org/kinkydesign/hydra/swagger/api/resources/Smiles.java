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
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.kinkydesign.hydra.swagger.api.dto.ConvertedValue;
import org.kinkydesign.hydra.swagger.api.dto.ConvertedValueExtension;
import org.kinkydesign.hydra.swagger.api.dto.ErrorReport;
import org.kinkydesign.hydra.swagger.api.dto.Value;

/**
 *
 * @author pantelispanka
 */
@Path("/smiles")
@Consumes({"application/json", "application/ld+json"})
@Produces({"application/json", "application/ld+json"})
@Api(value = "/Smiles", tags = "Smiles")
public class Smiles {

    @GET
    @Path("/to/cas/{smiles}")
    @Consumes({"application/json", "application/ld+json"})
    @Produces({"application/json", "application/ld+json"})
    @ApiOperation(value = "converts smiles to cas",extensions={
                    @Extension(name="supported models",properties={
                            @ExtensionProperty(name="returns", value="ConvertedValueExtension"),
                            @ExtensionProperty(name="returns ld", value="ConvertedValueExtension_ld"),
                            @ExtensionProperty(name="on error", value="ErrorReport"),
                            @ExtensionProperty(name="on error ld", value="ErrorReport_ld")
                    })
            })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "", response = ConvertedValueExtension.class)
        ,
            @ApiResponse(code = 500, message = "Unkown Error", response = ErrorReport.class)})
    public Response smilesToCas(@PathParam("smiles") String smiles) {
        ConvertedValueExtension convVal = new ConvertedValueExtension();
        convVal.setConvertedValue("D ACC 83541-34-8");
        convVal.setSomeIntValue(0);
        return Response.ok(convVal).build();
    }

    @POST
    @Path("/to/cas")
    @Consumes({"application/json", "application/ld+json", "application/xml"})
    @Produces({"application/json", "application/ld+json", "application/xml"})
    @ApiOperation(value = "converts smiles to cas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "", response = ConvertedValueExtension.class)
        ,
            @ApiResponse(code = 500, message = "Unkown Error", response = ErrorReport.class)})
    public Response smileToCas(Value smiles) {
        ConvertedValueExtension convVal = new ConvertedValueExtension();
        convVal.setConvertedValue("D ACC 83541-34-8");
        convVal.setSomeIntValue(0);
        if("error".equals(smiles.getValue())){
            throw new UnsupportedOperationException("MediatypeNotSupperted");
        }
        return Response.ok(convVal).build();
    }

}
