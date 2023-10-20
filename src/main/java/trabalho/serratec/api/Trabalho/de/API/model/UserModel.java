package trabalho.serratec.api.Trabalho.de.API.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import trabalho.serratec.api.Trabalho.de.API.DTO.UserInserirDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.UserUpdateDTO;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@NotBlank(message = "Nome é obrigatorio")
	private String nome;

	@NotEmpty
	@NotBlank
	private String sobrenome;

	@Column(unique = true)
	@NotEmpty
	@NotBlank
	@Email(message = "deve ser um endereço de e-mail bem formado")
	private String email;

	@NotEmpty
	@NotBlank
	private String senha;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.seguindo")
	private List<RelacionamentoModel> relacionamento;

	@Past(message = "A data é inválida")
	private Date dataNascimento;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<PostModel> postagens;

	@CreationTimestamp
	private LocalDateTime createdAt;
	
	public UserModel() {}

	public UserModel(UserInserirDTO novo_usuario) {
		this.nome = novo_usuario.getNome();
		this.sobrenome = novo_usuario.getSobrenome();
		this.email = novo_usuario.getEmail();
		this.senha = novo_usuario.getSenha();
		this.dataNascimento = novo_usuario.getDataNascimento();
	}
	
	public UserModel(UserUpdateDTO update_usuario) {
		this.nome = update_usuario.getNome();
		this.sobrenome = update_usuario.getSobrenome();
		this.email = update_usuario.getEmail();
		this.senha = update_usuario.getSenha();
		this.dataNascimento = update_usuario.getDataNascimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<PostModel> getPostagens() {
		return postagens;
	}

	public void setPostagens(Set<PostModel> postagens) {
		this.postagens = postagens;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) throws Exception {
		System.out.println(LocalDateTime.now());
		LocalDateTime dtNasc = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		if (LocalDateTime.now().isBefore(dtNasc)) {
			throw new Exception("Data de nascimento inválida");
//			throw new ExceptionHandlerController
		}
		this.dataNascimento = dataNascimento;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		return Objects.equals(id, other.id);
	}

}
