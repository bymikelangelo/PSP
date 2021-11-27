package ejercicios;

public class Ejercicio8 extends Thread{
	static int tiempo = 1000;
	public static void main(String[] args) {
		Thread[] hilos = new Thread[6];
		int i;
		for (i = 0; i < hilos.length; i++) {
			tiempo = tiempo*i+1;
			new Ejercicio8().start();
		}
		System.out.println("Terminé.");

	}
	
	public void run() {
		try {
			this.sleep(tiempo);
			System.out.println(this.getName() + " - Hola mundo.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
