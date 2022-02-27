package ejercicio15Restaurante;

import java.util.ArrayList;

public class Pila {
	private ArrayList<String> comandas = new ArrayList<>();
	private int contador = 0;
	private int limite;
	private boolean lleno = false;
	private boolean finalizada = false;
	
	public Pila (int limite) {
		this.limite = limite;
	}

	public int getContador() {
		return contador;
	}

	public synchronized boolean isLleno() {
		return lleno;
	}
	
	public synchronized boolean isFinalizada() {
		return finalizada;
	}

	public synchronized void anyadirComanda(String pedido) {
		if (contador < limite) {
			if (comandas.add(pedido)) {
				System.err.println("Se ha añadido " + pedido);
				contador++;
				lleno = true;
			}
		}
		else {
			this.finalizada = true;
		}

	}
	
	public synchronized String retirarComanda() {
		String comanda = null;
		if (lleno) {
			comanda = comandas.remove(comandas.size() - 1);
			if (comandas.size() == 0)
				lleno = false;
		}
		return comanda;
	}

}
