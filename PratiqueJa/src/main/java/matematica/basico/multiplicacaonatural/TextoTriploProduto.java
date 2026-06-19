package matematica.basico.multiplicacaonatural;

import java.util.Random;

// Biblioteca de enunciados de produto de três fatores (a x b x c). $a é a base grande
// e $b, $c fatores pequenos. O segundo argumento é a unidade exibida na resolução.
public class TextoTriploProduto
{
	static ProblemaTriploProduto problemas[] = {

	// --- produção (taxa x tempo x período) ---
	new ProblemaTriploProduto("Uma fábrica produz \\($a\\) peças por hora. Trabalhando \\($b\\) horas por dia, quantas peças produz em \\($c\\) dias?", "\\,\\text{peças}"),
	new ProblemaTriploProduto("Uma gráfica imprime \\($a\\) folhetos por hora, durante \\($b\\) horas por dia, por \\($c\\) dias. Quantos folhetos imprime?", "\\,\\text{folhetos}"),
	new ProblemaTriploProduto("Uma padaria assa \\($a\\) pães por fornada, faz \\($b\\) fornadas por dia, durante \\($c\\) dias. Quantos pães assa?", "\\,\\text{pães}"),
	new ProblemaTriploProduto("Uma máquina enche \\($a\\) garrafas por hora, opera \\($b\\) horas por dia, por \\($c\\) dias. Quantas garrafas enche?", "\\,\\text{garrafas}"),
	new ProblemaTriploProduto("Uma confeitaria faz \\($a\\) docinhos por hora, em \\($b\\) horas por dia, durante \\($c\\) dias. Quantos docinhos faz?", "\\,\\text{docinhos}"),
	new ProblemaTriploProduto("Uma costura industrial faz \\($a\\) peças por hora, \\($b\\) horas por dia, por \\($c\\) dias. Quantas peças são feitas?", "\\,\\text{peças}"),
	new ProblemaTriploProduto("Uma usina gera \\($a\\) kWh por hora, funcionando \\($b\\) horas por dia, em \\($c\\) dias. Quantos kWh gera?", "\\,\\text{kWh}"),
	new ProblemaTriploProduto("Um forno produz \\($a\\) tijolos por carga, faz \\($b\\) cargas por dia, durante \\($c\\) dias. Quantos tijolos produz?", "\\,\\text{tijolos}"),
	new ProblemaTriploProduto("Uma fábrica monta \\($a\\) brinquedos por turno, \\($b\\) turnos por dia, por \\($c\\) dias. Quantos brinquedos monta?", "\\,\\text{brinquedos}"),
	new ProblemaTriploProduto("Uma engarrafadora processa \\($a\\) latas por minuto, \\($b\\) minutos seguidos, em \\($c\\) ciclos. Quantas latas processa?", "\\,\\text{latas}"),

	// --- distribuição (por destino x por embalagem x destinos) ---
	new ProblemaTriploProduto("Um depósito envia \\($a\\) caixas para cada loja, com \\($b\\) unidades em cada caixa, para \\($c\\) lojas. Quantas unidades distribui?", "\\,\\text{unidades}"),
	new ProblemaTriploProduto("Uma transportadora leva \\($a\\) pacotes por viagem, com \\($b\\) itens cada, em \\($c\\) viagens. Quantos itens transporta?", "\\,\\text{itens}"),
	new ProblemaTriploProduto("Uma distribuidora entrega \\($a\\) engradados por rota, com \\($b\\) garrafas cada, em \\($c\\) rotas. Quantas garrafas entrega?", "\\,\\text{garrafas}"),
	new ProblemaTriploProduto("Um armazém despacha \\($a\\) sacos por caminhão, com \\($b\\) kg cada, em \\($c\\) caminhões. Quantos kg despacha?", "\\,\\text{kg}"),
	new ProblemaTriploProduto("Uma editora envia \\($a\\) livros por escola, em \\($b\\) caixas iguais, para \\($c\\) escolas. Quantos livros envia?", "\\,\\text{livros}"),
	new ProblemaTriploProduto("Uma loja recebe \\($a\\) peças por fornecedor, em \\($b\\) lotes, de \\($c\\) fornecedores. Quantas peças recebe?", "\\,\\text{peças}"),
	new ProblemaTriploProduto("Um centro de doação separa \\($a\\) cestas por bairro, com \\($b\\) itens cada, para \\($c\\) bairros. Quantos itens separa?", "\\,\\text{itens}"),
	new ProblemaTriploProduto("Uma cooperativa embala \\($a\\) frutas por caixa, \\($b\\) caixas por palete, em \\($c\\) paletes. Quantas frutas embala?", "\\,\\text{frutas}"),

	// --- empacotamento aninhado ---
	new ProblemaTriploProduto("Cada estojo tem \\($a\\) lápis, cada caixa tem \\($b\\) estojos e há \\($c\\) caixas. Quantos lápis há ao todo?", "\\,\\text{lápis}"),
	new ProblemaTriploProduto("Cada pacote tem \\($a\\) figurinhas, cada caixa tem \\($b\\) pacotes e há \\($c\\) caixas. Quantas figurinhas há?", "\\,\\text{figurinhas}"),
	new ProblemaTriploProduto("Cada bandeja tem \\($a\\) ovos, cada engradado tem \\($b\\) bandejas e há \\($c\\) engradados. Quantos ovos há?", "\\,\\text{ovos}"),
	new ProblemaTriploProduto("Cada cartela tem \\($a\\) comprimidos, cada caixa tem \\($b\\) cartelas e há \\($c\\) caixas. Quantos comprimidos há?", "\\,\\text{comprimidos}"),
	new ProblemaTriploProduto("Cada bloco tem \\($a\\) folhas, cada pacote tem \\($b\\) blocos e há \\($c\\) pacotes. Quantas folhas há?", "\\,\\text{folhas}"),
	new ProblemaTriploProduto("Cada caixa tem \\($a\\) parafusos, cada fardo tem \\($b\\) caixas e há \\($c\\) fardos. Quantos parafusos há?", "\\,\\text{parafusos}"),
	new ProblemaTriploProduto("Cada saco tem \\($a\\) balas, cada pote tem \\($b\\) sacos e há \\($c\\) potes. Quantas balas há?", "\\,\\text{balas}"),
	new ProblemaTriploProduto("Cada caixa tem \\($a\\) pregos, cada caixote tem \\($b\\) caixas e há \\($c\\) caixotes. Quantos pregos há?", "\\,\\text{pregos}"),

	// --- agrupamentos físicos (linhas x colunas x camadas/conjuntos) ---
	new ProblemaTriploProduto("Um estoque tem \\($a\\) caixas por prateleira, \\($b\\) prateleiras por estante e \\($c\\) estantes. Quantas caixas há?", "\\,\\text{caixas}"),
	new ProblemaTriploProduto("Um auditório tem \\($a\\) cadeiras por fileira, \\($b\\) fileiras por setor e \\($c\\) setores. Quantas cadeiras há?", "\\,\\text{cadeiras}"),
	new ProblemaTriploProduto("Um pomar tem \\($a\\) árvores por fileira, \\($b\\) fileiras por talhão e \\($c\\) talhões. Quantas árvores há?", "\\,\\text{árvores}"),
	new ProblemaTriploProduto("Um prédio tem \\($a\\) apartamentos por andar, \\($b\\) andares por torre e \\($c\\) torres. Quantos apartamentos há?", "\\,\\text{apartamentos}"),
	new ProblemaTriploProduto("Um estádio tem \\($a\\) lugares por fileira, \\($b\\) fileiras por arquibancada e \\($c\\) arquibancadas. Quantos lugares há?", "\\,\\text{lugares}"),
	new ProblemaTriploProduto("Uma plantação tem \\($a\\) pés por linha, \\($b\\) linhas por canteiro e \\($c\\) canteiros. Quantos pés há?", "\\,\\text{pés}"),
	new ProblemaTriploProduto("Um galpão tem \\($a\\) animais por baia, \\($b\\) baias por setor e \\($c\\) setores. Quantos animais há?", "\\,\\text{animais}"),
	new ProblemaTriploProduto("Um cinema tem \\($a\\) poltronas por fileira, \\($b\\) fileiras por sala e \\($c\\) salas. Quantas poltronas há?", "\\,\\text{poltronas}"),

	// --- coleção/pessoas (cada x cada x quantidade) ---
	new ProblemaTriploProduto("Cada turma tem \\($a\\) alunos, cada escola tem \\($b\\) turmas e há \\($c\\) escolas. Quantos alunos há?", "\\,\\text{alunos}"),
	new ProblemaTriploProduto("Cada ônibus leva \\($a\\) passageiros, cada excursão usa \\($b\\) ônibus e há \\($c\\) excursões. Quantas pessoas viajam?", "\\,\\text{pessoas}"),
	new ProblemaTriploProduto("Cada equipe tem \\($a\\) atletas, cada clube tem \\($b\\) equipes e há \\($c\\) clubes. Quantos atletas há?", "\\,\\text{atletas}"),
	new ProblemaTriploProduto("Cada mesa tem \\($a\\) convidados, cada salão tem \\($b\\) mesas e há \\($c\\) salões. Quantos convidados há?", "\\,\\text{convidados}"),
	new ProblemaTriploProduto("Cada coral tem \\($a\\) cantores, cada festival tem \\($b\\) corais e há \\($c\\) festivais. Quantos cantores há?", "\\,\\text{cantores}"),
	new ProblemaTriploProduto("Cada sala de congresso tem \\($a\\) participantes, cada evento usa \\($b\\) salas e há \\($c\\) eventos. Quantos participantes há?", "\\,\\text{participantes}"),

	// --- dinheiro (valor x quantidade x grupos) ---
	new ProblemaTriploProduto("Cada ingresso custa \\($a\\) reais. Uma família compra \\($b\\) ingressos e há \\($c\\) famílias. Qual o total arrecadado?", "\\,\\text{reais}"),
	new ProblemaTriploProduto("Cada caixa custa \\($a\\) reais. Uma loja compra \\($b\\) caixas e há \\($c\\) lojas. Quanto foi gasto?", "\\,\\text{reais}"),
	new ProblemaTriploProduto("Cada uniforme custa \\($a\\) reais. Cada turma precisa de \\($b\\) uniformes e há \\($c\\) turmas. Qual o custo total?", "\\,\\text{reais}"),
	new ProblemaTriploProduto("Cada livro custa \\($a\\) reais. Cada aluno leva \\($b\\) livros e há \\($c\\) alunos. Qual o valor total?", "\\,\\text{reais}"),
	new ProblemaTriploProduto("Cada diária custa \\($a\\) reais. Uma reserva tem \\($b\\) diárias e há \\($c\\) reservas. Quanto foi arrecadado?", "\\,\\text{reais}"),
	new ProblemaTriploProduto("Cada peça custa \\($a\\) reais. Um pedido tem \\($b\\) peças e há \\($c\\) pedidos. Qual o total?", "\\,\\text{reais}"),

	// --- consumo/medidas (por unidade x unidades x grupos) ---
	new ProblemaTriploProduto("Cada máquina consome \\($a\\) litros por hora, funciona \\($b\\) horas e há \\($c\\) máquinas. Quantos litros consome?", "\\,\\text{litros}"),
	new ProblemaTriploProduto("Cada caminhão carrega \\($a\\) kg por viagem, faz \\($b\\) viagens e há \\($c\\) caminhões. Quantos kg carrega?", "\\,\\text{kg}"),
	new ProblemaTriploProduto("Cada torneira solta \\($a\\) litros por minuto, fica aberta \\($b\\) minutos e há \\($c\\) torneiras. Quantos litros solta?", "\\,\\text{litros}"),
	new ProblemaTriploProduto("Cada lâmpada gasta \\($a\\) watts, cada sala tem \\($b\\) lâmpadas e há \\($c\\) salas. Qual o consumo em watts?", "\\,\\text{watts}"),
	new ProblemaTriploProduto("Cada aluno usa \\($a\\) folhas por prova, faz \\($b\\) provas e há \\($c\\) alunos. Quantas folhas são usadas?", "\\,\\text{folhas}"),
	new ProblemaTriploProduto("Cada animal come \\($a\\) gramas por refeição, come \\($b\\) refeições por dia e há \\($c\\) dias. Quantos gramas come?", "\\,\\text{gramas}"),

	// --- mais variados ---
	new ProblemaTriploProduto("$nomeM lê \\($a\\) páginas por hora, lê \\($b\\) horas por dia, durante \\($c\\) dias. Quantas páginas lê?", "\\,\\text{páginas}"),
	new ProblemaTriploProduto("$nomeF poupa \\($a\\) reais por semana, durante \\($b\\) semanas por mês, em \\($c\\) meses. Quanto poupa?", "\\,\\text{reais}"),
	new ProblemaTriploProduto("Um ciclista pedala \\($a\\) km por hora, \\($b\\) horas por dia, em \\($c\\) dias. Quantos km pedala?", "\\,\\text{km}"),
	new ProblemaTriploProduto("Uma escola distribui \\($a\\) cadernos por turma, \\($b\\) turmas por turno e \\($c\\) turnos. Quantos cadernos distribui?", "\\,\\text{cadernos}"),
	new ProblemaTriploProduto("Uma horta colhe \\($a\\) alfaces por canteiro, \\($b\\) canteiros por dia, em \\($c\\) dias. Quantas alfaces colhe?", "\\,\\text{alfaces}"),
	new ProblemaTriploProduto("Uma fábrica de calçados faz \\($a\\) pares por hora, \\($b\\) horas por turno e \\($c\\) turnos. Quantos pares faz?", "\\,\\text{pares}"),
	new ProblemaTriploProduto("Um zoológico recebe \\($a\\) visitantes por hora, fica aberto \\($b\\) horas, durante \\($c\\) dias. Quantos visitantes recebe?", "\\,\\text{visitantes}"),
	new ProblemaTriploProduto("Cada quadra tem \\($a\\) lugares, cada complexo tem \\($b\\) quadras e há \\($c\\) complexos. Quantos lugares há?", "\\,\\text{lugares}"),
	new ProblemaTriploProduto("Cada caixa tem \\($a\\) maçãs, cada feira recebe \\($b\\) caixas e há \\($c\\) feiras. Quantas maçãs há?", "\\,\\text{maçãs}"),
	new ProblemaTriploProduto("Cada vagão leva \\($a\\) passageiros, cada trem tem \\($b\\) vagões e há \\($c\\) trens. Quantos passageiros há?", "\\,\\text{passageiros}"),
	new ProblemaTriploProduto("Cada andar tem \\($a\\) lâmpadas, cada prédio tem \\($b\\) andares e há \\($c\\) prédios. Quantas lâmpadas há?", "\\,\\text{lâmpadas}"),
	new ProblemaTriploProduto("Cada caderno tem \\($a\\) folhas, cada aluno tem \\($b\\) cadernos e há \\($c\\) alunos. Quantas folhas há?", "\\,\\text{folhas}"),
	new ProblemaTriploProduto("Cada caixa de remédio tem \\($a\\) comprimidos, a farmácia recebe \\($b\\) caixas por dia, em \\($c\\) dias. Quantos comprimidos recebe?", "\\,\\text{comprimidos}"),
	new ProblemaTriploProduto("Cada barraca vende \\($a\\) lanches por hora, fica \\($b\\) horas aberta e há \\($c\\) barracas. Quantos lanches são vendidos?", "\\,\\text{lanches}"),
	new ProblemaTriploProduto("Cada operário coloca \\($a\\) tijolos por hora, trabalha \\($b\\) horas e há \\($c\\) operários. Quantos tijolos são colocados?", "\\,\\text{tijolos}"),
	new ProblemaTriploProduto("Cada classe planta \\($a\\) mudas, cada escola tem \\($b\\) classes e há \\($c\\) escolas. Quantas mudas são plantadas?", "\\,\\text{mudas}"),
	new ProblemaTriploProduto("Cada loja vende \\($a\\) sorvetes por dia, durante \\($b\\) dias e há \\($c\\) lojas. Quantos sorvetes são vendidos?", "\\,\\text{sorvetes}"),
	new ProblemaTriploProduto("Cada caixa tem \\($a\\) velas, cada pacote tem \\($b\\) caixas e há \\($c\\) pacotes. Quantas velas há?", "\\,\\text{velas}"),
	new ProblemaTriploProduto("Cada time faz \\($a\\) pontos por jogo, joga \\($b\\) jogos por rodada, em \\($c\\) rodadas. Quantos pontos faz?", "\\,\\text{pontos}"),
	new ProblemaTriploProduto("Cada caixa-d'água tem \\($a\\) litros, cada prédio tem \\($b\\) caixas e há \\($c\\) prédios. Quantos litros há?", "\\,\\text{litros}"),
	new ProblemaTriploProduto("Cada caixa tem \\($a\\) chocolates, cada fardo tem \\($b\\) caixas e há \\($c\\) fardos. Quantos chocolates há?", "\\,\\text{chocolates}"),
	new ProblemaTriploProduto("Cada pacote tem \\($a\\) biscoitos, cada caixa tem \\($b\\) pacotes e há \\($c\\) caixas. Quantos biscoitos há?", "\\,\\text{biscoitos}"),
	new ProblemaTriploProduto("Cada sala tem \\($a\\) carteiras, cada andar tem \\($b\\) salas e há \\($c\\) andares. Quantas carteiras há?", "\\,\\text{carteiras}"),
	new ProblemaTriploProduto("Uma fazenda produz \\($a\\) litros de leite por vaca, tem \\($b\\) vacas por curral e \\($c\\) currais. Quantos litros produz?", "\\,\\text{litros}"),
	new ProblemaTriploProduto("Cada caixa traz \\($a\\) canetas, cada loja recebe \\($b\\) caixas e há \\($c\\) lojas. Quantas canetas há?", "\\,\\text{canetas}"),
	new ProblemaTriploProduto("Cada hora a fábrica usa \\($a\\) gramas de tinta, opera \\($b\\) horas e há \\($c\\) máquinas. Quantos gramas usa?", "\\,\\text{gramas}"),
	new ProblemaTriploProduto("Cada palete tem \\($a\\) tijolos, cada caminhão leva \\($b\\) paletes e há \\($c\\) caminhões. Quantos tijolos há?", "\\,\\text{tijolos}"),
	new ProblemaTriploProduto("Cada turma arrecadou \\($a\\) latas, cada escola tem \\($b\\) turmas e há \\($c\\) escolas. Quantas latas foram arrecadadas?", "\\,\\text{latas}"),

	};

	public static ProblemaTriploProduto getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
