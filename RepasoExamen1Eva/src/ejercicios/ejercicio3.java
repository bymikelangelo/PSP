package ejercicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ejercicio3 {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("ejercicio1.txt"));
			String linea = br.readLine();
			while (linea != null) {
				System.out.println(linea);
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
