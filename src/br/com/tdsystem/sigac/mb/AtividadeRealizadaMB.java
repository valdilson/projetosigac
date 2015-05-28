package br.com.tdsystem.sigac.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.modelo.Aluno;

@ManagedBean
@ViewScoped
public class AtividadeRealizadaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private AlunoDAO alunoDAO = new AlunoDAO();
	private Aluno aluno = null;

}
