package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import comun.ArchivadorMensajes;
import teclado.Teclado;

public class Cliente {
	
	private final String PROPERTIES_FILE = "src/config/propertiesClient";
	private int puerto;
	private String host;
	private boolean conectado;
	private SSLSocket socket;
	private DataOutputStream out;
	private DataInputStream in;
	
	public Cliente () throws FileNotFoundException, IOException {
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
		
		System.setProperty("javax.net.ssl.trustStore", "src/almacen/usuarioMPF");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket cliente = (SSLSocket) factory.createSocket(host, puerto);
		
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
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
	
	public DataOutputStream getOut() {
		return out;
	}


	public void setOut(DataOutputStream out) {
		this.out = out;
	}


	public DataInputStream getIn() {
		return in;
	}


	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public void escribirMensaje(String mensaje) throws IOException {
		out.writeUTF(mensaje);
	}

	public String leerMensaje() throws IOException {
		return in.readUTF();
	}
	
	public class PaqueteEnvio implements Serializable{
		
		
	}

	public static void main(String[] args) {
		try {
			Cliente cliente = new Cliente();
			
			while (cliente.getSocket().isConnected()) {
				String mensaje = Teclado.solicitarCadena("");
				System.out.println(mensaje);
				cliente.escribirMensaje(mensaje);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
