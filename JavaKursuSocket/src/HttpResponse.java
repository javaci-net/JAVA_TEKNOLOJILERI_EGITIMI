
public class HttpResponse {

	private HttpStatus status;
	
	private String body;
	
	public HttpResponse(HttpStatus status, String body) {
		this.status = status;
		this.body = body;
	}
	
	public String toString() {
		StringBuilder sb  = new StringBuilder();
		sb.append("HTTP/2.0 ");
		sb.append(status.getCode());
		sb.append(" ");
		sb.append(status.getText());
		sb.append("\r\n\r\n");
		sb.append(body);
		return sb.toString();
	}
}
