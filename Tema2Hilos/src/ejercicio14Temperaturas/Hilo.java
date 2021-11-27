package ejercicio14Temperaturas;

public class Hilo extends Thread {
	
	int posicionInicial;
	int posicionFinal;
	int[] temperaturas;
	Resultado res;
	
	
	public Hilo(int posicionInicial, int posicionFinal, int[] temperaturas, Resultado res) {
		super();
		this.posicionInicial = posicionInicial;
		this.posicionFinal = posicionFinal;
		this.temperaturas = temperaturas;
		this.res = res;
	}
	
	public void run() {
		//System.out.println(getName() + " " + posicionInicial + " " + posicionFinal);
		int tempMayor = 0;
		int tempActual;
		for (int i = posicionInicial; i <= posicionFinal; i++) {
			tempActual = temperaturas[i];
			if (tempActual >= tempMayor) {
				tempMayor = tempActual;
			}
		}
		
		System.out.println(getName() + " " + tempMayor);
		
		res.setResultado(tempMayor);
	}
	
}
