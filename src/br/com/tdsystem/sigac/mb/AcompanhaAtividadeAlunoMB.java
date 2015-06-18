package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Hibernate;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;

@ManagedBean
@ViewScoped
public class AcompanhaAtividadeAlunoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Aluno> alunos;
	AlunoDAO alunoDAO;
	
	public AcompanhaAtividadeAlunoMB() {
		alunoDAO = new AlunoDAO();
		atualizarListaAlunos();
	}
	
	private void atualizarListaAlunos() {
		alunos = alunoDAO.listarAlunos();
		for (Aluno aluno : alunos) {
			aluno.setHorasRealizadas(0);
			for (AtividadeRealizada atividadeRealizada : aluno.getAtividadesRealizadas()) {
				aluno.setHorasRealizadas(aluno.getHorasRealizadas() + atividadeRealizada.getHorasAtividade());
			}			
		}
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}


}
