package trabalho.serratec.api.Trabalho.de.API.DTO;

import trabalho.serratec.api.Trabalho.de.API.model.PostModel;

public class User_PostDTO {
	
	private Long id;
	
	private String conteudo;
	
	public User_PostDTO() {}
	
	public User_PostDTO(PostModel post) {
		this.id = post.getId();
		this.conteudo = post.getConteudo();
	}

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
