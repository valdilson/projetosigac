package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.util.FacesUtil;
import br.com.tdsystem.sigac.modelo.negocio.Validacao;

@ManagedBean
@ViewScoped
public class UnidadeMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Unidade unidade;
	private UnidadeDAO unidadeDAO = null;
	private List<Unidade> listaUnidades;
	private List<Unidade> filtroUnidades;
	
	public UnidadeMB() {
		unidade = new Unidade();
		listarUnidades();
	}
	
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public List<Unidade> getListaUnidades() {
		return listaUnidades;
	}
	public void setListaUnidades(List<Unidade> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}
	public List<Unidade> getFiltroUnidades() {
		return filtroUnidades;
	}
	public void setFiltroUnidades(List<Unidade> filtroUnidades) {
		this.filtroUnidades = filtroUnidades;
	}
	
	public void cancelarEdicao(){
		unidade = new Unidade();
	}
	
	//Metodo valida campo nome vazio e grava Unidade
	public void salvar(){
		if(Validacao.validaCampoTexto(unidade.getNome())){
			try{
				
				unidadeDAO = new UnidadeDAO();
				unidadeDAO.salvar(unidade);
				FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");
				unidade = new Unidade();
				listarUnidades();
			}catch(RuntimeException e){
				if(e.getMessage().equals("could not execute statement")){
					FacesUtil.exibirMensagemErro("Já existe este nome cadastrado!");
				}else{
					FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
				}
			}
		}
	}
	
	//Metodo que pega o objeto selecionado na tabela para edição
	public void selecionaEdicao(Unidade unidade) {
		this.unidade = unidade;
	}
	
	
	//Metodo para exclusao da atividade
	public void excluir(Unidade unidade){
		
		try{
			unidadeDAO = new UnidadeDAO();
			unidadeDAO.exluir(unidade);
			listaUnidades.remove(unidade);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");
			
		}catch(RuntimeException e){
			if(e.getMessage().equals("could not execute statement")){
				FacesUtil.exibirMensagemErro("Recurso está sendo usado em outra tabela,\n"
						+ "verifique!");
			}else{
				FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
			}
		}
	}
	
	//metodo que faz a edição
	public void editar(){
		
		try{
			unidadeDAO = new UnidadeDAO();
			unidadeDAO.editar(unidade);
			FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");
			unidade = new Unidade();
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Erro ao editar registro!" + e.getMessage());
			FacesUtil.exibirMensagemErro("Erro ao editar registro!" + e.getCause());
		}
	}

	//Metodo que lista todas as unidades
	public void listarUnidades(){
		
		try{
			unidadeDAO = new UnidadeDAO();
			listaUnidades = unidadeDAO.listarUnidade();
			
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Não retornou registro!" + e.getMessage());
		}
	}
	
	//Metodo que faz pesquisa por codigo
	public void pesquisaCodigo(){
		
		try{
			String valorRecebido = FacesUtil.getParametro("codigoUnidade");
			if(valorRecebido != null){
				unidadeDAO = new UnidadeDAO();
				Long codigo = Long.parseLong(valorRecebido);
				unidade = unidadeDAO.pesquisaCodigo(codigo);
			}
			
		}catch(RuntimeException e){
			FacesUtil.exibirMensagemErro("Não retornou registro! "+e.getMessage());
		}
	}

}
