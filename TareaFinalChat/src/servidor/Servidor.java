package servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import comun.ArchivadorMensajes;

public class Servidor {
	
	private final String PROPERTIES_FILE = "src/config/propertiesServer";
	private int puerto;
	private String host;
	private Boolean escuchando;
	private HashMap<SSLSocket, HiloServidor> clientes;
	ArchivadorMensajes archivador; 
	
	public Servidor() throws NumberFormatException, FileNotFoundException, IOException{
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
			
		System.setProperty("java.net.ssl.keystore", "almacen/MPF");
		System.setProperty("java.net.ssl.keystorePassword", "123456");
		
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(puerto);
			
		clientes = new HashMap<>();
		
		archivador = new ArchivadorMensajes(clientes);
			
		escuchando = true;
		
		while (escuchando) {
			SSLSocket cliente = (SSLSocket) server.accept();
			server.setSoTimeout(0);
			
			HiloServidor hilo = new HiloServidor(cliente, archivador);
			clientes.put(cliente, hilo);
			hilo.start();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			Servidor servidor = new Servidor();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
