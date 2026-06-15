package matematica.avancado.estatistica.nivel1package;

import java.util.Random;

public class TextoModa
{
	static ProblemaModa  problemas[]= {
	new ProblemaModa("Os números de calçado vendidos em uma loja foram: $v. Qual é a moda?"),
	new ProblemaModa("As notas de uma turma em uma prova foram: $v. Qual é a moda das notas?"),
	new ProblemaModa("O número de filhos por família em uma pesquisa foi: $v. Qual é a moda?"),
	new ProblemaModa("As idades dos participantes de um curso são: $v. Qual é a idade modal?"),
	new ProblemaModa("As quantidades de gols por jogo de um campeonato foram: $v. Qual é a moda?"),
	new ProblemaModa("Os tamanhos de camiseta mais pedidos foram registrados como: $v. Qual é a moda?"),
	new ProblemaModa("O número de acertos dos alunos em um quiz foi: $v. Qual é a moda?"),
	new ProblemaModa("As temperaturas registradas (em °C) ao longo de alguns dias foram: $v. Qual é a moda?"),
	new ProblemaModa("As notas atribuídas a um produto por clientes foram: $v. Qual é a moda?"),
	new ProblemaModa("O número de pets por residência em um bairro foi: $v. Qual é a moda?"),
	new ProblemaModa("As quantidades de livros emprestados por dia foram: $v. Qual é a moda?"),
	new ProblemaModa("Os pontos obtidos por jogadores em uma partida foram: $v. Qual é a moda?"),
	new ProblemaModa("As respostas a uma enquete (de 1 a 10) foram: $v. Qual é a moda?"),
	new ProblemaModa("O número de horas de estudo por dia de um estudante foi: $v. Qual é a moda?")
	};

	public static ProblemaModa getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
