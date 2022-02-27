package ejercicio15Restaurante;

import java.util.ArrayList;

import teclado.Teclado;

public class Main {
	
	public static void main(String[] args) {
		
		Cocinero cocinero = new Cocinero("DABID MUÑOZ");
		Camarero camarero = new Camarero("CAMARERO");
		ArrayList<String> comandas = new ArrayList<>();
		
		int numeroComandas = Teclado.solicitarEntero("¿Cuantas comandas se van a atender? ");
		
		Pila pila = new Pila(numeroComandas);
		camarero.setPila(pila);
		cocinero.setPila(pila);
		
		//añadir las comandas a una pila común entre camarero y cocinero
		camarero.start();
		cocinero.start();
		
		try {
			camarero.join();
			cocinero.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finaliza el programa. ");
	}
}
