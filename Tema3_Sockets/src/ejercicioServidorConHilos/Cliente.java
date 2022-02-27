package ejercicioServidorConHilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	//variables modificables por el usuario.
	static String host = "localhost";
	static int puerto = 16000;
	
	public static void main(String[] args) {
		
		String id = "";
		String numeroMult = "";

		try {
			Socket miSocket = new Socket(host, puerto);
			
			//creación de los canales de comunicación
			DataInputStream in = new DataInputStream(miSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(miSocket.getOutputStream());
			
			//se recibe el mensaje del servidor y se muestra por pantalla
			String mensaje = in.readUTF();
			System.out.println(mensaje);	
			
			//algoritmo para sacar el ID y el multiplicador del mensaje recibido
			boolean despuesDeLaComa = false;
			for (int i = 0; i < mensaje.length(); i++) {
				char caracter = mensaje.charAt(i);
				if (mensaje.charAt(i) == ',') {
					despuesDeLaComa = true;
				}
				if (caracter >= '0' & caracter <= '9') {
					if (despuesDeLaComa) {
						numeroMult = numeroMult + String.valueOf(caracter);
					}
					else {
						id = id + String.valueOf(caracter);
					}
				}
			}
			
			//calculo de la peticion del servidor
			int resultado = Integer.parseInt(id) * Integer.parseInt(numeroMult);
			
			//se envia el resultado obtenido al servidor
			out.writeUTF(String.valueOf(resultado));
			out.flush();
			
			/*
			 * bucle que espera la respuesta del servidor. Si el servidor cierra la conexion salta la exception y
			 * se muestra el mensaje.
			*/
			boolean conectado = true;
			while(conectado) {
				try {
					mensaje = in.readUTF();
					System.out.println(mensaje);
				} catch (EOFException eofe) {
					System.out.println("El servidor ha finalizado la conexión. ");
					conectado = false;
				}
			}

			//se cierran los canales de comunicacion y termina el cliente.
			in.close();
			out.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
