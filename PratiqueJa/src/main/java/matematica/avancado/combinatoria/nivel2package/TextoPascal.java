package matematica.avancado.combinatoria.nivel2package;

import java.util.Random;

public class TextoPascal
{
	static ProblemaPascal  problemas[]= {
	new ProblemaPascal("Quantos subconjuntos possui um conjunto com $n elementos?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Um conjunto tem $n elementos distintos. Quantos subconjuntos podem ser formados (incluindo o vazio e o total)?", TipoPascal.Subconjuntos),
	new ProblemaPascal("De quantas maneiras é possível escolher um subconjunto de um conjunto com $n elementos?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Quantos subconjuntos diferentes existem em um conjunto de $n elementos?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Uma pizzaria oferece $n ingredientes extras. De quantas formas um cliente pode escolher os extras da pizza (podendo não escolher nenhum)?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Um cardápio permite adicionar $n complementos opcionais a um lanche. Quantas combinações de complementos são possíveis?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Qual é a soma dos elementos da linha $n do Triângulo de Pascal?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Um menu de personalização tem $n opções que podem ser ligadas ou desligadas. Quantas configurações distintas existem?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Quantos subconjuntos tem o conjunto formado por $n letras distintas?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Numa votação, cada um dos $n itens pode ser aprovado ou não. Quantos resultados de votação são possíveis?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Quantos subconjuntos podem ser formados a partir de um conjunto de $n cartas distintas?", TipoPascal.Subconjuntos),
	new ProblemaPascal("Um aplicativo tem $n filtros que podem ser ativados independentemente. Quantas combinações de filtros existem?", TipoPascal.Subconjuntos),
//
	new ProblemaPascal("Qual é o valor do coeficiente binomial \\(\\binom{$n}{$k}\\)?", TipoPascal.Binomial),
	new ProblemaPascal("Calcule o coeficiente binomial \\(\\binom{$n}{$k}\\) (posição $k da linha $n do Triângulo de Pascal).", TipoPascal.Binomial),
	new ProblemaPascal("Qual o valor de \\(\\binom{$n}{$k}\\)?", TipoPascal.Binomial),
	new ProblemaPascal("Determine o número combinatório \\(\\binom{$n}{$k}\\).", TipoPascal.Binomial),
	new ProblemaPascal("No Triângulo de Pascal, qual é o elemento na linha $n e posição $k (a partir de 0)?", TipoPascal.Binomial),
	new ProblemaPascal("Calcule o valor de \\(\\binom{$n}{$k}\\), que aparece como coeficiente na expansão binomial.", TipoPascal.Binomial),
	new ProblemaPascal("Qual é o coeficiente binomial \\(\\binom{$n}{$k}\\) usado no Binômio de Newton?", TipoPascal.Binomial),
	new ProblemaPascal("Determine \\(\\binom{$n}{$k}\\).", TipoPascal.Binomial)
	};

	public static ProblemaPascal getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
