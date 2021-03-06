package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.CursoDAO;
import br.com.tdsystem.sigac.modelo.Curso;
import br.com.tdsystem.sigac.util.FacesUtil;
import br.com.tdsystem.sigac.modelo.negocio.Validacao;

@ManagedBean
@ViewScoped
public class CursoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Curso> listaCurso = null;
	private List<Curso> filtroCursos = null;
	
	private Curso curso = null;
	private CursoDAO cursoDAO = null;
	
	public CursoMB() {
		listarCursos();
		curso = new Curso();
	}
		

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public CursoDAO getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(CursoDAO cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public List<Curso> getListaCurso() {
		return listaCurso;
	}

	public void setListaCurso(List<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}

	public List<Curso> getFiltroCursos() {
		return filtroCursos;
	}

	public void setFiltroCursos(List<Curso> filtroCursos) {
		this.filtroCursos = filtroCursos;
	}
	
	
	public void cancelarEdicao(){
		curso = new Curso();
		
	}

	public void salvar() {

		if (Validacao.validaCampoTexto(curso.getNome())) {
			try {

				cursoDAO = new CursoDAO();
				cursoDAO.salvar(curso);
				
				curso = new Curso();
				listarCursos();
				
				FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");

			} catch (Exception e) {
				if(e.getMessage().equals("could not execute statement")){
					FacesUtil.exibirMensagemErro("J� existe este nome cadastrado!" + e.getCause());
				}else{
					FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
				}
			}
		}
		
	}

	public void excluir(Curso curso) {

		try {
			cursoDAO = new CursoDAO();
			cursoDAO.excluir(curso);
			listaCurso.remove(curso);
			FacesUtil.exibirMensagemSucesso("Exclus�o feita com Sucesso!");

		} catch (Exception e) {
			if(e.getMessage().equals("could not execute statement")){
				FacesUtil.exibirMensagemErro("Recurso est� sendo usado em outra tabela,\n"
						+ "verifique!");
			}else{
				FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
			}
		}
	}

	public void selecionaEdicao(Curso curso) {
		this.curso = curso;
	}

	public void editar() {

		try {
			cursoDAO = new CursoDAO();
			cursoDAO.editar(curso);
			curso = new Curso();
			FacesUtil.exibirMensagemSucesso("Edi��o feita com Sucesso!");

		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Erro ao editar registro!"
					+ e.getMessage());
		}
	}

	public void listarCursos() {

		try {

			cursoDAO = new CursoDAO();
			listaCurso = cursoDAO.listaCurso();

		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("N�o retornou registro!"
					+ e.getMessage());
		}
	}

	public void pesquisaCodigo() {

		try {
			String valorRecebido = FacesUtil.getParametro("codigoCurso");
			if (valorRecebido != null) {
				cursoDAO = new CursoDAO();
				Long codigo = Long.parseLong(valorRecebido);
				curso = cursoDAO.pesquisaCodigo(codigo);
			}

		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("N�o retornou registro!"
					+ e.getMessage());
		}
	}
	
}

