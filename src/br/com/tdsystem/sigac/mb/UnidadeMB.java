package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UnidadeMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Unidade unidade;
	private UnidadeDAO unidadeDAO = null;
	private List<Unidade> listaUnidades;
	private List<Unidade> filtroUnidades;
	
	public UnidadeMB() {
		listarUnidades();
	}
	
	public synchronized Unidade getUnidade() {
		if(unidade == null){
			unidade = new Unidade();
		}
		return unidade;
	}
	public synchronized void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public synchronized List<Unidade> getListaUnidades() {
		return listaUnidades;
	}
	public synchronized void setListaUnidades(List<Unidade> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}
	public synchronized List<Unidade> getFiltroUnidades() {
		return filtroUnidades;
	}
	public synchronized void setFiltroUnidades(List<Unidade> filtroUnidades) {
		this.filtroUnidades = filtroUnidades;
	}
	
	
	public void salvar(){
		
		try{
			unidadeDAO = new UnidadeDAO();
			unidadeDAO.salvar(unidade);
			FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");
			
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Erro ao gravar registro!" + e.getMessage());
		}
	}
	
	
	public void excluir(){
		
		try{
			unidadeDAO = new UnidadeDAO();
			unidadeDAO.exluir(unidade);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");
			
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!" + e.getMessage());
		}
	}
	
	public void editar(){
		
		try{
			unidadeDAO = new UnidadeDAO();
			unidadeDAO.editar(unidade);
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");
			
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Erro ao editar registro!" + e.getMessage());
		}
	}

	public void listarUnidades(){
		
		try{
			unidadeDAO = new UnidadeDAO();
			listaUnidades = unidadeDAO.listarUnidade();
			
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Não retornou registro!" + e.getMessage());
		}
	}
	
	public void pesquisaCodigo(){
		
		try{
			String valorRecebido = FacesUtil.getParametro("codigoUnidade");
			if(valorRecebido != null){
				unidadeDAO = new UnidadeDAO();
				Long codigo = Long.parseLong(valorRecebido);
				unidade = unidadeDAO.pesquisaCodigo(codigo);
			}
			
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Não retornou registro!"+e.getMessage());
		}
	}

}
