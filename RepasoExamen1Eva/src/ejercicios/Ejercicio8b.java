package ejercicios;

public class Ejercicio8b extends Thread{
	
	static int tiempo = 0;
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++) {
			tiempo = tiempo + 1000;
			new Ejercicio8b().start();
		}
	}
	
	public void run() {
		try {
			sleep(tiempo);
			System.out.println(getName() + " - Hola Mundo");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
