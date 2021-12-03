package ejercicios;

public class Ejercicio8 implements Runnable{

	static int tiempo = 0;
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++) {
			Thread hilo = new Thread(new Ejercicio8());
			hilo.start();
			try {
				hilo.sleep(tiempo =+ 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Terminé");
	}
	
	public void run() {
		System.out.println("Hola Mundo");
	}

}
