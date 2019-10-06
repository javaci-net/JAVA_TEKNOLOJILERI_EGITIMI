package socketodev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class WeatherClient {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void startConnection(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String degree = "31";
			String userName = "john";
			String password = "doe";

			System.out.println(in.readLine());
			System.out.print(in.readLine());
			out.println(userName);
			System.out.println(userName);
			System.out.print(in.readLine());
			out.println(password);
			System.out.println(password);
			System.out.println(in.readLine());
			System.out.print(in.readLine());
			out.println(degree);
			System.out.println(degree);

			System.out.println(in.readLine());
			stopConnection();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		WeatherClient wc = new WeatherClient();
		wc.startConnection("localhost", 8002);
	}

}