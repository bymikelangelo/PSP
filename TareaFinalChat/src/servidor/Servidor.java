package servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import comun.GestorMensajes;

public class Servidor {
	
	private final String PROPERTIES_FILE = "src/config/propertiesServer";
	private int puerto;
	private String host;
	private Boolean escuchando;
	private ArrayList<SSLSocket> clientes;
	
	public Servidor() throws NumberFormatException, FileNotFoundException, IOException{
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
			
		System.setProperty("java.net.ssl.keystore", "AlmacenMPF/MPF");
		System.setProperty("java.net.ssl.keystorePassword", "123456");
		
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(puerto);
			
		clientes = new ArrayList<>();
		
		GestorMensajes gestor = new GestorMensajes(clientes);
			
		escuchando = true;
		
		while (escuchando) {
			SSLSocket cliente = (SSLSocket) server.accept();
			server.setSoTimeout(0);
			
			clientes.add(cliente);
			new HiloServidor(cliente, this, gestor).start();
		}
		
	}
	
	public static void main(String[] args) {
		
	}
		
}
