package br.com.tdsystem.sigac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.tdsystem.sigac.dao.CursoDAO;
import br.com.tdsystem.sigac.modelo.Curso;
import br.com.tdsystem.sigac.util.FacesUtil;

//Classe Converter serve para todos selectOnmenu's, ele pega o objeto selecionado
//no selectOnMenu e passa para o metodo três parametros, Contexto da Aplicação, Componente e o Objeto
//selecionado (String), busca no banco o valor e retornar o objeto selecionado.
@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {
	
	CursoDAO cursoDAO = null;
	Curso curso = null;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String entity) {
		try {
			Long valor = Long.parseLong(entity);
			cursoDAO = new CursoDAO();
			curso = cursoDAO.pesquisaCodigo(valor);				
		} catch (Exception e) {
			FacesUtil.exibirMensagemErro("Nao foi possivel converter CursoConverter");
		}
		
		return curso;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			
			curso = (Curso) obj;
			Long valor = curso.getCodigo();
			return valor.toString();
			
		} catch (Exception e) {
			return null;
		}
		
	}

}
