package br.com.tdsystem.sigac.util;

public class Validacao {
	
	public static Boolean validaCampoTexto(String campo){
		if(!campo.isEmpty()){
			return Boolean.TRUE;
		}else{
			FacesUtil.exibirMensagemAlerta("Verifique Campos vazios!\n");
			return Boolean.FALSE;
		}
		
	}

	public static Boolean validaCampoNumerico(String campo){
		
		if(campo.isEmpty()){
			FacesUtil.exibirMensagemAlerta("Verifique Campos vazios!\n");
			return Boolean.FALSE;
		}else{
			return Boolean.TRUE;
		}
	}
}
