import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * La clase Main contiene el m�todo Main que inicia el programa. Se encarga de generar los objetos necesarios
 * para la ejecuci�n del programa. Tambi�n rellena los arrays necesarios con informaci�n aleatoria para las
 * pruebas de ejecuci�n.
 * 
 * @author Mikelangelo
 *
 */
public class Main {
	/**
	 * Inicia el programa. 
	 * 
	 * El programa pregunta al usuario por el n�mero de Vendedores y Clientes.
	 * 
	 * Se generan con datos aleatorios los arrays de Vendedores y Clientes segun los datos del usuario.
	 * 
	 * Instancia un objeto de la clase Repartidor para generar el reparto de Clientes a Vendedores.
	 * 
	 * Llama al m�todo join() de cada Vendedor iniciado.
	 * 
	 * Finaliza mostrando la cantidad total en CajaRegistradora.
	 * @param args
	 */
	public static void main(String[] args) {
		
		CajaRegistradora caja = new CajaRegistradora();
		Scanner teclado = new Scanner(System.in);
		Random random = new Random();
		
		Joya[] joyas = new Joya[5];
		
//		joyas[0] = new Joya("Diamante", 1000);
//		joyas[1] = new Joya("Rub�", 500);
//		joyas[2] = new Joya("Zafiro", 500);
//		joyas[3] = new Joya("Anillo de Plata", 250);
//		joyas[4] = new Joya("Anillo de Oro", 400);
		
		joyas[0] = new Joya("Diamante", 1);
		joyas[1] = new Joya("Rub�", 1);
		joyas[2] = new Joya("Zafiro", 1);
		joyas[3] = new Joya("Anillo de Plata", 1);
		joyas[4] = new Joya("Anillo de Oro", 1);
		
		LinkedList<Cliente> clientes = new LinkedList();
		
		System.out.println("BIENVENIDO A NUESTRA JOYER�A!!!");
		System.out.println("�Cu�ntos vendedores atienden al p�blico...?");
		int numVendedores = teclado.nextInt();
		
		System.out.println(" y... �cu�ntos clientes hay esperando...?");
		int numClientes = teclado.nextInt();
		
		int numeracion;
		for (int i = 0; i < numClientes; i++) {
			numeracion = i + 1;
			clientes.add(new Cliente("CLIENTE " + numeracion, joyas[random.nextInt(joyas.length)]));
		}
		
		Vendedor[] vendedores = new Vendedor[numVendedores];
		for (int i = 0; i < numVendedores; i++) {
			numeracion = i + 1;
			vendedores[i] = new Vendedor("VENDEDOR " + numeracion, caja, clientes);
		}
		
		if (new Repartidor(vendedores, clientes).comprobarRepartidos()) {
			System.out.println("Se ha completado el reparto de clientes");
		}
		
//		int cont = 0;
//		do {
//			for (int i = 0; i < vendedores.length; i++) {
//				if (vendedores[i].isAlive()) {
//					if (vendedores[i].isEnEspera() == false) {
//						System.out.println("A�n quedan vendedores atendiendo");
//						cont++;
//					}
//				}
//			}
//		} while (cont != 0);
		
		for (int i = 0; i < vendedores.length; i++) {
			if (vendedores[i].isAlive()) {
				try {
					vendedores[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("\nTerminada la jornada de ventas. TOTAL RECAUDADO = " + caja.dineroTotal + "\n");
	}
	
}
