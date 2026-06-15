package matematica.avancado.combinatoria.nivel3package;

import java.util.Random;

public class TextoCombinacaoRestricao
{
	static ProblemaCombinacaoRestricao  problemas[]= {
	new ProblemaCombinacaoRestricao("Um grupo tem $a homens e $b mulheres. Quantas comissões de $c pessoas podem ser formadas com pelo menos 1 mulher?"),
	new ProblemaCombinacaoRestricao("Em uma turma há $a meninos e $b meninas. Quantas equipes de $c alunos podem ser montadas contendo pelo menos 1 menina?"),
	new ProblemaCombinacaoRestricao("Uma empresa tem $a analistas e $b gerentes. Quantos grupos de trabalho de $c pessoas podem ser formados com pelo menos 1 gerente?"),
	new ProblemaCombinacaoRestricao("Há $a jogadores de linha e $b goleiros. Quantas seleções de $c atletas podem ser feitas incluindo pelo menos 1 goleiro?"),
	new ProblemaCombinacaoRestricao("Em um hospital há $a enfermeiros e $b médicos. Quantas equipes de plantão de $c profissionais têm pelo menos 1 médico?"),
	new ProblemaCombinacaoRestricao("Uma banca tem $a livros de romance e $b de poesia. Quantas seleções de $c livros contêm pelo menos 1 de poesia?"),
	new ProblemaCombinacaoRestricao("Em um coral há $a tenores e $b sopranos. Quantos grupos de $c cantores podem ser formados com pelo menos 1 soprano?"),
	new ProblemaCombinacaoRestricao("Uma cesta tem $a maçãs e $b laranjas. De quantas formas escolher $c frutas incluindo pelo menos 1 laranja?"),
	new ProblemaCombinacaoRestricao("Há $a funcionários do setor A e $b do setor B. Quantas comissões de $c pessoas têm pelo menos 1 funcionário do setor B?"),
	new ProblemaCombinacaoRestricao("Em um clube há $a adultos e $b crianças. Quantos grupos de $c participantes podem ser formados com pelo menos 1 criança?"),
	new ProblemaCombinacaoRestricao("Uma loja tem $a camisas azuis e $b camisas vermelhas. De quantas formas escolher $c camisas incluindo pelo menos 1 vermelha?"),
	new ProblemaCombinacaoRestricao("Em um laboratório há $a químicos e $b biólogos. Quantas equipes de pesquisa de $c pessoas têm pelo menos 1 biólogo?"),
	new ProblemaCombinacaoRestricao("Há $a professores de exatas e $b de humanas. Quantas bancas de $c professores podem ser montadas com pelo menos 1 de humanas?"),
	new ProblemaCombinacaoRestricao("Uma equipe tem $a programadores e $b designers. Quantos times de $c pessoas podem ser formados com pelo menos 1 designer?"),
	new ProblemaCombinacaoRestricao("Em uma reunião há $a representantes nacionais e $b internacionais. Quantos comitês de $c membros têm pelo menos 1 internacional?"),
	new ProblemaCombinacaoRestricao("Há $a violinistas e $b violoncelistas. Quantos conjuntos de $c músicos podem ser formados com pelo menos 1 violoncelista?"),
	new ProblemaCombinacaoRestricao("Uma estante tem $a gibis e $b revistas. De quantas formas escolher $c itens incluindo pelo menos 1 revista?"),
	new ProblemaCombinacaoRestricao("Em uma startup há $a sócios e $b investidores. Quantos conselhos de $c pessoas têm pelo menos 1 investidor?")
	};

	public static ProblemaCombinacaoRestricao getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
