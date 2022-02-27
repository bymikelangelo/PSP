package comun;

import java.util.ArrayList;

import javax.net.ssl.SSLSocket;

public class GestorMensajes {

	private ArrayList<String> mensajes;
	private ArrayList<SSLSocket> clientes;
	
	public GestorMensajes(ArrayList<SSLSocket> clientes) {
		this.mensajes = new ArrayList<>();
		this.clientes = clientes;
	}
	
	public void repartirMensaje(String mensaje, SSLSocket cliente) {
		for (SSLSocket socket : clientes) {
			if (socket != cliente) {
				
			}
		}
	}
	
}
