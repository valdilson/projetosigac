package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@NamedQueries({
	@NamedQuery(name=Constante.NamedQueries.ALUNO_RECUPERARPORLOGIN, query="Select aluno from Aluno aluno where aluno.username = :username")
})
public class Aluno implements Serializable, IPessoa {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	private String nome;
	private String ra;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	private Unidade unidade;
	private String email;
	private Integer horasExigidas;
	private String username;
    private String password;
	
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
	
	
	
}
