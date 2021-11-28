import java.util.Iterator;
import java.util.LinkedList;

/**
 * La clase principal encargada de repartir entre el número de Vendedores los Clientes disponibles.
 * @author Mikel
 *
 */
public class Repartidor {

	private Vendedor[] vendedores;
	private LinkedList<Cliente> clientes;
	private int enActivo;
	
	public Repartidor(Vendedor[] vendedores, LinkedList<Cliente> clientes) {
		super();
		this.vendedores = vendedores;
		this.clientes = clientes;
		this.enActivo = 0;
	}
	
	/**
	 * Metodo llamado por comprobarRepartidos(). Asigna un cliente con "siendoAtendido = false" 
	 * a un vendedor disponible que recibe del método dameVendedor(). Si el vendedor no ha sido inicia, 
	 * lo inicia con el método start().
	 */
	public void repartir() {
		Vendedor tempVendedor;
		for (Cliente cliente: clientes) {
			if (cliente.isSiendoAtendido() == false) {
				tempVendedor = dameVendedor();
				if (tempVendedor != null) {
					System.out.println("Asignando a " + tempVendedor.getName() + " --> " + cliente.getNombre());
					//las variables siendoAtendido y enEspera de Cliente y Vendedor deben establecerse aquí
					//para evitar que un nuevo ciclo del bucle asocie un Cliente atendido a un Vendedor.
					cliente.setSiendoAtendido(true);
					tempVendedor.setEnEspera(false);
					tempVendedor.setCliente(cliente);
//					if (tempVendedor.isAlive() == false) {
//						tempVendedor.start();
//					}
				}
			}
		}
		iniciar();  //se inician los Vendedores una vez terminado el reparto
	}
	
	/**
	 * el método inicia los Vendedores asignados por el método repartir y que no han sido activados
	 * (enEspera = false y isAlive = false).
	 * Si todos los Vendedores disponibles han sido iniciados, no recorre el bucle.
	 */
	public void iniciar() {
		if (enActivo != vendedores.length) {
			for (int i = 0; i < vendedores.length; i++) {
				if (vendedores[i].isAlive() == false & vendedores[i].isEnEspera() == false) {
					vendedores[i].start();
					enActivo++;
				}
			}
		}
	}
	
	/**
	 * Se encarga de recorrer el array de Vendedores en busca de uno "libre".
	 * Devuelve un objeto de tipo Vendedor que esta enEspera (enEspera = true).
	 * Si no hay ningun Vendedor en espera, devuelve null.
	 * @return
	 */
	public Vendedor dameVendedor() {
		Vendedor tempVendedor = null;
		for (int i = 0; i < this.vendedores.length; i++) {
			if (vendedores[i].isEnEspera() == true) {
				tempVendedor = vendedores[i];
			}
		}
		return tempVendedor;
	}
	
	/**
	 * Se encarga de recorrer la colección de Clientes en busca de clientes "sinAtender" (siendoAtendido = false).
	 * El método se encarga de llamar al método repartir() cuando hay clientes disponibles.
	 * Si el cliente es establecido como "atendido" (atendido = true) por run() del Vendedor, el cliente se elimina
	 * a traves de un Iterator.
	 * @return
	 */
	public boolean comprobarRepartidos () {
		boolean todosRepartidos = false;
		Iterator<Cliente> iterador;
		while (todosRepartidos == false) {
			if (clientes.size() == 0) {
				todosRepartidos = true;
			}
			else {
				repartir();
				iterador = clientes.iterator();
				while (iterador.hasNext()) {
					Cliente tempCliente = iterador.next();
					if (tempCliente.isAtendido() == true) {
						iterador.remove();;
					}
				}
			}
		}
		return todosRepartidos;
	}


}
