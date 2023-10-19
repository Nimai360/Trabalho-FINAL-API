package trabalho.serratec.api.Trabalho.de.API.DTO;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public class Post_CommentDTO {
	
	private Long id;
	
	private String texto;
	
	private Post_UserDTO usuario;
	
	public Post_CommentDTO() {}
	
	public Post_CommentDTO(CommentModel comentario) {
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