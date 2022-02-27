package ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 16000;
		
		try {
			Socket miSocket = new Socket(host, puerto);
			
			InputStream entrada = miSocket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
			
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea + "\n");
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
