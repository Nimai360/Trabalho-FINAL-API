package trabalho.serratec.api.Trabalho.de.API.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import trabalho.serratec.api.Trabalho.de.API.model.UserModel;
import trabalho.serratec.api.Trabalho.de.API.repository.UserRepository;

@Service
public class UsuarioDetalheImpl implements UserDetailsService {

	@Autowired
	private UserRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel usuario = usuarioRepository.findByEmail(username);
		if (usuario == null) {
			return null;			
		}
		return new UsuarioDetalhe(usuario);
	}

}
