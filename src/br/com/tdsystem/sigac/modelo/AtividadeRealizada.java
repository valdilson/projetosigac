package br.com.tdsystem.sigac.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@Table(name="AtividadeRealizada")
@NamedQueries({
	@NamedQuery(name = Constante.NamedQueries.ATIVIVIDADE_REALIZADA_LISTA, 
				query = "Select atividadeRealizada from AtividadeRealizada atividadeRealizada"),
	@NamedQuery(name = Constante.NamedQueries.ATIVIVIDADE_REALIZADA_CODIGO, 
				query = "Select atividadeRealizada from AtividadeRealizada atividadeRealizada "
						+ "where atividadeRealizada.codigo = :codigo"),
})

public class AtividadeRealizada {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="codigo_aluno", referencedColumnName = "codigo")
	private Aluno aluno;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="codigo_atividade", referencedColumnName = "codigo")
	private Atividade atividade;
	
	@Column(name="horas_restantes")
	private Integer horasRestantes;
	
	private String dataRealizacao;
	
	private String enderecoComprovante;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Integer getHorasRestantes() {
		return horasRestantes;
	}
	public void setHorasRestantes(Integer horasRestantes) {
		this.horasRestantes = horasRestantes;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public String getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public String getEnderecoComprovante() {
		return enderecoComprovante;
	}
	public void setEnderecoComprovante(String enderecoComprovante) {
		this.enderecoComprovante = enderecoComprovante;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
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
