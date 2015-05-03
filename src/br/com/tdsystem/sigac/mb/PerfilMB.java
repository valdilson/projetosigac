package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.tdsystem.sigac.dao.PerfilDAO;
import br.com.tdsystem.sigac.modelo.Perfil;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PerfilMB implements Serializable {

	public PerfilMB() {
		listarPerfils();
	}

	private static final long serialVersionUID = 1L;

	private List<Perfil> listaPerfis = null;
	private List<Perfil> filtroPerfis = null;

	private Perfil perfil = null;
	private PerfilDAO perfilDAO = null;

	public List<Perfil> getFiltroPerfis() {
		return filtroPerfis;
	}

	public void setFiltroPerfis(List<Perfil> filtroPerfis) {
		this.filtroPerfis = filtroPerfis;
	}

	public List<Perfil> getListaperfis() {
		return listaPerfis;
	}

	public void setListaperfis(List<Perfil> listaperfis) {
		this.listaPerfis = listaperfis;
	}

	public Perfil getPerfil() {
		if (perfil == null) {
			perfil = new Perfil();
		}
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public void salvar() {

		try {

			perfilDAO = new PerfilDAO();
			perfilDAO.salvar(perfil);
			FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao gravar registro!"
					+ e.getMessage());
		}
	}

	public void excluir(Perfil perfil) {

		try {
			perfilDAO = new PerfilDAO();
			perfilDAO.exluir(perfil);
			listaPerfis.remove(perfil);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}

	public void selecionaEdicao(Perfil perfil) {
		this.perfil = perfil;
	}

	public void editar() {

		try {
			perfilDAO = new PerfilDAO();
			perfilDAO.editar(perfil);
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar registro!"
					+ e.getMessage());
		}
	}

	public void listarPerfils() {

		try {
			perfilDAO = new PerfilDAO();
			listaPerfis = perfilDAO.listaPerfil();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

	public void pesquisaCodigo() {

		try {
			String valorRecebido = FacesUtil.getParametro("codigoperfil");
			if (valorRecebido != null) {
				perfilDAO = new PerfilDAO();
				Long codigo = Long.parseLong(valorRecebido);
				perfil = perfilDAO.pesquisaCodigo(codigo);
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Não retornou registro!"
					+ e.getMessage());
		}
	}

}
