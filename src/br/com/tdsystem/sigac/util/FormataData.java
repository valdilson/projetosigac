package br.com.tdsystem.sigac.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {
	
	public static String formataData(Date data){
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
		return format.format(data);
	}

}
