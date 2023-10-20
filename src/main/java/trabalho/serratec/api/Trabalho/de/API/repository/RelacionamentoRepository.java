package trabalho.serratec.api.Trabalho.de.API.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;
import trabalho.serratec.api.Trabalho.de.API.model.UsuarioRelacionamentoPK;

public interface RelacionamentoRepository extends JpaRepository<RelacionamentoModel, Long>  {

//	List<RelacionamentoModel> findByIdSeguidor(Long id);
//	List<RelacionamentoModel> findByIdSeguindo(Long id);
	Optional<RelacionamentoModel> findByIdSeguidorAndIdSeguindo(Long IdSeguidor, Long IdSeguindo);
	
	@Query(value="SELECT * FROM usuario_relacionamento WHERE id_seguidor = :seguidor AND id_seguindo = :seguindo", nativeQuery = true)
	Optional<RelacionamentoModel> buscarRelacionamento(Long seguidor, Long seguindo);	
	
	
	@Query(value="SELECT * FROM usuario_relacionamento WHERE id_seguindo = :id", nativeQuery = true)
	List<RelacionamentoModel> findBySeguindoID(Long id);
	
	@Query(value="SELECT * FROM usuario_relacionamento WHERE id_seguidor = :id", nativeQuery = true)
	List<RelacionamentoModel> findBySeguidoPorID(Long id);
	
	Optional<RelacionamentoModel> findById(UsuarioRelacionamentoPK id);
}
