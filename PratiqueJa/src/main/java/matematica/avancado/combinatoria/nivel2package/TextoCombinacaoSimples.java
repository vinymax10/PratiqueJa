package matematica.avancado.combinatoria.nivel2package;

import java.util.Random;

public class TextoCombinacaoSimples
{
	static ProblemaCombinacaoSimples  problemas[]= {
	new ProblemaCombinacaoSimples("De $n livros, quantas formas de escolher $k para ler?"),
	new ProblemaCombinacaoSimples("Entre $n flores, de quantas maneiras podemos formar um buquê com $k delas?"),
	new ProblemaCombinacaoSimples("Com $n alunos, quantos grupos de $k podemos formar?"),
	new ProblemaCombinacaoSimples("De $n frutas, quantas combinações de $k podemos montar para uma salada?"),
	new ProblemaCombinacaoSimples("De $n brinquedos, quantas seleções de $k brinquedos podemos fazer?"),
	new ProblemaCombinacaoSimples("Com $n sabores de sorvete, quantas combinações de $k sabores podemos escolher?"),
	new ProblemaCombinacaoSimples("De $n peças de roupa, quantos conjuntos de $k peças diferentes podemos formar?"),
	new ProblemaCombinacaoSimples("Entre $n pessoas, de quantas formas podemos montar um comitê de $k integrantes?"),
	new ProblemaCombinacaoSimples("Com $n modelos de celular, quantas escolhas de $k modelos podemos fazer?"),
	new ProblemaCombinacaoSimples("De $n cores, quantos grupos de $k cores podemos selecionar?"),
	new ProblemaCombinacaoSimples("Entre $n pratos no cardápio, de quantas maneiras podemos pedir $k pratos?"),
	new ProblemaCombinacaoSimples("Com $n tipos de queijo, quantas combinações de $k tipos podemos fazer?"),
	new ProblemaCombinacaoSimples("De $n amigos, quantos grupos de $k amigos podemos formar para uma viagem?"),
	new ProblemaCombinacaoSimples("Entre $n filmes, quantas maratonas com $k filmes podemos montar?"),
	new ProblemaCombinacaoSimples("De $n jogos de tabuleiro, quantos podemos escolher para jogar $k partidas diferentes?"),
	new ProblemaCombinacaoSimples("Entre $n opções de presente, quantas combinações de $k presentes podemos fazer?"),
	new ProblemaCombinacaoSimples("De $n destinos turísticos, quantas viagens diferentes com $k destinos podemos planejar?"),
	new ProblemaCombinacaoSimples("Com $n cursos, quantas combinações de $k cursos podemos escolher?"),
	new ProblemaCombinacaoSimples("Entre $n animais no zoológico, quantos grupos de $k animais podemos visitar?"),
	new ProblemaCombinacaoSimples("De $n sabores de bolo, quantas combinações de $k sabores diferentes podemos escolher?"),
	new ProblemaCombinacaoSimples("Com $n monumentos turísticos, quantas combinações de $k podemos visitar?"),
	new ProblemaCombinacaoSimples("De $n séries disponíveis, quantos grupos de $k séries diferentes podemos assistir?"),
	new ProblemaCombinacaoSimples("Entre $n modelos de carro, quantas escolhas de $k modelos diferentes podemos fazer?"),
	new ProblemaCombinacaoSimples("Com $n acessórios, quantos conjuntos de $k acessórios podemos montar?"),
	new ProblemaCombinacaoSimples("De $n quadros, quantas exposições com $k quadros diferentes podemos fazer?"),
	new ProblemaCombinacaoSimples("Entre $n tipos de café, quantas seleções de $k tipos podemos montar?"),
	new ProblemaCombinacaoSimples("De $n jogos eletrônicos, quantos grupos de $k jogos podemos escolher para jogar?"),
	new ProblemaCombinacaoSimples("Com $n tipos de flores, quantas combinações de $k flores para um arranjo podemos fazer?"),
	new ProblemaCombinacaoSimples("Entre $n modelos de sapato, quantos grupos de $k modelos podemos selecionar?"),
	new ProblemaCombinacaoSimples("De $n capas de revista, quantas seleções de $k capas podemos fazer para exposição?"),
	new ProblemaCombinacaoSimples("Com $n cartões, quantas combinações de $k cartões podemos escolher para enviar?"),
	new ProblemaCombinacaoSimples("Entre $n pratos típicos, quantas combinações de $k pratos podemos montar?"),
	new ProblemaCombinacaoSimples("De $n projetos, quantos conjuntos de $k projetos diferentes podemos apresentar?"),
	new ProblemaCombinacaoSimples("Com $n estandes, quantas combinações de $k estandes para visitar podemos formar?"),
	new ProblemaCombinacaoSimples("Entre $n tipos de doces, quantas combinações de $k doces podemos escolher?"),
	new ProblemaCombinacaoSimples("De $n programas de TV, quantas seleções de $k programas podemos fazer?"),
	new ProblemaCombinacaoSimples("Entre $n bandas musicais, quantos grupos de $k bandas podemos montar para um festival?"),
	new ProblemaCombinacaoSimples("De $n sabores de pizza, quantas combinações de $k sabores podemos montar?"),
	new ProblemaCombinacaoSimples("Com $n cachorros em um abrigo, quantas combinações de $k cães para adoção podemos fazer?"),
	new ProblemaCombinacaoSimples("De $n marcas de perfume, quantas combinações de $k perfumes podemos escolher?"),
	new ProblemaCombinacaoSimples("Entre $n modelos de relógio, quantos grupos de $k modelos podemos selecionar?"),
	new ProblemaCombinacaoSimples("De $n ingredientes, quantas combinações de $k ingredientes podemos usar para um prato?"),
	new ProblemaCombinacaoSimples("Com $n opções de passeio, quantas escolhas de $k passeios podemos fazer?"),
	new ProblemaCombinacaoSimples("Entre $n personagens, quantos grupos de $k personagens podemos montar para um jogo?"),
	new ProblemaCombinacaoSimples("De $n programas de computador, quantas seleções de $k podemos instalar?"),
	new ProblemaCombinacaoSimples("Entre $n quadros, quantas combinações de $k quadros para uma galeria podemos fazer?"),
	new ProblemaCombinacaoSimples("De $n tipos de massa, quantas combinações de $k tipos diferentes podemos fazer para o almoço?"),
	new ProblemaCombinacaoSimples("Com $n ferramentas, quantas seleções de $k ferramentas podemos fazer?"),
	new ProblemaCombinacaoSimples("De $n tipos de chá, quantas combinações de $k chás podemos escolher para degustar?"),
	new ProblemaCombinacaoSimples("Entre $n revistas, quantos conjuntos de $k revistas podemos selecionar?"),
	new ProblemaCombinacaoSimples("De $n peças de teatro, quantas programações com $k peças podemos montar?"),
	new ProblemaCombinacaoSimples("Com $n roupas, quantas combinações de $k peças diferentes podemos fazer para um desfile?"),
	new ProblemaCombinacaoSimples("Entre $n instrumentos musicais, quantos conjuntos de $k instrumentos podemos escolher?"),
	new ProblemaCombinacaoSimples("De $n jogos de videogame, quantos conjuntos de $k jogos podemos formar?"),
	new ProblemaCombinacaoSimples("Entre $n estandes de feira, quantas combinações de $k estandes podemos visitar?"),
	new ProblemaCombinacaoSimples("De $n modelos de bicicleta, quantos grupos de $k modelos diferentes podemos escolher?"),
	new ProblemaCombinacaoSimples("Com $n documentários, quantas sessões com $k documentários diferentes podemos organizar?"),
	};
	
	public static ProblemaCombinacaoSimples getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
