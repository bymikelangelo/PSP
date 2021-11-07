package ejercicio6;

public class Ejercicio6 {
	public static void main(String[] args) {
		int numero = 0;
		for (int i = 0; i < 5; i++) {
			Hilo h = new Hilo();
			h.start();
		}
	}
}
