package ejercicio14Temperaturas;

public class Resultado {
	int resultado = 0;
	
	public Resultado() {
		
	}
	
	public Resultado(int r) {
		resultado = r;
	}
	
	public int getResultado() {
		return resultado;
	}
	
	public synchronized void setResultado(int resultado) {
		if (resultado > this.resultado) {
			this.resultado = resultado;
		}
	}

}
