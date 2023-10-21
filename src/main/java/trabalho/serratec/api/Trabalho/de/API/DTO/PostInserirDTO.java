package trabalho.serratec.api.Trabalho.de.API.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostInserirDTO {
	
	private Long id;
	
	@NotNull(message = "O post não pode ser nulo")
	@NotBlank(message = "O post não pode ser em branco")
	@NotEmpty(message = "O post não pode ser vazio")	
	private String conteudo;
	
	public PostInserirDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}	
}
