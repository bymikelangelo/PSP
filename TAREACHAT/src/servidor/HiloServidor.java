package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

import comun.PaqueteEnvio;

public class HiloServidor extends Thread{
	SSLSocket socketCliente;
	GestorMensajes gestor;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public HiloServidor(SSLSocket socketCliente, GestorMensajes almacen) {
		this.socketCliente = socketCliente;
		this.gestor = almacen;
	}
	
	public void escribirMensaje(PaqueteEnvio paquete) throws IOException {
		out.writeObject(paquete);
	}
	
	public PaqueteEnvio leerMensaje() throws ClassNotFoundException, IOException {
		return (PaqueteEnvio) in.readObject();
	}
	
	public void notificarMensaje(String mensaje) throws IOException {
		PaqueteEnvio paquete = new PaqueteEnvio("Servidor", socketCliente.getInetAddress().toString(), mensaje);
		System.out.println(mensaje);
		gestor.enviarMensaje(paquete);
	}

	public void run() {
		try {
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			in = new ObjectInputStream(socketCliente.getInputStream());
			
			PaqueteEnvio paqueteRecibido = leerMensaje();
			
			gestor.anyadirCliente(paqueteRecibido.getNick(), this);
			
			notificarMensaje("Entra en la sala de chat: " + paqueteRecibido.getNick()
			+ "(" + paqueteRecibido.getIp() + ")");
			
			while(true) {
				paqueteRecibido = leerMensaje();
				System.out.println(paqueteRecibido.getMensaje());
				gestor.enviarMensaje(paqueteRecibido);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
