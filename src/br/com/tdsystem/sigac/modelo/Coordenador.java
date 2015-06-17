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
@Table(name="Coordenador", uniqueConstraints={@UniqueConstraint(columnNames={"ra"})})
@NamedQueries({
	@NamedQuery(name = Constante.NamedQueries.COORDENADOR_RECUPERARPORLOGIN, query="Select coordenador from Coordenador coordenador where coordenador.nome = :nome"),
	@NamedQuery(name = Constante.NamedQueries.COORDENADOR_RECUPERAR_RA, query="Select coordenador from Coordenador coordenador where coordenador.ra = :ra"),
	@NamedQuery(name = Constante.NamedQueries.COORDENADOR_RECUPERA_LISTA, query="Select coordenador from Coordenador coordenador"),
	@NamedQuery(name = Constante.NamedQueries.COORDENADOR_RECUPERA_CODIGO, query="Select coordenador from Coordenador coordenador where coordenador.codigo = :codigo")
})
public class Coordenador implements Serializable, IPessoa {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "password", nullable = false)
    private String password;
	
	@Column(name = "ra", nullable = false)
    private String ra;
	
    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_unidade", referencedColumnName = "codigo")
	private Unidade unidade;
	
    @Column(name = "email", nullable = false)
	private String email;
    
    @Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
    
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
		this.nome = nome.toString();
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}
	
}
