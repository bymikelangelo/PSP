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
	private TreeMap<DatosCliente, HiloServidor> hilosClientes;
	//private ArrayList<PaqueteEnvio> paquetes;
	
	public GestorMensajes() {
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
	 * Devuelve verdadero o falto dependiendo del exito de la insercion
	 */
	public synchronized boolean anyadirCliente(DatosCliente cliente, HiloServidor hilo) {
		if (hilosClientes.containsKey(cliente) == false) {
			hilosClientes.put(cliente, hilo);
			System.out.println(hilo.getName() + " " + hilo.getId());
			return true;
		}
		else {
			return false;
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
	
	//envia el mensaje del emisor a todos los clientes receptores
	public synchronized void enviarMensaje(PaqueteEnvio paquete) throws IOException {
		DatosCliente cliente = new DatosCliente(paquete.getNick(), paquete.getIp());
		for (DatosCliente keyCliente : hilosClientes.keySet()) {
			if (!keyCliente.equals(cliente)) {
				hilosClientes.get(keyCliente).enviarMensaje(paquete);
			}
		}
	}
}
