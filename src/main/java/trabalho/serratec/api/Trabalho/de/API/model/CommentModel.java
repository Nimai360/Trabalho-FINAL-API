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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "tb_comments")
public class CommentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String texto;
	
	@ManyToOne
	@JoinColumn(name="id_postagem")
	@JsonBackReference
	private PostModel postagem;
	
	@Column
	private Long usuario_id;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}	
	
	

//	public UserModel getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(UserModel usuario) {
//		this.usuario = usuario;
//	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
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
