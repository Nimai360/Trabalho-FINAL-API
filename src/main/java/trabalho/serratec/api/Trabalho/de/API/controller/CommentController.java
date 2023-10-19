package trabalho.serratec.api.Trabalho.de.API.controller;

import java.io.IOException;
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
import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;
import trabalho.serratec.api.Trabalho.de.API.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
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
	
	@PostMapping
	public ResponseEntity<CommentModel> inserir(@RequestBody CommentModel comment) throws IOException {
		comment = commentService.inserir(null, comment);
		return ResponseEntity.status(HttpStatus.CREATED).body(comment);
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
