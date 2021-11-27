package ejercicio6;

public class Principal {

	static int numero = 0;
	public void ejecuta() {
		
		for (int i = 0; i < 50; i++) {
			System.err.println(numero++);
		}
		System.out.println("final " + numero );
		//System.err.println("Terminé");
	}

}
