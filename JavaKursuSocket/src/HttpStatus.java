
public enum HttpStatus {

	Ok(200, "Ok"), NotFound(404, "Not Found"), MethodNotAllowed(405, "Method Not Allowed");

	private int code;
	private String text;

	private HttpStatus(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

}
