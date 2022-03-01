package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import comun.PaqueteEnvio;

public class GestorMensajes {
	private HashMap<String, HiloServidor> clientes;
	private ArrayList<PaqueteEnvio> paquetes;
	
	public GestorMensajes() {
		this.clientes = new HashMap<>();
	}
	
	public synchronized void anyadirCliente(String nick, HiloServidor hilo) {
		if (clientes.containsKey(nick) == false) {
			clientes.put(nick, hilo);
		}
	}
	
	public synchronized void eliminarCliente(String nick) {
		clientes.remove(nick);
	}
	
	public synchronized void enviarMensaje(PaqueteEnvio paquete) throws IOException {
		String nick = paquete.getNick();
		for (String keyNick : clientes.keySet()) {
			if (!keyNick.equals(nick)) {
				clientes.get(keyNick).enviarMensaje(paquete);
			}
		}
	}
}
