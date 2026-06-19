package matematica.basico.adicaonatural;

import java.util.Random;

// Biblioteca de enunciados de soma contextualizada de três parcelas ($a + $b + $c).
public class TextoSomaTripla
{
	static ProblemaSomaTripla problemas[] = {

	// --- três momentos do dia ---
	new ProblemaSomaTripla("Uma padaria vendeu \\($a\\) pães pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantos pães foram vendidos no total?"),
	new ProblemaSomaTripla("Uma sorveteria vendeu \\($a\\) sorvetes pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantos sorvetes vendeu no dia?"),
	new ProblemaSomaTripla("Uma loja recebeu \\($a\\) clientes pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantos clientes recebeu no dia?"),
	new ProblemaSomaTripla("Uma farmácia atendeu \\($a\\) pessoas pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantas pessoas atendeu no total?"),
	new ProblemaSomaTripla("Um restaurante serviu \\($a\\) pratos no café, \\($b\\) no almoço e \\($c\\) no jantar. Quantos pratos serviu no dia?"),
	new ProblemaSomaTripla("Uma lanchonete vendeu \\($a\\) sucos pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantos sucos vendeu no dia?"),
	new ProblemaSomaTripla("Uma banca vendeu \\($a\\) jornais pela manhã, \\($b\\) ao meio-dia e \\($c\\) à tarde. Quantos jornais vendeu?"),
	new ProblemaSomaTripla("Um pedágio registrou \\($a\\) carros pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantos carros passaram no dia?"),

	// --- três dias / períodos ---
	new ProblemaSomaTripla("$nomeM caminhou \\($a\\) km na segunda, \\($b\\) km na terça e \\($c\\) km na quarta. Quantos km caminhou ao todo?"),
	new ProblemaSomaTripla("$nomeF leu \\($a\\) páginas na sexta, \\($b\\) no sábado e \\($c\\) no domingo. Quantas páginas leu no fim de semana?"),
	new ProblemaSomaTripla("Uma fábrica produziu \\($a\\) peças no primeiro dia, \\($b\\) no segundo e \\($c\\) no terceiro. Quantas peças produziu?"),
	new ProblemaSomaTripla("Uma cidade registrou \\($a\\) mm de chuva na segunda, \\($b\\) na terça e \\($c\\) na quarta. Quantos mm choveu ao todo?"),
	new ProblemaSomaTripla("$nomeM economizou \\($a\\) reais em janeiro, \\($b\\) em fevereiro e \\($c\\) em março. Quanto economizou no trimestre?"),
	new ProblemaSomaTripla("Uma empresa vendeu \\($a\\) unidades em abril, \\($b\\) em maio e \\($c\\) em junho. Quantas unidades vendeu?"),
	new ProblemaSomaTripla("Uma horta colheu \\($a\\) alfaces na primeira semana, \\($b\\) na segunda e \\($c\\) na terceira. Quantas alfaces colheu?"),
	new ProblemaSomaTripla("Um ciclista pedalou \\($a\\) km no sábado, \\($b\\) no domingo e \\($c\\) na segunda. Quantos km pedalou?"),

	// --- três rodadas / fases ---
	new ProblemaSomaTripla("Numa gincana, o time azul marcou \\($a\\) pontos na primeira rodada, \\($b\\) na segunda e \\($c\\) na terceira. Qual foi a pontuação total?"),
	new ProblemaSomaTripla("$nomeM fez \\($a\\) pontos na primeira fase, \\($b\\) na segunda e \\($c\\) na terceira de um jogo. Quantos pontos somou?"),
	new ProblemaSomaTripla("Um jogador acertou \\($a\\) cestas no primeiro tempo, \\($b\\) no segundo e \\($c\\) na prorrogação. Quantas cestas fez?"),
	new ProblemaSomaTripla("Uma equipe somou \\($a\\) pontos na etapa 1, \\($b\\) na etapa 2 e \\($c\\) na etapa 3. Qual foi o total?"),
	new ProblemaSomaTripla("$nomeF acertou \\($a\\) questões de português, \\($b\\) de matemática e \\($c\\) de ciências. Quantas questões acertou ao todo?"),
	new ProblemaSomaTripla("Num quiz, um aluno ganhou \\($a\\) pontos na rodada inicial, \\($b\\) na intermediária e \\($c\\) na final. Quantos pontos somou?"),

	// --- três grupos / categorias ---
	new ProblemaSomaTripla("Uma escola tem \\($a\\) alunos no 1º ano, \\($b\\) no 2º e \\($c\\) no 3º. Quantos alunos há ao todo?"),
	new ProblemaSomaTripla("Uma biblioteca tem \\($a\\) livros de ficção, \\($b\\) de poesia e \\($c\\) de história. Quantos livros há no total?"),
	new ProblemaSomaTripla("Um zoológico tem \\($a\\) aves, \\($b\\) répteis e \\($c\\) mamíferos. Quantos animais há ao todo?"),
	new ProblemaSomaTripla("Uma frutaria tem \\($a\\) maçãs, \\($b\\) laranjas e \\($c\\) bananas. Quantas frutas há no total?"),
	new ProblemaSomaTripla("Uma loja vendeu \\($a\\) camisetas, \\($b\\) calças e \\($c\\) bermudas. Quantas peças vendeu?"),
	new ProblemaSomaTripla("Um estacionamento tem \\($a\\) carros, \\($b\\) motos e \\($c\\) bicicletas. Quantos veículos há?"),
	new ProblemaSomaTripla("Uma festa teve \\($a\\) crianças, \\($b\\) adultos e \\($c\\) idosos. Quantas pessoas compareceram?"),
	new ProblemaSomaTripla("Uma papelaria vendeu \\($a\\) cadernos, \\($b\\) canetas e \\($c\\) lápis. Quantos itens vendeu?"),
	new ProblemaSomaTripla("Um pomar tem \\($a\\) laranjeiras, \\($b\\) macieiras e \\($c\\) pereiras. Quantas árvores há?"),

	// --- três lugares / fontes ---
	new ProblemaSomaTripla("Uma rede tem \\($a\\) clientes na filial A, \\($b\\) na B e \\($c\\) na C. Quantos clientes há ao todo?"),
	new ProblemaSomaTripla("Uma campanha arrecadou \\($a\\) alimentos numa escola, \\($b\\) numa igreja e \\($c\\) num clube. Quantos alimentos arrecadou?"),
	new ProblemaSomaTripla("Um show teve \\($a\\) pessoas na pista, \\($b\\) na arquibancada e \\($c\\) nos camarotes. Quantas pessoas assistiram?"),
	new ProblemaSomaTripla("Uma cidade tem \\($a\\) moradores no bairro A, \\($b\\) no B e \\($c\\) no C. Qual é a população dos três bairros?"),
	new ProblemaSomaTripla("Um evento recebeu \\($a\\) inscritos pela internet, \\($b\\) por telefone e \\($c\\) na bilheteria. Quantos inscritos houve?"),
	new ProblemaSomaTripla("Uma fazenda colheu \\($a\\) sacas no talhão norte, \\($b\\) no central e \\($c\\) no sul. Quantas sacas colheu?"),
	new ProblemaSomaTripla("Uma empresa tem \\($a\\) funcionários na produção, \\($b\\) nas vendas e \\($c\\) na administração. Quantos funcionários há?"),

	// --- dinheiro ---
	new ProblemaSomaTripla("$nomeF gastou \\($a\\) reais no mercado, \\($b\\) na farmácia e \\($c\\) na padaria. Quanto gastou ao todo?"),
	new ProblemaSomaTripla("Uma loja faturou \\($a\\) reais com roupas, \\($b\\) com calçados e \\($c\\) com acessórios. Qual foi o faturamento?"),
	new ProblemaSomaTripla("$nomeM recebeu \\($a\\) reais de salário, \\($b\\) de bônus e \\($c\\) de horas extras. Quanto recebeu no total?"),
	new ProblemaSomaTripla("Uma rifa arrecadou \\($a\\) reais na primeira semana, \\($b\\) na segunda e \\($c\\) na terceira. Quanto arrecadou?"),
	new ProblemaSomaTripla("Uma família gastou \\($a\\) reais com transporte, \\($b\\) com comida e \\($c\\) com lazer. Quanto gastou?"),
	new ProblemaSomaTripla("Uma vaquinha juntou \\($a\\) reais de um grupo, \\($b\\) de outro e \\($c\\) de um terceiro. Quanto foi juntado?"),

	// --- coleções / objetos ---
	new ProblemaSomaTripla("$nomeM tem \\($a\\) figurinhas de um álbum, \\($b\\) de outro e \\($c\\) de um terceiro. Quantas figurinhas tem?"),
	new ProblemaSomaTripla("$nomeF guardou \\($a\\) moedas num cofre, \\($b\\) noutro e \\($c\\) num terceiro. Quantas moedas guardou?"),
	new ProblemaSomaTripla("Uma caixa tem \\($a\\) parafusos, outra \\($b\\) e uma terceira \\($c\\). Quantos parafusos há ao todo?"),
	new ProblemaSomaTripla("Uma estante tem \\($a\\) livros na primeira prateleira, \\($b\\) na segunda e \\($c\\) na terceira. Quantos livros há?"),
	new ProblemaSomaTripla("Um colecionador tem \\($a\\) selos do Brasil, \\($b\\) da Argentina e \\($c\\) do Chile. Quantos selos tem?"),
	new ProblemaSomaTripla("$nomeM montou \\($a\\) peças de um quebra-cabeça, \\($b\\) de outro e \\($c\\) de um terceiro. Quantas peças montou?"),

	// --- produção / colheita ---
	new ProblemaSomaTripla("Uma fábrica fez \\($a\\) cadeiras, \\($b\\) mesas e \\($c\\) armários. Quantos móveis fez?"),
	new ProblemaSomaTripla("Uma confeitaria fez \\($a\\) brigadeiros, \\($b\\) beijinhos e \\($c\\) cajuzinhos. Quantos doces fez?"),
	new ProblemaSomaTripla("Uma gráfica imprimiu \\($a\\) cartazes, \\($b\\) panfletos e \\($c\\) folhetos. Quantos impressos produziu?"),
	new ProblemaSomaTripla("Uma horta produziu \\($a\\) tomates, \\($b\\) pepinos e \\($c\\) cenouras. Quantos legumes produziu?"),
	new ProblemaSomaTripla("Uma fazenda colheu \\($a\\) laranjas, \\($b\\) limões e \\($c\\) tangerinas. Quantas frutas colheu?"),
	new ProblemaSomaTripla("Uma marcenaria produziu \\($a\\) portas, \\($b\\) janelas e \\($c\\) prateleiras. Quantas peças produziu?"),

	// --- transporte / viagens ---
	new ProblemaSomaTripla("Um ônibus levou \\($a\\) passageiros na primeira viagem, \\($b\\) na segunda e \\($c\\) na terceira. Quantos passageiros transportou?"),
	new ProblemaSomaTripla("Um avião transportou \\($a\\) malas num voo, \\($b\\) noutro e \\($c\\) num terceiro. Quantas malas transportou?"),
	new ProblemaSomaTripla("Um caminhão entregou \\($a\\) caixas numa loja, \\($b\\) noutra e \\($c\\) numa terceira. Quantas caixas entregou?"),
	new ProblemaSomaTripla("Uma viagem teve \\($a\\) km no primeiro trecho, \\($b\\) no segundo e \\($c\\) no terceiro. Qual a distância total?"),
	new ProblemaSomaTripla("Um trem levou \\($a\\) pessoas pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantas pessoas transportou?"),

	// --- mais variados ---
	new ProblemaSomaTripla("Uma turma juntou \\($a\\) tampinhas numa semana, \\($b\\) noutra e \\($c\\) numa terceira. Quantas tampinhas juntou?"),
	new ProblemaSomaTripla("Um aquário tem \\($a\\) peixes vermelhos, \\($b\\) amarelos e \\($c\\) azuis. Quantos peixes há?"),
	new ProblemaSomaTripla("$nomeF tirou \\($a\\) fotos numa cidade, \\($b\\) noutra e \\($c\\) numa terceira. Quantas fotos tirou?"),
	new ProblemaSomaTripla("Uma escola plantou \\($a\\) mudas no pátio, \\($b\\) na frente e \\($c\\) nos fundos. Quantas mudas plantou?"),
	new ProblemaSomaTripla("Uma loja de brinquedos vendeu \\($a\\) bonecas, \\($b\\) carrinhos e \\($c\\) bolas. Quantos brinquedos vendeu?"),
	new ProblemaSomaTripla("Um teatro vendeu \\($a\\) ingressos na quinta, \\($b\\) na sexta e \\($c\\) no sábado. Quantos ingressos vendeu?"),
	new ProblemaSomaTripla("Uma fábrica gastou \\($a\\) litros de tinta numa pintura, \\($b\\) noutra e \\($c\\) numa terceira. Quantos litros gastou?"),
	new ProblemaSomaTripla("$nomeM resolveu \\($a\\) exercícios na segunda, \\($b\\) na terça e \\($c\\) na quarta. Quantos exercícios resolveu?"),
	new ProblemaSomaTripla("Uma reserva avistou \\($a\\) macacos, \\($b\\) tucanos e \\($c\\) araras. Quantos animais foram avistados?"),
	new ProblemaSomaTripla("Um supermercado vendeu \\($a\\) litros de leite, \\($b\\) de suco e \\($c\\) de água. Quantos litros vendeu?"),
	new ProblemaSomaTripla("Uma escola arrecadou \\($a\\) agasalhos numa turma, \\($b\\) noutra e \\($c\\) numa terceira. Quantos agasalhos arrecadou?"),
	new ProblemaSomaTripla("Um clube tem \\($a\\) sócios no futebol, \\($b\\) no vôlei e \\($c\\) no basquete. Quantos sócios há?"),
	new ProblemaSomaTripla("Uma feira teve \\($a\\) visitantes na sexta, \\($b\\) no sábado e \\($c\\) no domingo. Quantos visitantes teve?"),
	new ProblemaSomaTripla("Um pomar deu \\($a\\) frutas numa colheita, \\($b\\) noutra e \\($c\\) numa terceira. Quantas frutas deu ao todo?"),
	new ProblemaSomaTripla("Uma escola tem \\($a\\) meninos, \\($b\\) meninas e \\($c\\) professores. Quantas pessoas há na escola?"),
	new ProblemaSomaTripla("Um cinema vendeu \\($a\\) ingressos para a sessão da tarde, \\($b\\) para a da noite e \\($c\\) para a da madrugada. Quantos ingressos vendeu?"),
	new ProblemaSomaTripla("$nomeF comprou \\($a\\) maçãs, \\($b\\) peras e \\($c\\) bananas. Quantas frutas comprou?"),
	new ProblemaSomaTripla("Uma obra usou \\($a\\) sacos de cimento na fundação, \\($b\\) nas paredes e \\($c\\) no acabamento. Quantos sacos usou?"),
	new ProblemaSomaTripla("Uma loja recebeu \\($a\\) produtos na segunda, \\($b\\) na terça e \\($c\\) na quarta. Quantos produtos recebeu ao todo?"),
	new ProblemaSomaTripla("Um aeroporto teve \\($a\\) voos pela manhã, \\($b\\) à tarde e \\($c\\) à noite. Quantos voos houve no dia?"),

	};

	public static ProblemaSomaTripla getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
