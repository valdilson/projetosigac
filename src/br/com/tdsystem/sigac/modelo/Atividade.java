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
@Table(name = "Atividade")
@NamedQueries({
	@NamedQuery(name="Atividade.lista", query="Select atividade from Atividade atividade"),
	@NamedQuery(name="Atividade.codigo", query="Select atividade from Atividade atividade where atividade.codigo = :codigo"),
	@NamedQuery(name="Atividade.nome", query="Select atividade from Atividade atividade where atividade.nome = :nome"),
	@NamedQuery(name="Atividade.status", query="Select atividade from Atividade atividade where atividade.status = :status")
})
public class Atividade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	@Column(name = "nome")
	@NotEmpty(message = "Campo nome obrigatório!")
	private String nome;
	
	@Column(name = "descricao")
	@NotEmpty(message = "Campo descricao obrigatório!")
	private String descricao;
	
	@Column(name = "status")
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
