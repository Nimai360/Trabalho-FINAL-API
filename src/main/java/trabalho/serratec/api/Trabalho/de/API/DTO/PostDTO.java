package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;

public class PostDTO {
	
	private Long id;
	
	@NotNull(message = "O post não pode ser nulo")
	@NotBlank(message = "O post não pode ser em branco")
	@NotEmpty(message = "O post não pode ser vazio")	
	private String conteudo;
	
	private Post_UserDTO usuario;
	
	private Set<Post_CommentDTO> comentarios;
	
	private LocalDateTime date;
	
	public PostDTO() {}
	
	public PostDTO(PostModel post) {
		this.id = post.getId();
		this.conteudo = post.getConteudo();
		this.usuario = new Post_UserDTO(post.getUsuario());
		this.comentarios = new HashSet<>();
		for (CommentModel comentario : post.getComentarios()) {
			this.comentarios.add(new Post_CommentDTO(comentario));
		}
		this.date = post.getCreatedAt();
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

	public Post_UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(Post_UserDTO usuario) {
		this.usuario = usuario;
	}

	public Set<Post_CommentDTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Post_CommentDTO> comentarios) {
		this.comentarios = comentarios;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
