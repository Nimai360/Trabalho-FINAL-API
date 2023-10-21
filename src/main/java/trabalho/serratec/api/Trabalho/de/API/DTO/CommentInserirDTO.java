package trabalho.serratec.api.Trabalho.de.API.DTO;

import javax.validation.constraints.NotBlank;

public class CommentInserirDTO {
	
	@NotBlank
	private String texto;
	
	private Long postagem_id;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getPostagem_id() {
		return postagem_id;
	}

	public void setPostagem_id(Long postagem_id) {
		this.postagem_id = postagem_id;
	}	
}
