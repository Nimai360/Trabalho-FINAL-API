package trabalho.serratec.api.Trabalho.de.API.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import trabalho.serratec.api.Trabalho.de.API.DTO.Post_UserDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.UserDTO;
import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.model.UsuarioRelacionamentoPK;
import trabalho.serratec.api.Trabalho.de.API.repository.RelacionamentoRepository;
import trabalho.serratec.api.Trabalho.de.API.repository.UserRepository;
import trabalho.serratec.api.Trabalho.de.API.util.Utils;

@Service
public class RelacionamentoService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RelacionamentoRepository relacionamentoRepository;

	public List<Post_UserDTO> buscarSeguindoID(Long id_usuario) throws Exception {
		Optional<UserModel> usuario_seguidoOpt = userRepository.findById(id_usuario);
		if (!usuario_seguidoOpt.isPresent()) {
			throw new Exception("Usuário não encontrado");
		}
		List<RelacionamentoModel> usuariosList = relacionamentoRepository.findBySeguindoID(id_usuario);
		if (usuariosList.isEmpty()) {
			return null;
		}
		List<Post_UserDTO> usersList = new ArrayList<>();
		for (RelacionamentoModel relacionamentoModel : usuariosList) {
			usersList.add(new Post_UserDTO(relacionamentoModel.getId().getSeguidor()));
		}
		return usersList; // Seguidor segue o seguindo
	}

	public List<Post_UserDTO> buscarSeguidoPorID(Long id_usuario) throws Exception {
		Optional<UserModel> usuario_seguidoOpt = userRepository.findById(id_usuario);
		if (!usuario_seguidoOpt.isPresent()) {
			throw new Exception("Usuário não encontrado");
		}
		List<RelacionamentoModel> usuariosList = relacionamentoRepository.findBySeguidoPorID(id_usuario);
		if (usuariosList.isEmpty()) {
			return null;
		}
		List<Post_UserDTO> usersList = new ArrayList<>();
		for (RelacionamentoModel relacionamentoModel : usuariosList) {
			usersList.add(new Post_UserDTO(relacionamentoModel.getId().getSeguindo()));
		}
		return usersList; // Seguidor segue o seguindo
	}

	public RelacionamentoModel inserir(Long id_seguindo) throws Exception {
		Optional<UserModel> usuario_seguidoOpt = userRepository.findById(id_seguindo);
		if (usuario_seguidoOpt.isPresent()) {
			Optional<UserModel> usuario_logadoOpt = Optional
					.ofNullable(userRepository.findByEmail(Utils.getUsernameUsuarioLogado()));

			UsuarioRelacionamentoPK pk = new UsuarioRelacionamentoPK(usuario_logadoOpt.get(), usuario_seguidoOpt.get());
			Optional<RelacionamentoModel> relOpt = relacionamentoRepository.findById(pk);

//			Optional<RelacionamentoModel> relOpt = relacionamentoRepository.buscarRelacionamento(usuario_logadoOpt.get().getId(), usuario_seguidoOpt.get().getId());

			if (relOpt.isPresent()) {
				throw new Exception("Você já está seguindo este usuario");
			}

			RelacionamentoModel rm = new RelacionamentoModel(usuario_seguidoOpt.get(), usuario_logadoOpt.get());

			rm = relacionamentoRepository.save(rm);
			return rm;
		}
		throw new Exception("Usuário não encontrado");
	}

	public Boolean isMyFollowing(Long id_seguindo) throws Exception {
		Optional<UserModel> usuario_seguidoOpt = userRepository.findById(id_seguindo);
		if (usuario_seguidoOpt.isPresent()) {
			Optional<UserModel> usuario_logadoOpt = Optional
					.ofNullable(userRepository.findByEmail(Utils.getUsernameUsuarioLogado()));

			UsuarioRelacionamentoPK pk = new UsuarioRelacionamentoPK(usuario_logadoOpt.get(), usuario_seguidoOpt.get());
			Optional<RelacionamentoModel> relOpt = relacionamentoRepository.findById(pk);

			if (relOpt.isPresent()) {
				return true;
//				throw new Exception("Você já está seguindo este usuario");
			}
		}
		return false;
	}

	public ResponseEntity<Void> remover(Long id_seguindo) throws Exception {
		Optional<UserModel> usuario_seguidoOpt = userRepository.findById(id_seguindo);
		if (usuario_seguidoOpt.isPresent()) {
			Optional<UserModel> usuario_logadoOpt = Optional
					.ofNullable(userRepository.findByEmail(Utils.getUsernameUsuarioLogado()));

			UsuarioRelacionamentoPK pk = new UsuarioRelacionamentoPK(usuario_logadoOpt.get(), usuario_seguidoOpt.get());
			Optional<RelacionamentoModel> relOpt = relacionamentoRepository.findById(pk);

			if (!relOpt.isPresent()) {
				throw new Exception("Você ainda não segue este usuário");
			}

			RelacionamentoModel rm = new RelacionamentoModel(usuario_seguidoOpt.get(), usuario_logadoOpt.get());

			relacionamentoRepository.delete(rm);

			return ResponseEntity.noContent().build();
		}
		throw new Exception("Usuário não encontrado");
	}
}
