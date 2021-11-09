package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import teclado.Teclado;

public class Division {
	static Teclado teclado = new Teclado();
	public static void main(String[] args){
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			double numero1 = Double.parseDouble(br.readLine());
			double numero2 = Double.parseDouble(br.readLine());
			System.out.println("El resultado es: ");
			System.out.println(numero1/numero2);
		} catch (NumberFormatException nfe) {
			System.err.println("Fallo al introducir los números. ");
		} catch (ArithmeticException ae) {
			System.err.println("Fallo al dividir por 0. ");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
