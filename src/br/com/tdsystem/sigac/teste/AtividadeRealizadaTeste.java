package br.com.tdsystem.sigac.teste;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.eval.UnaryPlusEval;
import org.junit.Ignore;
import org.junit.Test;
import org.primefaces.model.UploadedFile;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.dao.AtividadeRealizadaDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.modelo.AtividadeRealizada;
import br.com.tdsystem.sigac.util.FormataData;

public class AtividadeRealizadaTeste {
	
	@Test
	//@Ignore
	public void salvarTeste(){
		try{			
			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.pesquisaCodigo(3l);
			
			File file = new File("C:/Thiaguitos.pdf");
			byte[] bFile = new byte[(int) file.length()];
			
			try {
	            FileInputStream fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bFile);
	            fileInputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			AtividadeDAO atividadeDAO = new AtividadeDAO();
			Atividade atividade = atividadeDAO.pesquisaCodigo(1l);
			
			AtividadeRealizadaDAO atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			AtividadeRealizada atividadeRealizada = new AtividadeRealizada();
			atividadeRealizada.setHorasRestantes(98);
			atividadeRealizada.setAluno(aluno);
			atividadeRealizada.setAtividade(atividade);
			atividadeRealizada.setDataEvento("05/06/15");
			atividadeRealizada.setDataUpload("05/06/15");
			atividadeRealizada.setComprovante(bFile);
			atividadeRealizadaDAO.salvar(atividadeRealizada);
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
			
		}	
	}
	
	@Test
	@Ignore
	public void editarTeste(){
		try {
			AtividadeRealizadaDAO atividadeRealizadaDAO = new AtividadeRealizadaDAO();
			AtividadeRealizada atividadeRealizada = atividadeRealizadaDAO.pesquisaCodigo(2l);
			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.pesquisaCodigo(6l);
			AtividadeDAO atividadeDAO = new AtividadeDAO();
			Atividade atividade = atividadeDAO.pesquisaCodigo(2l);
			
			atividadeRealizada.setHorasRestantes(60);
			atividadeRealizada.setAluno(aluno);
			atividadeRealizada.setAtividade(atividade);
			atividadeRealizadaDAO.editar(atividadeRealizada);
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}
	
	@Test
	@Ignore
	public void excluirTeste(){
		AtividadeRealizadaDAO atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		AtividadeRealizada atividadeRealizada = atividadeRealizadaDAO.pesquisaCodigo(1l);
		atividadeRealizadaDAO.excluir(atividadeRealizada);
	}
	
	@Test
	@Ignore
	public void listarAtividadesRealizadasTeste(){
		AtividadeRealizadaDAO  atividadeRealizadaDAO = new AtividadeRealizadaDAO();
		List<AtividadeRealizada> listaAtividades = atividadeRealizadaDAO.listarAtividadesRealizadas();
		
		for (AtividadeRealizada atividadeRealizada : listaAtividades) {
			System.out.println(" " + atividadeRealizada);
		}
	}

}
