package net.javaci.ws.springboot.sample1.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.javaci.ws.common.model.SalutationRequest;
import net.javaci.ws.common.model.SalutationResponse;
import net.javaci.ws.springboot.sample1.config.ObjectNotFoundException;

@Controller
@RequestMapping("/api/salutation/v0")
public class SalutationResourceV0 {

	@GetMapping("/sayhello0")
	@ResponseBody
	public SalutationResponse saluteGET0() {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, ananonymous");
		return response;
	}
	
	@GetMapping("/sayhello1")
	@ResponseBody
	public SalutationResponse saluteGET1(String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}
	
	
	@GetMapping("/sayhello2")
	@ResponseBody
	public SalutationResponse saluteGET2(@RequestParam(value = "name", defaultValue = "Jane Doe") String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}
	
	@GetMapping("/{guest}/sayhello3")
	@ResponseBody
	public SalutationResponse saluteGET3(@PathVariable String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return response;
	}
	
	@GetMapping("/{guest}/sayhello4")
	@ResponseBody
	public ResponseEntity<SalutationResponse> saluteGET4(@PathVariable String guest) {
		SalutationResponse response = new SalutationResponse();
		response.setSalutationResponse("Hello, " + guest);
		return new ResponseEntity<SalutationResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping("/sayhelloall")
	@ResponseBody
	public List<SalutationResponse> saluteGET4(String [] names) {
		List<SalutationResponse> responses = new ArrayList<>();
		
		for (String name : names) {
			SalutationResponse response = new SalutationResponse();
			response.setSalutationResponse("Hello, " + name);
			responses.add(response);
		}

		return responses;
	}
	
	@GetMapping("/sayhelloto/{id}")
	@ResponseBody
	public SalutationResponse saluteGET5(@PathVariable String id) {
		throw new ObjectNotFoundException("object with id " + id + " not found");
	}

	@PostMapping("/{guest}/sayhello")
	@ResponseBody
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

