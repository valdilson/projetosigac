package br.com.tdsystem.sigac.modelo;

public enum Periodo {

	
	PRIMEIRO("1� Periodo"),
	SEGUNDO("2� Periodo"),
	TERCEIRO("3� Periodo"),
	QUARTO("4� Periodo"),
	QUINTO("5� Periodo"),
	SEXTO("6� Periodo"),
	SETIMO("7� Periodo"),
	OITAVO("8� Periodo"),
	NONO("9� Periodo"),
	DECIMO("10� Periodo");
	
	private String descricao;
	
	private Periodo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Status recuperarPorDescricao(String descricao) throws IllegalArgumentException {
		Status[] arrayStatus = Status.values();
		for (Status status : arrayStatus) {
			if (status.getDescricao().equalsIgnoreCase(descricao)) {
				return status;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public static Periodo recuperarPorOrdinal(Integer ordinal) throws IllegalArgumentException {
		try {
			return values()[ordinal];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}
	
}
