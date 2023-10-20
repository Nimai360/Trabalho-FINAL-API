package trabalho.serratec.api.Trabalho.de.API.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioRelacionamentoPK implements Serializable  {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_seguidor")
	private UserModel seguidor; 
	
	@ManyToOne
	@JoinColumn(name = "id_seguindo")
	private UserModel seguindo;
	
	public UsuarioRelacionamentoPK() {}
	
	public UsuarioRelacionamentoPK(UserModel seguidor, UserModel seguindo) {
		this.seguidor = seguidor;
		this.seguindo = seguindo;
	}

	public UserModel getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(UserModel seguidor) {
		this.seguidor = seguidor;
	}

	public UserModel getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(UserModel seguindo) {
		this.seguindo = seguindo;
	} 
	
	@Override
	public int hashCode() {
		return Objects.hash(seguidor, seguindo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRelacionamentoPK other = (UsuarioRelacionamentoPK) obj;
		return Objects.equals(seguidor, other.seguidor) && Objects.equals(seguindo, other.seguindo);
	}
}
