package inicial;

public class UsaHilo3 extends Thread {

	/** 
	 * Constructor
	 */
	public UsaHilo3() {
		
	}
	
	/**
	 * Como es un hilo (extiende de thread), implemento run()
	 */
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Estoy en el hilo..." + i);
		}
	}

	/**
	 * Método main para hacer que la clase se pueda ejecutar
	 * @param args
	 */
	public static void main(String[] args) {
		UsaHilo3 mihilo = new UsaHilo3();
		mihilo.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("Estoy fuera del hilo... " + i);
		}

	}


}
