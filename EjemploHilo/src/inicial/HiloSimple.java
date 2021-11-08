package inicial;

public class HiloSimple extends Thread {

	/**
	 * El constructor
	 */
	public HiloSimple() {
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Estoy en el hilo..." + i);
		}
	}
}
