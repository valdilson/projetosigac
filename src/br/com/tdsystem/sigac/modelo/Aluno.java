package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@Table(name="Aluno", uniqueConstraints={@UniqueConstraint(columnNames={"ra"})})
@NamedQueries({
	@NamedQuery(name  = Constante.NamedQueries.ALUNO_RECUPERA_LISTA, query = "Select aluno from Aluno aluno"),
	@NamedQuery(name = Constante.NamedQueries.ALUNO_RECUPERARPORLOGIN, query = "Select aluno from Aluno aluno where aluno.ra = :ra"),
	@NamedQuery(name = Constante.NamedQueries.ALUNO_RECUPERA_CODIGO, query = "Select aluno from Aluno aluno where aluno.codigo = :codigo"),
	@NamedQuery(name = Constante.NamedQueries.ALUNO_RECUPERA_RA, query = "Select aluno from Aluno aluno where aluno.ra = :ra")
})
public class Aluno implements Serializable, IPessoa {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;
	
	@Column(name = "nome", nullable = false, length = 200)
	private String nome;
	
	@Column(name = "ra", nullable = false)
	private String ra;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_unidade", referencedColumnName = "codigo")
	private Unidade unidade;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password", nullable = false)
    private String password;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
    
    @Transient
    private String confirmaPassword;
    
    @Transient
    private Integer horasFaltantes;

	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_turma", referencedColumnName = "codigo")
    private Turma turma;
    
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_turno", referencedColumnName = "codigo")
    private Turno turno;
    
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_curso", referencedColumnName = "codigo")
    private Curso curso;
    
	@Column(name = "periodo")
	@Enumerated(EnumType.ORDINAL)
    private Periodo periodo;
	
	@Transient
	private Integer horasRealizadas;
	
	@Column(name = "statusAprovacao")
	@Enumerated(EnumType.ORDINAL)
	private StatusAprovacao statusApovacao;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="aluno", fetch=FetchType.EAGER)
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
		this.nome = nome.toUpperCase();
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public Integer getHorasRealizadas() {
		return horasRealizadas;
	}

	public void setHorasRealizadas(Integer horasRealizadas) {
		this.horasRealizadas = horasRealizadas;
	}

	public List<AtividadeRealizada> getAtividadesRealizadas() {
		return atividadesRealizadas;
	}

	public void setAtividadesRealizadas(
			List<AtividadeRealizada> atividadesRealizadas) {
		this.atividadesRealizadas = atividadesRealizadas;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
		public String getConfirmaPassword() {
			return confirmaPassword;
		}

		public void setConfirmaPassword(String confirmaPassword) {
			this.confirmaPassword = confirmaPassword;
		}

		
	public Integer getHorasFaltantes() {
			return horasFaltantes;
		}

		public void setHorasFaltantes(Integer horasFaltantes) {
			this.horasFaltantes = horasFaltantes;
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
		Aluno other = (Aluno) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
