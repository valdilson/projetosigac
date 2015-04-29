package br.com.tdsystem.sigac.util;

public class GeraTabela {

	public static void main(String[] args) {
		System.out.println("Gerando....");
		HibernateUtil.getSessionFactory();
		System.out.println("Depois do getSession....");
		HibernateUtil.getSessionFactory().close();
		System.out.println("Fim....");
	}

}
