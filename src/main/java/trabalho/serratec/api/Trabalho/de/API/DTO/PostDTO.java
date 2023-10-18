package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.util.List;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public class PostDTO {
	
	private String conteudo;
	
	private UserModel usuario;
	
	private List<CommentModel> comentarios;
	
	public PostDTO() {}
	
	public PostDTO(PostModel post) {
		this.conteudo = post.getConteudo();
//		this.usuario = post.getUsuario();
//		this.comentarios = post.getComentarios();
//		this.postagens = new HashSet<>();
//		for (PostModel postagem :  usuario.getUsuarioPerfis()) {
//			this.perfis.add(usuarioPerfil.getId().getPerfil());
//		}
	}
}
