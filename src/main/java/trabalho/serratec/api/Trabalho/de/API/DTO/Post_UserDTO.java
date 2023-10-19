package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public class Post_UserDTO {

	private Long id;
	
	private String nome;

	private String sobrenome;
	
	public Post_UserDTO() {}
	
	public Post_UserDTO(UserModel usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
}
