package ejercicio2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import teclado.Teclado;

public class ServidorHilo{
	
	public static void main(String[] args) {
		int numeroClientes = Teclado.solicitarEnteroPositivo("Introduce número de conexiones posibles. ");
		int puerto = 16000;
		
		Socket[] clientes = new Socket[numeroClientes];
		
		try {
			ServerSocket miServer = new ServerSocket(puerto);
			for (int i = 0; i < numeroClientes; i++) {
				clientes[i]= miServer.accept();
				miServer.setSoTimeout(0);
				HiloConexion conexion = new HiloConexion(clientes[i], i);
				conexion.start();
			}
			
			System.out.println("SERVIDOR --> Finalizadas conexiones totales.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static class HiloConexion extends Thread {
		Socket cliente;
		int posicion;
		
		public HiloConexion(Socket cliente, int posicion) {
			super();
			this.cliente = cliente;
			this.posicion = posicion;
		}

		public void run() {
			System.out.println("SERVIDOR " + this.getName() + " --> Conexión nº: " + (posicion + 1) + " establecida con: " 
			+ cliente.getInetAddress() + "/" + cliente.getLocalPort());
			OutputStream salida;
			try {
				salida = cliente.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(salida));
				bw.write("Eres el cliente número: " + (posicion + 1));
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
