import java.util.LinkedList;

public class Reparto {

	Vendedor[] vendedores;
	LinkedList<Cliente> clientes;
	
	public Reparto(Vendedor[] vendedores, LinkedList<Cliente> clientes) {
		super();
		this.vendedores = vendedores;
		this.clientes = clientes;
	}
	
	public void reparto() {
		Vendedor tempVendedor;
		for (Cliente cl: clientes) {
			if (cl.atendido == false) {
				dameVendedor();
			}
		}
	}
	
	public Vendedor dameVendedor() {
		Vendedor tempVendedor = null;
		for (int i = 0; i < this.vendedores.length; i++) {
			if (vendedores[i].atendiendo == false) {
				tempVendedor = vendedores[i];
			}
		}
		return tempVendedor;
	}

}
