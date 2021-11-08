package inicial;

public class HiloSimple2 implements Runnable {

	/**
	 * Constructor
	 */
	public HiloSimple2() {
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Estoy en el hilo..." + i);
		}
	}
}
