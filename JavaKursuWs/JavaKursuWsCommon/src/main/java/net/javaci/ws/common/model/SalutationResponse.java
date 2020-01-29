package net.javaci.ws.common.model;

import java.io.Serializable;

public class SalutationResponse implements Serializable {

	private static final long serialVersionUID = -4659743965595260288L;
	
	private String salutationResponse;

	public String getSalutationResponse() {
		return salutationResponse;
	}

	public void setSalutationResponse(String salutationResponse) {
		this.salutationResponse = salutationResponse;
	}

	@Override
	public String toString() {
		return String.format("SalutationResponse [salutationResponse=%s]", salutationResponse);
	}

}
