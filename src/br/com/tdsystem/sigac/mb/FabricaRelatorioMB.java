package br.com.tdsystem.sigac.mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.tdsystem.sigac.relatorios.CoordenadorRelatorio;
import br.com.tdsystem.sigac.relatorios.CursoRelatorio;
import br.com.tdsystem.sigac.util.FabricaRelatorio;
import br.com.tdsystem.sigac.util.FacesUtil;




@ManagedBean
@ViewScoped
public class FabricaRelatorioMB implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private List<?> lista= null;
	private List<?> filtro= null;
	
	public FabricaRelatorioMB() {
		// TODO Auto-generated constructor stub
		listarCurso();	
		
	}
	
	public void listarCoordenador() {
		CoordenadorRelatorio coordenador = new CoordenadorRelatorio();
		try {
			
			FabricaRelatorio fabrica = new FabricaRelatorio ();					
			lista = fabrica.listarDados(coordenador);
					
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemAlerta("Não foi possível acessar o banco"
					+ e.getMessage());
		}
		
	}
	
	public void listarCurso() {
		CursoRelatorio curso = new CursoRelatorio();
		try {
			
			FabricaRelatorio fabrica = new FabricaRelatorio ();					
			lista = fabrica.listarDados(curso);
					
		} catch (RuntimeException e) {
			FacesUtil.exibirMensagemAlerta("Não foi possível acessar o banco"
					+ e.getMessage());
		}
		
	}

	public List<?> getLista() {
		return lista;
	}

	public void setLista(List<?> lista) {
		this.lista = lista;
	}

	public List<?> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<?> filtro) {
		this.filtro = filtro;
	}





}
