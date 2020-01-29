package net.javaci.ws.jaxrs.sample1.server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@ApplicationPath("/api")
public class RestApplication extends Application {

	@Path("/health")
	public static class HealthCheck {
		
		@GET
		@Path("/check")
		public Response healthcheck() {
			return Response.ok().entity("Service online").build();
		}
		
		@GET
		@Path("/ping")
		public Response healthcheck2() {
			return Response.ok().entity("Pong").build();
		}
	}
	
}
