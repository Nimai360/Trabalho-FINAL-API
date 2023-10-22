package trabalho.serratec.api.Trabalho.de.API.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public class CommentDTO {
	
	private Long id;
	
	private Long post_id;
	
	@NotNull(message = "O comentário não pode ser nulo")
	@NotBlank(message = "O comentário não pode ser em branco")
	@NotEmpty(message = "O comentário não pode ser vazio")	
	private String texto;
	
	/*
	 * id_post
	 * nome
	 * sobrenome
	 * */
	private Post_UserDTO usuario;
	
	public CommentDTO() {}
	
	public CommentDTO(CommentModel comentario) {
		this.post_id = comentario.getPostagem().getId();
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.usuario = new Post_UserDTO(comentario.getUsuario());
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Post_UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(Post_UserDTO usuario) {
		this.usuario = usuario;
	}
	
}
