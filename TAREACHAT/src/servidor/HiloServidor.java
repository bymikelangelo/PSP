package servidor;

import java.io.EOFException;
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
		String nick = "";
		String ip = "";
		try {
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			in = new ObjectInputStream(socketCliente.getInputStream());
			
			PaqueteEnvio paqueteRecibido = leerMensaje();
			nick = paqueteRecibido.getNick();
			ip = paqueteRecibido.getIp();
			
			gestor.anyadirCliente(paqueteRecibido.getNick(), this);
			
			notificarMensaje("Entra en la sala de chat: " + nick
			+ "(" + ip + ")");
			
			while(true) {
				paqueteRecibido = leerMensaje();
				System.out.println(paqueteRecibido);
				if (paqueteRecibido != null) {
					System.out.println(paqueteRecibido.getMensaje());
					gestor.enviarMensaje(paqueteRecibido);
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
			System.err.println("Ha salido de la sala de chat: " + nick + "(" + ip + ")");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
