package trabalho.serratec.api.Trabalho.de.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalho.serratec.api.Trabalho.de.API.model.PostModel;

public interface PostRepository extends JpaRepository<PostModel, Long>  {

}
