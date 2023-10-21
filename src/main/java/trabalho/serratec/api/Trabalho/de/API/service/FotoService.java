package trabalho.serratec.api.Trabalho.de.API.service;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import trabalho.serratec.api.Trabalho.de.API.model.FotoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.repository.FotoRepository;
import trabalho.serratec.api.Trabalho.de.API.repository.UserRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public FotoModel inserir(UserModel usuario, MultipartFile file) throws IOException {
		FotoModel foto = new FotoModel();
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());
		foto.setUsuario(usuario);
		
		foto = fotoRepository.save(foto);
		
		return foto;
	}
	
	@Transactional
	public FotoModel buscarPorIdUsuario(Long id) {
		Optional<UserModel> usuarioOpt = userRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		
		Optional<FotoModel> fotoOpt = fotoRepository.findByUsuario(usuarioOpt.get());
		if (fotoOpt.isEmpty()) {
			return null;
		}
		
		return fotoOpt.get();
	}
	
}
