package procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File archivo = new File("archivos/ejercicio3.txt");
		try {
			FileReader lectura = new FileReader(archivo);
			BufferedReader bufferLectura = new BufferedReader(lectura);
			String linea;
			while ((linea = bufferLectura.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (FileNotFoundException fnfe) {
//			System.err.println(fnfe.getMessage());
			fnfe.printStackTrace();
		} catch (IOException e) {
//			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
