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

	public String getPassword();
	public void setPassword(String password);

	public String getRa();
	public void setRa(String codigo);
	
}
