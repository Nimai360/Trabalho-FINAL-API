package trabalho.serratec.api.Trabalho.de.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import trabalho.serratec.api.Trabalho.de.API.DTO.UserDTO;
import trabalho.serratec.api.Trabalho.de.API.model.FotoModel;
import trabalho.serratec.api.Trabalho.de.API.service.FotoService;
import trabalho.serratec.api.Trabalho.de.API.service.UserService;

@RestController
@RequestMapping("/fotos")
public class FotoController {

	@Autowired
	UserService userService;

	@Autowired
	FotoService fotoService;

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		FotoModel foto = fotoService.buscarPorIdUsuario(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, foto.getTipo());
		headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<UserDTO> inserirFoto(@RequestBody MultipartFile file) throws Exception {
		/*
		 * POSTMAN:
		 * Body:
		 * - Selecionar 'form-data' ao invés de 'raw'
		 * -- Key: "file"        value: (selecionar imagem)          content-type: "image/jpeg"
		 * */
		UserDTO user = userService.inserirFoto(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
}
