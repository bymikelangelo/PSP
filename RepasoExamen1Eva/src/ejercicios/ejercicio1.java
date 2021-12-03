package ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ejercicio1 {

	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		System.out.println("Empieza el programa");
		try {
			Process p = run.exec("net start");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter("ejercicio1.txt"));
			String linea = br.readLine();
			while (linea != null)  {
				System.out.println(linea);
				bw.write(linea + "\n");
				linea = br.readLine();
			}
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
