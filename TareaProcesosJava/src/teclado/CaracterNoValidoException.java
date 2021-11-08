package teclado;

public class CaracterNoValidoException extends RuntimeException{
	
	private String message = "Carácteres no válidos. Solo se permiten mayúsculas, minúsculas y números.";
	
	public CaracterNoValidoException () {
		super("Carácteres no válidos. Solo se permiten mayúsculas, minúsculas y números.");
	}
	
	/*public void informeError() {
		System.out.println(message);
	}*/
}