package matematica.avancado.estatistica.nivel1package;

import java.util.Random;

public class TextoMediaSimples
{
	static ProblemaMediaSimples  problemas[]= {
	new ProblemaMediaSimples("As notas obtidas por um grupo de alunos foram: $v. Qual é a média aritmética dessas notas?"),
	new ProblemaMediaSimples("As idades, em anos, de algumas pessoas são: $v. Qual é a idade média do grupo?"),
	new ProblemaMediaSimples("As temperaturas (em °C) registradas em alguns dias foram: $v. Qual é a temperatura média?"),
	new ProblemaMediaSimples("O número de gols marcados por um time em algumas partidas foi: $v. Qual é a média de gols por partida?"),
	new ProblemaMediaSimples("Os salários (em milhares de reais) de alguns funcionários são: $v. Qual é o salário médio?"),
	new ProblemaMediaSimples("As quantidades de livros lidos por mês foram: $v. Qual é a média mensal de livros lidos?"),
	new ProblemaMediaSimples("Os pontos obtidos por um jogador em cada rodada foram: $v. Qual é a média de pontos por rodada?"),
	new ProblemaMediaSimples("As alturas (em cm acima de 1 metro) de algumas plantas são: $v. Qual é a altura média?"),
	new ProblemaMediaSimples("O número de clientes atendidos por dia em uma loja foi: $v. Qual é a média diária de clientes?"),
	new ProblemaMediaSimples("As notas de um candidato em diferentes provas foram: $v. Qual é a média aritmética?"),
	new ProblemaMediaSimples("As quantidades de chuva (em mm) medidas em alguns dias foram: $v. Qual é a média de chuva?"),
	new ProblemaMediaSimples("O número de acertos de um estudante em vários simulados foi: $v. Qual é a média de acertos?"),
	new ProblemaMediaSimples("As vendas diárias (em unidades) de um produto foram: $v. Qual é a média de vendas por dia?"),
	new ProblemaMediaSimples("As notas de avaliação de um filme dadas por críticos foram: $v. Qual é a nota média?"),
	new ProblemaMediaSimples("Os tempos (em minutos) gastos em algumas tarefas foram: $v. Qual é o tempo médio?")
	};

	public static ProblemaMediaSimples getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
