package net.javaci.ws.springboot.sample1.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import net.javaci.ws.common.model.*;

@RestController
@RequestMapping("/api/salutation/v3")
public class SalutationResourceV3 {

	@RequestMapping( 
		method = RequestMethod.GET, 
		value = "/{guest}/sayhello" ,
		produces = MediaType.APPLICATION_JSON_VALUE 
	)
	public SalutationResponse saluteGET(@PathVariable String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}

	@RequestMapping( 
		method = RequestMethod.POST, 
		value = "/{guest}/sayhello" ,
		produces = MediaType.APPLICATION_JSON_VALUE 
	)
	public SalutationResponse salutePOST(
			@RequestBody SalutationRequest request, 
			@PathVariable("guest") String guest) {
		
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


