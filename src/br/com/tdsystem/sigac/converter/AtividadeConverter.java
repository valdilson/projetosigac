package br.com.tdsystem.sigac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tdsystem.sigac.dao.AtividadeDAO;
import br.com.tdsystem.sigac.modelo.Atividade;
import br.com.tdsystem.sigac.util.FacesUtil;

//Classe Converter serve para todos selectOnmenu's, ele pega o objeto selecionado
//no selectOnMenu e passa para o metodo tr�s parametros, Contexto da Aplica��o, Componente e o Objeto
//selecionado (String), busca no banco o valor e retornar o objeto selecionado.
@FacesConverter("atividadeConverter")
public class AtividadeConverter implements Converter {

	AtividadeDAO atividadeDAO = null;
	Atividade atividade = null;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			Long valor = Long.parseLong(arg2);
			atividadeDAO = new AtividadeDAO();
			
			atividade = atividadeDAO.pesquisaCodigo(valor);
			
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Nao foi possivel converter AtividadeConverter");
		}
		
		return atividade;
	}

	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			
			atividade = (Atividade) obj;
			Long valor = atividade.getCodigo();
			return valor.toString();
			
		} catch (Exception e) {
			return null;
		}
		
	}

}
