package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*Crea un programa en Java en el package "ejercicio1" que lea de teclado dos números y 
 * muestre por la salida estándar la división del primero entre el segundo. El programa 
 * ha de controlar todos los errores que se puedan producir y sacarlos por la salida de 
 * error, la ejecución normal saldrá la salida output. Haz ahora otro programa que ejecute 
 * al primero y no se produzca ningún bloqueo. Ha de mostrarse la salida (tanto si es de 
 * error o es la correcta) de lo que el primer programa produce.
 */

public class Llamada {

	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		Process p = null;
		try {
			p = run.exec("java -cp bin ejercicio1.Division");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Introduce dividiendo. ");
			bw.write(teclado.readLine());
			bw.newLine();
			System.out.print("Introduce divisor. ");
			bw.write(teclado.readLine());
			teclado.close();
			bw.flush();
			bw.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.err.println(linea);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int valorSalida;
		try {
			valorSalida = p.waitFor();
			System.out.println("Valor de Salida: " + valorSalida);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	}

}
