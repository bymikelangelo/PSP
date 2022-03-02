package servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import comun.PaqueteEnvio;

public class Servidor implements Runnable{
	
	private final String PROPERTIES_FILE = "src/config/propiedades";
	private int puerto;
	private String host;
	private int maximoClientes;
	private GestorMensajes gestor;
	private SSLServerSocket server;
	private FrameServidor frame;
	
	/*
	 * Recibe el frame por parametro. Carga las propiedades del servidor y los certificados
	 * de seguridad SSL. Tambien crea el gestor de mensajes del Servidor
	 */
	public Servidor(FrameServidor frame) throws NumberFormatException, FileNotFoundException, IOException{
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		this.host = propiedades.getProperty("host");
		this.puerto = Integer.valueOf(propiedades.getProperty("puerto"));
		this.maximoClientes = Integer.valueOf(propiedades.getProperty("maximoClientes"));
			
		System.setProperty("javax.net.ssl.keyStore", "src/almacen/MPF");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		this.server = (SSLServerSocket) factory.createServerSocket(puerto);
		
		this.gestor = new GestorMensajes();
		this.frame = frame;
	}
	
	
	public SSLServerSocket getServer() {
		return server;
	}
	
	//llama a frame para imprimir la notificacion por pantalla
	public void mostrarNotificacion(String notificacion) {
		//System.out.println(notificacion);
		frame.mostrarMensaje(notificacion);
	}
	
	//crea un paquete de datos para enviarlo al resto de clientes desde el gestor
	public void enviarNotificacion(String notificacion) throws IOException {
		gestor.enviarMensaje(new PaqueteEnvio("Servidor", server.getInetAddress().toString(), notificacion));
	}
	
	//llama a frame para mostra por pantalla el mensaje recibido en el paquete de datos
	public void mostrarMensaje(PaqueteEnvio paquete) {
		String mensaje = paquete.getNick() + "(" + paquete.getIp() +"): "+ paquete.getMensaje();
		//System.out.println(mensaje);
		frame.mostrarMensaje(mensaje);
	}
	
	//metodo llamado desde la clase gestor para escribir el mensaje a los clientes receptores
	public void enviarMensaje(PaqueteEnvio paquete) throws IOException {
		gestor.enviarMensaje(paquete);
	}
	
	//añade un nuevo cliente a la lista del gestor. Devuelve verdadero o falso si tiene exito
	public boolean nuevoCliente(DatosCliente cliente, HiloServidor hilo) {
		boolean anyadido = gestor.anyadirCliente(cliente, hilo);
		if (anyadido & frame != null) {
			frame.mostrarClientes(gestor.recibirDatosCliente());
		}
		return anyadido;
	}
	
	/*
	 * elimina un cliente de la lista del gestor. Es llamado por los hilos cuando el cliente 
	 * se ha desconectado o bien, si el servidor cierra la conexion
	 */
	public void eliminarCliente(DatosCliente cliente) {
		gestor.eliminarCliente(cliente);
		frame.mostrarClientes(gestor.recibirDatosCliente());
	}
	
	/*
	 * metodo que cierra todas las conexiones de los clientes conectados al servidor.
	 * Llama a cada hilo para cerrar las conexiones del cliente que atiende.
	 */
	public void cerrarServer() throws IOException {
		gestor.cerrarConexiones();
		gestor.eliminarClientes();
		server.close();
		frame.mostrarClientes(gestor.recibirDatosCliente());
	}
	
	//devuelve el numero de conexiones restantes disponibles
	public int conexionesRestantes() {
		return maximoClientes - gestor.numeroClientes();
	}
	
	
	@Override
	public void run() {
		//se inicia el servidor
		mostrarNotificacion("Escuchando (conexiones restantes: " + conexionesRestantes() + ")..." );
		
		//escucha hasta que el numero de clientes maximos es alcanzado
		try {
			while (gestor.numeroClientes() < maximoClientes) {
				SSLSocket socketCliente = (SSLSocket) server.accept();
				server.setSoTimeout(0);
				
				//inicia un hilo propio para cada cliente
				HiloServidor hilo = new HiloServidor(this, socketCliente);
				hilo.start();
					
			} 
			
			mostrarNotificacion("Máximo de conexiones alcanzado.");
			
		} catch (SocketException e) {  //salta cuando se cierra el SSLServerSocket
			e.printStackTrace();
			mostrarNotificacion("Conexión cerrada");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	
//	public static void main(String[] args) {
//		Servidor servidor;
//		try {
//			servidor = new Servidor();
//			new Thread(servidor).start();
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
