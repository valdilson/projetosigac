package br.com.tdsystem.sigac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tdsystem.sigac.dao.TurnoDAO;
import br.com.tdsystem.sigac.modelo.Turno;
import br.com.tdsystem.sigac.util.FacesUtil;

@FacesConverter("turnoConverter")
public class TurnoConverter implements Converter {

	TurnoDAO turnoDAO = null;
	Turno turno = null;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			Long valor = Long.parseLong(arg2);
			turnoDAO = new TurnoDAO();
			
			turno = turnoDAO.pesquisaCodigo(valor);
			
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Nao foi possivel converter TurnoConverter");
		}
		
		return turno;
	}

	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			
			turno = (Turno) obj;
			Long valor = turno.getCodigo();
			return valor.toString();
			
		} catch (Exception e) {
			return null;
		}
		
	}

}
