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
	private int maximoClientes;
	private Boolean escuchando;
	private GestorMensajes gestor;
	SSLServerSocket server;
	
	public Servidor() throws NumberFormatException, FileNotFoundException, IOException{
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
		maximoClientes = Integer.valueOf(propiedades.getProperty("maximoClientes"));
			
		System.setProperty("javax.net.ssl.keyStore", "src/almacen/MPF");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		server = (SSLServerSocket) factory.createServerSocket(puerto);
		
		gestor = new GestorMensajes();
		
	}
	
	@Override
	public void run() {
		System.out.println("Servidor iniciado");
		int contador = 0;
		while (contador < maximoClientes) {
			try {
				SSLSocket socketCliente = (SSLSocket) server.accept();
				server.setSoTimeout(0);
				
				HiloServidor hilo = new HiloServidor(socketCliente, gestor);
				hilo.start();
				
				contador++;
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		System.out.println("Max clientes conectados");
		
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
