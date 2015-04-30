package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
@NamedQueries({
	@NamedQuery(name="Perfil.lista", query="Select perfil from Perfil perfil"),
	@NamedQuery(name="Perfil.codigo", query="Select perfil from Perfil perfil where perfil.codigo = :codigo"),
	@NamedQuery(name="Perfil.nome", query="Select perfil from Perfil perfil where perfil.nome = :nome"),
	@NamedQuery(name="Perfil.status", query="Select perfil from Perfil perfil where perfil.status = :status")
})
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	
	@Column(name="nome")
	@NotEmpty(message="Campo nome obrigatório")
	private String nome;
	
	@Column(name="status")
	@NotEmpty(message="Campo status obrigatório")
	private String status;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
