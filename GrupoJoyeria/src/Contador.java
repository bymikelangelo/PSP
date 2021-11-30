
/**
 * Clase que almacena el tiempo total de todos los hilos ejecutados en el programa.
 * @author Mikelangelo
 *
 */
public class Contador {
	private long tiempoTotal;
	
	public Contador () {
		tiempoTotal = 0;
	}
	
	//método sincronizado para evitar perdida en las sumas establecidas por los distintos hilos
	public synchronized void sumarTiempo (long tiempo) {
		tiempoTotal = tiempoTotal + tiempo;
	}

	public long getTiempoTotal() {
		return tiempoTotal;
	}
	
}
