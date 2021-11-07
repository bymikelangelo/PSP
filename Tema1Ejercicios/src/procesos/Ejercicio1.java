package procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Runtime r = Runtime.getRuntime();
		String comando = "service --status-all";
//		String comando = "CMD /C net start";
		
		Process p;
		try {
			p = r.exec(comando);
			System.out.println("terminé");
			InputStream salida = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(salida));
			
			File archivo = new File("archivos/ejercicio1.txt");
			FileWriter escritor = new FileWriter(archivo, true);
			BufferedWriter bw = new BufferedWriter(escritor);
			
			String linea;
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
				bw.write(linea + "\n");
			}
			br.close();
			bw.close();
			
		} catch (Exception e) {	 
		     System.err.println("Error en: "+comando );
		     e.printStackTrace();
		}
		
	}

}
