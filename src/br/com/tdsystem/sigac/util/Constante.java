package br.com.tdsystem.sigac.util;


public interface Constante {

	interface AppConstante {
		String APP_NAME = "Sistema xpto";
	}
	
	interface NamedQueries {
		
		String PERFIL_LISTA = "Perfil.lista";
		String PERFIL_CODIGO = "Perfil.codigo";
		String PERFIL_NOME = "Perfil.nome";
		String PERFIL_STATUS = "Perfil.status";
		
		String ALUNO_RECUPERARPORLOGIN = "Aluno.recuperarPorLogin";
		String ALUNO_RECUPERA_LISTA = "Aluno.lista";
		String ALUNO_RECUPERA_CODIGO = "Aluno.codigo";
		String ALUNO_RECUPERA_RA = "Aluno.ra";
		
		String COORDENADOR_RECUPERARPORLOGIN = "Coordenador.recuperarPorLogin";
		String COORDENADOR_RECUPERAR_RA = "Coordenador.recuperarPorRA";
		String COORDENADOR_RECUPERA_LISTA = "Coordenador.lista";
		String COORDENADOR_RECUPERA_CODIGO = "Coordenador.codigo";
		
		String TURMA_LISTA = "Turma.lista";
		String TURMA_CODIGO = "Turma.codigo";
		String TURMA_NOME = "Turma.nome";
		String TURMA_STATUS = "Turma.status";
		
		String TURNO_LISTA = "Turno.lista";
		String TURNO_CODIGO = "Turno.codigo";
		String TURNO_NOME = "Turno.nome";
		String TURNO_STATUS = "Turno.status";
		
		String PERIODO_LISTA = "Periodo.lista";
		String PERIODO_CODIGO = "Periodo.codigo";
		String PERIODO_NOME = "Periodo.nome";
		String PERIODO_STATUS = "Periodo.status";
		
		String ATIVIDADE_LISTA = "Atividade.lista";
		String ATIVIDADE_CODIGO = "Atividade.codigo";
		String ATIVIDADE_NOME = "Atividade.nome";
		String ATIVIDADE_STATUS = "Atividade.status";
		
		String CURSO_LISTA = "Curso.lista";
		String CURSO_CODIGO = "Curso.codigo";
		String CURSO_NOME = "Curso.nome";
		String CURSO_STATUS = "Curso.status";
		
		String ATIVIVIDADE_REALIZADA_LISTA = "AtividadeRealizada.lista";
		String ATIVIVIDADE_REALIZADA_CODIGO = "AtividadeRealizada.codigo";
		String ATIVIVIDADE_REALIZADA_ALUNO = "AtividadeRealizada.aluno";
	}
	
}
