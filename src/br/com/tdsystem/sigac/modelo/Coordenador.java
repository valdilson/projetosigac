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
@Table(name="Coordenador")
@NamedQueries({
	@NamedQuery(name = Constante.NamedQueries.COORDENADOR_RECUPERARPORLOGIN, query="Select coordenador from Coordenador coordenador where coordenador.nome = :nome"),
	@NamedQuery(name = Constante.NamedQueries.COORDENADOR_RECUPERA_LISTA, query="Select coordenador from Coordenador coordenador"),
	@NamedQuery(name = Constante.NamedQueries.COORDENADOR_RECUPERA_CODIGO, query="Select coordenador from Coordenador coordenador where coordenador.codigo = :codigo")
})
public class Coordenador implements Serializable, IPessoa {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	private String nome;
    private String password;
	
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_unidade", referencedColumnName = "codigo")
	private Unidade unidade;
	
	private String email;
	
	@Transient
	private Integer horasRealizadas;
	
	@Transient
	private String confirmaPassword;
	
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

	public String getConfirmaPassword() {
		return confirmaPassword;
	}

	public void setConfirmaPassword(String confirmaPassword) {
		this.confirmaPassword = confirmaPassword;
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
		Coordenador other = (Coordenador) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordenador [codigo=" + codigo + ", nome=" + nome
				+ ", password=" + password
				+ ", unidade=" + unidade + ", email=" + email
				+ ", horasRealizadas=" + horasRealizadas
				+ ", confirmaPassword=" + confirmaPassword
				+ ", atividadesRealizadas=" + atividadesRealizadas + "]";
	}
	
	
	
}
