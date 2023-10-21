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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import trabalho.serratec.api.Trabalho.de.API.DTO.PostDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.PostInserirDTO;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.repository.PostRepository;
import trabalho.serratec.api.Trabalho.de.API.repository.UserRepository;
import trabalho.serratec.api.Trabalho.de.API.util.Utils;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	public List<PostDTO> listar() {
		List<PostModel> postList = postRepository.findAll();

		List<PostDTO> postDtoList = postList.stream().map(post -> {
			return new PostDTO(post);
		}).collect(Collectors.toList());

		return postDtoList;
	}

	public PostDTO buscar(Long id) {
		Optional<PostModel> postOpt = postRepository.findById(id);
		if (postOpt.isEmpty()) {
			return null;
		}
		return new PostDTO(postOpt.get());
	}

	public PostModel inserir(MultipartFile file, PostInserirDTO postagem) throws IOException {
		String userNameLogado = Utils.getUsernameUsuarioLogado();
		UserModel user = userRepository.findByEmail(userNameLogado);
		PostModel pm = new PostModel(postagem, user);
		
		pm = postRepository.save(pm);
		return pm;
	}

	public ResponseEntity atualizar(@RequestBody PostModel postagem, @PathVariable Long id) {
		var post = postRepository.findById(id).orElse(null);

		if (post == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post não encontrado");
		}

		Utils.copyNonNullProperties(postagem, post);

		var postUpdated = postRepository.save(postagem);
		return ResponseEntity.ok().body(postUpdated);
	}

	public ResponseEntity<Void> remover(Long id) { // 15
		Optional<PostModel> postOpt = postRepository.findById(id);
		if (postOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		postRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
