package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.util.Set;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;

public class PostDTO {
	
	private Long id;
	
	private String conteudo;
	
	private Post_UserDTO usuario;
	
	private Set<CommentModel> comentarios;
	
	public PostDTO() {}
	
	public PostDTO(PostModel post) {
		this.conteudo = post.getConteudo();
		this.usuario = new Post_UserDTO(post.getUsuario());
//		this.comentarios = new HashSet<>();
//		for (CommentModel comentarios :  usuario.getUsuarioPerfis()) {
//			this.perfis.add(usuarioPerfil.getId().getPerfil());
//		}
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

	public Set<CommentModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<CommentModel> comentarios) {
		this.comentarios = comentarios;
	}
	
	
}
