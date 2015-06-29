package br.com.tdsystem.sigac.mb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.dao.AtividadeRealizadaDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;

import br.com.tdsystem.sigac.modelo.IPessoa;
import br.com.tdsystem.sigac.modelo.Status;
import br.com.tdsystem.sigac.modelo.StatusAprovacao;
import br.com.tdsystem.sigac.modelo.negocio.FormataData;
import br.com.tdsystem.sigac.util.FacesUtil;

@ManagedBean
@ViewScoped
/*
 * Esta Classe de controle(MB) está sendo usada por duas views(uploadAluno e downloadAluno),
 * portanto alguns métodos servem apenas para alguma tela específica ou usuário específico.
 */
public class AtividadeRealizadaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	//Declaração dos Objetos
	private UploadedFile uploadfile;
	private Date dataUpload = null;
	private StreamedContent file, fileDownload;
	private InputStream is = null;
	private ByteArrayOutputStream bos = null;
	
	private AtividadeRealizada atividadeRealizada = null;
	private Aluno aluno = null;
	private Atividade atividade = null;

	private List<Aluno> listaDeAlunos = null;
	private List<Atividade> listaDeAtividades = null;
	private List<Atividade> filtroDeAtividades = null;
	
	private List<AtividadeRealizada> listaDeAtividadesRealizadas = null;
	private List<AtividadeRealizada> filtroDeAtividadesRealizadas = null;

	private AtividadeDAO atividadeDAO = null;
	private AtividadeRealizadaDAO atividadeRealizadaDAO = null;
	
	private PieChartModel graficoAtividades;
	
	//Injetando o MB login para recuperação do usuario em sessão
	@ManagedProperty(value="#{loginMB}")
	private LoginMB loginMB;
	
	//Metodo Construtor
	public AtividadeRealizadaMB() {
		atividadeRealizada = new AtividadeRealizada();
		dataUpload = new Date();
		preencheListas();
		
		//Pega o usuario logado e verifica se é uma instancia de Aluno para calcular as horas
		//So o aluno tem acesso a esse metodo, pois ele é executado apenas na tela de Upload
		IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
		if (usuario instanceof Aluno) {
			aluno = (Aluno) usuario;			
			atualizarHorasRealizadas(aluno);
		}
		
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/resources/download/modeloAC.pdf");
        file = new DefaultStreamedContent(stream, "download/pdf", "downloaded_modeloAC.pdf");
        
        
        
        //Metodo de atualizacao do grafico
        atualizarGrafico();
	}
	
	private static void atualizarHorasRealizadas(Aluno aluno) {
		aluno.setHorasRealizadas(0);
		for (AtividadeRealizada atividadeRealizada : aluno.getAtividadesRealizadas()) {
			if (atividadeRealizada.getStatusApovacao().equals(StatusAprovacao.APROVADO)) {
				aluno.setHorasRealizadas(aluno.getHorasRealizadas() + atividadeRealizada.getAtividade().getHoras());				
			}
		}
		if (aluno.getHorasRealizadas() >= aluno.getCurso().getHorasExigidas()) {
			aluno.setStatusApovacao(StatusAprovacao.APROVADO);
		} else {
			aluno.setStatusApovacao(StatusAprovacao.PENDENTE);			
		}
	}

	//Metodo que faz o Upload do Arquivo
	public void handleFileUpload(FileUploadEvent event) {

		try {
			//Pega o arquivo e o inputStream do arquivo ("Caminho Real")
			is = event.getFile().getInputstream();
			
			//objeto que sabe converter inputStream em Array de Bytes
			bos = new ByteArrayOutputStream();
			
			//pegando os bytes de cada caracter do InpuStream do arquivo e adcionando em uma variavel inteira
			// é preciso fazer isso para saber a quantidade de bytes e o tamanho dele.
			int next = is.read();
			
			//Escrevendo essa leitura caracter por caracter até o arquivo ser todo escrito dentro do bos
			while (next > -1 ) {
				bos.write(next);
				next = is.read();
			}
			
			bos.flush();
			
			//aqui estão os bytes do documento upado
			byte[] btFile = bos.toByteArray();
			atividadeRealizada.setComprovante(btFile); 
			
			FacesMessage message = new FacesMessage("Succeso", event.getFile()
					.getFileName() + atividadeRealizada.getComprovante());
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		} catch (Exception ex) {
			Logger.getLogger(AtividadeRealizadaMB.class.getName()).log(Level.SEVERE, null, ex);
			FacesUtil.exibirMensagemErro("try :" + ex.getMessage());
		}
	}
	
	//Metodo que valida o documento, setando o Enum para Aprovado, sendo assim a atividade entra na contagem
	// do metodo atualizarHorasRealizadas(aluno).
	public void validaDocumento(AtividadeRealizada atividadeRealizada){
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		atividadeRealizada.setStatusApovacao(StatusAprovacao.APROVADO);
		atividadeRealizadaDAO.editar(atividadeRealizada);
	}
	
	//Metodo que invalida o documento setando o Enum para invalido, sendo assim ele nao entra na contagem
	// do metodo atualizarHorasRealizadas(aluno) que so conta atividades realizadas com status Aprovado
	public void invalidaDocumento(AtividadeRealizada atividadeRealizada){
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		atividadeRealizada.setStatusApovacao(StatusAprovacao.INVALIDO);
		atividadeRealizadaDAO.editar(atividadeRealizada);
	}
	
	//metodo que formata a data atual
	public String formataData(){
		Date dataAtual =  new Date();
		
		//Classe FormataData e seu metodo estático
		String data = FormataData.formataData(dataAtual);
		return data;
	}
	
	public void selecionaEdicao(AtividadeRealizada atividadeRealizada) {
		this.atividadeRealizada = atividadeRealizada;
	}

	//Metodo que preenche as litas necessárias e é chamado no construtor da classe
	public void preencheListas() {
		try {
			atividadeDAO = new AtividadeDAO();
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			listaDeAtividades = atividadeDAO.listaAtividade(Status.ATIVO);
			
			//pega o usuario logado para passar como parametro para o metodo polimorfico 
			IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
			listaDeAtividadesRealizadas = atividadeRealizadaDAO.listarAtividadesRealizadas(usuario);

			if (!listaDeAtividades.isEmpty()) {
				atividadeRealizada.setAtividade(listaDeAtividades.get(0));				
			}
			
			//Pega o inpuStream("caminho real") do Documento Modelo que esta na pasta do projeto e adciona no objeto File.
			InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext())
					.getResourceAsStream("/resources/download/modeloAC.pdf");
	        file = new DefaultStreamedContent(stream, "download/pdf", "downloaded_modeloAC.pdf");
			
		} catch (Exception e) {
			System.out.println("Erro nulo: " + e.getMessage());
			System.out.println("Erro: " + e.getCause());
		}

	}
	
	//Metodo que verifica se a atividade já foi submetida uma vez, evitando a duplicidade
	private Boolean verificaAtividadeRepetida(){
		
		//Pega o usuario logado
		IPessoa usuario = FacesUtil.getUsuarioLogado().getUsuario();
		Boolean grava = Boolean.TRUE;
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		
		//Recupera uma lista de atividadesRealizadas do usuario
		//(hora sendo aluno, hora sendo todas se usuario for coordenador)
		List<AtividadeRealizada> listaDeAtividadesRealizadas = atividadeRealizadaDAO.
				listarAtividadesRealizadas(usuario);
		for (AtividadeRealizada ForAtividadeRealizada : listaDeAtividadesRealizadas) {
			if(ForAtividadeRealizada.getAtividade().getCodigo() == atividadeRealizada.
					getAtividade().getCodigo()){
				grava = Boolean.FALSE;
			}
		}
		return grava;
	}
	
	//metodo que salva um upload submetido
	public void salvar() {
		if(verificaAtividadeRepetida()){
			try {
				atividadeRealizada.setDataUpload(FormataData.formataData(getDataUpload()));
				
				//recuperando as seleções da tela(Atividade selecionada e usuario)
				atividadeRealizadaDAO = new AtividadeRealizadaDAO();
				atividade = atividadeDAO.pesquisaCodigo(atividadeRealizada.getAtividade().getCodigo());
				atividadeRealizada.setAluno((Aluno) loginMB.getUsuario().getUsuario());
				
				//Setando como pendente a atividade pois precisa ser validada pelo coordenador
				atividadeRealizada.setStatusApovacao(StatusAprovacao.PENDENTE);
				
				//Chamando o metodo da DAO que é quem realmente sabe salvar no banco.
				atividadeRealizadaDAO.salvar(atividadeRealizada);
				atualizarHorasRealizadas(aluno);
				preencheListas();
				atualizarGrafico();
				atividadeRealizada = new AtividadeRealizada();
				dataUpload = new Date();
				
				FacesUtil.exibirMensagemSucesso("Comprovante Submetido com sucesso!");
			} catch (Exception e) {
				FacesUtil.exibirMensagemErro("Erro ao submeter comprovante!"
						+ e.getMessage()+e.getCause());
				FacesUtil.exibirMensagemErro("Cause!"
						+ e.getCause());
			}
		}else{
			FacesUtil.exibirMensagemAlerta("Atividade já lançada!\n"
											+ "ou já atingiu status aprovado!");
		}
	}
	
	//Metodo Excluir Atividade(Upload), salvo ela não tenha sido validada pelo coordenador
	public void excluir(AtividadeRealizada atividadeRealizada) {

		try {
			atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			atividadeRealizadaDAO.excluir(atividadeRealizada);
			FacesUtil.exibirMensagemSucesso("Exclusão feita com Sucesso!");
			listaDeAtividadesRealizadas.remove(atividadeRealizada);
			preencheListas();
			atualizarGrafico();
			atualizarHorasRealizadas(aluno);
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao excluir registro!"
					+ e.getMessage());
		}
	}
	
	//Metodo que edita uma atividade*
	//* metodo em OFF devido a regra de negocio estabelcida e nao poder editar a atividade,
	// melhor seria excluir e submeter novamente.
	public void editar(){
		if(verificaAtividadeRepetida()){
			try {
				atividadeRealizadaDAO = new AtividadeRealizadaDAO();
				atividadeRealizadaDAO.editar(atividadeRealizada);
				atividadeRealizada = new AtividadeRealizada();
				atualizarGrafico();
			} catch (Exception e) {
				FacesUtil.exibirMensagemErro("Erro ao editar a atividade!"
						+ e.getMessage()+e.getCause());
				FacesUtil.exibirMensagemErro("Cause!"
						+ e.getCause());
			}
		}
	}
	
	
	//metodo que atualiza o grafico com as atividades mais realizadas
	public void atualizarGrafico() {
        
		graficoAtividades = new PieChartModel();
        
		//Setando os atributos(Titulo, posição w(west), e os labels para aparecer.)
        graficoAtividades.setTitle("Relação de Atividades mais Realizdas");
        graficoAtividades.setLegendPosition("w");
        graficoAtividades.setShowDataLabels(Boolean.TRUE);
        
        //Declarando um HashMap "filter"(Tabela que utiliza chave e valor), com isso setamos
        //o nome da atividade na String e a contagem no Integer.
        Map<String, Integer> filter = new HashMap<String, Integer>();
        
        
        List<Atividade> atividades = atividadeDAO.listaAtividade();
        List<AtividadeRealizada> atividadesRealizadas = atividadeRealizadaDAO.listarAtividadesRealizadas(
        		FacesUtil.getUsuarioLogado().getUsuario());
        
        //For para preencher os nomes das atividades
        for (Atividade atividade : atividades) {
        	filter.put(atividade.getNome(), 0);
        }
        
        //For para preenhcer fazendo a contagem e adcionando na respectiva atividade.
        Integer cont = null;
        for (AtividadeRealizada atividade : atividadesRealizadas) {
        	cont = filter.get(atividade.getAtividade().getNome());
        	filter.put(atividade.getAtividade().getNome(), ++cont);
		}
        
        //Declarando um SetEntry para colocar as informações que estão dentro do filter(HashMap),
        // dentro do grafico.
        Set<Entry<String, Integer>> entrySet = filter.entrySet();
        for (Entry<String, Integer> entry : entrySet) {
			graficoAtividades.set(entry.getKey(), entry.getValue());
		}
    }
	
	//Metodo usado pela View downloadAtividade, recupera o comprovante submetido pelo Aluno para visualização e
	// validação do coordenador.
	public void download(AtividadeRealizada atividadeRealizada) {
		InputStream stream = new ByteArrayInputStream(atividadeRealizada.getComprovante());
        fileDownload = new DefaultStreamedContent(stream, "download/pdf", "atividade-" + atividadeRealizada.getCodigo() + ".pdf");
	}

	
	//Metodos Get e Set's
	public List<AtividadeRealizada> getListaDeAtividadesRealizadas() {
		return listaDeAtividadesRealizadas;
	}

	public void setListaDeAtividadesRealizadas(
			List<AtividadeRealizada> listaDeAtividadesRealizadas) {
		this.listaDeAtividadesRealizadas = listaDeAtividadesRealizadas;
	}

	public List<AtividadeRealizada> getFiltroDeAtividadesRealizadas() {
		return filtroDeAtividadesRealizadas;
	}

	public void setFiltroDeAtividadesRealizada(
			List<AtividadeRealizada> filtroDeAtividadesRealizadas) {
		this.filtroDeAtividadesRealizadas = filtroDeAtividadesRealizadas;
	}

	public StreamedContent getFile() {
        return file;
    }

	public AtividadeRealizada getAtividadeRealizada() {
		return atividadeRealizada;
	}

	public void setAtividadeRealizada(AtividadeRealizada atividadeRealizada) {
		this.atividadeRealizada = atividadeRealizada;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Atividade> getListaDeAtividades() {
		return listaDeAtividades;
	}

	public void setListaDeAtividades(List<Atividade> listaDeAtividades) {
		this.listaDeAtividades = listaDeAtividades;
	}

	public List<Atividade> getFiltroDeAtividades() {
		return filtroDeAtividades;
	}

	public void setFiltroDeAtividades(List<Atividade> filtroDeAtividades) {
		this.filtroDeAtividades = filtroDeAtividades;
	}


	public void setAtividadeDAO(AtividadeDAO atividadeDAO) {
		this.atividadeDAO = atividadeDAO;
	}

	public List<Aluno> getListaDeAlunos() {
		return listaDeAlunos;
	}

	public void setListaDeAlunos(List<Aluno> listaDeAlunos) {
		this.listaDeAlunos = listaDeAlunos;
	}

	public UploadedFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(UploadedFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}
	
	public Date getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public PieChartModel getGraficoAtividades() {
		return graficoAtividades;
	}

	public void setGraficoAtividades(PieChartModel graficoAtividades) {
		this.graficoAtividades = graficoAtividades;
	}
}