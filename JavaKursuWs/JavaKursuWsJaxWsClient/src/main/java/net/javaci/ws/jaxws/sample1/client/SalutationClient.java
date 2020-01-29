package net.javaci.ws.jaxws.sample1.client;

import java.util.logging.Logger;

import net.javaci.ws.jaxws.sample1.client.soap.stubs.SalutationEndpoint;
import net.javaci.ws.jaxws.sample1.client.soap.stubs.SalutationEndpointService;
import net.javaci.ws.jaxws.sample1.client.soap.stubs.SalutationRequest;
import net.javaci.ws.jaxws.sample1.client.soap.stubs.SalutationResponse;

public class SalutationClient {

	public static void main(String[] args) {
		
		SalutationEndpointService serviceClient = new SalutationEndpointService();
		SalutationEndpoint port = serviceClient.getSalutationEndpointPort();
		
		SalutationRequest request = new SalutationRequest();
		request.setSalutation("Prof.");
		SalutationResponse response = port.salute(request, "Volkan");
		
		Logger.getAnonymousLogger().info("The salute response is " + response.getSalutationResponse() );
		
		String getItResponse = port.getIt();
		Logger.getAnonymousLogger().info("The getIt response is " + getItResponse );
	}

}
