package ejercicios;

import java.util.Random;
import java.util.Scanner;

public class Ejercicio14{
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Random random = new Random();
		int temperaturas[] = new int[3650];
		for (int i = 0; i < temperaturas.length; i ++) {
			temperaturas[i] = -50000 + random.nextInt(100000);
			System.out.println(temperaturas[i]);
		}
		
		System.out.println("Escribe numero de hilos. ");
		int numerohilos = teclado.nextInt();
		
		int posiciones = 3650 / numerohilos;
		HiloTemp hilos[] = new HiloTemp[numerohilos];
		Resultado res = new Resultado();
		int posIni = 0;
		int posFin = posIni + posiciones - 1; 
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new HiloTemp(temperaturas, posIni, posFin, res);
			posIni = posIni + posiciones;
			posFin = posIni + posiciones - 1;
			hilos[i].start();
		}
		
		if (3650 % numerohilos > 0) {
				
		}
		
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("TEMPERATURA MAYOR = " + res.getResultado());
	}
}

class HiloTemp extends Thread {
	private int temperaturas[];
	private int posIni;
	private int posFin;
	Resultado resultado;
	public HiloTemp(int[] temperaturas, int posIni, int posFin, Resultado resultado) {
		this.temperaturas = temperaturas;
		this.posIni = posIni;
		this.posFin = posFin;
		this.resultado = resultado;
	}
	
	public void run() {
		System.out.println(getName() + " - " + posIni + " - " + posFin);
		int tempMayor = temperaturas[posIni];
		for (int i = posIni; i <= posFin; i++) {
			if (tempMayor < temperaturas[i]) {
				tempMayor = temperaturas[i];
			}
		}
		System.out.println(getName() + " tempMayor = " + tempMayor);
		resultado.compResult(tempMayor);
	}
}

class Resultado {
	private int resultado = 0;
	public Resultado () {
		
	}
	
	public synchronized void compResult (int temp) {
		if (this.resultado < temp) {
			resultado = temp;
		}
	}
	
	public int getResultado() {
		return resultado;
	}
}


