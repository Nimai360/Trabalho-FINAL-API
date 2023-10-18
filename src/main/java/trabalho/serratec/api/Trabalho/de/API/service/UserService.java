package trabalho.serratec.api.Trabalho.de.API.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import trabalho.serratec.api.Trabalho.de.API.DTO.UserDTO;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.repository.UserRepository;
import trabalho.serratec.api.Trabalho.de.API.util.Utils;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
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
	
	public UserModel inserir(MultipartFile file, UserModel user) throws IOException {
		user = userRepository.save(user);
		return user;
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto, @PathVariable Long id) {
//		Optional<Produto> produtoOpt = produtoRepository.findById(id);
//		if (produtoOpt.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		/*
//		produto.setId(id);
//		produto = produtoRepository.save(produto);
//		*/
//		
//		Produto produtoBD = produtoOpt.get();
//		produtoBD.setDataCadastro(produto.getDataCadastro());
//		produtoBD.setDescricao(produto.getDescricao());
//		produtoBD.setValor(produto.getValor());
//		
//		produto = produtoRepository.save(produtoBD);
//		return ResponseEntity.ok(produto);
//	}
	
	public ResponseEntity atualizar(@RequestBody UserModel usuario, @PathVariable Long id) {
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
			return ResponseEntity.notFound().build();
		}
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
