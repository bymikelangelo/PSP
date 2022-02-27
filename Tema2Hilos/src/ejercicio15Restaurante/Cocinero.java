package ejercicio15Restaurante;

public class Cocinero extends Thread{
	
	private String pedido;
	private Pila pila;
	
	public Cocinero (String nombre) {
		super(nombre);
	}
	
	public Pila getPila() {
		return pila;
	}

	public void setPila(Pila pila) {
		this.pila = pila;
	}

	public String getPedido() {
		return pedido;
	}

	public synchronized void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public void run() {
		while(!pila.isFinalizada() | pila.isLleno()) {
			try {
				if (pila.isLleno()) {
					System.err.println(getName() + " ha consumido " + pila.retirarComanda());
				}
				else {
					System.err.println(getName() + " no ha podido consumir porque no hay comandas. ");
				}
				sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.err.println(getName() + " finaliza su trabajo.");
		
//		for (int i = 0; i < numClientes; i++) {
//			// leer la comanda y cocinar
//			synchronized (lasComandas) {
//				if (lasComandas.estaVacia()) {
//				lasComandas.wait();
//				}
//				// en este punto hay algo en la lista de comandas
//				// sacar la comida de la lista de comandas
//				Comanda c = lasComandas.sacar();
//				System.out.println("COCINERO: estoy cocinando " +  getPlato());
//				sleep(COCINANDO);
//				// sacar la comida de la lista de comandas
//				lasComandas.notifyAll();
//				// añadir try-catch
//			}
//		}
		
	}
}
