package ejercicio15Restaurante;

public class Camarero extends Thread{

	private String pedido;
	private Pila pila;
	
	public Camarero (String nombre) {
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
	
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public void run(){
		// cambiar esto
		while (!pila.isFinalizada()) {
			pedido = Utilidades.a_ingredientesAzar();
			pila.anyadirComanda(pedido);
			System.err.println(getName() + " añadió " + pedido);
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.err.println(getName() + " finaliza su trabajo. ");
		
//		for (int i = 0; i < numClientes; i++) {
//			// atender al cliente
//			sleep(TIEMPO_DE_ESPERA);
//			Comanda c = new Comanda(Utilidades.a_ingredientesAzar(), i);
//			// escribir en la lista de comandas la comanda
//			synchronized (lasComandas) {
//				while (estaLlena()) {
//					System.out.println("CAMARERO: Me espero que está llena");
//					lasComandas.wait();
//				}
//				lasComandas.agnade(c);
//				System.out.println("Añado el plato " + c.getPlato() + " a la comanda. ");
//				lasComandas.notifyAll();
//			}
//		}
//		// añadir try-catch;
		
	}
	
}
