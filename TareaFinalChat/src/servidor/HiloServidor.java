package servidor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import javax.net.ssl.SSLSocket;

import comun.PaqueteEnvio;

public class HiloServidor extends Thread{
	private Servidor servidor;
	private SSLSocket socketCliente;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private boolean conectado = false;
	
	//mensajes establecidos que indican si se es posible establecer la conexion.
	public final String NICK_DUPLICADO = "nick_no_valido";
	public final String CONEX_VERIFICADA = "conexion_verificada";
	public final String MAX_CONEX_ALCANZADAS = "maximas_conexiones_alcanzadas";

	
	//recibe el servidor que llama al hilo y el socket del cliente que debe atender
	public HiloServidor(Servidor servidor, SSLSocket socketCliente) {
		this.servidor = servidor;
		this.socketCliente = socketCliente;
	}
	
	//lee el paquete de datos del cliente atentido
	public PaqueteEnvio recibirMensaje() throws ClassNotFoundException, IOException {
		return (PaqueteEnvio) in.readObject();
	}
	
	/*
	 * envia el paquete de datos al cliente atendido. Método llamado por la clase
	 * gestor cuando se reparter los mensajes a los clientes receptores
	 */
	public void enviarMensaje(PaqueteEnvio paquete) throws IOException {
		out.writeObject(paquete);
		out.flush();
	}
	
	//muestra y envia las notificaciones que genera el hilo en la ejecucion
	public void notificar(String notificacion) throws IOException {
		servidor.mostrarNotificacion(notificacion);
		servidor.enviarNotificacion(notificacion);
	}
	
	//cierra los canales de comunicacion del cliente/servidor de este hilo
	public void cerrarConexion() throws IOException {
		in.close();
		out.close();
		socketCliente.close();
	}

	
	public void run() {
		DatosCliente cliente = null;
		try {
			//inicia el servidor. Abre los canales de comunicacion con el cliente
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			in = new ObjectInputStream(socketCliente.getInputStream());

			
			/*
			 * bucle que verifica si el usuario puede establecer conexion con el servidor. 
			 * El cliente envia un paquete de datos con su nick. Si el nick ya existe entre
			 * los clientes conectados, se espera a recibir otro nick para verificacion
			 */
			PaqueteEnvio paqueteRecibido;
			conectado = false;
			String respuestaGestor = "";
			do {
				//recibe paquete con el nick
				paqueteRecibido = recibirMensaje();
				
				//se prueba la insercion del nick. Si existe envia mensaje de no validacion.
				cliente = new DatosCliente(paqueteRecibido.getNick(), paqueteRecibido.getIp());
				respuestaGestor = servidor.nuevoCliente(cliente, this);
				if (respuestaGestor.equals(CONEX_VERIFICADA)) {
					//insercion correcta, se envia mensaje de verificacion
					enviarMensaje(new PaqueteEnvio("Servidor", servidor.getServer().getInetAddress().toString(), respuestaGestor));
					conectado = true;
					
					notificar("Entra en la sala de chat: " + cliente.toString() + " (conexiones restantes: " + servidor.conexionesRestantes() + ")...");
					
					//bucle encargado de esperar a recibir mensajes del cliente conectado
					while(true) {
						paqueteRecibido = recibirMensaje();
						if (paqueteRecibido.getMensaje() != null) {
							servidor.mostrarMensaje(paqueteRecibido);
							servidor.enviarMensaje(paqueteRecibido);
						}
					}
				}
				else if (respuestaGestor.equals(NICK_DUPLICADO)){
					//insercion fallida, se envia mensaje con error de nick duplicado
					enviarMensaje(new PaqueteEnvio("Servidor", servidor.getServer().getInetAddress().toString(), respuestaGestor));
					conectado = false;
				}
				else {
					enviarMensaje(new PaqueteEnvio("Servidor", servidor.getServer().getInetAddress().toString(), respuestaGestor));
					conectado = false;
				}
			} while (conectado = false & respuestaGestor.equals(NICK_DUPLICADO));
			
			System.out.println("hilo final");

		} catch (ClassNotFoundException e) {  //salta si el tipo de objeto enviado no corresponde
			e.printStackTrace();
		} catch (EOFException | SocketException e) {
			/*
			 * excepcion capturada cuando el cliente cierra la conexion o son cerrados los flujos de
			 * entrada y salida. 
			 */
			if (conectado) {
				try {
					//notificacion solo si el cliente llegó a entablar conexion
					notificar("Se ha perdido la conexion con: " + cliente.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conectado) {
					/*
					 * eliminacion del cliente de la lista de cliente si llegó a entablar conexion y
					 * fue añadido a la lista de clientes. 
					 */
					servidor.eliminarCliente(cliente);
					servidor.escuchando();
				}
				
				//cierre de los canales de comunicacion al finalizar el hilo de ejecucion
				cerrarConexion();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
