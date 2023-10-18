package trabalho.serratec.api.Trabalho.de.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalho.serratec.api.Trabalho.de.API.model.CommentModel;

public interface CommentRepository extends JpaRepository<CommentModel, Long>  {

}
