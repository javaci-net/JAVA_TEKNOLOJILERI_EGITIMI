import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {

	public static void main(String[] args) throws IOException {
	
		Socket s = new Socket("localhost", 8001);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter out = new PrintWriter(s.getOutputStream());
		
		String line1 = br.readLine();
		String line2 = br.readLine();
		System.out.println("Okunan Line1:"+ line1);
		System.out.println("Okunan Line2:"+ line2);
		
		out.write("George\n");
		out.flush();
		
		String line3 = br.readLine();
		System.out.println("Okunan Line3:"+ line3);
		
		
	}
}
