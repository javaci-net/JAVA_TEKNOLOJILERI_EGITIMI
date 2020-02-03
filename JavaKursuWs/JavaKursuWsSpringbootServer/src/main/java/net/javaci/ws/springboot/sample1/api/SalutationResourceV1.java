package net.javaci.ws.springboot.sample1.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.javaci.ws.common.model.SalutationRequest;
import net.javaci.ws.common.model.SalutationResponse;

@RestController
@RequestMapping(
	value = "/api/salutation/v1",
	produces = { 
		MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE 
	}
)
public class SalutationResourceV1 {

	@GetMapping("/sayhello0")
	public SalutationResponse saluteGET0() {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, ananonymous");
		return response;
	}
	
	@GetMapping("/sayhello1")
	public SalutationResponse saluteGET1(String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}
	
	
	@GetMapping("/sayhello2")
	public SalutationResponse saluteGET2(@RequestParam(value = "name", defaultValue = "Jane Doe") String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}
	
	@GetMapping("/{guest}/sayhello3")
	public SalutationResponse saluteGET3(@PathVariable String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}
	
	@GetMapping("/{guest}/sayhello4")
	public ResponseEntity<SalutationResponse> saluteGET4(@PathVariable String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return new ResponseEntity<SalutationResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/sayhelloall")
	public List<SalutationResponse> saluteGET4(String [] names) {
		List<SalutationResponse> responses = new ArrayList<>();
		
		for (String name : names) {
			SalutationResponse response = new SalutationResponse();
			response.setSalutationResponse("Hello, " + name);
			responses.add(response);
		}

		return responses;
	}


	@PostMapping(value = "/{guest}/sayhello", 
		consumes = { 
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE 
		}
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

