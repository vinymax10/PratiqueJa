package Matematica.Avancado.Combinatoria.Nivel3Package;

import java.util.Random;

public class TextoCombinacaoRepeticao
{
	static ProblemaCombinacaoRepeticao  problemas[]= {
	new ProblemaCombinacaoRepeticao("De quantas formas podemos escolher $a frutas entre $b tipos diferentes, permitindo repetições e sem importar a ordem?"),
	new ProblemaCombinacaoRepeticao("Uma criança escolhe $a balas entre $b sabores diferentes. Quantas combinações possíveis existem com repetição?"),
	new ProblemaCombinacaoRepeticao("Em uma floricultura há $b tipos de flores. Quantas combinações com $a flores são possíveis, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantos sucos diferentes podem ser feitos com $a frutas escolhidas entre $b tipos, permitindo repetições?"),
	new ProblemaCombinacaoRepeticao("Quantos grupos de $a moedas podem ser formados usando $b valores diferentes, com repetição e sem ordem?"),
	new ProblemaCombinacaoRepeticao("De quantas maneiras podemos formar uma combinação de $a doces com $b sabores, podendo repetir sabores?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a camisetas podem ser escolhidas entre $b modelos diferentes, com repetição?"),
	new ProblemaCombinacaoRepeticao("Uma bolsa pode conter $a itens de maquiagem escolhidos entre $b tipos. Quantas combinações existem com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantos buquês com $a flores podem ser feitos com $b variedades, podendo repetir e sem ordem?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a bolas são possíveis usando $b cores, com repetição e sem ordem?"),
	new ProblemaCombinacaoRepeticao("Quantos copos com $a bolas de sorvete podem ser montados com $b sabores, com repetição e sem ordem?"),
	new ProblemaCombinacaoRepeticao("Com $b tipos de adesivos, quantas combinações de $a podem ser feitas, permitindo repetições?"),
	new ProblemaCombinacaoRepeticao("Quantas formas de combinar $a bijuterias entre $b opções diferentes, com repetição, existem?"),
	new ProblemaCombinacaoRepeticao("Quantos kits com $a lápis podem ser montados com $b cores diferentes, com repetição?"),
	new ProblemaCombinacaoRepeticao("Escolhendo $a peças de dominó entre $b tipos disponíveis com repetição, quantas combinações são possíveis?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a remédios podem ser formadas a partir de $b opções, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas cestas com $a frutas podem ser feitas com $b tipos, com repetição e sem ordem?"),
	new ProblemaCombinacaoRepeticao("Um estudante escolhe $a materiais escolares entre $b tipos. Quantas combinações existem com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas formas há de escolher $a figurinhas entre $b disponíveis, com repetição e sem importar a ordem?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a sobremesas são possíveis escolhendo entre $b opções, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas formas de selecionar $a canetas entre $b cores diferentes, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a flores podem ser escolhidas entre $b espécies, com repetição?"),
	new ProblemaCombinacaoRepeticao("Montar um mix com $a sabores entre $b disponíveis. Quantas combinações possíveis com repetição?"),
	new ProblemaCombinacaoRepeticao("Com $b tipos de suco, quantas combinações de $a copos são possíveis, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a papéis coloridos podem ser feitas com $b cores disponíveis, com repetição?"),
	new ProblemaCombinacaoRepeticao("Escolher $a bolas entre $b cores, com repetição e sem importar a ordem. Quantas combinações possíveis?"),
	new ProblemaCombinacaoRepeticao("Com $b tipos de perfume, quantas combinações de $a unidades podem ser feitas, com repetição?"),
	new ProblemaCombinacaoRepeticao("Montar uma cesta com $a itens entre $b produtos, com repetição. Quantas combinações diferentes?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a pulseiras podem ser feitas com $b tipos de contas, com repetição?"),
	new ProblemaCombinacaoRepeticao("Com $b sabores de bolo, quantas combinações de $a camadas podem ser feitas, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantos conjuntos de $a lápis podem ser montados com $b cores diferentes, com repetição?"),
	new ProblemaCombinacaoRepeticao("Com $b opções de sorvete, quantas combinações de $a bolas podem ser escolhidas, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a meias podem ser escolhidas entre $b cores, com repetição?"),
	new ProblemaCombinacaoRepeticao("De quantas formas é possível fazer um mix de $a nozes entre $b tipos, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a tintas podem ser escolhidas entre $b cores, com repetição e sem ordem?"),

	new ProblemaCombinacaoRepeticao("De quantas formas é possível escolher $a frutas entre $b tipos diferentes, podendo repetir as frutas?"),
	new ProblemaCombinacaoRepeticao("Uma sorveteria tem $b sabores. De quantas formas se pode montar um pote com $a bolas, podendo repetir sabores?"),
	new ProblemaCombinacaoRepeticao("De quantas formas se pode formar um buquê com $a flores de $b tipos diferentes, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações possíveis há ao escolher $a camisetas entre $b modelos, podendo repetir os modelos?"),
	new ProblemaCombinacaoRepeticao("Quantos conjuntos de $a canetas podem ser feitos com $b cores disponíveis, com repetição permitida?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a bolas podem ser feitas com $b cores disponíveis, se a cor pode se repetir?"),
	new ProblemaCombinacaoRepeticao("Quantos kits de $a lápis podem ser formados com $b cores distintas, podendo repetir cor?"),
	new ProblemaCombinacaoRepeticao("Quantos conjuntos de $a moedas são possíveis com $b valores diferentes, podendo repetir valores?"),
	new ProblemaCombinacaoRepeticao("De quantas formas $a produtos podem ser selecionados entre $b categorias, com repetição permitida?"),
	new ProblemaCombinacaoRepeticao("Quantos conjuntos com $a objetos podem ser formados a partir de $b diferentes tipos, com repetição?"),
	new ProblemaCombinacaoRepeticao("Quantos grupos de $a números são possíveis escolhendo entre $b números, com repetição permitida?"),
	new ProblemaCombinacaoRepeticao("Quantos pacotes de $a figurinhas podem ser montados com $b tipos disponíveis, repetindo figurinhas?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a sabores podem ser feitas com $b opções, com repetição permitida?"),
	new ProblemaCombinacaoRepeticao("Quantas maneiras de montar um mix com $a frutas entre $b variedades, repetindo frutas?"),
	new ProblemaCombinacaoRepeticao("Quantos conjuntos de $a botões podem ser escolhidos entre $b cores, permitindo repetição de cor?"),
	new ProblemaCombinacaoRepeticao("De quantas formas se pode encher $a frascos com $b essências, permitindo repetir as essências?"),
	new ProblemaCombinacaoRepeticao("Quantas maneiras de escolher $a enfeites existem entre $b tipos, com repetição permitida?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a meias podem ser formadas com $b modelos, podendo repetir modelos?"),
	new ProblemaCombinacaoRepeticao("Quantos pedidos de $a pratos podem ser feitos entre $b tipos no cardápio, permitindo repetir pratos?"),
	new ProblemaCombinacaoRepeticao("De quantas formas se pode montar um kit de $a lápis escolhendo entre $b cores com reposição?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações de $a adesivos podem ser feitas com $b modelos disponíveis, repetindo modelos?"),
	new ProblemaCombinacaoRepeticao("Quantas formas há de fazer $a escolhas entre $b produtos, com repetição permitida?"),
	new ProblemaCombinacaoRepeticao("Quantos conjuntos de $a tintas podem ser escolhidos entre $b cores, repetição permitida?"),
	new ProblemaCombinacaoRepeticao("Quantas combinações com $a peças podem ser montadas com $b tipos disponíveis, repetindo tipos?"),
	new ProblemaCombinacaoRepeticao("Quantos conjuntos de $a doces podem ser montados a partir de $b opções, com repetição?"),

	};
	
	public static ProblemaCombinacaoRepeticao getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
