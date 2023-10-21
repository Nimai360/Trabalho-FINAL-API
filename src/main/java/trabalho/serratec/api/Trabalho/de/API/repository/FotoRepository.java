package trabalho.serratec.api.Trabalho.de.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trabalho.serratec.api.Trabalho.de.API.model.FotoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

@Repository
public interface FotoRepository extends JpaRepository<FotoModel, Long> {

	public Optional<FotoModel> findByUsuario(UserModel userModel);
	
}
