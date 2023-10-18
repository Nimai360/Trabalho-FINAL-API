package trabalho.serratec.api.Trabalho.de.API.DTO;

import java.util.Date;
import java.util.List;

import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public class UserDTO {
	
	private String nome;

	private String sobrenome;

	private String email;

	private List<RelacionamentoModel> relacionamento;

	private Date dataNascimento;

	private List<PostModel> postagens;
	
	public UserDTO() {}
	
	public UserDTO(UserModel usuario) {
//		this.nome = usuario.getNome();
//		this.sobrenome = usuario.getSobrenome();
//		this.email = usuario.getEmail();
//		this.dataNascimento = usuario.getDataNascimento();
//		this.postagens = new HashSet<>();
//		for (PostModel postagem :  usuario.getUsuarioPerfis()) {
//			this.perfis.add(usuarioPerfil.getId().getPerfil());
//		}
	}

}
