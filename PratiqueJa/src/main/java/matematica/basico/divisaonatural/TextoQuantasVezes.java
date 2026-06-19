package matematica.basico.divisaonatural;

import java.util.Random;

// Biblioteca de enunciados de divisão como medida: quantas vezes $b cabe em $a (exata).
// $a é o total e $b a medida/capacidade que se repete; a resposta é a / b.
public class TextoQuantasVezes
{
	static ProblemaQuantasVezes problemas[] = {

	// --- ideia direta ---
	new ProblemaQuantasVezes("Quantas vezes o número \\($b\\) cabe no número \\($a\\)?"),
	new ProblemaQuantasVezes("Quantas vezes \\($b\\) cabe em \\($a\\)?"),
	new ProblemaQuantasVezes("Quantas parcelas iguais a \\($b\\) somam \\($a\\)?"),
	new ProblemaQuantasVezes("Por quantas vezes precisamos repetir \\($b\\) para chegar a \\($a\\)?"),

	// --- capacidade (encher recipientes) ---
	new ProblemaQuantasVezes("Um copo comporta \\($b\\) ml de suco. Quantas vezes precisamos enchê-lo para obter \\($a\\) ml no total?"),
	new ProblemaQuantasVezes("Um balde leva \\($b\\) litros. Quantos baldes cheios são necessários para juntar \\($a\\) litros?"),
	new ProblemaQuantasVezes("Uma garrafa guarda \\($b\\) ml. Quantas garrafas cheias formam \\($a\\) ml?"),
	new ProblemaQuantasVezes("Uma jarra comporta \\($b\\) litros. Quantas jarras cheias são necessárias para \\($a\\) litros?"),
	new ProblemaQuantasVezes("Um regador leva \\($b\\) litros. Quantas vezes ele precisa ser enchido para usar \\($a\\) litros de água?"),
	new ProblemaQuantasVezes("Uma caneca comporta \\($b\\) ml. Quantas canecas cheias dão \\($a\\) ml?"),
	new ProblemaQuantasVezes("Um tanque é abastecido de \\($b\\) em \\($b\\) litros. Quantos abastecimentos completam \\($a\\) litros?"),
	new ProblemaQuantasVezes("Uma xícara leva \\($b\\) ml. Quantas xícaras cheias enchem uma vasilha de \\($a\\) ml?"),

	// --- guardar em caixas/recipientes ---
	new ProblemaQuantasVezes("Uma caixa comporta \\($b\\) livros. Quantas caixas cheias são necessárias para guardar \\($a\\) livros?"),
	new ProblemaQuantasVezes("Uma cartela leva \\($b\\) ovos. Quantas cartelas cheias guardam \\($a\\) ovos?"),
	new ProblemaQuantasVezes("Um engradado leva \\($b\\) garrafas. Quantos engradados cheios formam \\($a\\) garrafas?"),
	new ProblemaQuantasVezes("Um estojo comporta \\($b\\) lápis. Quantos estojos cheios guardam \\($a\\) lápis?"),
	new ProblemaQuantasVezes("Uma sacola leva \\($b\\) maçãs. Quantas sacolas cheias são necessárias para \\($a\\) maçãs?"),
	new ProblemaQuantasVezes("Um pote guarda \\($b\\) bombons. Quantos potes cheios formam \\($a\\) bombons?"),
	new ProblemaQuantasVezes("Uma caixa armazena \\($b\\) parafusos. Quantas caixas cheias guardam \\($a\\) parafusos?"),
	new ProblemaQuantasVezes("Um pacote leva \\($b\\) biscoitos. Quantos pacotes cheios formam \\($a\\) biscoitos?"),

	// --- passos, medidas e comprimento ---
	new ProblemaQuantasVezes("Cada passo de $nomeM mede \\($b\\) palmos. Quantos passos ele dá para percorrer \\($a\\) palmos?"),
	new ProblemaQuantasVezes("Uma régua mede \\($b\\) cm. Quantas vezes ela é usada para medir \\($a\\) cm?"),
	new ProblemaQuantasVezes("Cada salto de um sapo avança \\($b\\) cm. Quantos saltos para percorrer \\($a\\) cm?"),
	new ProblemaQuantasVezes("Uma fita de \\($b\\) cm é repetida quantas vezes para formar \\($a\\) cm?"),
	new ProblemaQuantasVezes("Cada volta na pista tem \\($b\\) metros. Quantas voltas são \\($a\\) metros?"),
	new ProblemaQuantasVezes("Um pedaço de corda tem \\($b\\) metros. Quantos pedaços iguais formam \\($a\\) metros?"),
	new ProblemaQuantasVezes("Cada degrau tem \\($b\\) cm de altura. Quantos degraus para subir \\($a\\) cm?"),

	// --- tempo ---
	new ProblemaQuantasVezes("Cada música dura \\($b\\) minutos. Quantas músicas cabem em \\($a\\) minutos?"),
	new ProblemaQuantasVezes("Cada rodada de um jogo leva \\($b\\) minutos. Quantas rodadas cabem em \\($a\\) minutos?"),
	new ProblemaQuantasVezes("$nomeF estuda \\($b\\) minutos por sessão. Quantas sessões formam \\($a\\) minutos de estudo?"),
	new ProblemaQuantasVezes("Cada episódio dura \\($b\\) minutos. Quantos episódios cabem em \\($a\\) minutos?"),
	new ProblemaQuantasVezes("Um intervalo dura \\($b\\) minutos. Quantos intervalos cabem em \\($a\\) minutos?"),
	new ProblemaQuantasVezes("Cada aula tem \\($b\\) minutos. Quantas aulas inteiras cabem em \\($a\\) minutos?"),

	// --- dinheiro ---
	new ProblemaQuantasVezes("Cada ingresso custa \\($b\\) reais. Quantos ingressos podem ser comprados com \\($a\\) reais?"),
	new ProblemaQuantasVezes("Cada figurinha custa \\($b\\) reais. Quantas figurinhas $nomeM compra com \\($a\\) reais?"),
	new ProblemaQuantasVezes("Cada caderno custa \\($b\\) reais. Quantos cadernos cabem em \\($a\\) reais?"),
	new ProblemaQuantasVezes("Uma passagem custa \\($b\\) reais. Quantas passagens dá para comprar com \\($a\\) reais?"),
	new ProblemaQuantasVezes("Cada brinquedo custa \\($b\\) reais. Quantos brinquedos podem ser comprados com \\($a\\) reais?"),
	new ProblemaQuantasVezes("Cada lanche custa \\($b\\) reais. Quantos lanches dá para comprar com \\($a\\) reais?"),

	// --- agrupar pessoas/coisas ---
	new ProblemaQuantasVezes("Cada van leva \\($b\\) pessoas. Quantas vans cheias são necessárias para \\($a\\) pessoas?"),
	new ProblemaQuantasVezes("Cada mesa acomoda \\($b\\) convidados. Quantas mesas cheias para \\($a\\) convidados?"),
	new ProblemaQuantasVezes("Cada equipe tem \\($b\\) jogadores. Quantas equipes se formam com \\($a\\) jogadores?"),
	new ProblemaQuantasVezes("Cada barco leva \\($b\\) passageiros. Quantos barcos cheios para \\($a\\) passageiros?"),
	new ProblemaQuantasVezes("Cada fileira tem \\($b\\) cadeiras. Quantas fileiras cheias formam \\($a\\) cadeiras?"),
	new ProblemaQuantasVezes("Cada grupo tem \\($b\\) alunos. Quantos grupos se formam com \\($a\\) alunos?"),
	new ProblemaQuantasVezes("Cada cabana abriga \\($b\\) crianças. Quantas cabanas cheias para \\($a\\) crianças?"),

	// --- produção e repetição ---
	new ProblemaQuantasVezes("Uma máquina embala \\($b\\) itens por ciclo. Quantos ciclos para embalar \\($a\\) itens?"),
	new ProblemaQuantasVezes("Cada fornada assa \\($b\\) pães. Quantas fornadas para assar \\($a\\) pães?"),
	new ProblemaQuantasVezes("Cada viagem leva \\($b\\) caixas. Quantas viagens para transportar \\($a\\) caixas?"),
	new ProblemaQuantasVezes("Cada lote tem \\($b\\) peças. Quantos lotes formam \\($a\\) peças?"),
	new ProblemaQuantasVezes("Cada carga do forno faz \\($b\\) tijolos. Quantas cargas para \\($a\\) tijolos?"),
	new ProblemaQuantasVezes("Cada turno produz \\($b\\) unidades. Quantos turnos para produzir \\($a\\) unidades?"),
	new ProblemaQuantasVezes("Cada palete leva \\($b\\) sacos. Quantos paletes para \\($a\\) sacos?"),

	// --- comida ---
	new ProblemaQuantasVezes("Cada receita usa \\($b\\) ovos. Quantas receitas dá para fazer com \\($a\\) ovos?"),
	new ProblemaQuantasVezes("Cada bandeja leva \\($b\\) docinhos. Quantas bandejas cheias formam \\($a\\) docinhos?"),
	new ProblemaQuantasVezes("Cada porção tem \\($b\\) biscoitos. Quantas porções há em \\($a\\) biscoitos?"),
	new ProblemaQuantasVezes("Cada pizza rende \\($b\\) fatias. Quantas pizzas para obter \\($a\\) fatias?"),
	new ProblemaQuantasVezes("Cada saco de ração pesa \\($b\\) kg. Quantos sacos formam \\($a\\) kg?"),
	new ProblemaQuantasVezes("Cada cesta leva \\($b\\) frutas. Quantas cestas cheias para \\($a\\) frutas?"),

	// --- variados ---
	new ProblemaQuantasVezes("Cada caixa de leite tem \\($b\\) litros. Quantas caixas formam \\($a\\) litros?"),
	new ProblemaQuantasVezes("Cada página comporta \\($b\\) figurinhas. Quantas páginas cheias para \\($a\\) figurinhas?"),
	new ProblemaQuantasVezes("Cada caderno tem \\($b\\) folhas. Quantos cadernos têm ao todo \\($a\\) folhas?"),
	new ProblemaQuantasVezes("Cada coluna do mural tem \\($b\\) cartazes. Quantas colunas cheias formam \\($a\\) cartazes?"),
	new ProblemaQuantasVezes("Cada prateleira leva \\($b\\) potes. Quantas prateleiras cheias para \\($a\\) potes?"),
	new ProblemaQuantasVezes("Cada vagão leva \\($b\\) passageiros. Quantos vagões cheios para \\($a\\) passageiros?"),
	new ProblemaQuantasVezes("Cada balcão expõe \\($b\\) produtos. Quantos balcões cheios para \\($a\\) produtos?"),
	new ProblemaQuantasVezes("Cada caixa de azulejos cobre \\($b\\) ladrilhos. Quantas caixas para \\($a\\) ladrilhos?"),
	new ProblemaQuantasVezes("Cada pacote tem \\($b\\) velas. Quantos pacotes formam \\($a\\) velas?"),
	new ProblemaQuantasVezes("Cada estante guarda \\($b\\) livros. Quantas estantes cheias para \\($a\\) livros?"),
	new ProblemaQuantasVezes("Cada baia abriga \\($b\\) animais. Quantas baias cheias para \\($a\\) animais?"),
	new ProblemaQuantasVezes("Cada caixa traz \\($b\\) canetas. Quantas caixas formam \\($a\\) canetas?"),
	new ProblemaQuantasVezes("Cada saquinho tem \\($b\\) balas. Quantos saquinhos formam \\($a\\) balas?"),
	new ProblemaQuantasVezes("Cada arara comporta \\($b\\) roupas. Quantas araras cheias para \\($a\\) roupas?"),
	new ProblemaQuantasVezes("Cada cofrinho guarda \\($b\\) moedas. Quantos cofrinhos cheios formam \\($a\\) moedas?"),
	new ProblemaQuantasVezes("Cada fileira do pomar tem \\($b\\) árvores. Quantas fileiras cheias para \\($a\\) árvores?"),
	new ProblemaQuantasVezes("Cada caminhão carrega \\($b\\) toneladas. Quantos caminhões para transportar \\($a\\) toneladas?"),
	new ProblemaQuantasVezes("Cada folha de adesivos tem \\($b\\) adesivos. Quantas folhas para \\($a\\) adesivos?"),
	new ProblemaQuantasVezes("Cada bilhete de rifa custa \\($b\\) reais. Quantos bilhetes dá para comprar com \\($a\\) reais?"),
	new ProblemaQuantasVezes("Cada vasilha leva \\($b\\) litros. Quantas vasilhas cheias formam \\($a\\) litros?"),
	new ProblemaQuantasVezes("Cada caixa de remédio tem \\($b\\) comprimidos. Quantas caixas para \\($a\\) comprimidos?"),
	new ProblemaQuantasVezes("Cada time inscrito paga \\($b\\) reais. Quantos times se inscreveram se foram arrecadados \\($a\\) reais?"),

	};

	public static ProblemaQuantasVezes getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
