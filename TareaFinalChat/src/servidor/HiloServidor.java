package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLSocket;

import comun.GestorMensajes;

public class HiloServidor extends Thread{
	
	private SSLSocket cliente;
	private Servidor servidor;
	private DataOutputStream out;
	private DataInputStream in;
	private GestorMensajes gestor;
	
	
	public HiloServidor(SSLSocket cliente, Servidor servidor, GestorMensajes gestor) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.gestor = gestor;
	}

	public void run() {
		try {
			out = new DataOutputStream(cliente.getOutputStream());
			in = new DataInputStream(cliente.getInputStream());
			
			System.out.println("SERVIDOR --> Entra en la sala de chat: " + cliente.getInetAddress() + "/" + cliente.getPort());
			while (cliente.isConnected()) {
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String recibirMensaje() throws IOException {
		return in.readUTF();
	}
	
	public void imprimirMensaje() {
		
	}
	
}
