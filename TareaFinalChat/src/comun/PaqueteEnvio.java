package comun;

import java.io.Serializable;

public class PaqueteEnvio implements Serializable {
	String nick;
	String ip;
	String mensaje;
	
	public PaqueteEnvio(String nick, String ip) {
		super();
		this.nick = nick;
		this.ip = ip;
		this.mensaje = null;
	}
	
	public PaqueteEnvio(String nick, String ip, String mensaje) {
		super();
		this.nick = nick;
		this.ip = ip;
		this.mensaje = mensaje;
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

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "PaqueteEnvio [nick=" + nick + ", ip=" + ip + ", mensaje=" + mensaje + "]";
	}
	
}
