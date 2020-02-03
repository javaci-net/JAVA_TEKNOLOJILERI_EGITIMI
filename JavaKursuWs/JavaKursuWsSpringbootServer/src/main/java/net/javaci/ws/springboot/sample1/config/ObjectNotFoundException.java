package net.javaci.ws.springboot.sample1.config;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6621376981960090736L;
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
}
