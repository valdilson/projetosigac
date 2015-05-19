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
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@Table(name="Aluno")
@NamedQueries({
	@NamedQuery(name  = Constante.NamedQueries.ALUNO_RECUPERA_LISTA, query = "Select aluno from Aluno aluno"),
	@NamedQuery(name = Constante.NamedQueries.ALUNO_RECUPERARPORLOGIN, query = "Select aluno from Aluno aluno where aluno.username = :username"),
	@NamedQuery(name = "Aluno.codigo", query = "Select aluno from Aluno aluno where aluno.codigo = :codigo")
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
	private Integer horasExigidas = 100;
	private String username;
    private String password;
    
    @Transient
    private String confirmaPassword;
    
    public String getConfirmaPassword() {
		return confirmaPassword;
	}

	public void setConfirmaPassword(String confirmaPassword) {
		this.confirmaPassword = confirmaPassword;
	}

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
	public String toString() {
		return "Aluno [codigo=" + codigo + ", nome=" + nome + ", ra=" + ra
				+ ", unidade=" + unidade + ", email=" + email
				+ ", horasExigidas=" + horasExigidas + ", username=" + username
				+ ", password=" + password + ", turma=" + turma + ", turno="
				+ turno + ", curso=" + curso + ", periodo=" + periodo
				+ ", horasRealizadas=" + horasRealizadas
				+ ", atividadesRealizadas=" + atividadesRealizadas + "]";
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
