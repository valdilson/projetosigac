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
	@NamedQuery(name = "Unidade.codigo", query = "Select unidade from Unidade unidade where unidade.codigo = :codigo"),
	@NamedQuery(name = "Unidade.nome", query = "Select unidade from Unidade unidade where unidade.nome = :nome"),
	
})
public class Unidade implements Serializable {

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
