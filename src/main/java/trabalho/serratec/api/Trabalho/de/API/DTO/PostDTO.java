package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.util.HashSet;
import java.util.Set;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;

public class PostDTO {
	
	private Long id;
	
	private String conteudo;
	
	private Post_UserDTO usuario;
	
	private Set<Post_CommentDTO> comentarios;
	
	public PostDTO() {}
	
	public PostDTO(PostModel post) {
		this.id = post.getId();
		this.conteudo = post.getConteudo();
		this.usuario = new Post_UserDTO(post.getUsuario());
		this.comentarios = new HashSet<>();
		for (CommentModel comentario : post.getComentarios()) {
			this.comentarios.add(new Post_CommentDTO(comentario));
		}
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
	
	
}
