package br.com.tdsystem.sigac.modelo;

public enum Periodo {

	PRIMEIRO("1º Periodo"),
	SEGUNDO("2º Periodo"),
	TERCEIRO("3º Periodo"),
	QUARTO("4º Periodo"),
	QUINTO("5º Periodo"),
	SEXTO("6º Periodo"),
	SETIMO("7º Periodo"),
	OITAVO("8º Periodo"),
	NONO("9º Periodo"),
	DECIMO("10º Periodo");
	
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
