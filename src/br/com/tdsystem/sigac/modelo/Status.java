package br.com.tdsystem.sigac.modelo;

public enum Status {

	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String descricao;
	
	private Status(String descricao) {
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
	
	public static Status recuperarPorOrdinal(Integer ordinal) throws IllegalArgumentException {
		try {
			return values()[ordinal];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}
	
}
