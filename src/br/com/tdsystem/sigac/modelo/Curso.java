package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"nome"})})
@NamedQueries({
		@NamedQuery(name = Constante.NamedQueries.CURSO_LISTA, query = "Select curso from Curso curso"),
		@NamedQuery(name = Constante.NamedQueries.CURSO_CODIGO, query = "Select curso from Curso curso where curso.codigo = :codigo"),
		@NamedQuery(name = Constante.NamedQueries.CURSO_NOME, query = "Select curso from Curso curso where curso.nome like :nome"),
		@NamedQuery(name = Constante.NamedQueries.CURSO_STATUS, query = "Select curso from Curso curso where curso.status = :status") })
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	@Column(name = "nome")
	//@NotEmpty(message = "Campo Nome obrigatório")
	private String nome;

	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nome=" + nome + ", status="
				+ status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
}
