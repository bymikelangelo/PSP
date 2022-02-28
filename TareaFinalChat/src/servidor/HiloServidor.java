package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLSocket;

import comun.ArchivadorMensajes;

public class HiloServidor extends Thread{
	
	private SSLSocket cliente;
	private DataOutputStream out;
	private DataInputStream in;
	private ArchivadorMensajes archivador;
	
	public HiloServidor(SSLSocket cliente, ArchivadorMensajes archivador) {
		this.cliente = cliente;
		this.archivador = archivador;
	}

	public void run() {
		try {
			out = new DataOutputStream(cliente.getOutputStream());
			in = new DataInputStream(cliente.getInputStream());
			
			System.out.println("SERVIDOR --> Entra en la sala de chat: " + cliente.getInetAddress() + "/" + cliente.getPort());
			while (cliente.isConnected()) {
				String mensaje = leerMensaje();
				archivador.anyadirMensaje(cliente, mensaje);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String leerMensaje() throws IOException {
		return in.readUTF();
	}
	
	public void escribirMensaje(String mensaje) throws IOException {
		out.writeUTF(mensaje);
	}
	
}
