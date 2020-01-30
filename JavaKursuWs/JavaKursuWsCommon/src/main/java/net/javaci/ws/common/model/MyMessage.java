package net.javaci.ws.common.model;

import java.util.concurrent.atomic.AtomicInteger;

public class MyMessage {

	private static final AtomicInteger COUNTER = new AtomicInteger(1);
	
	private int id;
	
	private String text;
	
	public MyMessage(String text) {
		this.text = text;
		this.id = COUNTER.getAndIncrement();
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

}
