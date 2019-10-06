package socketodev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class WeatherServer {

	public static void main(String[] args) throws IOException {
		int port = 8002;
		
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Server basladi. Port: " + port);
		
		while(true) {
			System.out.println("Beklemede..");
			Socket client = server.accept();
			System.out.println("Client connected on sub port(sin): " + client.getPort());
			Thread thread = new WeatherServerThread(client);
			thread.start();
		}
	}
	// ayrı dosyada yapmistim ama soruda 2 sinif yazilmasi bekleniyor dedigi icin inner class yaptim 
	// sanki boyle yapmanin bir handikapi varmis gibi geldi ama calismasinda bi farklilik goremedim
	public static class WeatherServerThread extends Thread {

		Socket socket;
		PrintWriter out;
		BufferedReader br;
		String userName;
		String password;

		public WeatherServerThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			out.println("Welcome to very important server");

			out.println("User Name: ");
			try {
				String input = br.readLine();
				if (checkCredentials(input, ((byte) 1))) {
					this.userName = input;
				} else
					closeConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("Password: ");
			try {
				String input = br.readLine();
				if (checkCredentials(input, ((byte) 0))) {
					this.password = input;
				} else
					closeConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (this.userName != null && this.password != null) {
				out.println("Welcome " + this.userName);
			}

			out.println("What's the temperature: ");

			String inputDegree;

			try {
				while ((inputDegree = br.readLine()) != null) {
					if (isNumeric(inputDegree)) {
						out.println(getWeatherStatus(inputDegree));
						closeConnection();
					} else {
						out.println("Please type only numeric value : ");
					}
				}
			} catch (IOException e1) {
				// do nothing
			}
		}

		public String getWeatherStatus(String weatherDegree) {
			int integerDegree = Integer.parseInt(weatherDegree);
			if (10 <= integerDegree && integerDegree <= 30) {
				return "Its normal\n";
			} else if (integerDegree > 30) {
				return "Its HOT\n";
			} else if (integerDegree < 10) {
				return "Its cold\n";
			} else {
				return "Hatali giris";
			}
		}

		// key : 1 for username - 0 for password checking selection
		public boolean checkCredentials(String creds, byte key) {

			switch (key) {
			case 1:
				return creds.equals("john");
			case 0:
				return creds.equals("doe");
			default:
				return false;
			}
		}

		public void closeConnection() {
			try {
				br.close();
				out.println("Server closes connection");
				out.close();

				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public boolean isNumeric(String str) {
			if (str.isEmpty()) {
				return false;
			}

			try {
				double d = Double.parseDouble(str);
				int i = Integer.parseInt(str);
				float f = Float.parseFloat(str);
				long l = Long.parseLong(str);

			} catch (NumberFormatException | NullPointerException nfe) {
				return false;
			}
			return true;
		}

	}

}

