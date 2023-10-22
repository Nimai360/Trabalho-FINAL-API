package trabalho.serratec.api.Trabalho.de.API.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import trabalho.serratec.api.Trabalho.de.API.DTO.UserDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.UserInserirDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.UserUpdateDTO;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.repository.UserRepository;
import trabalho.serratec.api.Trabalho.de.API.util.Utils;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private FotoService fotoService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<UserDTO> listar() throws StreamReadException, DatabindException, IOException {
		List<UserModel> usuariosList = userRepository.findAll();

		List<UserDTO> usuariosDtoList = usuariosList.stream().map(user -> {
//			return new UserDTO(user); 
			return adicionaImagemURI(new UserDTO(user));
		}).collect(Collectors.toList());

		return usuariosDtoList;
	}
	
	public UserDTO adicionaImagemURI(UserDTO usuario) {
		URI uri = ServletUriComponentsBuilder.
				fromCurrentContextPath()
				.path("/usuarios/{id}/foto")
				.buildAndExpand(usuario.getId())
				.toUri();
		
		usuario.setUrl(uri.toString());
		return usuario;
	}

	public UserDTO buscar(Long id) {
		Optional<UserModel> usuarioOpt = userRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		return new UserDTO(usuarioOpt.get());
	}

	public UserDTO inserirComFoto(MultipartFile file, UserInserirDTO user) throws Exception {
		if (!user.getSenha().equals(user.getConfirmarSenha())) {
			throw new Exception("Senha e Confirma Senha devem ser iguais");
		}
		UserModel usuarioEmailExistente = userRepository.findByEmail(user.getEmail());
		if (usuarioEmailExistente != null) {
			throw new Exception("Email já cadastrado.");
		}
		user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
		UserModel usuario = new UserModel(user);

		usuario = userRepository.save(usuario);
//		return new UserDTO(usuario);
		if(file == null) {
			return new UserDTO(usuario);
		}
		
		fotoService.inserir(usuario, file);
		return adicionaImagemURI(new UserDTO(usuario));
	}
	
	public UserDTO inserir(UserInserirDTO user) throws Exception {
		if (!user.getSenha().equals(user.getConfirmarSenha())) {
			throw new Exception("Senha e Confirma Senha devem ser iguais");
		}
		UserModel usuarioEmailExistente = userRepository.findByEmail(user.getEmail());
		if (usuarioEmailExistente != null) {
			throw new Exception("Email já cadastrado.");
		}
		user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
		UserModel usuario = new UserModel(user);

		usuario = userRepository.save(usuario);
		return new UserDTO(usuario);
	}
	
	public UserDTO inserirFoto(MultipartFile file) throws Exception {
		UserModel usuario = userRepository.findByEmail(Utils.getUsernameUsuarioLogado());
		fotoService.inserir(usuario, file);
		return adicionaImagemURI(new UserDTO(usuario));
	}

	public ResponseEntity atualizar(UserUpdateDTO usuario, Long id) {
		UserModel user = userRepository.findById(id).orElse(null);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
		}
		System.out.println(user.getDataNascimento());
		Utils.copyNonNullProperties(usuario, user);

		var userUpdated = userRepository.save(user);
		return ResponseEntity.ok().body(new UserDTO(userUpdated));
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
