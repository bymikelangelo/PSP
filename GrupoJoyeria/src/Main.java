import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		Random random = new Random();
		
		Joya[] joyas = new Joya[5];
		
		joyas[0] = new Joya("Diamante", 1000);
		joyas[1] = new Joya("Rub�", 500);
		joyas[2] = new Joya("Zafiro", 500);
		joyas[3] = new Joya("Anillo de Plata", 250);
		joyas[4] = new Joya("Anillo de Oro", 400);
		
		int numClientes = 10;
		LinkedList<Cliente> clientes = new LinkedList();
		
		for (int i = 0; i < numClientes; i++) {
			clientes.add(new Cliente(joyas[random.nextInt(joyas.length)]));
		}
		
		System.out.println("BIENVENIDO A NUESTRA JOYER�A!!!");
		System.out.println("�Cu�ntos vendedores atienden al p�blico...?");
		int numVendedores = teclado.nextInt();
		
		
	}
	

}
