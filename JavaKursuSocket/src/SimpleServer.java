import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException {

		int port = 8001;

		ServerSocket server = new ServerSocket(port);

		System.out.println("Server started on port: " + port);
		// IP bina
		// port daire no
		// socker dairede yasayan bir suru insan

		Socket client = server.accept();

		System.out.println("Client connected");

		PrintWriter out = new PrintWriter(client.getOutputStream());

		out.write("Hosgeldiniz...\n");
		out.write("Isminiz nedir? \n");
		out.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

		String name = br.readLine();

		out.write("Merhaba  " + name + "\n");
		out.flush();

		// String name =
	}

}
