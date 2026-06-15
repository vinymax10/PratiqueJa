package matematica.avancado.estatistica.nivel2package;

import java.util.Random;

public class TextoMediaPonderada
{
	static ProblemaMediaPonderada  problemas[]= {
	new ProblemaMediaPonderada("Em uma disciplina, as notas e seus pesos foram: $d. Qual é a média ponderada do aluno?"),
	new ProblemaMediaPonderada("Um produto recebeu avaliações com pesos diferentes: $d. Qual é a nota média ponderada?"),
	new ProblemaMediaPonderada("As notas de um concurso por etapa, com seus pesos, foram: $d. Qual é a média ponderada final?"),
	new ProblemaMediaPonderada("Um candidato obteve as seguintes notas e pesos nas provas: $d. Qual é a média ponderada?"),
	new ProblemaMediaPonderada("Os componentes da nota final e seus pesos são: $d. Qual é a média ponderada?"),
	new ProblemaMediaPonderada("Em uma seleção, os critérios receberam notas e pesos: $d. Qual é a pontuação média ponderada?"),
	new ProblemaMediaPonderada("As notas de um trimestre por instrumento avaliativo, com pesos, foram: $d. Qual é a média ponderada?"),
	new ProblemaMediaPonderada("Um investimento teve rendimentos com pesos distintos: $d. Qual é o rendimento médio ponderado?"),
	new ProblemaMediaPonderada("As notas em diferentes módulos do curso, com seus pesos, foram: $d. Qual é a média ponderada?"),
	new ProblemaMediaPonderada("Os índices de qualidade avaliados, com pesos, foram: $d. Qual é o índice médio ponderado?"),
	new ProblemaMediaPonderada("As notas de uma avaliação e seus respectivos pesos são: $d. Calcule a média ponderada."),
	new ProblemaMediaPonderada("Em um processo seletivo, as fases tiveram notas e pesos: $d. Qual é a média ponderada?")
	};

	public static ProblemaMediaPonderada getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
