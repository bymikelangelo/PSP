
public class CajaRegistradora {

	int dineroTotal;

	public int getDineroTotal() {
		return dineroTotal;
	}

	public synchronized void setDineroTotal(int dineroTotal) {
		this.dineroTotal = dineroTotal;
	}
	
	public synchronized void sumarVenta(int cantidad) {
		this.dineroTotal = this.dineroTotal + cantidad;
	}
	
}
