package procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ejercicio2 {
	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();
		File archivo = new File("archivos/ejercicio2.txt");
		Process p;
		try {
			p = r.exec("java -cp bin HolaMundo");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
			String linea;
			while ((linea = br.readLine()) != null) {
				bw.write(linea);
				System.out.println(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
