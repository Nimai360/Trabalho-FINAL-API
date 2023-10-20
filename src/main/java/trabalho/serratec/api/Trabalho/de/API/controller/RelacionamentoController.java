package trabalho.serratec.api.Trabalho.de.API.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trabalho.serratec.api.Trabalho.de.API.DTO.UserDTO;
import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.service.RelacionamentoService;

@RestController
@RequestMapping("/relacionamento")
public class RelacionamentoController {
	
	@Autowired
	RelacionamentoService relacionamentoService;
	
//	@GetMapping
//	public ResponseEntity<List<UserDTO>> listar() throws StreamReadException, DatabindException, IOException {
//		List<UserDTO> usuarios = userService.listar();
//		return ResponseEntity.ok(usuarios);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<UserDTO> listar(@PathVariable Long id) {
//		UserDTO usuario = userService.buscar(id);
//		if (usuario == null) {
//			ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(usuario);
//	}
	
	@PostMapping("/{id}")
	public ResponseEntity<RelacionamentoModel> inserir(@Valid @PathVariable Long id) throws Exception {
		RelacionamentoModel rel = relacionamentoService.inserir(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(rel);
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity update(@Valid @RequestBody UserModel usuario, @PathVariable Long id) {
//		return userService.atualizar(usuario, id);
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> remover(@PathVariable Long id) {
//		return userService.remover(id);
//	}
}
