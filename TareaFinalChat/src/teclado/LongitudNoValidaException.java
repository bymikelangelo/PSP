package teclado;

public class LongitudNoValidaException extends RuntimeException {

	public LongitudNoValidaException(int minimoCaracteres) {
		super("Debes escribir mínimo " + minimoCaracteres + " caracteres.");
	}
}
