package net.javaci.ws.common.model;

import java.io.Serializable;

public class SalutationRequest implements Serializable {

	private static final long serialVersionUID = -8953030014463153176L;
	
	private String salutation;

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	@Override
	public String toString() {
		return String.format("SalutationRequest [salutation=%s]", salutation);
	}
	
}
