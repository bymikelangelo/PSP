
/**
 * Clase que almacena el tiempo total de todos los hilos ejecutados en el programa.
 * @author mikelangelo
 *
 */
public class Contador {
	private long tiempoTotal;
	
	public Contador () {
		tiempoTotal = 0;
	}
	
	public synchronized void sumarTiempo (long tiempo) {
		tiempoTotal = tiempoTotal + tiempo;
	}

	public long getTiempoTotal() {
		return tiempoTotal;
	}
	
}
