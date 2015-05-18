package br.com.tdsystem.sigac.modelo;

public interface IPessoa {

	public Long getCodigo();
	public void setCodigo(Long codigo);
	
	public String getNome();
	public void setNome(String nome);
	
	public Unidade getUnidade();
	public void setUnidade(Unidade unidade);
	
	public String getEmail();
	public void setEmail(String email);
	
	public String getUsername();
	public void setUsername(String username);

	public String getPassword();
	public void setPassword(String password);

}