package matematica.avancado.estatistica.nivel2package;

import java.util.Random;

public class TextoMediana
{
	static ProblemaMediana  problemas[]= {
	new ProblemaMediana("As notas de alguns alunos foram: $v. Qual é a mediana?"),
	new ProblemaMediana("As idades (em anos) de um grupo são: $v. Qual é a mediana das idades?"),
	new ProblemaMediana("Os salários (em milhares de reais) de funcionários são: $v. Qual é a mediana salarial?"),
	new ProblemaMediana("As temperaturas (em °C) registradas foram: $v. Qual é a mediana?"),
	new ProblemaMediana("Os preços (em reais) de um produto em várias lojas são: $v. Qual é a mediana dos preços?"),
	new ProblemaMediana("As quantidades de gols por partida foram: $v. Qual é a mediana?"),
	new ProblemaMediana("Os tempos (em minutos) gastos em uma prova foram: $v. Qual é a mediana dos tempos?"),
	new ProblemaMediana("As alturas (em cm) de alguns jogadores são: $v. Qual é a mediana das alturas?"),
	new ProblemaMediana("As pontuações obtidas em um jogo foram: $v. Qual é a mediana?"),
	new ProblemaMediana("Os números de clientes atendidos por dia foram: $v. Qual é a mediana?"),
	new ProblemaMediana("As notas de avaliação de um filme foram: $v. Qual é a mediana?"),
	new ProblemaMediana("As quantidades de chuva (em mm) medidas foram: $v. Qual é a mediana?"),
	new ProblemaMediana("Os pesos (em kg) de encomendas são: $v. Qual é a mediana dos pesos?"),
	new ProblemaMediana("Os números de acertos em simulados foram: $v. Qual é a mediana?")
	};

	public static ProblemaMediana getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
