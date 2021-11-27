
public class Vendedor extends Thread {

	CajaRegistradora caja;
	Cliente[] clientes;
	boolean atendiendo;
	
	public Vendedor(CajaRegistradora caja, Cliente[] clientes) {
		super();
		this.caja = caja;
		this.clientes = clientes;
	}
	
	
}
