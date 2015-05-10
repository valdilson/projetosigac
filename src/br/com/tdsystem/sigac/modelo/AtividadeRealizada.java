package br.com.tdsystem.sigac.modelo;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AtividadeRealizada {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	private Atividade atividade;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	private Aluno aluno;
	
	private Date dataRealizacao;
	
	private String enderecoComprovante;
	
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Date getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public String getEnderecoComprovante() {
		return enderecoComprovante;
	}
	public void setEnderecoComprovante(String enderecoComprovante) {
		this.enderecoComprovante = enderecoComprovante;
	}
	

}
