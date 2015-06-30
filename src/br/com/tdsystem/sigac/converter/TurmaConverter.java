package br.com.tdsystem.sigac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tdsystem.sigac.dao.TurmaDAO;
import br.com.tdsystem.sigac.modelo.Turma;
import br.com.tdsystem.sigac.util.FacesUtil;

//Classe Converter serve para todos selectOnmenu's, ele pega o objeto selecionado
//no selectOnMenu e passa para o metodo três parametros, Contexto da Aplicação, Componente e o Objeto
//selecionado (String), busca no banco o valor e retornar o objeto selecionado.
@FacesConverter("turmaConverter")
public class TurmaConverter implements Converter {

	TurmaDAO turmaDAO = null;
	Turma turma = null;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			Long valor = Long.parseLong(arg2);
			turmaDAO = new TurmaDAO();
			
			turma = turmaDAO.pesquisaCodigo(valor);
			
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Nao foi possivel converter TurmaConverter");
		}
		
		return turma;
	}

	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			
			turma = (Turma) obj;
			Long valor = turma.getCodigo();
			return valor.toString();
			
		} catch (Exception e) {
			return null;
		}
		
	}

}
