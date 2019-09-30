import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

	public static void main(String[] args) throws IOException {
		int port = 8001;

		ServerSocket server = new ServerSocket(port);

		System.out.println("Http server started on port: " + port);
		
		
		
		while (true) {
			System.out.println("Accepting: ");
			
			Socket client = server.accept();
			System.out.println("Client connected on sub port(sin): " + client.getPort());
				
			Thread t = new HttpServerThread(client);
			t.start();
		}	
		
		
	}

}
