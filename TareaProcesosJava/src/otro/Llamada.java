package otro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/*Crea un programa en Java en el package "ejercicio1" que lea de teclado dos n�meros y 
 * muestre por la salida est�ndar la divisi�n del primero entre el segundo. El programa 
 * ha de controlar todos los errores que se puedan producir y sacarlos por la salida de 
 * error, la ejecuci�n normal saldr� la salida output. Haz ahora otro programa que ejecute 
 * al primero y no se produzca ning�n bloqueo. Ha de mostrarse la salida (tanto si es de 
 * error o es la correcta) de lo que el primer programa produce.
 */

public class Llamada {

	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		Process p = null;
		try {
			p = run.exec("cmd /c start /wait java -cp bin otro.Division");
			p.waitFor();
			System.out.println("espera");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			br.close();
		} 
		catch (InterruptedException ie) {
			ie.printStackTrace();
		} 
		catch (IOException e) {
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
	}
}
