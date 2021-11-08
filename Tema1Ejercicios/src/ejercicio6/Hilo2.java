package ejercicio6;

public class Hilo2 implements Runnable{

	public Hilo2() {
		
	}
	
	public void run() {
		Principal p = new Principal();
		p.ejecuta();
	}
}
