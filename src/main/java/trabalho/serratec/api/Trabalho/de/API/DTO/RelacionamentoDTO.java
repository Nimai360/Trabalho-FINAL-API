package trabalho.serratec.api.Trabalho.de.API.DTO;

public class RelacionamentoDTO {
	
	private Long idSeguidor;

	private String nomeSeguidor;

	private Long idSeguido;

	private String nomeSeguido;

	public Long getIdSeguidor() {
		return idSeguidor;
	}

	public void setIdSeguidor(Long idSeguidor) {
		this.idSeguidor = idSeguidor;
	}

	public String getNomeSeguidor() {
		return nomeSeguidor;
	}

	public void setNomeSeguidor(String nomeSeguidor) {
		this.nomeSeguidor = nomeSeguidor;
	}

	public Long getIdSeguido() {
		return idSeguido;
	}

	public void setIdSeguido(Long idSeguido) {
		this.idSeguido = idSeguido;
	}

	public String getNomeSeguido() {
		return nomeSeguido;
	}

	public void setNomeSeguido(String nomeSeguido) {
		this.nomeSeguido = nomeSeguido;
	}

}
