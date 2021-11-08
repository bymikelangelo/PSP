package inicial;

public class UsaHilo4 {

	public static void main(String[] args) {

		// defino una clase anónima!!
		Thread hilo = new Thread() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("Estoy en el hilo..." + i);
				}
			}
		};
		hilo.start();
		
		Thread hilo2 = new Thread() {
			public void run() {
				System.out.println("Hola mundo. ");
			}
		};
		hilo2.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("Estoy fuera del hilo... " + i);
		}

	}

}
