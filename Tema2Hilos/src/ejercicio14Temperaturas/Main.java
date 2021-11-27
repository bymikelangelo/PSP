package ejercicio14Temperaturas;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int[] temperaturas = new int[3650];
		Resultado res = new Resultado();
		
		for (int i = 0; i < temperaturas.length; i++) {
			Random random = new Random();
			temperaturas[i] = random.nextInt(50000);
			System.out.println(temperaturas[i]);
		}

		Hilo[] hilos = new Hilo[10];
		int posiciones = (3650/10);
		int posicionInicial = 0;
		int posicionFinal = posicionInicial + posiciones - 1;
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Hilo(posicionInicial, posicionFinal, temperaturas, res);
			posicionInicial = posicionFinal + 1;
			posicionFinal += posiciones;
			hilos[i].start();
		}
		
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Resultado final: " + res.getResultado());
		System.out.println("Termina principal");
	}

}
