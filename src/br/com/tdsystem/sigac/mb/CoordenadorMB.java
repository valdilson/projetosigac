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
import br.com.tdsystem.sigac.modelo.negocio.CriptografaSenhaMD5;
import br.com.tdsystem.sigac.modelo.negocio.Validacao;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CoordenadorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	//Declaração dos Objetos
	private Coordenador coordenador = null;
	private CoordenadorDAO coordenadorDAO = null;
	private UnidadeDAO unidadeDAO = null;
	private PerfilEnum perfil;

	private List<Coordenador> listaDeCoordenadores = null;
	private List<Coordenador> filtroDeCoordenadores = null;
	private List<Unidade> listaDeUnidades = null;
	
	//Metodo construtor
	public CoordenadorMB() {
		coordenador = new Coordenador();
		pesquisaListaCoordenadores();
	}

	public void selecionaEdicao(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	//Metodo que chama a camada DAO que sabe como trazer uma lista de Coordenadores
	public void pesquisaListaCoordenadores() {
		try {
			coordenadorDAO = new CoordenadorDAO();
			unidadeDAO = new UnidadeDAO();
			
			listaDeCoordenadores = coordenadorDAO.listarCoordenadores();
			listaDeUnidades = unidadeDAO.listarUnidade();

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemAlerta("Não foi possível acessar o banco"
					+ e.getMessage());
		}
	}
	
	//Metodo para instancia uma nova atividade para cancelar a edição, necessário para
	// o botao editar sumir da View, pois ele renderiza quando o codigo é != null
    public void cancelarEdicao(){
    	coordenador = new Coordenador();	
    }

    //Metodo que Salva um Coordenador
	public void salvar() throws NoSuchAlgorithmException {
		//Valida o campo Nome para nao ser vazio
		if(Validacao.validaCampoTexto(coordenador.getNome())){
			
			try{
			coordenadorDAO = new CoordenadorDAO();
			
			//Pesquisa se existe o RA informado no banco para evitar a duplicidade
			Coordenador other = coordenadorDAO.pesquisaRA(coordenador.getRa());
			
			//Retornando vazio (null), continua as validações
			if(other == null) {
				if (!coordenador.getPassword().equals(coordenador.getConfirmaPassword())) {
					FacesUtil.exibirMensagemSucesso("Senhas não conferem ou vazias!");
				} else {
					
					//Criptografa a senha e chama a camada DAO que sabe como gravar o Coordenador
					String senha = CriptografaSenhaMD5.converteSenhaMD5(coordenador.getPassword());
					coordenador.setPassword(senha);
					coordenadorDAO.salvar(coordenador);
					coordenador = new Coordenador();
					pesquisaListaCoordenadores();
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
	}

	//Metodo que Edita um Coordenador
	public void editar() throws NoSuchAlgorithmException {
		try {
			
			//Criptografa a senha com MD5
			String senha = CriptografaSenhaMD5.converteSenhaMD5(coordenador
					.getPassword());
			if (coordenador.getPassword() != ""
					&& coordenador.getPassword().equals(
							coordenador.getConfirmaPassword())) {

				coordenador.setPassword(senha);
				coordenadorDAO = new CoordenadorDAO();
				coordenadorDAO.editar(coordenador);
				FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");
				coordenador = new Coordenador();
			} else {
				FacesUtil.exibirMensagemSucesso("Senhas não conferem ou vazias!");
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar Coordenador!"
					+ e.getMessage());
		}
	}
	
	//Metodo que exclui um Coordenador
	public void excluir(Coordenador coordenador) {

		try {
			
			coordenadorDAO = new CoordenadorDAO();
			coordenadorDAO.excluir(coordenador);

			//Remove o coordenador da lista da View
			listaDeCoordenadores.remove(coordenador);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");

		} catch (RuntimeException e) {
			if(e.getMessage().equals("could not execute statement")){
				FacesUtil.exibirMensagemErro("Recurso está sendo usado em outra tabela,\n"
						+ "verifique!");
			}else{
				FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
			}
		}
	}

	//Metodos Get e Set's
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