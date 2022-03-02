package servidor;

import java.util.Objects;

/*
 * Clase utilizada para almacenar los datos de los clientes en el 
 * TreeMap del gestor. Implementa comparable para permitir añadir nuevos
 * nicks al TreeMap
 */
public class DatosCliente implements Comparable<DatosCliente>{
	private String nick;
	private String ip;
	
	public DatosCliente(String nick, String ip) {
		super();
		this.nick = nick;
		this.ip = ip;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return nick + "(" + ip + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatosCliente other = (DatosCliente) obj;
		return Objects.equals(nick, other.nick);
	}


	@Override
	public int compareTo(DatosCliente o) {
		return this.nick.compareTo(o.nick);
	}
	
	

}
