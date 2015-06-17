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
@Table(name = "Periodo", uniqueConstraints = { @UniqueConstraint(columnNames = { "nome" }) })
@NamedQueries({
		@NamedQuery(name = Constante.NamedQueries.PERIODO_LISTA, query = "Select periodo from Periodo periodo"),
		@NamedQuery(name = Constante.NamedQueries.PERIODO_CODIGO, query = "Select periodo from Periodo periodo where periodo.codigo = :codigo"),
		@NamedQuery(name = Constante.NamedQueries.PERIODO_NOME, query = "Select periodo from Periodo periodo where periodo.nome like :nome"),
		@NamedQuery(name = Constante.NamedQueries.PERIODO_STATUS, query = "Select periodo from Periodo periodo where periodo.status = :status") })
public class Periodo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "nome", length = 100, nullable = false)
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
		this.nome = nome.toUpperCase();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Periodo other = (Periodo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
