package trabalho.serratec.api.Trabalho.de.API.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trabalho.serratec.api.Trabalho.de.API.DTO.Post_UserDTO;
import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.service.RelacionamentoService;

@RestController
@RequestMapping("/")
public class RelacionamentoController {
	
	@Autowired
	RelacionamentoService relacionamentoService;
	
	@GetMapping("/following/{id}")
	public ResponseEntity<List<Post_UserDTO>> listarSeguindoId(@PathVariable Long id) throws Exception {
		List<Post_UserDTO> usuariosList = relacionamentoService.buscarSeguindoID(id);
		if (usuariosList == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuariosList);
	}
	
	@GetMapping("/followers/{id}")
	public ResponseEntity<List<Post_UserDTO>> listarSeguidoId(@PathVariable Long id) throws Exception {
		List<Post_UserDTO> usuariosList = relacionamentoService.buscarSeguidoPorID(id);
		System.out.println(usuariosList == null);
		if (usuariosList == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuariosList);
	}
	
	@PostMapping("/follow/{id}")
	public ResponseEntity<RelacionamentoModel> inserir(@Valid @PathVariable Long id) throws Exception {
		RelacionamentoModel rel = relacionamentoService.inserir(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(rel);
	}
	
	@DeleteMapping("/unfollow/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) throws Exception {
		return relacionamentoService.remover(id);
	}
}
