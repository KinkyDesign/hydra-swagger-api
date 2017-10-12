/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kinkydesign.hydra.swagger.api.resources;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author pantelispanka
 */
@Path("/entrypoint")
@Consumes({"application/json"})
@Produces({"application/json"})
@Api(value = "/EntryPoint", tags = "EntryPoint")
public class EntryPoint {
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @ApiOperation(value = "Returns the Json Ld of the api Entry point as declared from the Hydra core")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response getEntryLd(){
        
        ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
        node.put("@context", uriInfo.getAbsolutePath().getRawPath().toString() + "/contexts/EntryPoint.jsonld");
        node.put("@id", uriInfo.getAbsolutePath().getRawPath().toString());
        node.put("@type", "EntryPoint");
        String[] path = uriInfo.getAbsolutePath().getPath().toString().split("/");
        String toSmiles = path[0] + path[1] + "/api/cas/to/smiles";
        node.put("cas_to_smiles", toSmiles);
        String toCas = path[0] + path[1] + "/api/smiles/to/cas";
        node.put("smiles_to_cas", toCas);
        return Response.ok(node).build();
    }
    
    
    @Path("/contexts/EntryPoint.jsonld")
    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})

    public Response getEntryPoint(){
        ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
        Map<String, Object> cont = new HashMap<>();
        cont.put("hydra", "http://www.w3.org/ns/hydra/core#");
        cont.put("vocab", "http://localhost:8080/HydraApi/api/entrypoint/contexts/vocab");
        Map<String, Object> cas = new HashMap<>();
        cas.put("@id", "vocab:EntryPoint/cas_to_smiles");
        cas.put("@type", "@id");
        cont.put("cas_to_smiles", cas);
        Map<String, Object> smiles = new HashMap<>();
        smiles.put("@id", "vocab:EntryPoint/smiles_to_cas");
        smiles.put("@type", "@id");
        cont.put("smiles_to_cas", smiles);
        node.putPOJO("@context", cont);
        return Response.ok(node).build();
    }
    
    @GET
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("/contexts/vocab")
    public Response getVocab(){
        
        ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
        
        Map<String, Object> cont = new HashMap<>();
        cont.put("hydra", "http://www.w3.org/ns/hydra/core#");
        cont.put("vocab", "http://localhost:8080/HydraApi/api/entrypoint/contexts/vocab");
        Map<String, Object> prop = new HashMap<>();
        prop.put("@id", "hydra:property");
        prop.put("@type", "@id");
        cont.put("property", prop);
        cont.put("ApiDocumentation", "hydra:ApiDocumentation");
        cont.put("readonly", "hydra:readonly");
        cont.put("writeonly", "hydra:writeonly");
        cont.put("supportedClass", "hydra:supportedClass");
        cont.put("supportedProperty","hydra:supportedProperty");
        cont.put("supportedOperation","hydra:supportedOperation");
        Map<String, Object> exp = new HashMap<>();
        exp.put("@id", "hydra:expects");
        exp.put("@type", "@id");
        cont.put("expects", exp);
        Map<String, Object> ret = new HashMap<>();
        exp.put("@id", "hydra:returns");
        exp.put("@type", "@id");
        cont.put("returns", ret);
        cont.put("statusCodes", "hydra:statusCodes");
        cont.put("code", "hydra:statusCode");
        
        
        
        
        node.putPOJO("@context", cont);
        node.put("@id", "http://localhost:8080/HydraApi/api/entrypoint/contexts/vocab");
        node.put("@type", "ApiDocumentation");
        
        
        
        List<Object> supCl = new ArrayList();
        
        Map<String, Object> resources = new HashMap<>();
        Map<String, Object> resou = new HashMap<>();
        resou.put("@id", "http://www.w3.org/ns/hydra/core#Resource");
        resou.put("@type", "hydra:Class");
        resou.put("hydra:title", "Resource");
        resou.put("hydra:description", null);
        resou.put("supportedOperation",  new ArrayList());
        resou.put("supportedProperty",  new ArrayList());
        resources.put("",resou);
        
        supCl.add(resou);
        
        
        Map<String, Object> entryPoint = new HashMap<>();
        entryPoint.put("@id", "vacab:EntryPoint");
        entryPoint.put("@type", "hydra:Class");
        entryPoint.put("subClassOf", null);
        entryPoint.put("label", "EntryPoint");
        entryPoint.put("description", "The main entry point or homepage of the API.");
        
        List<Object> supOp = new ArrayList<>();
                
        Map<String, Object> entryP = new HashMap<>();
        entryP.put("@id", "_:entry_point");
        entryP.put("@type", "hydra:Operation");
        entryP.put("method", "GET");
        entryP.put("label", "The APIs main entry point.");
        entryP.put("description", null);
        entryP.put("expects", null);
        entryP.put("returns", "vocab:EntryPoint");
        entryP.put("statusCodes", new ArrayList<>());
        

        supOp.add(entryP);
        
        entryPoint.put("supportedOperation", supOp);
        
        
        List<Object> subProp = new ArrayList<>();
        
        Map<String, Object> proprt1 = new HashMap<>();
        proprt1.put("@id", "vocab:EntryPoint/cas_to_smiles");
        proprt1.put("@type", "hydra:Link");
        proprt1.put("label", "cas_to_smiles");
        proprt1.put("description", "Converts cas to smiles");
        proprt1.put("domain", "vocab:EntryPoint/");
        
        List<Object> supOper1 = new ArrayList<>();
        Map<String, Object> oper1 = new HashMap<>();
        oper1.put("@id", "_:retrieve_covretded_value");
        oper1.put("@type", "hydra:Operation");
        oper1.put("method", "GET");
        oper1.put("label", "Returns a coverted value from cas to smiles");
        oper1.put("description", null);
        oper1.put("expects", "String");
        oper1.put("returns", "http://schema.org/convertedValue");
        oper1.put("statusCodes", "[200, 500]");
        supOper1.add(oper1);
        
        proprt1.put("supportedOperation", supOper1);
        
        proprt1.put("hydra:title", "cas_to_smiles");
        proprt1.put("hydra:description", "Converts smiles to cas");
        proprt1.put("required", null);
        
        supCl.add(entryPoint);
        supCl.add(proprt1);
        
        
        Map<String, Object> proprt2 = new HashMap<>();
        proprt2.put("@id", "vocab:EntryPoint/cas_to_smiles");
        proprt2.put("@type", "hydra:Link");
        proprt2.put("label", "cas_to_smiles");
        proprt2.put("description", "Converts cas to smiles");
        proprt2.put("domain", "vocab:EntryPoint/");
        
        List<Object> supOper2 = new ArrayList<>();
        Map<String, Object> oper2 = new HashMap<>();
        oper2.put("@id", "_:retrieve_covretded_value");
        oper2.put("@type", "hydra:Operation");
        oper2.put("method", "POST");
        oper2.put("label", "Returns a coverted value from cas to smiles");
        oper2.put("description", null);
        oper2.put("expects", "http://schema.org/value");
        oper2.put("returns", "http://schema.org/convertedValue");
        oper2.put("statusCodes", "[200, 500]");
        supOper2.add(oper2);
        
        proprt2.put("supportedOperation", supOper2);
        
        proprt2.put("hydra:title", "cas_to_smiles");
        proprt2.put("hydra:description", "Converts smiles to cas");
        proprt2.put("required", null);
        
        supCl.add(entryPoint);
        supCl.add(proprt2);
        
        
        
        
        
        node.putPOJO("supportedClass", supCl);
        return Response.ok(node).build();
    }
}
