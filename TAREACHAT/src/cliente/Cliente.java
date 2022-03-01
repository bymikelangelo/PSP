package cliente;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import comun.PaqueteEnvio;
import teclado.Teclado;

public class Cliente implements Runnable{
	
	private final String PROPERTIES_FILE = "src/config/propiedades";
	private int puerto;
	private String host;
	private String nick;
	private SSLSocket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public Cliente(String nick) throws IOException {
		this.nick = nick;
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
		
		System.setProperty("javax.net.ssl.trustStore", "src/almacen/usuarioMPF");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		socket = (SSLSocket) factory.createSocket(host, puerto);
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		
	}
	
	@Override
	public void run() {
		try {
			out.writeObject(new PaqueteEnvio(nick, socket.getInetAddress().toString()));
			while(true) {
				String mensaje = Teclado.solicitarCadena("Mensaje: ");
				System.out.println(mensaje);
				out.writeObject(new PaqueteEnvio(nick, socket.getInetAddress().toString(), mensaje));
				out.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		String nick = Teclado.solicitarCadena("Introduce nick: ");
		try {
			Cliente cliente = new Cliente(nick);
			new Thread(cliente).start();
			
			while(true) {
				PaqueteEnvio paqueteRecibido = (PaqueteEnvio) cliente.in.readObject();
				System.out.println(paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje());
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}
