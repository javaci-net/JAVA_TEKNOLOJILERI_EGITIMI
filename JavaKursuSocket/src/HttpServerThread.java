import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;

public class HttpServerThread extends Thread {

	private Socket socket;
	
	public HttpServerThread(Socket socket) {
		this.socket = socket;
	
	}
	
	@Override
	public void run() {
		
		/// Kutrukta bekleyen is varmi?
		// Yokla bekle
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			communicate(br, out);
			br.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void communicate(BufferedReader br, PrintWriter out) throws IOException {
		//GET /path/to/file/index.html HTTP/1.0
/*
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/		String requestLine = br.readLine();
		if (requestLine == null) {
			System.out.println("Hatali request line. null olamaz");
			return;
		}
		System.out.println("Request Line:" + requestLine);
		String parts[] = requestLine.split(" ");
		if (parts.length != 3 ) {
			System.out.println("Invalid request line format: " + requestLine);
			return;
		}
		if ("GET".equals(parts[0]) == false) {
			out.print(new HttpResponse(HttpStatus.MethodNotAllowed, "").toString());
			out.flush();
			return;
		}
		
		String fileContent = "";
		try {
			fileContent = readFile("." + parts[1]);
		} catch (FileNotFoundException e) {
			out.print(new HttpResponse(HttpStatus.NotFound, "File Not Found").toString());
			out.flush();
			return;
		}
		String content = new HttpResponse(HttpStatus.Ok, fileContent).toString();
		
		out.print(content);
		out.flush();
		
	}
	
	private String readFile(String fileName) throws IOException  {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		StringBuffer sb = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		return sb.toString();
	}
	
}
