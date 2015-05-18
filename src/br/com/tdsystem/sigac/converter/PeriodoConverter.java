package br.com.tdsystem.sigac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tdsystem.sigac.dao.PeriodoDAO;
import br.com.tdsystem.sigac.modelo.Periodo;
import br.com.tdsystem.sigac.util.FacesUtil;

@FacesConverter("periodoConverter")
public class PeriodoConverter implements Converter {

	PeriodoDAO periodoDAO = null;
	Periodo periodo = null;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			Long valor = Long.parseLong(arg2);
			periodoDAO = new PeriodoDAO();
			
			periodo = periodoDAO.pesquisaCodigo(valor);
			
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Nao foi possivel converter");
		}
		
		return periodo;
	}

	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			
			periodo = (Periodo) obj;
			Long valor = periodo.getCodigo();
			return valor.toString();
			
		} catch (Exception e) {
			return null;
		}
		
	}

}
