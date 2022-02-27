package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {

		int puerto = 16000;
		
		try {
			ServerSocket miServer = new ServerSocket(puerto);
			Socket cliente = miServer.accept();
			
			//canal de salida de datos
			OutputStream os = cliente.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			System.out.println("SERVIDOR: enviando mensaje a: " + cliente.getLocalAddress() + "/" + puerto);
			bw.write("Hola soy el SERVIDOR y te he enviado un mensaje");
			bw.newLine();
			bw.flush();
			
			//canal de entrada de datos
			InputStream is = cliente.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.println("SERVIDOR: recibiendo mensaje de: " + cliente.getLocalAddress() + "/" + puerto);
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
			//cerrado canales de comunicacion
			br.close();
			bw.close();
			miServer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
