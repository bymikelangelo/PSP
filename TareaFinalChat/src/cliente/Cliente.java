package cliente;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
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
	private FrameCliente frame;
	public final String NICK_DUPLICADO = "nick_no_valido";
	public final String CONEX_VERIFICADA = "conexion_verificada";
	
	/*
	 * Recibe el frame por parametro. Carga las propiedades del cliente y los certificados
	 * de seguridad SSL.
	 */
	public Cliente(FrameCliente frame) throws IOException {
		this.frame = frame;
		Properties propiedades = new Properties();
		propiedades.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		host = propiedades.getProperty("host");
		puerto = Integer.valueOf(propiedades.getProperty("puerto"));
		conectado = false;
		
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
	
	//envia el paquete de datos con el mensaje extraido del frame al hilo que atiende
	public void enviarMensaje(String mensaje) throws IOException {
		PaqueteEnvio paquete = new PaqueteEnvio(nick, socket.getInetAddress().toString(), mensaje);
		out.writeObject(paquete);
	}
	
	//llama a la interfaz para mostrar el mensaje recibido
	public void mostrarMensaje(PaqueteEnvio paquete) {
		if (paquete.getMensaje() != null) {
			//System.out.println(paquete.getNick() + ": " + paquete.getMensaje());
			frame.mostrarMensaje(paquete);
		}
	}
	
	//cierra las conexiones del cliente al servidor
	public void cerrarConexion() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	
	@Override
	public void run() {
		HiloCliente hilo;
		try {
			//inicia el cliente. Abre los canales de comunicacion con el servidor
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			/*
			 * bucle que se encarga de enviar el nick al hilo del servidor.
			 * Finaliza cuando el nick especificado es válido y el servidor
			 * verifica la conexion
			 */
			PaqueteEnvio paqueteRecibido;
			conectado = false;
			do {
				do {
					nick = frame.introducirNick();
				} while (nick == null || nick.equals(""));
				
				//envia el paquete con el nick recibido del frame
				out.writeObject(new PaqueteEnvio(nick, socket.getInetAddress().toString()));
				
				//obtiene respuesta del servidor
				paqueteRecibido = (PaqueteEnvio) in.readObject();
				String respuesta = paqueteRecibido.getMensaje();
				if (respuesta.equals(NICK_DUPLICADO)) {
					//conexion no verificada por el servidor
					conectado = false;		
					frame.mostrarPanelMensaje("El nick introducido ya está en uso.");
				}
				else if (respuesta.equals(CONEX_VERIFICADA)) {
					//conexion verificada por el servidor
					conectado = true;
					frame.ponerNombre();
				}
			} while (conectado == false);

//			hilo = new HiloCliente(this);
//			hilo.start();
			
			/*
			 * bucle que mantiene al cliente a la escucha de recibir mensajes de
			 * otros clientes
			 */
			while (true) {
				paqueteRecibido = (PaqueteEnvio) in.readObject();
				mostrarMensaje(paqueteRecibido);
			}

		} catch (ClassNotFoundException e) {  //salta si el tipo de objeto enviado no corresponde
			e.printStackTrace();
		} catch (EOFException | SocketException e) {
			/*
			 * excepcion capturada cuando el servidor cierra la conexion o son cerrados los flujos de
			 * entrada y salida. 
			 */
			e.printStackTrace();
			frame.mostrarPanelMensaje("Se ha perdido la conexión con el anfitrión");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//cierre de los canales de comunicacion al finalizar el hilo de ejecucion
				cerrarConexion();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	
//	public static void main(String[] args) {
//		String nick = Teclado.solicitarCadena("Introduce nick: ");
//		try {
//			Cliente cliente = new Cliente(nick);
//			new Thread(cliente).start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}			
//	}

}
