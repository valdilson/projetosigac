package br.com.tdsystem.sigac.modelo.negocio;

import java.util.Date;

import br.com.tdsystem.sigac.util.FacesUtil;

public class Validacao {
	
	public static Boolean validaCampoTexto(String campo){
		if(!campo.isEmpty()){
			return Boolean.TRUE;
		}else{
			FacesUtil.exibirMensagemAlerta("Verifique Campos vazios!\n");
			return Boolean.FALSE;
		}
		
	}
	
	public static Boolean validaCadastroAtividade(String campo, Integer hora, Date data){
		if(!campo.isEmpty() || !hora.equals("") || !data.equals("")){
			return Boolean.TRUE;
		}else{
			FacesUtil.exibirMensagemAlerta("Verifique Campos vazios!\n");
			return Boolean.FALSE;
		}
		
	}

}
