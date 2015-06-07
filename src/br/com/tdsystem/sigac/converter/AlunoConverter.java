package br.com.tdsystem.sigac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tdsystem.sigac.dao.AlunoDAO;
import br.com.tdsystem.sigac.modelo.Aluno;
import br.com.tdsystem.sigac.util.FacesUtil;

@FacesConverter("alunoConverter")
public class AlunoConverter implements Converter {

	AlunoDAO alunoDAO = null;
	Aluno aluno = null;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			Long valor = Long.parseLong(arg2);
			alunoDAO = new AlunoDAO();
			
			aluno = alunoDAO.pesquisaCodigo(valor);
			
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Nao foi possivel converter");
		}
		
		return aluno;
	}

	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			
			aluno = (Aluno) obj;
			Long valor = aluno.getCodigo();
			return valor.toString();
			
		} catch (Exception e) {
			return null;
		}
		
	}


}
