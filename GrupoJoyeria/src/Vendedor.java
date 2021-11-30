import java.util.LinkedList;

/**
 * Clase encargada de procesar la venta de un cliente. Recibe un objeto Contador desde Main para
 * hallar el tiempo total empleado por todos los hilos. 
 * 
 * Recibe la caja registradora desde Main. Llama al método sincronizado sumarVenta() para sumar 
 * el precio de la joya de cada cliente al total de la caja registradora. 
 * @author mikelangelo
 *
 */
public class Vendedor extends Thread {

	private CajaRegistradora caja;
	private LinkedList<Cliente> clientes;
	private Cliente cliente;
	private Contador contador;
	private boolean enEspera;
	
	public Vendedor(String nombre, CajaRegistradora caja, LinkedList<Cliente> clientes, Contador contador) {
		super(nombre);
		this.caja = caja;
		this.clientes = clientes;
		this.contador = contador;
		this.enEspera = true;
	}
	
	/**
	 * El método inicia el Thread Vendedor. El bucle while mantiene al Vendedor "en espera" mientras existan Clientes 
	 * "sin atender" (atendido = false). 
	 * 
	 * Desde aquí se ejecuta la parte mas importante del programa, la llamada al método sincronizado sumarVenta(cantidad)
	 * de la clase caja suministrada. Una vez efectuado el método, el Vendedor se establece "en espera" (enEspera = true). 
	 * 
	 * Cuando todos los clientes han sido atendidos (clientes.size = 0) el Vendedor finaliza.
	 */
	public void run() {
		long inicio = System.currentTimeMillis();
		while (clientes.size() != 0) {
			if (cliente != null) {
				System.out.println(super.getName() + " --> estoy atendiento a " + cliente.getNombre());
				caja.sumarVenta(cliente.getJoya().getPrecio());
				System.out.println(super.getName() + " --> " + cliente.getNombre() + " a comprado " + cliente.getJoya().getNombre() + " --> " + cliente.getJoya().getPrecio());
				cliente.setAtendido(true);
				setCliente(null);
				System.out.println(super.getName() + " --> VENTA FINALIZADA. Me quedo a la espera de un nuevo cliente. ");
				enEspera = true;
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		long fin = System.currentTimeMillis();
		long total = fin - inicio;
		contador.sumarTiempo(total);
		System.out.println(super.getName() + " --> mi trabajo ha finalizado. TIEMPO EMPLEADO: " + total + " ms");
	}

	public CajaRegistradora getCaja() {
		return caja;
	}

	public void setCaja(CajaRegistradora caja) {
		this.caja = caja;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isEnEspera() {
		return enEspera;
	}

	public void setEnEspera(boolean enEspera) {
		this.enEspera = enEspera;
	}
	
	
}
