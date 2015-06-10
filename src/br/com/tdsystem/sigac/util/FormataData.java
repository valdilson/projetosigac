package br.com.tdsystem.sigac.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {
	
	public static String formataData(Date data){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(data);
	}
	
	public static String dataAtual(){
		Date dataAtual =  new Date();
		String data = formataData(dataAtual);
		return data;
	}
	
	public String retornaDataAtual(){
		Date dataAtual =  new Date();
		String data = formataData(dataAtual);
		return data;
	}

}
