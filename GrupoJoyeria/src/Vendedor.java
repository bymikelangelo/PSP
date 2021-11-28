import java.util.LinkedList;

public class Vendedor extends Thread {

	private CajaRegistradora caja;
	private LinkedList<Cliente> clientes;
	private Cliente cliente;
	private boolean enEspera;
	
	public Vendedor(String nombre, CajaRegistradora caja, LinkedList<Cliente> clientes) {
		super(nombre);
		this.caja = caja;
		this.clientes = clientes;
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
		System.out.println(super.getName() + " --> mi trabajo ha finalizado.");
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
