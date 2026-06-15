package matematica.avancado.estatistica.nivel2package;

import java.util.Random;

public class TextoFrequencia
{
	static ProblemaFrequencia  problemas[]= {
	new ProblemaFrequencia("Em uma pesquisa, o número de filhos por família (filhos → nº de famílias) foi: $d. Quantas famílias têm no máximo $k filhos?", TipoFrequencia.AteNoMaximo),
	new ProblemaFrequencia("O número de gols por jogo (gols → nº de jogos) foi: $d. Em quantos jogos houve no máximo $k gols?", TipoFrequencia.AteNoMaximo),
	new ProblemaFrequencia("A quantidade de acertos em uma prova (acertos → nº de alunos) foi: $d. Quantos alunos tiveram no máximo $k acertos?", TipoFrequencia.AteNoMaximo),
	new ProblemaFrequencia("O número de pets por residência (pets → nº de residências) foi: $d. Quantas residências têm no máximo $k pets?", TipoFrequencia.AteNoMaximo),
	new ProblemaFrequencia("O número de faltas por aluno (faltas → nº de alunos) foi: $d. Quantos alunos tiveram no máximo $k faltas?", TipoFrequencia.AteNoMaximo),
	new ProblemaFrequencia("A quantidade de reclamações por dia (reclamações → nº de dias) foi: $d. Em quantos dias houve no máximo $k reclamações?", TipoFrequencia.AteNoMaximo),
	new ProblemaFrequencia("O número de livros lidos por mês (livros → nº de pessoas) foi: $d. Quantas pessoas leram no máximo $k livros?", TipoFrequencia.AteNoMaximo),
//
	new ProblemaFrequencia("Em uma pesquisa, o número de filhos por família (filhos → nº de famílias) foi: $d. Quantas famílias têm pelo menos $k filhos?", TipoFrequencia.PeloMenos),
	new ProblemaFrequencia("O número de gols por jogo (gols → nº de jogos) foi: $d. Em quantos jogos houve pelo menos $k gols?", TipoFrequencia.PeloMenos),
	new ProblemaFrequencia("A quantidade de acertos em uma prova (acertos → nº de alunos) foi: $d. Quantos alunos tiveram pelo menos $k acertos?", TipoFrequencia.PeloMenos),
	new ProblemaFrequencia("O número de pets por residência (pets → nº de residências) foi: $d. Quantas residências têm pelo menos $k pets?", TipoFrequencia.PeloMenos),
	new ProblemaFrequencia("O número de viagens por ano (viagens → nº de pessoas) foi: $d. Quantas pessoas fizeram pelo menos $k viagens?", TipoFrequencia.PeloMenos),
	new ProblemaFrequencia("A quantidade de cursos concluídos (cursos → nº de funcionários) foi: $d. Quantos funcionários concluíram pelo menos $k cursos?", TipoFrequencia.PeloMenos),
	new ProblemaFrequencia("O número de vendas por dia (vendas → nº de dias) foi: $d. Em quantos dias houve pelo menos $k vendas?", TipoFrequencia.PeloMenos)
	};

	public static ProblemaFrequencia getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
