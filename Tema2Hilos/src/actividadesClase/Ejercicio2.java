package actividadesClase;

import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 implements Runnable{

	static int segundos;
	static Thread hilo;
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduce segundos: ");
		segundos = teclado.nextInt();
		Thread hilo = new Thread(new Ejercicio2());
		hilo.start();
	}
	
	
	public void run() {
//		for (int i = 0; i <= segundos; i++) {
//			try {
//				hilo.sleep(1000);
//				System.out.println(i);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				break;
//			}
//		}
		//con bucle infinito
		int i = 0;
		while (true) {
			try {
				hilo.sleep(1000);
				System.out.println(i);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
