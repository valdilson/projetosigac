package br.com.tdsystem.sigac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tdsystem.sigac.dao.UnidadeDAO;
import br.com.tdsystem.sigac.modelo.Unidade;
import br.com.tdsystem.sigac.util.FacesUtil;

@FacesConverter("unidadeConverter")
public class UnidadeConverter implements Converter {

	UnidadeDAO unidadeDAO = null;
	Unidade unidade = null;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			Long valor = Long.parseLong(arg2);
			unidadeDAO = new UnidadeDAO();
			
			unidade = unidadeDAO.pesquisaCodigo(valor);
			
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Nao foi possivel converter UnidadeConverter");
		}
		
		return unidade;
	}

	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			
			unidade = (Unidade) obj;
			Long valor = unidade.getCodigo();
			return valor.toString();
			
		} catch (Exception e) {
			return null;
		}
		
	}
}
