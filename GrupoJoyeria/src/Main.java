import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import teclado.Teclado;

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
		Contador contador = new Contador();
		Teclado teclado = new Teclado();
		Random random = new Random();
		
		// listado de Joyas disponibles
		Joya[] joyas = new Joya[5];
		
//		joyas[0] = new Joya("Diamante", 1000);
//		joyas[1] = new Joya("Rub�", 500);
//		joyas[2] = new Joya("Zafiro", 500);
//		joyas[3] = new Joya("Anillo de Plata", 250);
//		joyas[4] = new Joya("Anillo de Oro", 400);
		
		//precios establecidos a "1" para comprobar funcionamiento
		joyas[0] = new Joya("Diamante", 1);
		joyas[1] = new Joya("Rub�", 1);
		joyas[2] = new Joya("Zafiro", 1);
		joyas[3] = new Joya("Anillo de Plata", 1);
		joyas[4] = new Joya("Anillo de Oro", 1);
		//fin del listado de joyas
		
		LinkedList<Cliente> clientes = new LinkedList();
		
		System.out.println("BIENVENIDO A NUESTRA JOYER�A!!!");
		int numVendedores = teclado.solicitarEnteroPositivo("�Cu�ntos vendedores atienden al p�blico...?" );
		int numClientes = teclado.solicitarEnteroPositivo("y... �cu�ntos clientes hay esperando...? ");
		
		int numeracion;
		//se origina array de Clientes con datos aleatorios
		for (int i = 0; i < numClientes; i++) {
			numeracion = i + 1;
			clientes.add(new Cliente("CLIENTE " + numeracion, joyas[random.nextInt(joyas.length)]));
		}
		
		Vendedor[] vendedores = new Vendedor[numVendedores];
		//se origina lista de Vendedores
		for (int i = 0; i < numVendedores; i++) {
			numeracion = i + 1;
			vendedores[i] = new Vendedor("VENDEDOR " + numeracion, caja, clientes, contador);
		}
		
		Repartidor repartidor = new Repartidor(vendedores, clientes, contador);
		//cuando se acaban de repartir todos los clientes salta el mensaje.
		if (repartidor.comprobarRepartidos()) {
			System.out.println("\nSE HA COMPLETADO EL REPARTO DE CLIENTES\n");
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
		System.out.println("TIEMPO TOTAL INVERTIDO = " + contador.getTiempoTotal() + " ms\n");
	}
	
}
