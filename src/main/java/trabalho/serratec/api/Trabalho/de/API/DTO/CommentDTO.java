package trabalho.serratec.api.Trabalho.de.API.DTO;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public class CommentDTO {
	private String texto;
	
	private UserModel usuario;
	
	public CommentDTO() {}
	
	public CommentDTO(CommentModel comentario) {
		this.texto = comentario.getTexto();
//		this.usuario = comentario.getUsuario();
	}
}
