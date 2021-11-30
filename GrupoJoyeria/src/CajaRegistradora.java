
/**
 * Clase que almacena la cantidad total recaudada por las diferentes ventas.
 * @author Mikelangelo
 *
 */
public class CajaRegistradora {

	int dineroTotal;

	public int getDineroTotal() {
		return dineroTotal;
	}

	//método sincronizado para evitar perdida en las sumas establecidas por los distintos hilos
	public synchronized void setDineroTotal(int dineroTotal) {
		this.dineroTotal = dineroTotal;
	}
	
	public synchronized void sumarVenta(int cantidad) {
		this.dineroTotal = this.dineroTotal + cantidad;
		System.out.println("\nSe ha sumado la cantidad de: " + cantidad + ". TOTAL = " + dineroTotal + "\n");
	}
	
}
