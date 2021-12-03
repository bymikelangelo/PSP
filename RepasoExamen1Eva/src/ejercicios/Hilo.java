package ejercicios;

public class Hilo extends Thread{

	public void run() {
		System.out.println("Soy el Hilo: " + getName());
	}

}
