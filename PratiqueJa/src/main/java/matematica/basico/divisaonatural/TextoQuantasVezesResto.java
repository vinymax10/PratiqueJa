package matematica.basico.divisaonatural;

import java.util.Random;

// Biblioteca de enunciados de divisão como medida COM RESTO: quantas vezes $b cabe em $a
// (quantos recipientes/grupos cheios) e quanto sobra. Reaproveita ProblemaDivisaoResto.
// $a é o total e $b a medida/capacidade que se repete.
public class TextoQuantasVezesResto
{
	static ProblemaDivisaoResto problemas[] = {

	// --- ideia direta ---
	new ProblemaDivisaoResto("Quantas vezes o número \\($b\\) cabe em \\($a\\)? Há algum resto?"),
	new ProblemaDivisaoResto("Quantas vezes \\($b\\) cabe em \\($a\\) e quanto sobra?"),
	new ProblemaDivisaoResto("Quantas parcelas inteiras iguais a \\($b\\) cabem em \\($a\\) e qual é o resto?"),

	// --- capacidade (recipientes cheios) ---
	new ProblemaDivisaoResto("Um copo comporta \\($b\\) ml. Quantos copos cheios cabem em \\($a\\) ml e quantos ml sobram?"),
	new ProblemaDivisaoResto("Um balde leva \\($b\\) litros. Quantos baldes cheios cabem em \\($a\\) litros e quantos litros sobram?"),
	new ProblemaDivisaoResto("Uma garrafa guarda \\($b\\) ml. Quantas garrafas cheias cabem em \\($a\\) ml e quantos ml sobram?"),
	new ProblemaDivisaoResto("Uma vasilha leva \\($b\\) litros. Quantas vasilhas cheias formam \\($a\\) litros e quantos litros sobram?"),
	new ProblemaDivisaoResto("Um tanque comporta \\($b\\) litros por vasilha. Quantas vasilhas cheias cabem em \\($a\\) litros e quanto sobra?"),
	new ProblemaDivisaoResto("Uma jarra comporta \\($b\\) ml. Quantas jarras cheias cabem em \\($a\\) ml e quantos ml sobram?"),
	new ProblemaDivisaoResto("Um regador leva \\($b\\) litros. Quantas vezes ele é enchido para usar \\($a\\) litros e quantos litros sobram?"),
	new ProblemaDivisaoResto("Uma caneca leva \\($b\\) ml. Quantas canecas cheias cabem em \\($a\\) ml e quanto sobra?"),

	// --- transporte (lotação) ---
	new ProblemaDivisaoResto("Um ônibus comporta \\($b\\) passageiros. Quantos ônibus cheios são necessários para \\($a\\) pessoas e quantas ficam no último ônibus?"),
	new ProblemaDivisaoResto("Uma van leva \\($b\\) passageiros. Quantas vans cheias para \\($a\\) pessoas e quantas sobram para a última?"),
	new ProblemaDivisaoResto("Um barco comporta \\($b\\) pessoas. Quantos barcos cheios para \\($a\\) pessoas e quantas sobram?"),
	new ProblemaDivisaoResto("Um teleférico leva \\($b\\) pessoas por cabine. Quantas cabines cheias para \\($a\\) pessoas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada elevador leva \\($b\\) pessoas. Quantas viagens cheias para \\($a\\) pessoas e quantas sobram na última?"),
	new ProblemaDivisaoResto("Um vagão leva \\($b\\) passageiros. Quantos vagões cheios para \\($a\\) passageiros e quantos sobram?"),

	// --- armazenar em caixas/recipientes ---
	new ProblemaDivisaoResto("Uma caixa armazena \\($b\\) garrafas. Quantas caixas cheias formam \\($a\\) garrafas e quantas garrafas sobram?"),
	new ProblemaDivisaoResto("Uma caixa comporta \\($b\\) livros. Quantas caixas cheias cabem em \\($a\\) livros e quantos sobram?"),
	new ProblemaDivisaoResto("Uma cartela leva \\($b\\) ovos. Quantas cartelas cheias formam \\($a\\) ovos e quantos ovos sobram?"),
	new ProblemaDivisaoResto("Um estojo leva \\($b\\) lápis. Quantos estojos cheios para \\($a\\) lápis e quantos lápis sobram?"),
	new ProblemaDivisaoResto("Um pote guarda \\($b\\) bombons. Quantos potes cheios formam \\($a\\) bombons e quantos sobram?"),
	new ProblemaDivisaoResto("Uma sacola leva \\($b\\) maçãs. Quantas sacolas cheias para \\($a\\) maçãs e quantas sobram?"),
	new ProblemaDivisaoResto("Um engradado leva \\($b\\) refrigerantes. Quantos engradados cheios para \\($a\\) refrigerantes e quantos sobram?"),
	new ProblemaDivisaoResto("Uma caixa de remédio tem \\($b\\) comprimidos. Quantas caixas cheias para \\($a\\) comprimidos e quantos sobram?"),

	// --- tempo ---
	new ProblemaDivisaoResto("Cada música dura \\($b\\) minutos. Quantas músicas inteiras cabem em \\($a\\) minutos e quantos minutos sobram?"),
	new ProblemaDivisaoResto("Cada episódio dura \\($b\\) minutos. Quantos episódios inteiros cabem em \\($a\\) minutos e quanto sobra?"),
	new ProblemaDivisaoResto("Cada aula tem \\($b\\) minutos. Quantas aulas inteiras cabem em \\($a\\) minutos e quantos minutos sobram?"),
	new ProblemaDivisaoResto("Cada rodada leva \\($b\\) minutos. Quantas rodadas completas cabem em \\($a\\) minutos e quanto sobra?"),
	new ProblemaDivisaoResto("Cada intervalo dura \\($b\\) minutos. Quantos intervalos inteiros há em \\($a\\) minutos e quanto sobra?"),
	new ProblemaDivisaoResto("Cada sessão de estudo dura \\($b\\) minutos. Quantas sessões inteiras cabem em \\($a\\) minutos e quanto sobra?"),

	// --- dinheiro ---
	new ProblemaDivisaoResto("Cada ingresso custa \\($b\\) reais. Quantos ingressos dá para comprar com \\($a\\) reais e quanto sobra?"),
	new ProblemaDivisaoResto("Cada caderno custa \\($b\\) reais. Quantos cadernos $nomeM compra com \\($a\\) reais e quanto sobra?"),
	new ProblemaDivisaoResto("Cada lanche custa \\($b\\) reais. Quantos lanches dá para comprar com \\($a\\) reais e quanto sobra?"),
	new ProblemaDivisaoResto("Cada figurinha custa \\($b\\) reais. Quantas figurinhas cabem em \\($a\\) reais e quanto sobra?"),
	new ProblemaDivisaoResto("Cada brinquedo custa \\($b\\) reais. Quantos brinquedos dá para levar com \\($a\\) reais e quanto sobra?"),
	new ProblemaDivisaoResto("Cada passagem custa \\($b\\) reais. Quantas passagens dá para comprar com \\($a\\) reais e quanto sobra?"),

	// --- medidas e comprimento ---
	new ProblemaDivisaoResto("Cada pedaço de corda tem \\($b\\) metros. Quantos pedaços inteiros saem de \\($a\\) metros e quantos metros sobram?"),
	new ProblemaDivisaoResto("Cada passo mede \\($b\\) cm. Quantos passos inteiros cabem em \\($a\\) cm e quantos cm sobram?"),
	new ProblemaDivisaoResto("Cada fita tem \\($b\\) cm. Quantas fitas inteiras saem de \\($a\\) cm e quanto sobra?"),
	new ProblemaDivisaoResto("Cada volta na pista tem \\($b\\) metros. Quantas voltas inteiras cabem em \\($a\\) metros e quantos metros sobram?"),
	new ProblemaDivisaoResto("Cada degrau tem \\($b\\) cm. Quantos degraus inteiros há em \\($a\\) cm e quanto sobra?"),
	new ProblemaDivisaoResto("Cada azulejo cobre \\($b\\) cm. Quantos azulejos inteiros cabem em \\($a\\) cm e quanto sobra?"),

	// --- produção e repetição ---
	new ProblemaDivisaoResto("Cada fornada assa \\($b\\) pães. Quantas fornadas inteiras para \\($a\\) pães e quantos sobram?"),
	new ProblemaDivisaoResto("Cada lote tem \\($b\\) peças. Quantos lotes completos formam \\($a\\) peças e quantas sobram?"),
	new ProblemaDivisaoResto("Cada viagem leva \\($b\\) caixas. Quantas viagens cheias para \\($a\\) caixas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada palete leva \\($b\\) sacos. Quantos paletes cheios para \\($a\\) sacos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada ciclo embala \\($b\\) itens. Quantos ciclos completos para \\($a\\) itens e quantos sobram?"),
	new ProblemaDivisaoResto("Cada carga do forno faz \\($b\\) tijolos. Quantas cargas completas para \\($a\\) tijolos e quantos sobram?"),

	// --- comida ---
	new ProblemaDivisaoResto("Cada receita usa \\($b\\) ovos. Quantas receitas inteiras dá para fazer com \\($a\\) ovos e quantos ovos sobram?"),
	new ProblemaDivisaoResto("Cada bandeja leva \\($b\\) docinhos. Quantas bandejas cheias para \\($a\\) docinhos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada pizza rende \\($b\\) fatias. Quantas pizzas inteiras para obter \\($a\\) fatias e quantas fatias sobram?"),
	new ProblemaDivisaoResto("Cada porção tem \\($b\\) biscoitos. Quantas porções inteiras há em \\($a\\) biscoitos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada cesta leva \\($b\\) frutas. Quantas cestas cheias para \\($a\\) frutas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada saco de ração pesa \\($b\\) kg. Quantos sacos cheios formam \\($a\\) kg e quantos kg sobram?"),

	// --- agrupar pessoas/coisas ---
	new ProblemaDivisaoResto("Cada equipe tem \\($b\\) jogadores. Quantas equipes completas se formam com \\($a\\) jogadores e quantos sobram?"),
	new ProblemaDivisaoResto("Cada mesa acomoda \\($b\\) convidados. Quantas mesas cheias para \\($a\\) convidados e quantos sobram?"),
	new ProblemaDivisaoResto("Cada fileira tem \\($b\\) cadeiras. Quantas fileiras cheias formam \\($a\\) cadeiras e quantas sobram?"),
	new ProblemaDivisaoResto("Cada cabana abriga \\($b\\) crianças. Quantas cabanas cheias para \\($a\\) crianças e quantas sobram?"),
	new ProblemaDivisaoResto("Cada grupo tem \\($b\\) alunos. Quantos grupos completos com \\($a\\) alunos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada chave do torneio tem \\($b\\) times. Quantas chaves completas com \\($a\\) times e quantos sobram?"),

	// --- variados ---
	new ProblemaDivisaoResto("Cada página comporta \\($b\\) figurinhas. Quantas páginas cheias para \\($a\\) figurinhas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada prateleira leva \\($b\\) potes. Quantas prateleiras cheias para \\($a\\) potes e quantos sobram?"),
	new ProblemaDivisaoResto("Cada cofrinho guarda \\($b\\) moedas. Quantos cofrinhos cheios formam \\($a\\) moedas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada caixa traz \\($b\\) canetas. Quantas caixas cheias para \\($a\\) canetas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada saquinho tem \\($b\\) balas. Quantos saquinhos cheios para \\($a\\) balas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada baia abriga \\($b\\) animais. Quantas baias cheias para \\($a\\) animais e quantos sobram?"),
	new ProblemaDivisaoResto("Cada estante guarda \\($b\\) livros. Quantas estantes cheias para \\($a\\) livros e quantos sobram?"),
	new ProblemaDivisaoResto("Cada pacote tem \\($b\\) velas. Quantos pacotes cheios formam \\($a\\) velas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada caixa de azulejos cobre \\($b\\) ladrilhos. Quantas caixas cheias para \\($a\\) ladrilhos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada arara comporta \\($b\\) roupas. Quantas araras cheias para \\($a\\) roupas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada caderno tem \\($b\\) folhas. Quantos cadernos inteiros formam \\($a\\) folhas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada caminhão leva \\($b\\) caixas. Quantos caminhões cheios para \\($a\\) caixas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada bandeja de mudas leva \\($b\\) mudas. Quantas bandejas cheias para \\($a\\) mudas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada cartão leva \\($b\\) botões. Quantos cartões cheios para \\($a\\) botões e quantos sobram?"),
	new ProblemaDivisaoResto("Cada rolinho tem \\($b\\) moedas. Quantos rolinhos cheios para \\($a\\) moedas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada caixa leva \\($b\\) lâmpadas. Quantas caixas cheias para \\($a\\) lâmpadas e quantas sobram?"),
	new ProblemaDivisaoResto("Cada bandeja leva \\($b\\) pãezinhos. Quantas bandejas cheias para \\($a\\) pãezinhos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada caixa de ovos tem \\($b\\) ovos. Quantas caixas cheias para \\($a\\) ovos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada pacote leva \\($b\\) parafusos. Quantos pacotes cheios para \\($a\\) parafusos e quantos sobram?"),
	new ProblemaDivisaoResto("Cada cesto leva \\($b\\) bolas. Quantos cestos cheios para \\($a\\) bolas e quantas sobram?"),

	};

	public static ProblemaDivisaoResto getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
