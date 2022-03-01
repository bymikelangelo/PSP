package cliente;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

import comun.PaqueteEnvio;
import teclado.Teclado;

public class HiloCliente extends Thread{
	Cliente cliente;

	public HiloCliente(Cliente cliente) {
		super();
		this.cliente = cliente;
	}
	
	public void run() {
		try {
			while(true) {
				String mensaje = Teclado.solicitarCadena("");
				cliente.getOut().writeObject(new PaqueteEnvio(cliente.getNick(), cliente.getSocket().getInetAddress().toString(), mensaje));
				cliente.getOut().flush();
			}
		
		} catch (EOFException | SocketException e) {
			//e.printStackTrace();
			System.err.println("Perdida la conexion con: " + cliente.getSocket().getInetAddress());
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
