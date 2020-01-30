package net.javaci.ws.sparkjava.sample1;

import static spark.Spark.get;

import net.javaci.ws.common.model.MyMessage;
import net.javaci.ws.sparkjava.sample1.transformers.JsonTransformer;

public class SparkJavaApplication1 {

	public static void main(String[] args) {
		
		get("/hello1", (req, res) -> "Hello World");
		
		get("/hello2", "application/json", (request, response) -> {
		    return new MyMessage("Hello World");
		}, new JsonTransformer());
		
	}

}
