package net.javaci.ws.jaxws.sample1.server;

import javax.jws.WebService;

import net.javaci.ws.common.model.SalutationRequest;
import net.javaci.ws.common.model.SalutationResponse;

@WebService
public class SalutationEndpoint {

	public String getIt() {
		return "Got it!";
	}
	
	public SalutationResponse salute(SalutationRequest request, String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + request.getSalutation() + " " + guest);
		return response;
	}
	
}
