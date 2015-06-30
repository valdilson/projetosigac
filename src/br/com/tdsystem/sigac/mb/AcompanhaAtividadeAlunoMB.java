package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.dao.AtividadeRealizadaDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.modelo.StatusAprovacao;

@ManagedBean
@ViewScoped
/*
 * Este controller é utilizado apenas pela View acompanhAtividadeAluno e é apenas vista pelo Coordenador
 * Ela tem como objetivo se comportar como uma fabrica de relatórios dinâmicos.
 */
public class AcompanhaAtividadeAlunoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Declaração dos Objetos
	private List<AtividadeRealizada> listaDeAtividadesRealizadas = null;
	private List<Aluno> filtroAlunos;
	AtividadeRealizadaDAO atividadeRealizadaDAO = null;
	
	//Metodo Construtor
	public AcompanhaAtividadeAlunoMB() {
		
		atualizahorasRealizadasLista();
	}
	
	//Metodo que atualiza as horas dos alunos para conferência do Coordenador
	public void atualizahorasRealizadasLista(){
		atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		listaDeAtividadesRealizadas = 
				atividadeRealizadaDAO.listarAtividadesRealizadasLista();
		
		for (AtividadeRealizada atividadeRealizada : listaDeAtividadesRealizadas) {
			
			if(atividadeRealizada.getStatusApovacao().equals(StatusAprovacao.APROVADO)){
				atividadeRealizada.getAluno().setHorasRealizadas(atividadeRealizada.getAluno().getHorasRealizadas()
						+ atividadeRealizada.getAtividade().getHoras());
				atividadeRealizada.getAluno().setHorasFaltantes
				(atividadeRealizada.getAluno().getCurso().getHorasExigidas()
						- atividadeRealizada.getAluno().getHorasRealizadas());
			}
		}
	}
	

	public List<Aluno> getFiltroAlunos() {
		return filtroAlunos;
	}

	public void setFiltroAlunos(List<Aluno> filtroAlunos) {
		this.filtroAlunos = filtroAlunos;
	}

	public List<AtividadeRealizada> getListaDeAtividadesRealizadas() {
		return listaDeAtividadesRealizadas;
	}

	public void setListaDeatividadesRealizadas(
			List<AtividadeRealizada> listaDeAtividadesRealizadas) {
		this.listaDeAtividadesRealizadas = listaDeAtividadesRealizadas;
	}

}
