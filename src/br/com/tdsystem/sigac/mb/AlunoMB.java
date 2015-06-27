package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.dao.CursoDAO;
import br.com.tdsystem.sigac.dao.TurmaDAO;
import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Coordenador;
import br.com.tdsystem.sigac.modelo.Curso;
import br.com.tdsystem.sigac.modelo.IPessoa;
import br.com.tdsystem.sigac.modelo.PerfilEnum;
import br.com.tdsystem.sigac.modelo.Periodo;
import br.com.tdsystem.sigac.modelo.Turma;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.modelo.negocio.CriptografaSenhaMD5;
import br.com.tdsystem.sigac.util.FacesUtil;
import br.com.tdsystem.sigac.modelo.negocio.Validacao;

@ManagedBean
@ViewScoped
public class AlunoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno aluno = null;
	private AlunoDAO alunoDAO = null;
	private PerfilEnum perfil;
	private TurnoDAO turnoDAO = null;
	private TurmaDAO turmaDAO = null;
	private UnidadeDAO unidadeDAO = null;
	private List<Aluno> listaDeAlunos = null;
	private List<Aluno> filtroDeAlunos = null;
	private List<Curso> listaDeCursos = null;
	private List<Turno> listaDeTurnos = null;
	private List<Turma> listaDeTurmas = null;
	private List<Unidade> listaDeUnidades = null;
	private List<Periodo> listaPeriodos = null;
	
	private String password;
	
	@ManagedProperty(value = "#{loginMB}")
	private LoginMB loginMB;
	
	public AlunoMB() {
		aluno = new Aluno();
		listaPeriodos = new ArrayList<Periodo>();
		pesquisarListaAlunos();
		pesquisaListaCurso();
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public List<Aluno> getListaDeAlunos() {
		return listaDeAlunos;
	}

	public void setListaDeAlunos(List<Aluno> listaDeAlunos) {
		this.listaDeAlunos = listaDeAlunos;
	}

	public List<Aluno> getFiltroDeAlunos() {
		return filtroDeAlunos;
	}

	public void setFiltroDeAlunos(List<Aluno> filtroDeAlunos) {
		this.filtroDeAlunos = filtroDeAlunos;
	}

	public List<Curso> getListaDeCursos() {
		return listaDeCursos;
	}

	public void setListaDeCursos(List<Curso> listaDeCursos) {
		this.listaDeCursos = listaDeCursos;
	}

	public List<Turno> getListaDeTurnos() {
		return listaDeTurnos;
	}

	public void setListaDeTurnos(List<Turno> listaDeTurnos) {
		this.listaDeTurnos = listaDeTurnos;
	}

	public List<Turma> getListaDeTurmas() {
		return listaDeTurmas;
	}

	public void setListaDeTurmas(List<Turma> listaDeTurmas) {
		this.listaDeTurmas = listaDeTurmas;
	}
	
	public List<Unidade> getListaDeUnidades() {
		return listaDeUnidades;
	}

	public void setListaDeUnidades(List<Unidade> listaDeUnidades) {
		this.listaDeUnidades = listaDeUnidades;
	}

	public void validaCampos() throws NoSuchAlgorithmException{
		if(Validacao.validaCampoTexto(aluno.getNome())){
			salvar();
		}
	}
	
	public void cancelarEdicao () {
		aluno = new Aluno();
	}
	
	
	public void salvar() throws NoSuchAlgorithmException {
		String password = aluno.getPassword();
		String cpassword = aluno.getConfirmaPassword();

		try {
			alunoDAO = new AlunoDAO();
			
			Aluno other = alunoDAO.pesquisaRA(aluno.getRa());
			
			if(other == null) {
				if (!password.equals(cpassword)) {
					FacesUtil.exibirMensagemSucesso("Senhas não conferem ou vazias!");
				} else {

					String senha = CriptografaSenhaMD5.converteSenhaMD5(aluno.getPassword());
					aluno.setPassword(senha);
					alunoDAO.salvar(aluno);
					aluno = new Aluno();
					pesquisarListaAlunos();
					pesquisaListaCurso();
					FacesUtil.exibirMensagemSucesso("Cadastro feito com Sucesso!");
				}
			} else {
				FacesUtil.exibirMensagemAlerta("RA já cadastrado no sistema, verifique!");				
			}
		} catch (RuntimeException e) {
			if(e.getMessage().equals("could not execute statement")){
				FacesUtil.exibirMensagemErro("Já existe este RA cadastrado!");
			}else{
				FacesUtil.exibirMensagemErro("Erro: " + e.getMessage());
			}
		}
	}

	public void excluir(Aluno aluno) {

		try {

			alunoDAO = new AlunoDAO();
			alunoDAO.excluir(aluno);

			listaDeAlunos.remove(aluno);
			FacesUtil.exibirMensagemSucesso("Exclusï¿½o feita com Sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao cadastrar Aluno!"
					+ e.getMessage());
		}
	}

	public void editar() throws NoSuchAlgorithmException {

		try {
			String senha = null;
			if (aluno.getPassword() != null && !aluno.getPassword().equals("")) {
				senha = CriptografaSenhaMD5.converteSenhaMD5(aluno.getPassword());
			} else {
				senha = password;
				aluno.setPassword(senha);
				aluno.setConfirmaPassword(password);
			}
			if (aluno.getPassword() != "" && aluno.getPassword().equals(aluno.getConfirmaPassword())) {
				aluno.setPassword(senha);
				alunoDAO = new AlunoDAO();
				alunoDAO.editar(aluno);
				FacesUtil.exibirMensagemSucesso("Edição feita com Sucesso!");
				aluno = new Aluno();
			} else {
				FacesUtil.exibirMensagemSucesso("Senhas não conferem ou vazias!");
			}

		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemErro("Erro ao editar Aluno!"
					+ e.getMessage());
		}
	}

	public void pesquisarListaAlunos() {

		try {
			alunoDAO = new AlunoDAO();
			turmaDAO = new TurmaDAO();
			turnoDAO = new TurnoDAO();
			unidadeDAO = new UnidadeDAO();
			
			IPessoa pessoaLogada = FacesUtil.getUsuarioLogado().getUsuario();
			if (pessoaLogada instanceof Coordenador) {
				listaDeAlunos = alunoDAO.listarAlunos();				
			} else {
				aluno = (Aluno) pessoaLogada;
				password = aluno.getPassword();
			}
			
			listaDeTurmas = turmaDAO.listaTurma();
			listaDeTurnos = turnoDAO.listaTurno();
			listaDeUnidades = unidadeDAO.listarUnidade();
			
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemAlerta("Nao retornou registro"
					+ e.getMessage());
		}

	}

	public void pesquisaListaCurso() {
		try {
			CursoDAO cursoDAO = new CursoDAO();
			listaDeCursos = cursoDAO.listaCurso();
			Periodo qtdPeriodos = listaDeCursos.get(0).getQtdPeriodos();
			aluno.setCurso(listaDeCursos.get(0));
			if (qtdPeriodos != null) {
				atualizaListaPeriodo(qtdPeriodos);				
			} else {
				listaPeriodos = new ArrayList<Periodo>();
			}
		} catch (RuntimeException e) {
			System.err.println(e);
		}
	}

	public void selecionaEdicao(Aluno aluno) {
		this.aluno = aluno;
		setPassword(aluno.getPassword());
	}
	
	public void atualizarComboPeriodo() {
		atualizaListaPeriodo(aluno.getCurso().getQtdPeriodos());
    }
	
	private void atualizaListaPeriodo(Periodo periodo) {
		Periodo[] periodos = Periodo.values();
		listaPeriodos.clear();
		for (int i = 0; i < periodo.ordinal(); i++) {
			listaPeriodos.add(periodos[i]);
		}
		System.out.println(listaPeriodos.size());
	}

	public List<Periodo> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(List<Periodo> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
