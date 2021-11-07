package ejemploHilo;

public class Hilo extends Thread{

	public Hilo() {
		
	}
	
	public void run() {
		
		System.err.println("Hola soy el Hilo.");
	}
}
