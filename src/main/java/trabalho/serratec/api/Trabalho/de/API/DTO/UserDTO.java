package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public class UserDTO {

	private Long id;

	private String nome;

	private String sobrenome;

	private String email;

	private List<RelacionamentoModel> relacionamento;

	private Date dataNascimento;

	private Set<User_PostDTO> postagens;

	public UserDTO() {
	}

	public UserDTO(UserModel usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.dataNascimento = usuario.getDataNascimento();
		if (usuario.getPostagens() != null) {
			this.postagens = new HashSet<>();
			for (PostModel postagem : usuario.getPostagens()) {
				this.postagens.add(new User_PostDTO(postagem));
			}
		}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RelacionamentoModel> getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(List<RelacionamentoModel> relacionamento) {
		this.relacionamento = relacionamento;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<User_PostDTO> getPostagens() {
		return postagens;
	}

	public void setPostagens(Set<User_PostDTO> postagens) {
		this.postagens = postagens;
	}
}
