package teclado;

public class CaracterNoValidoException extends RuntimeException{
	
	private String message = "Car�cteres no v�lidos. Solo se permiten may�sculas, min�sculas y n�meros.";
	
	public CaracterNoValidoException () {
		super("Car�cteres no v�lidos. Solo se permiten may�sculas, min�sculas y n�meros.");
	}
	
	/*public void informeError() {
		System.out.println(message);
	}*/
}