package trabalho.serratec.api.Trabalho.de.API.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import trabalho.serratec.api.Trabalho.de.API.DTO.CommentDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.CommentInserirDTO;
import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.model.UsuarioRelacionamentoPK;
import trabalho.serratec.api.Trabalho.de.API.repository.CommentRepository;
import trabalho.serratec.api.Trabalho.de.API.repository.PostRepository;
import trabalho.serratec.api.Trabalho.de.API.repository.RelacionamentoRepository;
import trabalho.serratec.api.Trabalho.de.API.repository.UserRepository;
import trabalho.serratec.api.Trabalho.de.API.util.Utils;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	RelacionamentoRepository relacionamentoRepository;

	public List<CommentDTO> listar() {
		List<CommentModel> commentList = commentRepository.findAll();

		List<CommentDTO> commentDtoList = commentList.stream().map(comment -> {
			return new CommentDTO(comment);
		}).collect(Collectors.toList());

		return commentDtoList;
	}

	public CommentDTO buscar(Long id) {
		Optional<CommentModel> commentOpt = commentRepository.findById(id);
		if (commentOpt.isEmpty()) {
			return null;
		}
		return new CommentDTO(commentOpt.get());
	}

	public CommentDTO inserir(CommentDTO comment) throws Exception {
		UserModel userLogado = userRepository.findByEmail(Utils.getUsernameUsuarioLogado());
		Optional<PostModel> pm = postRepository.findById(comment.getPost_id());
		
		// Verifica se postagem existe
		if(!pm.isPresent()) {
			throw new Exception("Postagem não encontrada");
		}
		// Verifica se o usuario informado é o mesmo que está logado
		if(userLogado.equals(pm.get().getUsuario())) {
			CommentModel cm = new CommentModel(comment.getTexto(), pm.get(), userLogado);
			cm = commentRepository.save(cm);
			return new CommentDTO(cm);
		}
		
		// Postagem existe e usuario diferente do logado
		UsuarioRelacionamentoPK pk = new UsuarioRelacionamentoPK(userLogado, pm.get().getUsuario());
		Optional<RelacionamentoModel> relOpt = relacionamentoRepository.findById(pk);
		if(relOpt.isPresent()) {
			CommentModel cm = new CommentModel(comment.getTexto(), pm.get(), userLogado);
			System.out.println("Ou está salvando aqui?");
			cm = commentRepository.save(cm);
			return new CommentDTO(cm);			
		}
		throw new Exception("Comentário só pode ser feito quando seguir este usuário");
	}

	public ResponseEntity atualizar(@RequestBody CommentModel comment, @PathVariable Long id) {
		var comentario = commentRepository.findById(id).orElse(null);

		if (comentario == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comentário não encontrado");
		}

		Utils.copyNonNullProperties(comment, comentario);

		var postUpdated = commentRepository.save(comment);
		return ResponseEntity.ok().body(postUpdated);
	}

	public ResponseEntity<Void> remover(Long id) { // 15
		Optional<CommentModel> commentOpt = commentRepository.findById(id);
		if (commentOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		commentRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
