package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.modelo.StatusAprovacao;

@ManagedBean
@ViewScoped
public class AcompanhaAtividadeAlunoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Aluno> listaDeAlunos;
	private List<Aluno> filtroAlunos;
	AlunoDAO alunoDAO;
	
	public AcompanhaAtividadeAlunoMB() {
		alunoDAO = new AlunoDAO();
		atualizarListaAlunos();
	}
	
	private void atualizarListaAlunos() {
		listaDeAlunos = alunoDAO.listarAlunos();
		for (Aluno aluno : listaDeAlunos) {
			aluno.setHorasRealizadas(0);
			for (AtividadeRealizada atividadeRealizada : aluno.getAtividadesRealizadas()) {
				aluno.setHorasRealizadas(aluno.getHorasRealizadas() + atividadeRealizada.getHorasAtividade());
				aluno.setHorasFaltantes(aluno.getCurso().getHorasExigidas() - aluno.getHorasRealizadas());
			}
			if(aluno.getHorasFaltantes() <= 0){
				try {
					aluno.setStatusApovacao(StatusAprovacao.APROVADO);
					alunoDAO.editarComum(aluno);
				} catch (Exception e) {
					System.out.println("Nao calculou: "+ e.getCause());
				}
				
			}else{
				aluno.setStatusApovacao(StatusAprovacao.PENDENTE);
				alunoDAO.editarComum(aluno);
			}
		}
	}
	
	public void calculaHoras(){
		
	}

	public List<Aluno> getListaDeAlunos() {
		return listaDeAlunos;
	}

	public void setListaDeAlunos(List<Aluno> listaDeAlunos) {
		this.listaDeAlunos = listaDeAlunos;
	}

	public List<Aluno> getFiltroAlunos() {
		return filtroAlunos;
	}

	public void setFiltroAlunos(List<Aluno> filtroAlunos) {
		this.filtroAlunos = filtroAlunos;
	}

}
