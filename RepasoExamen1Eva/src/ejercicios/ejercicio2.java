package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ejercicio2 {

	public static void main(String[] args) {
		String comando[] = {"java", "-cp", "bin", "ejercicios.HolaMundo"};
		ProcessBuilder p = new ProcessBuilder(comando);
		p.redirectOutput(new File("ejercicio2.txt"));
		try {
			p.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
