package ejercicioServidorConHilos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor{
	
	//variables modificables por el usuario
	static int numeroClientes = 7;
	static int numeroMultiplicar = 7;
	static int puerto = 16000;

	public static void main(String[] args) {

		Socket[] clientes = new Socket[numeroClientes];
		
		try {
			ServerSocket miServer = new ServerSocket(puerto);
			System.err.println("SERVIDOR --> Escuchando en puerto: " + puerto);
			Random random = new Random();
			int idAzar;
			//escucha hasta el número de clientes especificado.
			for (int i = 0; i < numeroClientes; i++) {
				clientes[i]= miServer.accept();
				miServer.setSoTimeout(0);
				idAzar = random.nextInt(9999)+1;
				HiloConexion conexion = new HiloConexion(clientes[i], idAzar, numeroMultiplicar);
				conexion.start();
			}
			
			//finaliza al terminar todas las conexiones posibles
			System.err.println("SERVIDOR --> Agotadas todas las conexiones posibles.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
