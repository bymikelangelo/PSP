package ejemploHilo;

public class Principal {

	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++) {
			Hilo h = new Hilo();	
			h.start();
		}
		System.out.println("Termine.");
	}

}
