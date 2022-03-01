package servidor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

import comun.PaqueteEnvio;

public class HiloServidor extends Thread{
	private SSLSocket socketCliente;
	private GestorMensajes gestor;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public HiloServidor(SSLSocket socketCliente, GestorMensajes almacen) {
		this.socketCliente = socketCliente;
		this.gestor = almacen;
	}
	
	public PaqueteEnvio recibirMensaje() throws ClassNotFoundException, IOException {
		return (PaqueteEnvio) in.readObject();
	}
	
	public void enviarMensaje(PaqueteEnvio paquete) throws IOException {
		out.writeObject(paquete);
		out.flush();
	}
	
	public void enviarNotificacion(String notificacion) throws IOException {
		PaqueteEnvio paquete = new PaqueteEnvio("Servidor", socketCliente.getInetAddress().toString(), notificacion);
		gestor.enviarMensaje(paquete);
	}

	public void run() {
		String nick = "";
		String ip = "";
		String notificacion;
		try {
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			in = new ObjectInputStream(socketCliente.getInputStream());
			PaqueteEnvio paqueteRecibido = recibirMensaje();
			nick = paqueteRecibido.getNick();
			ip = paqueteRecibido.getIp();
			
			gestor.anyadirCliente(paqueteRecibido.getNick(), this);
			notificacion = "Entra en la sala de chat: " + nick
					+ "(" + ip + ")";
			enviarNotificacion(notificacion);
			System.out.println(notificacion);
			
			while(true) {
				paqueteRecibido = recibirMensaje();
				if (paqueteRecibido.getMensaje() != null) {
					System.out.println(paqueteRecibido.getNick() + "(" + paqueteRecibido.getIp() + "): " + paqueteRecibido.getMensaje());
					gestor.enviarMensaje(paqueteRecibido);
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			//e.printStackTrace();
			gestor.eliminarCliente(nick);
			notificacion = "Ha salido de la sala de chat: " + nick + "(" + ip + ")";
			System.err.println(notificacion);
			try {
				enviarNotificacion(notificacion);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socketCliente.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
