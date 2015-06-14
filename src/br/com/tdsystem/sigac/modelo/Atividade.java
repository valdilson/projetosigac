package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


//import javax.validation.constraints.NotNull;
import br.com.tdsystem.sigac.util.Constante;
//import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Atividade")
@NamedQueries({
	@NamedQuery(name = Constante.NamedQueries.ATIVIDADE_LISTA, query = "Select atividade from Atividade atividade"),
	@NamedQuery(name = Constante.NamedQueries.ATIVIDADE_CODIGO, query = "Select atividade from Atividade atividade where atividade.codigo = :codigo"),
	@NamedQuery(name = Constante.NamedQueries.ATIVIDADE_NOME, query = "Select atividade from Atividade atividade where atividade.nome like :nome"),
	@NamedQuery(name = Constante.NamedQueries.ATIVIDADE_STATUS, query = "Select atividade from Atividade atividade where atividade.status = :status") 
})
public class Atividade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	@Column(name = "nome")
	//@NotEmpty(message = "Campo nome obrigatório!")
	private String nome;
	
	@Column(name="horas")
	//@NotNull(message = "Campo horas Obrigatório!")
	private Integer horas;

	@Column(name = "descricao")
	//@NotEmpty(message = "Campo descricao obrigatório!")
	private String descricao;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@Transient
	private Integer quantidadeVezesExec;
	
	
	public Atividade() {
		super();
	}

	public Atividade(String nome, Integer quantidadeVezesExec) {
		super();
		this.nome = nome;
		this.quantidadeVezesExec = quantidadeVezesExec;
	}



	@OneToMany(cascade=CascadeType.ALL, mappedBy="atividade")
	private List<AtividadeRealizada> atividadesRealizadas;

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
	
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	
	@Override
	public String toString() {
		return "Atividade [codigo=" + codigo + ", nome=" + nome + ", horas="
				+ horas + ", descricao=" + descricao + ", status=" + status
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((horas == null) ? 0 : horas.hashCode());
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
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (horas == null) {
			if (other.horas != null)
				return false;
		} else if (!horas.equals(other.horas))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public Integer getHoras() {
		return horas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getQuantidadeVezesExec() {
		return quantidadeVezesExec;
	}

	public void setQuantidadeVezesExec(Integer quantidadeVezesExec) {
		this.quantidadeVezesExec = quantidadeVezesExec;
	}
	
}
