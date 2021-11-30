
public class Cliente {

	private String nombre;
	private Joya joya;
	private boolean siendoAtendido;  //se establece a "true" en repartir() por el Repartidor cuando establece el Cliente al Vendedor
	private boolean atendido;  //establecido por el Thread Vendedor a "true" en run() cuando se suma la cantidad de la joya comprada
	
	public Cliente (String nombre, Joya joya) {
		this.nombre = nombre;
		this.joya = joya;
		this.siendoAtendido = false;
		this.atendido = false;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Joya getJoya() {
		return joya;
	}

	public void setJoya(Joya joya) {
		this.joya = joya;
	}
	
	public boolean isSiendoAtendido() {
		return siendoAtendido;
	}

	public void setSiendoAtendido(boolean siendoAtendido) {
		this.siendoAtendido = siendoAtendido;
	}

	public boolean isAtendido() {
		return atendido;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}
	
}
