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

import trabalho.serratec.api.Trabalho.de.API.DTO.CommentDTO;
import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.repository.CommentRepository;
import trabalho.serratec.api.Trabalho.de.API.util.Utils;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;

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

	public CommentModel inserir(MultipartFile file, CommentModel comment) throws IOException {
		comment = commentRepository.save(comment);
		return comment;
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
