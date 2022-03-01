package cliente;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
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
	private boolean conectado;
	
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

	}


	public String getNick() {
		return nick;
	}
	
	public SSLSocket getSocket() {
		return socket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public boolean isConectado() {
		return conectado;
	}

	
	@Override
	public void run() {
		HiloCliente hilo;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			out.writeObject(new PaqueteEnvio(nick, socket.getInetAddress().toString()));
			
			hilo = new HiloCliente(this);
			hilo.start();
			
			PaqueteEnvio paqueteRecibido;
			while (true) {
				paqueteRecibido = (PaqueteEnvio) in.readObject();
				if (paqueteRecibido.getMensaje() != null) {
					System.out.println(paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje());
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException | SocketException e) {
			//e.printStackTrace();
			System.err.println("Perdida la conexion con: " + socket.getInetAddress());
			try {
				in.close();
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
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
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}

}
