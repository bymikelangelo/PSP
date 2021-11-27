package ejercicios;

public class Ejercicio7 extends Thread {

	static int numHilos = 0;
//	public Ejercicio7() {
//		numHilos++;
//	}
//	
	public static void main(String[] args) {
		Thread[] hilos = new Thread[100];
		
		int i;
		for (i = 0; i < hilos.length; i++) {
			hilos[i]= new Ejercicio7();
			hilos[i].start();
		}
		
		for (i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Terminé.");
	}
	
	public void run() {
		System.out.println("Soy el hilo numero " + numHilos++);
	}

}
