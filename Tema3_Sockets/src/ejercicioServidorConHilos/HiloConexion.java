package ejercicioServidorConHilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HiloConexion extends Thread{
	Socket cliente;
	int idCliente;
	int numeroMultiplicar;
	
	public HiloConexion(Socket cliente, int idAzar, int numeroMultiplicar) {
		super();
		this.cliente = cliente;
		this.idCliente = idAzar;
		this.numeroMultiplicar = numeroMultiplicar;
	}

	public void run() {
		System.out.println("SERVIDOR " + this.getName() + " --> Enviando protocolo de conexi�n a : " 
		+ cliente.toString());
		try {
			//se crean los canales de entrada y salida de datos
			DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
			DataInputStream in = new DataInputStream(cliente.getInputStream());
			
			//se escribe el mensaje con el ID generado y el numero a multiplicar al cliente
			out.writeUTF("Eres el cliente con ID= " + idCliente + ", "
					+ "multiplica tu ID por " + numeroMultiplicar + " y devu�lvelo. ");
			out.flush();
			
			//se lee la respuesta recibida por el cliente
			String respuesta = in.readUTF();
			
			//se comprueba si la respuesta recibida es correcta o err�nea.
			if (String.valueOf(idCliente * numeroMultiplicar).equals(respuesta)) {
				System.out.println("SERVIDOR --> Conexion establecida con: " + cliente.toString());
				out.writeUTF("Conexion verificada.");
			}	
			else {
				out.writeUTF("Acceso denegado.");
			}
			out.flush();
			
			//cerramos la conexi�n con el cliente y los canales de comunicaci�n 
			in.close();
			out.close();
			cliente.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
