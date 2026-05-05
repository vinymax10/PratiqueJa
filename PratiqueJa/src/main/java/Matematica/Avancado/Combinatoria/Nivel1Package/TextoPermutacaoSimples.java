package Matematica.Avancado.Combinatoria.Nivel1Package;

import java.util.Random;

public class TextoPermutacaoSimples
{
	static ProblemaPermutacaoSimples  problemas[]= {
	new ProblemaPermutacaoSimples("De quantas formas diferentes $n pessoas podem ser organizadas em uma fila?"),
	new ProblemaPermutacaoSimples("Quantas maneiras diferentes podemos organizar $n livros distintos em uma estante?"),
	new ProblemaPermutacaoSimples("De quantas formas $n cartas distintas podem ser distribuídas em uma ordem?"),
	new ProblemaPermutacaoSimples("Quantas formas diferentes podemos dispor $n bolas coloridas em linha?"),
	new ProblemaPermutacaoSimples("Em uma corrida, de quantas formas $n corredores podem chegar?"),
	new ProblemaPermutacaoSimples("De quantas maneiras podemos ordenar $n nomes diferentes em uma lista?"),
	new ProblemaPermutacaoSimples("Quantas sequências diferentes podem ser formadas com $n letras distintas?"),
	new ProblemaPermutacaoSimples("De quantas formas $n números únicos podem ser posicionados lado a lado?"),
	new ProblemaPermutacaoSimples("Quantas maneiras de sentar $n pessoas em uma fileira existem?"),
	new ProblemaPermutacaoSimples("Quantas ordens diferentes de execução para $n tarefas distintas são possíveis?"),
	new ProblemaPermutacaoSimples("De quantas formas podemos colocar $n fotos em sequência num mural?"),
	new ProblemaPermutacaoSimples("Quantos modos diferentes podemos listar $n países distintos?"),
	new ProblemaPermutacaoSimples("Quantas maneiras diferentes podemos empilhar $n caixas diferentes?"),
	new ProblemaPermutacaoSimples("Quantas filas diferentes com $n pessoas distintas podem ser feitas?"),
	new ProblemaPermutacaoSimples("De quantas formas $n ingressos diferentes podem ser distribuídos em sequência?"),
	new ProblemaPermutacaoSimples("Quantos arranjos diferentes com $n livros podemos fazer em uma prateleira?"),
	new ProblemaPermutacaoSimples("Quantas ordens possíveis para $n slides distintos existem numa apresentação?"),
	new ProblemaPermutacaoSimples("De quantas formas podemos escrever $n letras únicas em sequência?"),
	new ProblemaPermutacaoSimples("Quantas ordens diferentes podemos fazer com $n produtos diferentes na vitrine?"),
	new ProblemaPermutacaoSimples("Quantas sequências possíveis com $n notas musicais diferentes podem existir?"),
	new ProblemaPermutacaoSimples("Quantos times podem ser organizados em uma ordem específica com $n atletas?"),
	new ProblemaPermutacaoSimples("Quantos resultados possíveis para $n candidatos em uma eleição?"),
	new ProblemaPermutacaoSimples("De quantas formas podemos ordenar $n sabores diferentes de sorvete?"),
	new ProblemaPermutacaoSimples("Quantas disposições possíveis para $n objetos diferentes sobre uma mesa?"),
	new ProblemaPermutacaoSimples("Quantas ordens diferentes podemos fazer com $n palavras distintas?"),
	new ProblemaPermutacaoSimples("Quantas classificações possíveis para $n corredores numa maratona?"),
	new ProblemaPermutacaoSimples("Quantas formas diferentes podemos organizar $n brinquedos distintos em fila?"),
	new ProblemaPermutacaoSimples("De quantas formas podemos dispor $n documentos diferentes em uma pasta?"),
	new ProblemaPermutacaoSimples("Quantas maneiras existem de ordenar $n quadros únicos em uma galeria?"),
	new ProblemaPermutacaoSimples("Quantos modos diferentes de exibir $n peças distintas em um museu?"),
	new ProblemaPermutacaoSimples("Quantas maneiras de listar $n tarefas diárias diferentes existem?"),
	new ProblemaPermutacaoSimples("De quantas formas diferentes podemos programar $n shows em sequência?"),
	new ProblemaPermutacaoSimples("Quantas ordens distintas de $n elementos químicos únicos podem ser listadas?"),
	new ProblemaPermutacaoSimples("Quantas maneiras de ordenar $n produtos diferentes em uma prateleira?"),
	new ProblemaPermutacaoSimples("Quantas sequências únicas podemos formar com $n livros escolares distintos?"),
	new ProblemaPermutacaoSimples("Quantas maneiras diferentes de organizar $n CDs de música numa prateleira?"),
	new ProblemaPermutacaoSimples("De quantas formas $n quadros podem ser dispostos em uma galeria?"),
	new ProblemaPermutacaoSimples("De quantas maneiras diferentes podemos arranjar $n brinquedos em uma fila?"),
	new ProblemaPermutacaoSimples("Quantas ordens diferentes podemos formar com $n livros na prateleira?"),
	new ProblemaPermutacaoSimples("De quantas formas $n tarefas podem ser distribuídas entre $n pessoas?"),
	new ProblemaPermutacaoSimples("Quantas formas diferentes podemos organizar $n peças de roupa em um armário?"),
	new ProblemaPermutacaoSimples("De quantas formas podemos organizar $n filmes diferentes em uma lista?"),
	new ProblemaPermutacaoSimples("Quantas maneiras diferentes podemos posicionar $n letras de um alfabeto em ordem?"),
	new ProblemaPermutacaoSimples("Quantas possibilidades de ordem existem para $n carros estacionados em fila?"),
	new ProblemaPermutacaoSimples("De quantas formas podemos arranjar $n objetos diferentes em uma prateleira?"),
	new ProblemaPermutacaoSimples("Quantos diferentes resultados podem ser formados com $n itens de uma lista?"),
	new ProblemaPermutacaoSimples("De quantas formas diferentes $n jogadores podem ser escalados para $n posições?"),
	new ProblemaPermutacaoSimples("Quantos arranjos possíveis podemos fazer com $n números diferentes?"),
	new ProblemaPermutacaoSimples("Quantas maneiras de organizar $n frutas em um cesto existem?"),
	new ProblemaPermutacaoSimples("Quantas formas diferentes podemos organizar $n músicas numa playlist?"),
	new ProblemaPermutacaoSimples("De quantas maneiras diferentes podemos organizar $n alunos em uma sala?"),
	new ProblemaPermutacaoSimples("Quantas possibilidades de arranjo para $n livros de diferentes tamanhos existem?"),
	new ProblemaPermutacaoSimples("De quantas maneiras podemos arranjar $n tipos de sanduíches em uma prateleira?")
	};
	
	public static ProblemaPermutacaoSimples getProblema()
	{
		Random rand=new Random();
		int index=rand.nextInt(problemas.length);
		return problemas[index].clone();
	}
	
}
