package procesos;

import java.io.File;
import java.io.IOException;

public class Ejercicio1_conPB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder("service", "--status-all");
		File archivo = new File("archivos/ejercicio1.txt");
		pb.redirectOutput(archivo);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
