package net.javaci.ws.jaxrs.sample1.server.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import net.javaci.ws.common.model.SalutationRequest;
import net.javaci.ws.common.model.SalutationResponse;

@Path("/salutation")
public class SalutationResource {

	@Path("/{guest}/sayhello")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SalutationResponse saluteGET(@PathParam("guest") String guest ) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}
	
	@Path("/{guest}/sayhello")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SalutationResponse salutePOST(SalutationRequest request , @PathParam("guest") String guest ) {
		SalutationResponse response = new SalutationResponse();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
		return response;
	}
	
}
