package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import comun.GestorMensajes;
import teclado.Teclado;

public class Cliente {
	
	private final String PROPERTIES_FILE = "src/config/propertiesClient";
	private int puerto;
	private String host;
	private boolean conectado;
	private SSLSocket socket;
	
	public Cliente () throws FileNotFoundException, IOException {
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
		
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket cliente = (SSLSocket) factory.createSocket(host, puerto);
	}
	
	
	public int getPuerto() {
		return puerto;
	}


	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public boolean isConectado() {
		return conectado;
	}


	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	
	public SSLSocket getSocket() {
		return socket;
	}


	public void setSocket(SSLSocket socket) {
		this.socket = socket;
	}


	public static void main(String[] args) {
		DataOutputStream out;
		DataInputStream in;
		
		try {
			Cliente cliente = new Cliente();
			out = new DataOutputStream(cliente.getSocket().getOutputStream());
			in = new DataInputStream(cliente.getSocket().getInputStream());
			while (cliente.getSocket().isConnected()) {
				String mensaje = Teclado.solicitarCadena("");
				System.out.println(mensaje);
				out.writeUTF(mensaje);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
