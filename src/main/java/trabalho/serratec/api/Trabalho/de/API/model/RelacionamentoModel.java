package trabalho.serratec.api.Trabalho.de.API.model;

import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "UsuarioRelacionamento")
public class RelacionamentoModel {
	
	@EmbeddedId
	private UsuarioRelacionamentoPK id = new UsuarioRelacionamentoPK();
	
	@CreationTimestamp
	private LocalDateTime dataInicioSeguimento;
	
	public RelacionamentoModel() {
	}

	public RelacionamentoModel(UserModel seguindo, UserModel seguidor) {
		this.id.setSeguindo(seguindo);
		this.id.setSeguidor(seguidor);
	}

	public UsuarioRelacionamentoPK getId() {
		return id;
	}

	public void setId(UsuarioRelacionamentoPK id) {
		this.id = id;
	}

	public LocalDateTime getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDateTime dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}
}
