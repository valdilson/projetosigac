package br.com.tdsystem.sigac.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.modelo.Aluno;

@ManagedBean
@ViewScoped
public class PreCadastroAlunoMB implements Serializable{
	
	private static final long serialVersionUID = 1L;

	Aluno preAluno = null;
	AlunoDAO alunoDAO = null;
	
	
	public PreCadastroAlunoMB(){
		preAluno = new Aluno();
	}
	
	public void salvar(){
		alunoDAO = new AlunoDAO();
		preAluno.setCurso(null);
		preAluno.setPeriodo(null);
		preAluno.setTurma(null);
		preAluno.setTurno(null);
		preAluno.setUnidade(null);
		alunoDAO.salvar(preAluno);
	}
}
