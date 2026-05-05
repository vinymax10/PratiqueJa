package Matematica.Avancado.Combinatoria.Nivel3Package;

import java.util.Random;

public class TextoPermutacaoRepeticao
{
	static ProblemaPermutacaoRepeticao  problemas[]= {
	new ProblemaPermutacaoRepeticao("Quantas anagramas diferentes podem ser formados usando todas as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("De quantas formas distintas podemos organizar todas as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("Quantas palavras distintas podem ser escritas usando todas as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("Considerando todas as letras, quantos arranjos diferentes podemos formar com a palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("Quantas maneiras diferentes existem para ordenar todas as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("De quantos modos diferentes podemos permutar as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("Quantas sequências distintas podem ser formadas rearranjando todas as letras de $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("Quantas palavras, mesmo sem sentido, podem ser formadas com todas as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("Quantos anagramas possíveis podem ser gerados com as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),
	new ProblemaPermutacaoRepeticao("Quantas diferentes combinações de letras podem ser formadas usando todas as letras da palavra $a?", TipoPermutacaoRepeticao.Letra),

	};
	
	public static ProblemaPermutacaoRepeticao getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
