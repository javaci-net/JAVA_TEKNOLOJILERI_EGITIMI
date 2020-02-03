package net.javaci.ws.springboot.sample1.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaci.ws.common.model.SalutationRequest;
import net.javaci.ws.common.model.SalutationResponse;

@RestController
@RequestMapping("/api/salutation/v4")
public class SalutationResourceV4 {

	@GetMapping(
		value = "/{guest}/sayhello", 
		produces = { 
			MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE 
		}
	)
	public SalutationResponse saluteGET(@PathVariable String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}

	@PostMapping(
		value = "/{guest}/sayhello", 
		consumes = { 
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE 
		},
		produces = { 
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,  MediaType.TEXT_XML_VALUE 
		}
	)
	public SalutationResponse salutePOST(@RequestBody SalutationRequest request, @PathVariable("guest") String guest) {
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
