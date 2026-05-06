package matematica.avancado.combinatoria.nivel2package;

import java.util.Random;

public class TextoArranjoSimples
{
	static ProblemaArranjoSimples  problemas[]= {
	new ProblemaArranjoSimples("De $n livros, quantos modos diferentes podemos escolher e organizar $k em uma prateleira?"),
	new ProblemaArranjoSimples("De $n filmes, quantos jeitos diferentes podemos montar uma maratona com $k filmes?"),
	new ProblemaArranjoSimples("Entre $n corredores, de quantas formas podemos premiar os primeiros $k lugares?"),
	new ProblemaArranjoSimples("Em uma eleição com $n candidatos, de quantas maneiras podemos escolher e ordenar $k vencedores?"),
	new ProblemaArranjoSimples("De $n músicas, quantas playlists diferentes podemos criar escolhendo $k músicas em sequência?"),
	new ProblemaArranjoSimples("De $n cartas, quantas sequências diferentes podemos formar escolhendo $k delas?"),
	new ProblemaArranjoSimples("Entre $n alunos, quantas maneiras de formar uma equipe de $k membros em ordem de apresentação?"),
	new ProblemaArranjoSimples("Com $n atletas, de quantas maneiras podemos formar um pódio de $k posições?"),
	new ProblemaArranjoSimples("De $n quadros, quantas formas podemos organizar $k deles em uma exposição?"),
	new ProblemaArranjoSimples("Com $n livros, de quantas maneiras diferentes podemos organizar $k em uma estante?"),
	new ProblemaArranjoSimples("De $n brinquedos, quantas formas diferentes de arrumar $k brinquedos em fila?"),
	new ProblemaArranjoSimples("De $n camisas, quantas formas de organizar $k delas na vitrine?"),
	new ProblemaArranjoSimples("Com $n séries disponíveis, quantas formas diferentes de escolher $k séries para assistir em sequência?"),
	new ProblemaArranjoSimples("De $n pratos, quantas sequências diferentes de $k pratos podemos montar para um menu especial?"),
	new ProblemaArranjoSimples("Entre $n árvores, quantas formas podemos organizar $k em uma fila para plantação?"),
	new ProblemaArranjoSimples("De $n perfumes, quantas maneiras diferentes de escolher e ordenar $k para presente?"),
	new ProblemaArranjoSimples("Com $n quadros, quantas sequências possíveis podemos montar escolhendo $k deles?"),
	new ProblemaArranjoSimples("De $n livros de receitas, quantas ordens diferentes podemos escolher $k livros?"),
	new ProblemaArranjoSimples("De $n tipos de flores, quantas sequências podemos formar escolhendo $k tipos?"),
	new ProblemaArranjoSimples("Com $n bolsas, quantas maneiras de organizar $k delas em uma exposição?"),
	new ProblemaArranjoSimples("Entre $n sabores de pizza, quantas sequências podemos montar escolhendo $k sabores?"),
	new ProblemaArranjoSimples("Entre $n bandas, quantos shows diferentes podemos montar com $k bandas por noite?"),
	new ProblemaArranjoSimples("De $n alunos, quantas formas diferentes podemos escolher $k alunos para fazer uma apresentação, considerando que a ordem da apresentação importa?"),
	new ProblemaArranjoSimples("De $n filmes, quantos arranjos de $k filmes diferentes podemos organizar para assistir?"),
	new ProblemaArranjoSimples("De $n móveis, quantas formas diferentes de posicionar $k peças em uma sala?"),
	new ProblemaArranjoSimples("De $n sabores de bolo, quantas sequências possíveis podemos fazer com $k sabores?"),
	new ProblemaArranjoSimples("De $n destinos turísticos, quantas formas de planejar $k viagens em sequência?"),
	new ProblemaArranjoSimples("Entre $n estandes, quantas formas diferentes de visitar $k estandes em sequência?"),
	new ProblemaArranjoSimples("De $n opções de cores, quantos arranjos podemos fazer escolhendo $k cores para pintar?"),
	new ProblemaArranjoSimples("De $n nomes, quantas sequências diferentes podemos formar escolhendo $k deles?"),
	new ProblemaArranjoSimples("De $n objetos, quantas formas diferentes podemos organizar $k em uma prateleira?"),
	new ProblemaArranjoSimples("De $n modelos de carro, quantos arranjos podemos montar com $k modelos?"),
	new ProblemaArranjoSimples("Entre $n amigos, quantas sequências de $k pessoas podemos montar para fotos?"),
	new ProblemaArranjoSimples("De $n tipos de café, quantos arranjos podemos montar escolhendo $k tipos diferentes?"),
	new ProblemaArranjoSimples("De $n bolsas, quantas maneiras diferentes de apresentar $k delas em desfile?"),
	new ProblemaArranjoSimples("De $n filmes, quantas sessões diferentes podemos organizar com $k filmes em sequência?"),
	new ProblemaArranjoSimples("De $n peças teatrais, quantas programações podemos montar com $k peças diferentes?"),
	new ProblemaArranjoSimples("De $n remédios, quantas sequências diferentes de $k medicamentos podemos planejar?"),
	new ProblemaArranjoSimples("De $n séries de TV, quantas listas podemos montar escolhendo $k séries em sequência?"),
	new ProblemaArranjoSimples("Entre $n bolas coloridas, quantas filas possíveis com $k bolas diferentes?"),
	new ProblemaArranjoSimples("De $n flores, quantas filas podemos montar escolhendo $k flores distintas?"),
	new ProblemaArranjoSimples("De $n títulos de livro, quantas prateleiras diferentes podemos formar com $k livros?"),
	new ProblemaArranjoSimples("De $n documentários, quantas sessões únicas podemos organizar escolhendo $k deles?"),
	new ProblemaArranjoSimples("Entre $n ingredientes, quantas sequências de pratos podemos montar escolhendo $k deles?"),
	new ProblemaArranjoSimples("De $n cores de tinta, quantos arranjos podemos montar usando $k cores em sequência?"),
	new ProblemaArranjoSimples("Entre $n planetas, quantas missões de $k planetas diferentes podemos organizar?"),
	new ProblemaArranjoSimples("De $n filmes animados, quantas sessões distintas podemos organizar com $k animações?"),
	new ProblemaArranjoSimples("De $n capas de revista, quantas formas de expor $k capas diferentes?"),
	new ProblemaArranjoSimples("De $n destinos de viagem, quantas rotas podemos montar escolhendo $k destinos em ordem?"),

	};
	
	public static ProblemaArranjoSimples getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
