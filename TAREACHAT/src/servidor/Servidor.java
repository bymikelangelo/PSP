package servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Servidor implements Runnable{
	
	private final String PROPERTIES_FILE = "src/config/propertiesServer";
	private int puerto;
	private String host;
	private Boolean escuchando;
	private Almacenamiento almacen;
	SSLServerSocket server;
	
	public Servidor() throws NumberFormatException, FileNotFoundException, IOException{
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
			
		System.setProperty("javax.net.ssl.keyStore", "src/almacen/MPF");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		server = (SSLServerSocket) factory.createServerSocket(puerto);
		
		almacen = new Almacenamiento();
		
	}
	
	@Override
	public void run() {
		System.out.println("Servidor iniciado");
		while (true) {
			try {
				SSLSocket socketCliente = (SSLSocket) server.accept();
				server.setSoTimeout(0);
				
				HiloServidor hilo = new HiloServidor(socketCliente, almacen);
				hilo.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}	
	
	
	public static void main(String[] args) {
		Servidor servidor;
		try {
			servidor = new Servidor();
			new Thread(servidor).start();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
