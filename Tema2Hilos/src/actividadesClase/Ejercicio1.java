package actividadesClase;

public class Ejercicio1 implements Runnable{

	public static void main(String[] args) {
		Thread hilo = new Thread(new Ejercicio1());
		hilo.start();
		System.out.println("Nombre original: " + hilo.getName());
		hilo.setName("miHilo");
		System.out.println("Nombre cambiado: " + hilo.getName());
		System.out.println("ID: " + hilo.getId());
		System.out.println("Prioridad: " + hilo.getPriority());
		//la prioridad no cambia internamente a pesar de establecer el método
		hilo.setPriority(hilo.MAX_PRIORITY);
		System.out.println("Prioridad cambiada: " + hilo.getPriority());
		
	}

	@Override
	public void run() {
		System.out.println("Soy el hilo. ");
		
	}

}
