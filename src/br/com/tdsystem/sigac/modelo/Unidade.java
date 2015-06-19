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
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;

import br.com.tdsystem.sigac.util.Constante;

@Entity
@Table(name="Unidade", uniqueConstraints = { @UniqueConstraint(columnNames = { "nome" }) })
@NamedQueries({
	@NamedQuery(name = Constante.NamedQueries.UNIDADE_LISTA, query = "Select unidade from Unidade unidade"),
	@NamedQuery(name = Constante.NamedQueries.UNIDADE_CODIGO, query = "Select unidade from Unidade unidade where unidade.codigo = :codigo"),
	@NamedQuery(name = Constante.NamedQueries.UNIDADE_NOME, query = "Select unidade from Unidade unidade where unidade.nome = :nome")
})
public class Unidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@Column(nullable = false, name="nome", length = 150)
	private String nome;
	
	@Column(nullable = false, name="endereco", length = 200)
	private String endereco;
	
	@Column(nullable = false, name="bairro", length = 100)
	private String bairro;
	
	@Column(nullable = false, name="cidade", length = 100)
	private String cidade;
	
	@Column(nullable = false, name="estado")
	private String estado;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="telefone")
	private String telefone;
	
	@Email
	@Column(name="email", length = 80)
	private String email;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		
		this.endereco = endereco.toUpperCase();
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade.toUpperCase();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado.toUpperCase();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
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
		Unidade other = (Unidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
