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

import trabalho.serratec.api.Trabalho.de.API.DTO.PostDTO;
import trabalho.serratec.api.Trabalho.de.API.model.PostModel;
import trabalho.serratec.api.Trabalho.de.API.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping
	public ResponseEntity<List<PostDTO>> listar() {
		List<PostDTO> post = postService.listar();
		return ResponseEntity.ok(post);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> listar(@PathVariable Long id) {
		PostDTO post = postService.buscar(id);
		if (post == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(post);
	}
	
	@PostMapping
	public ResponseEntity<PostModel> inserir(@RequestBody PostModel post) throws IOException {
		post = postService.inserir(null, post);
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody PostModel post, @PathVariable Long id) {
		return postService.atualizar(post, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		return postService.remover(id);
	}
}
