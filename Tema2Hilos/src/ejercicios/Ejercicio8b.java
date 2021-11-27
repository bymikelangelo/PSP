package ejercicios;

public class Ejercicio8b implements Runnable{

	static int tiempo = 1000;
	public static void main(String[] args) {
		Thread[] hilos = new Thread[6];
		int i;
		for (i = 0; i < hilos.length; i++) {
			tiempo = tiempo*i+1;
			new Thread(new Ejercicio8b()).start();;
		}
		System.out.println("Terminé.");

	}
	@Override
	public void run() {
		
		
	}
	

}
