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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Unidade")
@NamedQueries({
	@NamedQuery(name = "Unidade.lista", query = "Select unidade from Unidade unidade"),
	@NamedQuery(name = "Unidade.buscarCodigo", query = "Select unidade from Unidade unidade where unidade.codigo = :codigo"),
	@NamedQuery(name = "Unidade.nome", query = "Select unidade from Unidade unidade where unidade.nome = :nome")
	
})
public class Unidade implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Unidade [codigo=" + codigo + ", nome=" + nome + ", endereco="
				+ endereco + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + ", cep=" + cep + ", telefone="
				+ telefone + "]";
	}

	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@Column(nullable = false, name="nome")
	@NotEmpty(message = "Campo nome Obrigatório!")
	private String nome;
	
	@Column(nullable = false, name="endereco")
	@NotEmpty(message = "Campo endereco Obrigatório!")
	private String endereco;
	
	@Column(nullable = false, name="bairro")
	@NotEmpty(message = "Campo bairro Obrigatório!")
	private String bairro;
	
	@Column(nullable = false, name="cidade")
	@NotEmpty(message = "Campo cidade Obrigatório!")
	private String cidade;
	
	@Column(nullable = false, name="estado")
	@NotEmpty(message = "Campo estado Obrigatório!")
	private String estado;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="telefone")
	private String telefone;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	

}
