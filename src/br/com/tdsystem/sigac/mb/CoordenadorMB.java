package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.CoordenadorDAO;
import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.util.CriptografaSenhaMD5;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CoordenadorMB implements Serializable {

	public CoordenadorMB() {
		coordenador = new Coordenador();
		pesquisaListaCoordenadores();
	}

	private static final long serialVersionUID = 1L;

	private Coordenador coordenador = null;
	private CoordenadorDAO coordenadorDAO = null;
	private UnidadeDAO unidadeDAO = null;
	private PerfilEnum perfil;

	private List<Coordenador> listaDeCoordenadores = null;
	private List<Coordenador> filtroDeCoordenadores = null;
	private List<Unidade> listaDeUnidades = null;

	public void selecionaEdicao(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public void pesquisaListaCoordenadores() {
		try {
			coordenadorDAO = new CoordenadorDAO();
			unidadeDAO = new UnidadeDAO();
			
			listaDeCoordenadores = coordenadorDAO.listarCoordenadores();
			listaDeUnidades = unidadeDAO.listarUnidade();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemAlerta("N�o foi poss�vel acessar o banco"
					+ e.getMessage());
		}

	}
	
	
    public void cancelarEdicao(){
    	coordenador = new Coordenador();
    	
    }

	public void salvar() throws NoSuchAlgorithmException {
		String password = coordenador.getPassword();
		String cpassword = coordenador.getConfirmaPassword();
		try{
		coordenadorDAO = new CoordenadorDAO();
		
		Coordenador other = coordenadorDAO.pesquisaRA(coordenador.getRa());
		
		if(other == null) {
			if (!password.equals(cpassword)) {
				FacesUtil.exibirMensagemSucesso("Senhas não conferem ou vazias!");
			} else {

				String senha = CriptografaSenhaMD5.converteSenhaMD5(coordenador.getPassword());
				coordenador.setPassword(senha);
				coordenadorDAO.salvar(coordenador);
				coordenador = new Coordenador();
				FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");
			}
		} else {
			FacesUtil.exibirMensagemAlerta("RA ja cadastrado no sistema, verifique!");				
		}
	} catch (RuntimeException e) {
		if(e.getMessage().equals("could not execute statement")){
			FacesUtil.exibirMensagemErro("Já existe este RA cadastrado!");
		}else{
			FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
		}
	}

	}

	public void editar() throws NoSuchAlgorithmException {
		try {
			String senha = CriptografaSenhaMD5.converteSenhaMD5(coordenador
					.getPassword());
			if (coordenador.getPassword() != ""
					&& coordenador.getPassword().equals(
							coordenador.getConfirmaPassword())) {

				coordenador.setPassword(senha);
				coordenadorDAO = new CoordenadorDAO();
				coordenadorDAO.editar(coordenador);
				FacesUtil.exibirMensagemSucesso("Edi��o feita com Sucesso!");
				coordenador = new Coordenador();
			} else {
				FacesUtil.exibirMensagemSucesso("Senha n�o confere ou vazia!");
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar Coordenador!"
					+ e.getMessage());
		}
	}
	
	public void excluir(Coordenador coordenador) {

		try {

			coordenadorDAO = new CoordenadorDAO();
			coordenadorDAO.excluir(coordenador);

			listaDeCoordenadores.remove(coordenador);
			FacesUtil.exibirMensagemSucesso("Exclus�o feita com Sucesso!");

		} catch (RuntimeException e) {
			if(e.getMessage().equals("could not execute statement")){
				FacesUtil.exibirMensagemErro("Recurso est� sendo usado em outra tabela,\n"
						+ "verifique!");
			}else{
				FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
			}
		}
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public List<Coordenador> getListaDeCoordenadores() {
		return listaDeCoordenadores;
	}

	public void setListaDeCoordenadores(List<Coordenador> listaDeCoordenadores) {
		this.listaDeCoordenadores = listaDeCoordenadores;
	}

	public List<Coordenador> getFiltroDeCoordenadores() {
		return filtroDeCoordenadores;
	}

	public void setFiltroDeCoordenadores(List<Coordenador> filtroDeCoordenadores) {
		this.filtroDeCoordenadores = filtroDeCoordenadores;
	}

	public List<Unidade> getListaDeUnidades() {
		return listaDeUnidades;
	}

	public void setListaDeUnidades(List<Unidade> listaDeUnidades) {
		this.listaDeUnidades = listaDeUnidades;
	}

}
