package trabalho.serratec.api.Trabalho.de.API.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import trabalho.serratec.api.Trabalho.de.API.DTO.UserDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.UserInserirDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.UserUpdateDTO;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.service.UserService;

@RestController
@RequestMapping("/usuarios")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> listar() throws StreamReadException, DatabindException, IOException {
		List<UserDTO> usuarios = userService.listar();
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> listar(@PathVariable Long id) {
		UserDTO usuario = userService.buscar(id);
		if (usuario == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
//	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//	public ResponseEntity<UserDTO> inserir(@RequestPart MultipartFile file, @RequestPart UserInserirDTO usuario) throws Exception {
	@PostMapping
	public ResponseEntity<UserDTO> inserir(@RequestBody UserInserirDTO usuario) throws Exception {
		UserDTO user = userService.inserir(null, usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@Valid @RequestBody UserUpdateDTO usuario, @PathVariable Long id) {
		return userService.atualizar(usuario, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		return userService.remover(id);
	}
}
