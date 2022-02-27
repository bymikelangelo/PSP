package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String host = "localhost";
		int puerto = 16000;
		
		try {
			Socket miSocket = new Socket(host , puerto);
			
			//canal de entrada de datos
			InputStream is = miSocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.println("CLIENTE: recibiendo mensaje de: " + host + "/" + puerto);
			String mensajeRecibido = "";
			String linea;
			while ((linea = br.readLine()) != null) {
				mensajeRecibido = mensajeRecibido + linea;
				System.out.println(linea);
			}
			
			//canal de salida de datos
			OutputStream os = miSocket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			System.out.println("CLIENTE: enviando mensaje al servidor: " + host + "/" + puerto);
			bw.write(mensajeRecibido + " fsdf");
			bw.newLine();
			bw.flush();
			
			//cerrando canales de comunicacion
			bw.close();
			br.close();
			miSocket.close();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
