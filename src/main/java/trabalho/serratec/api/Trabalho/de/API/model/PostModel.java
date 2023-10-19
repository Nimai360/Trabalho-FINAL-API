package trabalho.serratec.api.Trabalho.de.API.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "tb_posts")
public class PostModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String conteudo;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	@JsonBackReference
	private UserModel usuario;
	
	@OneToMany(mappedBy="postagem", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<CommentModel> comentarios;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public UserModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UserModel usuario) {
		this.usuario = usuario;
	}

	public Set<CommentModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<CommentModel> comentarios) {
		this.comentarios = comentarios;
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
		PostModel other = (PostModel) obj;
		return Objects.equals(id, other.id);
	}
}
