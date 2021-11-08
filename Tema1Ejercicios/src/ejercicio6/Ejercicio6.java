package ejercicio6;

public class Ejercicio6 {
	public static void main(String[] args) {
		int numero = 0;
		//con extends Thread
		for (int i = 0; i < 5; i++) {
			Hilo h = new Hilo();
			h.start();
		}
		//con implements Runnable
		for (int i = 0; i < 5; i++) {
			Hilo2 h = new Hilo2();
			new Thread(h).start();
		}
	}
}
