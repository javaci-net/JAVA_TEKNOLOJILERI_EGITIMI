package net.javaci.ws.jaxrs.sample1.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.StatusType;

import net.javaci.ws.common.model.*;

public class SalutationRestClientApp {

	private static final String SALUTATION_RESOURCE_TEMPLATE = "guest";
	
	private static final String SALUTATION_RESOURCE_URL = "http://localhost:8080/api/salutation/"
			+ "{" + SALUTATION_RESOURCE_TEMPLATE + "}"
			+ "/sayhello";

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		SalutationRequest salutationRequest = new SalutationRequest();
		salutationRequest.setSalutation("Dear");
		
		//SalutationResponse syncResponse = syncRestRequest(salutationRequest);
		//Logger.getAnonymousLogger().info("Response from sync call: " + syncResponse);
		
		SalutationResponse asyncResponse = asyncRestRequest(salutationRequest);
		Logger.getAnonymousLogger().info("Response from async call: " + asyncResponse);
		
	}

	private static SalutationResponse asyncRestRequest(SalutationRequest salutationRequest) throws InterruptedException, ExecutionException {
		Client client = ClientBuilder.newClient();
		
		Future<Response> futureResponse = client
			.target(SALUTATION_RESOURCE_URL)
			.resolveTemplate(SALUTATION_RESOURCE_TEMPLATE, "Egemen")
			.request(MediaType.APPLICATION_JSON)
			.async()
			.post(Entity.entity(salutationRequest, MediaType.APPLICATION_JSON));
		
		while(true) {
			Logger.getAnonymousLogger().info("Waiting Response");
			if(futureResponse.isDone() || futureResponse.isCancelled()) {
				break;
			}
			Thread.sleep(500);
		}
		
		Response response = futureResponse.get();
		
		StatusType statusInfo = response.getStatusInfo();
		
		if (statusInfo.getStatusCode() == 200) {
			return response.readEntity(SalutationResponse.class);
		} else {
			Logger.getAnonymousLogger().warning("Response error : " + statusInfo.getReasonPhrase());
			return null;
		}
	}

	private static SalutationResponse syncRestRequest(SalutationRequest salutationRequest) {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client
			.target(SALUTATION_RESOURCE_URL)
			.resolveTemplate(SALUTATION_RESOURCE_TEMPLATE, "Egemen")
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.entity(salutationRequest, MediaType.APPLICATION_JSON));
		
		StatusType statusInfo = response.getStatusInfo();
		
		if (statusInfo.getStatusCode() == 200) {
			return response.readEntity(SalutationResponse.class);
		} else {
			Logger.getAnonymousLogger().warning("Response error : " + statusInfo.getReasonPhrase());
			return null;
		}
	}

}
