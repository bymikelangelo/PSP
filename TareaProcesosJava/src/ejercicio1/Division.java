package ejercicio1;

import java.io.IOException;
import teclado.Teclado;

public class Division {
	static Teclado teclado = new Teclado();
	public static void main(String[] args){
		double numero1 = teclado.solicitarRealEnDoblePrecision("Introduce dividendo. ");
		double numero2 = teclado.solicitarRealEnDoblePrecision("Introduce divisor. ");
		
		try {
			System.out.println("El resultado es: ");
			System.out.println(numero1/numero2);
		} catch (NumberFormatException nfe) {
			System.err.println("Fallo al introducir los números. ");
		} catch (ArithmeticException ae) {
			System.err.println("Fallo al dividir por 0. ");
		} 
	}

}
