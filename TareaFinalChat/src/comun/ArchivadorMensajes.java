package comun;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.SSLSocket;

import servidor.HiloServidor;

public class ArchivadorMensajes {

	private HashMap<SSLSocket, HiloServidor> clientes;
	private HashMap<SSLSocket, ArrayList<String>> mensajes;
	
	public ArchivadorMensajes(HashMap<SSLSocket, HiloServidor> clientes) {
		this.clientes = clientes;
		this.mensajes = new HashMap<>();
	}
	
	public HashMap<SSLSocket, ArrayList<String>> getMensajes() {
		return mensajes;
	}

	public void setMensajes(HashMap<SSLSocket, ArrayList<String>> mensajes) {
		this.mensajes = mensajes;
	}
	
	public synchronized void anyadirMensaje (SSLSocket cliente, String mensaje) {
		ArrayList pendientes;
		if (mensajes.containsKey(cliente)) {
			pendientes = mensajes.get(cliente);
			pendientes.add(mensaje);
		}
		else {
			pendientes = new ArrayList<String>();
			pendientes.add(mensaje);
			mensajes.put(cliente, pendientes);
		}
	}
	
	public void imprimirMensajes() {
		if (mensajes.isEmpty() == false) {
			for (SSLSocket cliente : mensajes.keySet()) {
				
			}
		}
	}
	
}
