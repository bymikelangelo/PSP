package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import comun.PaqueteEnvio;

public class Almacenamiento {
	private HashMap<String, HiloServidor> clientes;
	private ArrayList<PaqueteEnvio> paquetes;
	
	public Almacenamiento() {
		this.clientes = new HashMap<>();
	}
	
	public synchronized void anyadirCliente(String nick, HiloServidor hilo) {
		if (clientes.containsKey(nick) == false) {
			clientes.put(nick, hilo);
		}
	}
	
	public void enviarMensaje(PaqueteEnvio paquete) throws IOException {
		String nick = paquete.getNick();
		for (String keyNick : clientes.keySet()) {
			if (!keyNick.equals(nick)) {
				clientes.get(keyNick).escribirMensaje(paquete);
			}
		}
	}
}
