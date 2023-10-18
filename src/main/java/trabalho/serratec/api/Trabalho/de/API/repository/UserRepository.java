package trabalho.serratec.api.Trabalho.de.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalho.serratec.api.Trabalho.de.API.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}
