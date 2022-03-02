package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import comun.PaqueteEnvio;

/*
 * Clase que contiene los datos de los clientes y reparte los mensajes a los receptores
 */
public class GestorMensajes {
	int maximoClientes;
	private TreeMap<DatosCliente, HiloServidor> hilosClientes;
	
	/*
	 * mensajes establecidos que indican si se es posible establecer la conexion. El
	 * gestor los devuelve si es posible o no añadir a un nuevo cliente y establecer 
	 * conexion con el servidor
	 */
	public final String NICK_DUPLICADO = "nick_no_valido";
	public final String CONEX_VERIFICADA = "conexion_verificada";
	public final String MAX_CONEX_ALCANZADAS = "maximas_conexiones_alcanzadas";
	
	//private ArrayList<PaqueteEnvio> paquetes;
	
	public GestorMensajes(int maximoClientes) {
		this.maximoClientes = maximoClientes;
		this.hilosClientes = new TreeMap<>();
	}
	
	//devuelve los datos de los clientes para mostrarlos en el frame
	public Set<DatosCliente> recibirDatosCliente() {
		return hilosClientes.keySet();
	}
	
	//devuelve la cantidad de clientes añadidos
	public int numeroClientes() {
		return hilosClientes.size();
	}
	
	/*
	 * añade los datos del cliente y su hilo de ejecucion en la coleccion.
	 * Devuelve un mensaje establecido dependiendo del resultado de la insercion
	 */
	public synchronized String anyadirCliente(DatosCliente cliente, HiloServidor hilo) {
		if (numeroClientes() < maximoClientes) {
			if (hilosClientes.containsKey(cliente) == false) {
				//se anñade cliente a la lista y se verifica conexion
				hilosClientes.put(cliente, hilo);
				System.out.println(hilo.getName() + " " + hilo.getId());
				return CONEX_VERIFICADA;
			}
			else {
				//el nick es duplicado y no se añade
				return NICK_DUPLICADO;
			}
		}
		else {
			//se han alcanzado el maximo de conexiones
			return MAX_CONEX_ALCANZADAS;
		}

	}
	
	//elimina el cliente pasado por parametro
	public synchronized void eliminarCliente(DatosCliente cliente) {
		hilosClientes.remove(cliente);
	}
	
	//llama al hilo para cerrar las conexiones del cliente atendido por el hilo
	public synchronized void cerrarConexion(DatosCliente cliente) throws IOException {
		hilosClientes.get(cliente).cerrarConexion();
		//System.out.println("Cerrada conexion " + cliente.getNick());
	}
	
	//cierra todas las conexiones de los clientes conectados al servidor
	public synchronized void cerrarConexiones() throws IOException {
		Iterator<DatosCliente> iterador = hilosClientes.keySet().iterator();
		while (iterador.hasNext()) {
			DatosCliente cliente = iterador.next();
			cerrarConexion(cliente);
		}
	}
	
	/*
	 * elimina todos los clientes del servidor. Llamado por el frame cuando se corta 
	 * la conexion total del servidor.
	 */
	public synchronized void eliminarClientes() {
		Iterator<DatosCliente> iterador = hilosClientes.keySet().iterator();
		while (iterador.hasNext()) {
			DatosCliente cliente = iterador.next();
			iterador.remove();
		}
	}
	
//	//envia el mensaje del emisor a todos los clientes receptores
//	public synchronized void enviarMensaje(PaqueteEnvio paquete) throws IOException {
//		DatosCliente cliente = new DatosCliente(paquete.getNick(), paquete.getIp());
//		for (DatosCliente keyCliente : hilosClientes.keySet()) {
//			if (!keyCliente.equals(cliente)) {
//				hilosClientes.get(keyCliente).enviarMensaje(paquete);
//			}
//		}
//	}
	
	//envia el mensaje del emisor a todos los clientes receptores
	public synchronized void enviarMensaje(PaqueteEnvio paquete) {
		DatosCliente cliente = new DatosCliente(paquete.getNick(), paquete.getIp());
		for (DatosCliente keyCliente : hilosClientes.keySet()) {
			try {
				if (!keyCliente.equals(cliente)) {
					hilosClientes.get(keyCliente).enviarMensaje(paquete);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
