package trabalho.serratec.api.Trabalho.de.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalho.serratec.api.Trabalho.de.API.model.RelacionamentoModel;

public interface UsuarioRelacionamentoRepository extends JpaRepository<RelacionamentoModel, Long>  {

}
