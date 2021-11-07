package procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio4 {

	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		Process p = null;
		try {
			 p = run.exec("java -cp bin procesos.Ejercicio3");
			 InputStream entradaStream = p.getInputStream();
			 BufferedReader br = new BufferedReader(new InputStreamReader(entradaStream));
			 BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"));
			 String linea;
			 System.out.println("Mostrando salida: ");
			 while((linea = br.readLine()) != null) {
				 System.out.println(linea);
				 bw.write(linea);
			 } 
			 br.close();
			 bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			InputStream errorStream = p.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(errorStream));
			String linea;
			System.out.println("Mostrando salida de error: ");
			while((linea = br.readLine()) != null) {
				 System.err.println(linea);
			} 
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
