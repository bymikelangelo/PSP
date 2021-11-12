package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Division {
	public static void main(String[] args){
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			double numero1 = Double.parseDouble(br.readLine());
			double numero2 = Double.parseDouble(br.readLine());
			double division = numero1/numero2;
			System.out.println("El resultado es: " + division);
		} catch (NumberFormatException nfe) {
			System.err.println("Fallo al introducir los números. ");
		} catch (NullPointerException npe) {
			System.err.println("Fallo al introducir los números. ");
		} catch (ArithmeticException ae) {  //no entrará a la excepcion por tratarse de numeros double.
			System.err.println("Fallo al dividir por 0. ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
