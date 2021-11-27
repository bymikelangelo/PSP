package compartirinfo.tres;

public class Cuenta {
	private int saldo;
	
	Cuenta(int s) {
		saldo = s;
	}

	int getSaldo() {
		return saldo;
	}

	synchronized boolean restar(int cantidad) {
		if (saldo >= cantidad) {
			saldo = saldo - cantidad;
			return true;
		}
		else
			return false;			
	}

	/**
	 * Comprueba que el dinero que va a retirarse está en la cuenta
	 * Si hay suficiente duerme 0,5 segundos y se lo resta a la cuenta
	 * Muestra el nombre de quien se ha llevado el dinero y de cuanto saldo queda
	 * Si no hay dinero suficiente muestra el mensaje
	 * Si el saldo es negativo muestra el mensaje
	 * 
	 * @param cant
	 * @param nom
	 */
	void RetirarDinero(int cant, String nom) {
		System.out.println("Se van a retirar " +  cant + " euros. Saldo disponible: " + getSaldo());
		try {
			Thread.sleep(500);
			if (restar(cant))
				System.out.println(nom + " ha sacado: " + cant + " euros. Saldo disponible: " + getSaldo());
			else 
				System.out.println("Error al sacar dinero. SIN SALDO");
		} catch (InterruptedException e) {				// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}// retirar
}
