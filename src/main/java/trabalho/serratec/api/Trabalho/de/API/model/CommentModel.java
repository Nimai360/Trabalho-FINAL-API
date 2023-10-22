package trabalho.serratec.api.Trabalho.de.API.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import trabalho.serratec.api.Trabalho.de.API.DTO.CommentDTO;
import trabalho.serratec.api.Trabalho.de.API.DTO.CommentInserirDTO;

@Entity(name = "tb_comments")
public class CommentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	private String texto;
	
	@ManyToOne
	@JoinColumn(name="id_postagem")
	@JsonBackReference
	private PostModel postagem;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private UserModel usuario;

	@CreationTimestamp
	private LocalDateTime createdAt;
	
	public CommentModel() {}
	
	public CommentModel(String comment, PostModel post, UserModel user) {
		this.texto = comment;
		this.postagem = post;
		this.usuario = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public UserModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UserModel usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}	

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public PostModel getPostagem() {
		return postagem;
	}

	public void setPostagem(PostModel postagem) {
		this.postagem = postagem;
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
		CommentModel other = (CommentModel) obj;
		return Objects.equals(id, other.id);
	}
}
