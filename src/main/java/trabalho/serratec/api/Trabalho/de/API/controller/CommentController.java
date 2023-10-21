package trabalho.serratec.api.Trabalho.de.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trabalho.serratec.api.Trabalho.de.API.DTO.CommentDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.CommentInserirDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.PostDTO;
import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.service.CommentService;
import trabalho.serratec.api.Trabalho.de.API.service.PostService;
import trabalho.serratec.api.Trabalho.de.API.service.RelacionamentoService;
import trabalho.serratec.api.Trabalho.de.API.service.UserService;
import trabalho.serratec.api.Trabalho.de.API.util.Utils;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	RelacionamentoService relacionamentoService;

	@Autowired
	PostService postService;

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<CommentDTO>> listar() {
		List<CommentDTO> comment = commentService.listar();
		return ResponseEntity.ok(comment);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommentDTO> listar(@PathVariable Long id) {
		CommentDTO comment = commentService.buscar(id);
		if (comment == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(comment);
	}

//	@PostMapping
//	public ResponseEntity<CommentDTO> inserir(@RequestBody CommentModel comment) throws Exception {
//		// Seguidor comenta em quem é seguido
//		if (relacionamentoService.isMyFollowing(comment.getPostagem().getUsuario().getId())) {
//			PostDTO postDTO = postService.buscar(comment.getPostagem().getId());
//			if (postDTO != null) {
//				comment = commentService.inserir(null, comment);
//				return ResponseEntity.status(HttpStatus.CREATED).body(new CommentDTO(comment));
//			}
//			throw new Exception("Este post não existe");
//		}
//		throw new Exception("Você não pode comentar nesta postagem, pois você não segue este usuário");
//	}
	@PostMapping
	public ResponseEntity<CommentDTO> inserir(@RequestBody CommentInserirDTO comment) throws Exception{
		// Seguidor comenta em quem é seguido
		CommentModel comentario = commentService.inserir(comment);
		return ResponseEntity.status(HttpStatus.CREATED).body(new CommentDTO(comentario));
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody CommentModel comment, @PathVariable Long id) {
		return commentService.atualizar(comment, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		return commentService.remover(id);
	}
}
