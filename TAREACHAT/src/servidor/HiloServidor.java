package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

import comun.PaqueteEnvio;

public class HiloServidor extends Thread{
	SSLSocket socketCliente;
	Almacenamiento almacen;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public HiloServidor(SSLSocket socketCliente, Almacenamiento almacen) {
		this.socketCliente = socketCliente;
		this.almacen = almacen;
	}
	
	public void escribirMensaje(PaqueteEnvio paquete) throws IOException {
		out.writeObject(paquete);
	}
	
	public PaqueteEnvio leerMensaje() throws ClassNotFoundException, IOException {
		return (PaqueteEnvio) in.readObject();
	}

	public void run() {
		try {
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			in = new ObjectInputStream(socketCliente.getInputStream());
			
			PaqueteEnvio paqueteRecibido = leerMensaje();
			
			almacen.anyadirCliente(paqueteRecibido.getNick(), this);
			
			System.out.println("Se ha conectado " + paqueteRecibido.getNick());
			
			while(true) {
				paqueteRecibido = leerMensaje();
				System.out.println(paqueteRecibido.getMensaje());
				almacen.enviarMensaje(paqueteRecibido);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
