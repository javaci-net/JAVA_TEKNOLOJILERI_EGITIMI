package net.javaci.ws.springboot.sample1.api;

import org.springframework.web.bind.annotation.*;

import net.javaci.ws.common.model.SalutationRequest;
import net.javaci.ws.common.model.SalutationResponse;

@RestController
@RequestMapping("/api/salutation/v2")
public class SalutationResourceV2 {

	@GetMapping("/{guest}/sayhello")
	public SalutationResponse saluteGET(@PathVariable String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}

	@PostMapping("/{guest}/sayhello")
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

