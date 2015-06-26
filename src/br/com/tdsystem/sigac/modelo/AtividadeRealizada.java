package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@Table(name = "AtividadeRealizada")
@NamedQueries({
		@NamedQuery(name = Constante.NamedQueries.ATIVIVIDADE_REALIZADA_LISTA, query = "Select atividadeRealizada from AtividadeRealizada atividadeRealizada"),
		@NamedQuery(name = Constante.NamedQueries.ATIVIVIDADE_REALIZADA_LISTA_INDIVIDUAL, 
					query = "Select atividadeRealizada from AtividadeRealizada atividadeRealizada"
							+ " where atividadeRealizada.aluno.codigo = :codigo_aluno"),
		@NamedQuery(name = Constante.NamedQueries.ATIVIVIDADE_REALIZADA_CODIGO, query = "Select atividadeRealizada from AtividadeRealizada atividadeRealizada "
				+ "where atividadeRealizada.codigo = :codigo"), })
public class AtividadeRealizada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_aluno", referencedColumnName = "codigo")
	private Aluno aluno;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_atividade", referencedColumnName = "codigo")
	private Atividade atividade;

	@Column(name = "horas_atividade")
	private Integer horasAtividade;

	@Column(name = "dataEvento")
	private String dataEvento;
	
	@Column(name = "dataUpload")
	private String dataUpload;

	@Lob
	@Column(name = "comprovante", nullable = false, columnDefinition = "mediumblob")
	private byte[] comprovante;
	
	@Column(name = "statusAprovacao")
	@Enumerated(EnumType.ORDINAL)
	private StatusAprovacao statusApovacao;
	
	public String getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(String dataUpload) {
		this.dataUpload = dataUpload;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getHorasAtividade() {
		return horasAtividade;
	}

	public void setHorasAtividade(Integer horasAtividade) {
		this.horasAtividade = horasAtividade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

	public byte[] getComprovante() {
		return comprovante;
	}

	public void setComprovante(byte[] comprovante) {
		this.comprovante = comprovante;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	

	public StatusAprovacao getStatusApovacao() {
		return statusApovacao;
	}

	public void setStatusApovacao(StatusAprovacao statusApovacao) {
		this.statusApovacao = statusApovacao;
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
		AtividadeRealizada other = (AtividadeRealizada) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
