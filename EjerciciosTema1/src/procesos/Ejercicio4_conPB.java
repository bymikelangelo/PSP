package procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio4_conPB {

	public static void main(String[] args) {
		
		File archivoSalida = new File("salida.txt");
		File archivoError = new File("error.txt");
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "procesos.Ejercicio3");

		try {
			if (!archivoSalida.exists())
				archivoSalida.createNewFile();
			if (!archivoError.exists())
				archivoError.createNewFile();
			pb.redirectOutput(archivoSalida);
//			pb.redirectError(archivoError);
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.err.println(linea);
			}
//			BufferedReader br = new BufferedReader(new FileReader(archivoSalida));
//			String linea;
//			System.out.println("Mostrando archivo: " + archivoSalida.getName());
//			while ((linea = br.readLine()) != null) {
//				System.out.println(linea);
//			}
//			br = new BufferedReader(new FileReader(archivoError));
//			System.out.println("Mostrando archivo: " + archivoError.getName());
//			while ((linea = br.readLine()) != null) {
//				System.out.println(linea);
//			}
			System.out.println("Programa finalizado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
