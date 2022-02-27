package ejercicioSSL;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import teclado.Teclado;

public class Servidor {

	public static void main(String[] args) {
		int numeroClientes = Teclado.solicitarEnteroPositivo("Introduce número de conexiones posibles. ");
		int puerto = 16000;
		
		System.setProperty("javax.net.ssl.keyStore", "src/ejercicioSSL/almacenSSL");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		
		SSLSocket[] clientes = new SSLSocket[numeroClientes];
		
		try {
			SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket miServer = (SSLServerSocket) factory.createServerSocket(puerto);
			System.out.println("SERVIDOR --> Escuchando por el puerto: " + puerto);
			
			for (int i = 0; i < numeroClientes; i++) {
				clientes[i]= (SSLSocket) miServer.accept();
				miServer.setSoTimeout(0);
				
				System.out.println("SERVIDOR --> Conexión nº: " + (i + 1) + " establecida con: " 
				+ clientes[i].getInetAddress() + "/" + clientes[i].getLocalPort());
				OutputStream salida = clientes[i].getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(salida));
				
				bw.write("Eres el cliente número: " + (i + 1));
				bw.flush();
				bw.close();
			}
			
			System.out.println("SERVIDOR --> Finalizadas conexiones totales.");
			
			miServer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
