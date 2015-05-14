package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@NamedQueries({
	@NamedQuery(name  = Constante.NamedQueries.ALUNO_RECUPERA_LISTA, query = "Select aluno from Aluno aluno"),
	@NamedQuery(name=Constante.NamedQueries.ALUNO_RECUPERARPORLOGIN, query="Select aluno from Aluno aluno where aluno.username = :username"),
	@NamedQuery(name=Constante.NamedQueries.ALUNO_RECUPERA_CODIGO, query="Select aluno from Aluno aluno where aluno.codigo = :codigo")
})
public class Aluno implements Serializable, IPessoa {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	private String nome;
	private String ra;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_unidade", referencedColumnName = "codigo")
	private Unidade unidade;
	private String email;
	private Integer horasExigidas;
	private String username;
    private String password;
    
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_turma", referencedColumnName = "codigo")
    private Turma turma;
    
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_turno", referencedColumnName = "codigo")
    private Turno turno;
    
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_curso", referencedColumnName = "codigo")
    private Curso curso;
    
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_periodo", referencedColumnName = "codigo")
    private Periodo periodo;
	
	@Transient
	private Integer horasRealizadas;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="aluno")
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
		this.email = email;
	}

	public Integer getHorasExigidas() {
		return horasExigidas;
	}

	public void setHorasExigidas(Integer horasExigidas) {
		this.horasExigidas = horasExigidas;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((atividadesRealizadas == null) ? 0 : atividadesRealizadas
						.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((horasExigidas == null) ? 0 : horasExigidas.hashCode());
		result = prime * result
				+ ((horasRealizadas == null) ? 0 : horasRealizadas.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		result = prime * result + ((ra == null) ? 0 : ra.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		if (atividadesRealizadas == null) {
			if (other.atividadesRealizadas != null)
				return false;
		} else if (!atividadesRealizadas.equals(other.atividadesRealizadas))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (horasExigidas == null) {
			if (other.horasExigidas != null)
				return false;
		} else if (!horasExigidas.equals(other.horasExigidas))
			return false;
		if (horasRealizadas == null) {
			if (other.horasRealizadas != null)
				return false;
		} else if (!horasRealizadas.equals(other.horasRealizadas))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		if (ra == null) {
			if (other.ra != null)
				return false;
		} else if (!ra.equals(other.ra))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [codigo=" + codigo + ", nome=" + nome + ", ra=" + ra
				+ ", unidade=" + unidade.getNome() + ", email=" + email
				+ ", horasExigidas=" + horasExigidas + ", username=" + username
				+ ", password=" + password + ", turma=" + turma.getNome() + ", turno="
				+ turno.getNome() + ", curso=" + curso.getNome() + ", periodo=" + periodo.getNome()
				+ ", horasRealizadas=" + horasRealizadas
				+ ", atividadesRealizadas=" + atividadesRealizadas + "]";
	}
}
