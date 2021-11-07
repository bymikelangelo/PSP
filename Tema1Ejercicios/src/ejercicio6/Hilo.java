package ejercicio6;

public class Hilo extends Thread{

	public Hilo() {
		
	}
	
	public void run() {
		Principal p = new Principal();
		p.ejecuta();
	}
}
