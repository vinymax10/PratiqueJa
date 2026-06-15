package matematica.avancado.combinatoria.nivel1package;

import java.util.Random;

public class TextoPermutacaoCircular
{
	static ProblemaPermutacaoCircular  problemas[]= {
	new ProblemaPermutacaoCircular("De quantas formas $n pessoas podem se sentar ao redor de uma mesa redonda?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n crianças podem formar uma roda de ciranda?"),
	new ProblemaPermutacaoCircular("De quantas formas $n cavaleiros podem se sentar ao redor de uma mesa circular?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n convidados podem ser dispostos ao redor de uma mesa redonda?"),
	new ProblemaPermutacaoCircular("De quantas formas $n amigos podem se posicionar em círculo para uma brincadeira?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n pessoas podem dançar de mãos dadas formando uma roda?"),
	new ProblemaPermutacaoCircular("De quantas formas $n diplomatas podem se acomodar ao redor de uma mesa de negociação circular?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n jogadores podem se sentar em volta de uma mesa de pôquer redonda?"),
	new ProblemaPermutacaoCircular("De quantas formas $n contas distintas podem ser dispostas em um colar circular fixo?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n pessoas podem ocupar os lugares ao redor de uma fogueira?"),
	new ProblemaPermutacaoCircular("De quantas formas $n executivos podem se sentar ao redor de uma mesa de reunião circular?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n cadeiras com pessoas distintas podem ser arranjadas em círculo?"),
	new ProblemaPermutacaoCircular("De quantas formas $n participantes podem se posicionar ao redor de uma roleta?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n pessoas podem ser sentadas em volta de uma mesa de jantar redonda?"),
	new ProblemaPermutacaoCircular("De quantas formas $n estudantes podem formar um círculo para um debate?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n bailarinos podem se organizar em uma roda de dança?"),
	new ProblemaPermutacaoCircular("De quantas formas $n pessoas podem se distribuir ao redor de uma mesa circular de jogos?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n chefes de estado podem se sentar em uma mesa-redonda de cúpula?"),
	new ProblemaPermutacaoCircular("De quantas formas $n flores distintas podem ser dispostas ao redor de um arranjo circular?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n pessoas podem se acomodar ao redor de uma mesa giratória?"),
	new ProblemaPermutacaoCircular("De quantas formas $n amigos podem se sentar ao redor de uma mesa circular de café?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n integrantes podem se posicionar em uma roda de capoeira?"),
	new ProblemaPermutacaoCircular("De quantas formas $n pessoas podem se organizar em círculo para uma dinâmica de grupo?"),
	new ProblemaPermutacaoCircular("De quantas maneiras $n convidados podem ser distribuídos em volta de uma mesa redonda de casamento?")
	};

	public static ProblemaPermutacaoCircular getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}

}
