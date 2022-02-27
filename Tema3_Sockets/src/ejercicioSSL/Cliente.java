package ejercicioSSL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Cliente {

	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 16000;
		
		System.setProperty("javax.net.ssl.trustStore", "src/ejercicioSSL/usuarioAlmacenSSL");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		
		try {
			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket miSocket = (SSLSocket) factory.createSocket(host, puerto);
			
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
