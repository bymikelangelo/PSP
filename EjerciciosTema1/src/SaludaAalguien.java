import java.util.Scanner;

public class SaludaAalguien {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Hola, como te llamas?");
			String nombre = sc.nextLine();
			System.out.println("HOLAAAA" + nombre + ", soy un proceso nuevo!!");
		}
	}

}
