package teclado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;

// Clase Teclado para la entrada de datos primitivos desde el teclado
// mediante flujos de caracteres (InputStreamReader y BufferedReader).
public class Teclado {
	
	// Solicita la introducción de un número entero 'int' por teclado:
	// - Visualiza en la consola un mensaje informativo para el usuario. 
	// - Devuelve el número entero leído desde el teclado.
	public static int solicitarEntero(String mensaje) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		int numeroEntero = 0;
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				numeroEntero = Integer.parseInt(teclado.readLine());
				error = false;
			} 
			catch (NumberFormatException nfe) {
				System.out.println("El dato introducido no tiene formato de número entero.");
				error = true;
			}
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			} 
		}
		return numeroEntero;
	}
	
	// Solicita la introduccion de un numero entero 'long' por teclado:
	public static long solicitarLong(String mensaje) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		long numeroLong = 0;
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				numeroLong = Long.parseLong(teclado.readLine());
				error = false;
			} 
			catch (NumberFormatException nfe) {
				System.out.println("El dato introducido no tiene formato de número entero.");
				error = true;
			}
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			} 
		}
		return numeroLong;
	}
	
	// Solicita la introducción de un número entero 'int' positivo por teclado:
	// - Visualiza en la consola un mensaje informativo para el usuario.
	// - Devuelve el número entero positivo leído desde el teclado.
	public static int solicitarEnteroPositivo(String mensaje) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		int numeroEnteroPositivo = 0;
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				numeroEnteroPositivo = Integer.parseInt(teclado.readLine());
				while (numeroEnteroPositivo <= 0) {
					System.out.println("El dato introducido debe ser mayor que cero.");
					System.out.print(mensaje);
					numeroEnteroPositivo = Integer.parseInt(teclado.readLine());
				}
				error = false;
			} 
			catch (NumberFormatException nfe) {
				System.out.println("El dato introducido no tiene formato de número entero.");
				error = true;
			}
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			} 
		}
		return numeroEnteroPositivo;
	}
	
	// Solicita la introducción de un número real 'float' por teclado:
	// - Visualiza en la consola un mensaje informativo para el usuario.
	// - Devuelve el número real en simple precisión leído desde el teclado.
	public static float solicitarRealEnSimplePrecision(String mensaje) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		float numeroRealEnSimplePrecision = 0.0F;
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				numeroRealEnSimplePrecision = Float.parseFloat(teclado.readLine());
				error = false;
			} 
			catch (NumberFormatException nfe) {
				System.out.println("El dato introducido no tiene formato de número real.");
				error = true;
			}
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			} 
		}
		return numeroRealEnSimplePrecision;
	}
	
	// Solicita la introducción de un número real 'double' por teclado:
	// - Visualiza en la consola un mensaje informativo para el usuario.
	// - Devuelve el número real en doble precisión leído desde el teclado.
	public static double solicitarRealEnDoblePrecision(String mensaje) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		double numeroRealEnDoblePrecision = 0.0D;
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				numeroRealEnDoblePrecision = Double.parseDouble(teclado.readLine());
				error = false;
			} 
			catch (NumberFormatException nfe) {
				System.out.println("El dato introducido no tiene formato de número real.");
				error = true;
			}
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			} 
		}
		return numeroRealEnDoblePrecision;
	}

	// Solicita la introducción de un carácter 'char' por teclado:
	// - Visualiza en la consola un mensaje informativo para el usuario.
	// - Devuelve el carácter leído desde el teclado.
	public static char solicitarCaracter(String mensaje) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		String cadenaConCaracter = "";
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				cadenaConCaracter = teclado.readLine();
				while (cadenaConCaracter.length() != 1) {
					System.out.println("El dato introducido debe ser un único carácter.");
					System.out.print(mensaje);
					cadenaConCaracter = teclado.readLine();
				}
				error = false;
			} 
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			}
		}
		return cadenaConCaracter.charAt(0);
	}
	
	// Solicita la introducción de una cadena de caracteres 'String' por teclado:
	// - Visualiza en la consola un mensaje informativo para el usuario.
	// - Devuelve la cadena de caracteres leída desde el teclado.
	public static String solicitarCadena(String mensaje) {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		String cadenaDeCaracteres = "";
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				cadenaDeCaracteres = teclado.readLine();
				error = false;
			} 
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			}
		}
		return cadenaDeCaracteres;
	}
	
	public static String solicitarPassword(String mensaje) throws CaracterNoValidoException, LongitudNoValidaException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		String password = "";
		boolean error = true;
		while (error) {
			try {
				System.out.print(mensaje);
				password = teclado.readLine();
				String patronPassword = "[a-zA-Z0-9]+";
				if (password.length()>=4)
					if (password.matches(patronPassword))
						error = false;
					else {
						CaracterNoValidoException cnve = new CaracterNoValidoException();
						throw cnve;
					}
				else {
					LongitudNoValidaException lnve = new LongitudNoValidaException(4);
					throw lnve;
				}	
				/*for (int i=0; i<password.length(); i++) {
					System.out.println(password.charAt(i));
					if (password.charAt(i)<'0' || (password.charAt(i)>'9' && password.charAt(i)<'A') || 
							(password.charAt(i)>'Z' && password.charAt(i)<'a') || password.charAt(i)>'z') {
						CaracterNoValidoException cnve = new CaracterNoValidoException();
						throw cnve;
					}
				}
				error = false;*/
			} 
			catch (LongitudNoValidaException lnve) {
				System.out.println(lnve.getMessage());
				error = true;
			}
			catch (CaracterNoValidoException cnve) {
				System.out.println(cnve.getMessage());
				error = true;
			}
			catch (IOException ioe) {
				System.out.println("Se ha producido un error de entrada/salida.");
				error = true;
			}
		}
		return password;
	}

}
