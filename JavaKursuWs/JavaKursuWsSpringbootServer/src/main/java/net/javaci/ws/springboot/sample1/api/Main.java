package net.javaci.ws.springboot.sample1.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Main {

	@GetMapping("/")
	void handleMainUrl(HttpServletResponse response) throws IOException {
		response.sendRedirect("./swagger-ui.html#/");
	}

}
