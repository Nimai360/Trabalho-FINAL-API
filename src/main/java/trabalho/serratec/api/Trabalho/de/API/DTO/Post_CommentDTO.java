package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;

public class Post_CommentDTO {
	
	private Long id;
	
	@NotNull(message = "O comentário não pode ser nulo")
	@NotBlank(message = "O comentário não pode ser em branco")
	@NotEmpty(message = "O comentário não pode ser vazio")	
	private String texto;
	
	private Post_UserDTO usuario;
	
	private LocalDateTime data;
	
	public Post_CommentDTO() {}
	
	public Post_CommentDTO(CommentModel comentario) {
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.usuario = new Post_UserDTO(comentario.getUsuario());
		this.data = comentario.getCreatedAt();
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
}
