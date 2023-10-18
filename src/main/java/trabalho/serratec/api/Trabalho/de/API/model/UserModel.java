package trabalho.serratec.api.Trabalho.de.API.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@NotBlank
	private String nome;
	
	@NotEmpty
	@NotBlank
	private String sobrenome;
	
	@Column(unique = true)
	@NotEmpty
	@NotBlank
	private String email;
	
	@NotEmpty
	@NotBlank
	private String senha;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "relacionamento_perfis",
	joinColumns = @JoinColumn(name = "userId_seguindo"),
	inverseJoinColumns = @JoinColumn(name = "userId_seguidor"))
	private List<RelacionamentoModel> relacionamento;
	
	@NotEmpty
	@NotBlank
	private Date dataNascimento;
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PostModel> postagens;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setDataNascimento(Date dataNascimento) {
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
