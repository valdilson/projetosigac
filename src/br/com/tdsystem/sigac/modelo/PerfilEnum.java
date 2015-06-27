package br.com.tdsystem.sigac.modelo;

import java.io.Serializable;

public enum PerfilEnum implements Serializable{

	ALUNO("Aluno"),
	COORDENADOR("Coordenador");
	
	private String descricao;
	
	private PerfilEnum(String descricao) {
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
	
	public static PerfilEnum recuperarPorOrdinal(Integer ordinal) throws IllegalArgumentException {
		try {
			return values()[ordinal];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}
	
	
}
