package teclado;

import java.util.Random;

public class Aleatorios {

	static Random random = new Random();
	
	private String[] titulo = {"Poema de Gilgamesh", "Todo se desmorona", "Cuentos infantiles", "Divina comedia", "Orgullo y prejuicio", "Papá Goriot", "Molloy", "Decamerón", "Ficciones", "Cumbres Borrascosas", 
			"El extranjero", "Poemas", "Viaje al fin de la noche", "Don Quijote de la Mancha", "Los cuentos de Canterbury", "Relatos cortos", "Nostromo", "Grandes Esperanzas", "Jacques el fatalista", "Berlin Alexanderplatz", 
			"Crimen y castigo", "Middlemarch", "El hombre invisible", "Medea", "¡Absalom, Absalom!", "Madame Bovary", "Romancero gitano", "Cien años de soledad", "Fausto", "Almas muertas",
			"El tambor de hojalata", "Gran Sertón: Veredas", "Hambre", "El viejo y el mar", "Ilíada", "Casa de muñecas", "Ulises", "El proceso", "Shakuntala", "El sonido de la montaña",
			"Zorba, el griego", "Hijos y amantes", "Gente independiente", "El cuaderno dorado", "Pippi Calzaslargas", "Diario de un loco", "Hijos de nuestro barrio", "Los Buddenbrook", "Moby-Dick", "El Señor de los Anillos"};
			
	private String[] autor = {"Anónimo", "Chinua Achebe", "Hans Christian Andersen", "Dante Alighieri", "Jane Austen", "Honoré de Balzac", "Samuel Beckett Nobel", "Giovanni Boccaccio", "Jorge Luis Borges", "Emily Brontë",
			"Albert Camus", "Paul Celan", "Louis-Ferdinand Céline", "Miguel de Cervantes", "Geoffrey Chaucer", "Antón Chéjov", "Joseph Conrad", "Charles Dickens", "Denis Diderot", "Alfred Döblin",
			"Fiódor Dostoievski", "George Eliot", "Ralph Ellison", "Eurípides", "William Faulkner", "Gustave Flaubert", "Federico García Lorca", "Gabriel García Márquez", "Johann Wolfgang von Goethe", "Nikolai Gogol",
			"Günter Grass", "João Guimarães Rosa", "Knut Hamsun", "Ernest Hemingway", "Homero", "Henrik Ibsen", "James Joyce", "Franz Kafka", "Kālidāsa", "Yasunari Kawabata",
			"Nikos Kazantzakis", "D. H. Lawrence", "Halldór Laxness", "Doris Lessing", "Astrid Lindgren", "Lu Xun", "Naguib Mahfuz", "Thomas Mann", "Herman Melville", "J. R. R. Tolkien"};
	
	private String[] nombre = {"Alex", "Yasser", "Andrea", "David", "Iván", "Paloma", "Julio", "Inés", "Emma", "Pilar",
			"Marta", "María", "Antonio", "Estaban", "Daniel", "Jorge", "Iulen", "Raquel", "Tomás", "Matias",
			"Guillermo", "Miguel", "Will", "César", "Marcos", "Verónica", "Frodo", "Gandalf", "Aragorn", "Luigi",
			"Richard", "Michael", "Matteo", "Laura", "Yuna", "Auron", "Titus", "Cloud", "Ramón", "Juan",
			"Bilbo", "Axl", "Steven", "Mario", "Leonardo", "Cloud", "Lionel", "Diego", "Mikel", "Miguel Ángel"};
	
	private String[] apellido = {"Jaso", "Bolsón", "García", "Lobera", "Marquez", "Picasso", "Perez", "Smith", "Pedroche", "Arcas",
			"Millán", "Amati", "Echávarri", "Prats", "Tomás", "Casco", "Rivas", "Castillo", "Huszak", "Siles",
			"Atiencia", "Pedraza", "Fiordean", "Bargas", "Llosa", "Romero", "Rodríguez", "Torres", "Ronaldo", "Messi",
			"Flores", "Rose", "Slash", "Scott", "Oliver", "Cameron", "Sánchez", "Díaz", "Ayuso", "Tyler",
			"Laborda", "Jobs", "Gates", "César", "Jerry", "Vital", "Serra", "Strife", "López", "Ruíz"};
	
	private int numero;
	
	public String obtenerTitulo() {
		return titulo[random.nextInt(titulo.length)];
	}
	
	public String obtenerAutor() {
		return autor[random.nextInt(autor.length)];
	}
	
	public String obtenerNombre() {
		return nombre[random.nextInt(nombre.length)];
	}
	
	public String obtenerApellido() {
		return apellido[random.nextInt(apellido.length)];
	}
	
	public String obtenerNombreCompleto() {
		return nombre[random.nextInt(nombre.length)] + " " + apellido[random.nextInt(apellido.length)];
	}
	public int obtenerNumeroEntero(int numero) {
		return random.nextInt(numero + 1);
	}
	
	public int obtenerNumeroEnteroPositivo(int numero) {
		return random.nextInt(numero) + 1;
	}
	
}
