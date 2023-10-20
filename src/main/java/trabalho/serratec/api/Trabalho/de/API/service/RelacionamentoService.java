package trabalho.serratec.api.Trabalho.de.API.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

	public List<UserDTO> listar() {
		List<UserModel> usuariosList = userRepository.findAll();

		List<UserDTO> usuariosDtoList = usuariosList.stream().map(user -> {
			return new UserDTO(user);
		}).collect(Collectors.toList());

		return usuariosDtoList;
	}

	public UserDTO buscar(Long id) {
		Optional<UserModel> usuarioOpt = userRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		return new UserDTO(usuarioOpt.get());
	}

	public RelacionamentoModel inserir(Long id_seguindo) throws Exception {
		Optional<UserModel> usuario_seguidoOpt = userRepository.findById(id_seguindo);
		if (usuario_seguidoOpt.isPresent()) {
			Optional<UserModel> usuario_logadoOpt = Optional
					.ofNullable(userRepository.findByEmail(Utils.getUsernameUsuarioLogado()));
			
			UsuarioRelacionamentoPK pk = new UsuarioRelacionamentoPK(usuario_logadoOpt.get(), usuario_seguidoOpt.get());
			Optional<RelacionamentoModel> relOpt = relacionamentoRepository.findById(pk);
			
//			Optional<RelacionamentoModel> relOpt = relacionamentoRepository.buscarRelacionamento(usuario_logadoOpt.get().getId(), usuario_seguidoOpt.get().getId());
			
			if(relOpt.isPresent()) {
				throw new Exception("Você já está seguindo este usuario");
			}
			
			RelacionamentoModel rm = new RelacionamentoModel(usuario_seguidoOpt.get(), usuario_logadoOpt.get());

			rm = relacionamentoRepository.save(rm);
			return rm;
		}
		throw new Exception("Usuário não encontrado");
	}

	public ResponseEntity atualizar(UserModel usuario, Long id) {
		var user = userRepository.findById(id).orElse(null);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
		}

		Utils.copyNonNullProperties(usuario, user);

		var userUpdated = userRepository.save(usuario);
		return ResponseEntity.ok().body(userUpdated);
	}

	public ResponseEntity<Void> remover(Long id) { // 15
		Optional<UserModel> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			return null;
		}
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
