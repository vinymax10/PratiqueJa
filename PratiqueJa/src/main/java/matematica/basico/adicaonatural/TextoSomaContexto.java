package matematica.basico.adicaonatural;

import java.util.Random;

// Biblioteca de enunciados contextualizados para a soma de duas parcelas (pergunta o total).
// Cada entrada é um template com placeholders $a, $b e, opcionalmente, $nomeM / $nomeF.
// getProblema() sorteia e clona um template; os valores são gerados depois pelo Problema.
public class TextoSomaContexto
{
	static ProblemaSomaContexto problemas[] = {

	// --- comércio e dinheiro ---
	new ProblemaSomaContexto("Em uma loja, \\($a\\) produtos foram vendidos pela manhã e \\($b\\) produtos à tarde. Quantos produtos foram vendidos no total?"),
	new ProblemaSomaContexto("Uma livraria vendeu \\($a\\) livros pela manhã e \\($b\\) livros à tarde. Quantos livros foram vendidos no dia?"),
	new ProblemaSomaContexto("$nomeF economizou \\($a\\) reais em janeiro e \\($b\\) reais em fevereiro. Quanto ela economizou nos dois meses?"),
	new ProblemaSomaContexto("Uma loja de brinquedos vendeu \\($a\\) bonecas e \\($b\\) carrinhos. Quantos brinquedos foram vendidos?"),
	new ProblemaSomaContexto("Uma papelaria vendeu \\($a\\) cadernos e \\($b\\) canetas. Quantos itens foram vendidos?"),
	new ProblemaSomaContexto("Um supermercado vendeu \\($a\\) litros de leite e \\($b\\) litros de suco. Quantos litros foram vendidos?"),
	new ProblemaSomaContexto("Uma farmácia vendeu \\($a\\) caixas de remédio na segunda e \\($b\\) na terça. Quantas caixas vendeu ao todo?"),
	new ProblemaSomaContexto("$nomeM gastou \\($a\\) reais no mercado e \\($b\\) reais na farmácia. Quanto ele gastou no total?"),
	new ProblemaSomaContexto("Uma sorveteria vendeu \\($a\\) casquinhas no sábado e \\($b\\) no domingo. Quantas casquinhas vendeu no fim de semana?"),
	new ProblemaSomaContexto("Numa feira, uma barraca arrecadou \\($a\\) reais pela manhã e \\($b\\) reais à tarde. Quanto arrecadou no dia?"),
	new ProblemaSomaContexto("Uma loja recebeu \\($a\\) clientes na sexta e \\($b\\) clientes no sábado. Quantos clientes recebeu nos dois dias?"),
	new ProblemaSomaContexto("Uma banca vendeu \\($a\\) revistas e \\($b\\) jornais. Quantos exemplares foram vendidos?"),

	// --- escola e leitura ---
	new ProblemaSomaContexto("$nomeF leu \\($a\\) livros de ficção e \\($b\\) livros de aventura. Quantos livros ela leu no total?"),
	new ProblemaSomaContexto("Uma escola tem \\($a\\) alunos no turno da manhã e \\($b\\) alunos no turno da tarde. Quantos alunos a escola tem no total?"),
	new ProblemaSomaContexto("$nomeF resolveu \\($a\\) exercícios de manhã e \\($b\\) exercícios à noite. Quantos exercícios ela resolveu?"),
	new ProblemaSomaContexto("$nomeF leu \\($a\\) páginas pela manhã e \\($b\\) páginas à noite. Quantas páginas ela leu no dia?"),
	new ProblemaSomaContexto("Uma biblioteca recebeu uma doação de \\($a\\) livros de ficção e \\($b\\) livros de não-ficção. Quantos livros foram doados no total?"),
	new ProblemaSomaContexto("Numa prova, $nomeM acertou \\($a\\) questões de português e \\($b\\) de matemática. Quantas questões ele acertou?"),
	new ProblemaSomaContexto("A turma A tem \\($a\\) estudantes e a turma B tem \\($b\\) estudantes. Quantos estudantes há nas duas turmas?"),
	new ProblemaSomaContexto("$nomeM colou \\($a\\) figurinhas em uma página e \\($b\\) em outra. Quantas figurinhas ele colou?"),
	new ProblemaSomaContexto("Uma escola distribuiu \\($a\\) cadernos e \\($b\\) lápis aos alunos. Quantos materiais foram distribuídos?"),
	new ProblemaSomaContexto("Na sala de leitura há \\($a\\) livros infantis e \\($b\\) livros juvenis. Quantos livros há ao todo?"),

	// --- esportes e jogos ---
	new ProblemaSomaContexto("$nomeM marcou \\($a\\) pontos na primeira fase e \\($b\\) pontos na segunda fase de um jogo. Quantos pontos ele marcou no total?"),
	new ProblemaSomaContexto("Numa competição, $nomeM obteve \\($a\\) pontos na primeira prova e \\($b\\) pontos na segunda prova. Quantos pontos ele acumulou no total?"),
	new ProblemaSomaContexto("Um time fez \\($a\\) pontos no primeiro tempo e \\($b\\) pontos no segundo. Quantos pontos marcou na partida?"),
	new ProblemaSomaContexto("Na temporada, o time A fez \\($a\\) gols e o time B fez \\($b\\). Quantos gols os dois fizeram juntos?"),
	new ProblemaSomaContexto("$nomeF ganhou \\($a\\) medalhas na natação e \\($b\\) no atletismo. Quantas medalhas ela ganhou?"),
	new ProblemaSomaContexto("Um jogador fez \\($a\\) cestas no primeiro jogo e \\($b\\) no segundo. Quantas cestas fez ao todo?"),
	new ProblemaSomaContexto("Numa corrida, $nomeM correu \\($a\\) metros e depois mais \\($b\\) metros. Quantos metros ele correu?"),
	new ProblemaSomaContexto("Um clube tem \\($a\\) sócios no time de futebol e \\($b\\) no de vôlei. Quantos sócios há nos dois times?"),

	// --- comida e cozinha ---
	new ProblemaSomaContexto("Uma frutaria vendeu \\($a\\) laranjas pela manhã e \\($b\\) laranjas à tarde. Quantas laranjas foram vendidas no dia?"),
	new ProblemaSomaContexto("Uma padaria assou \\($a\\) pães de manhã e \\($b\\) pães à tarde. Quantos pães foram assados no dia?"),
	new ProblemaSomaContexto("Uma confeitaria fez \\($a\\) brigadeiros e \\($b\\) beijinhos. Quantos doces foram feitos no total?"),
	new ProblemaSomaContexto("No pomar foram colhidas \\($a\\) maçãs e \\($b\\) peras. Quantas frutas foram colhidas ao todo?"),
	new ProblemaSomaContexto("Uma pizzaria vendeu \\($a\\) pizzas na sexta e \\($b\\) no sábado. Quantas pizzas vendeu?"),
	new ProblemaSomaContexto("$nomeF preparou \\($a\\) salgados e \\($b\\) docinhos para a festa. Quantas guloseimas ela preparou?"),
	new ProblemaSomaContexto("Uma horta produziu \\($a\\) alfaces e \\($b\\) tomates. Quantas hortaliças foram colhidas no total?"),
	new ProblemaSomaContexto("Um restaurante serviu \\($a\\) pratos no almoço e \\($b\\) no jantar. Quantos pratos serviu no dia?"),
	new ProblemaSomaContexto("$nomeM comprou \\($a\\) maçãs e \\($b\\) bananas na feira. Quantas frutas ele comprou?"),
	new ProblemaSomaContexto("Uma fazenda produziu \\($a\\) litros de leite pela manhã e \\($b\\) à tarde. Quantos litros produziu?"),

	// --- viagem e transporte ---
	new ProblemaSomaContexto("$nomeM andou \\($a\\) km de bicicleta na segunda-feira e \\($b\\) km na terça-feira. Quantos km ele andou ao todo?"),
	new ProblemaSomaContexto("Numa viagem, o carro percorreu \\($a\\) km no primeiro dia e \\($b\\) km no segundo. Quantos km percorreu?"),
	new ProblemaSomaContexto("Um ônibus levava \\($a\\) passageiros e embarcaram mais \\($b\\) no ponto seguinte. Quantos passageiros há agora?"),
	new ProblemaSomaContexto("Um estacionamento recebeu \\($a\\) carros pela manhã e \\($b\\) carros à tarde. Quantos carros entraram no dia?"),
	new ProblemaSomaContexto("Um aeroporto teve \\($a\\) voos pela manhã e \\($b\\) à tarde. Quantos voos houve no dia?"),
	new ProblemaSomaContexto("$nomeF caminhou \\($a\\) metros de ida e \\($b\\) metros de volta. Quantos metros ela caminhou?"),
	new ProblemaSomaContexto("Um trem transportou \\($a\\) passageiros na primeira viagem e \\($b\\) na segunda. Quantos passageiros transportou?"),
	new ProblemaSomaContexto("Numa excursão foram \\($a\\) adultos e \\($b\\) crianças. Quantas pessoas participaram?"),

	// --- natureza e animais ---
	new ProblemaSomaContexto("Uma fazenda colheu \\($a\\) laranjas pela manhã e \\($b\\) laranjas à tarde. Quantas laranjas foram colhidas no total?"),
	new ProblemaSomaContexto("Num zoológico nasceram \\($a\\) filhotes em um ano e \\($b\\) no ano seguinte. Quantos filhotes nasceram?"),
	new ProblemaSomaContexto("Um aquário tinha \\($a\\) peixes e foram adicionados mais \\($b\\). Quantos peixes há no aquário?"),
	new ProblemaSomaContexto("Num parque foram plantadas \\($a\\) árvores na primavera e \\($b\\) no outono. Quantas árvores foram plantadas?"),
	new ProblemaSomaContexto("Uma reserva tem \\($a\\) aves de uma espécie e \\($b\\) de outra. Quantas aves há ao todo?"),
	new ProblemaSomaContexto("$nomeM observou \\($a\\) borboletas pela manhã e \\($b\\) à tarde. Quantas borboletas ele observou?"),
	new ProblemaSomaContexto("Um apicultor coletou \\($a\\) potes de mel em junho e \\($b\\) em julho. Quantos potes coletou?"),
	new ProblemaSomaContexto("Numa floresta foram avistados \\($a\\) macacos e \\($b\\) tucanos. Quantos animais foram avistados?"),

	// --- casa e objetos ---
	new ProblemaSomaContexto("A turma coletou \\($a\\) tampinhas de garrafa na primeira semana e \\($b\\) na segunda semana. Quantas tampinhas foram coletadas no total?"),
	new ProblemaSomaContexto("$nomeF tirou \\($a\\) fotos pela manhã e \\($b\\) fotos à tarde no passeio. Quantas fotos ela tirou no total?"),
	new ProblemaSomaContexto("Numa festa havia \\($a\\) convidados adultos e \\($b\\) convidados crianças. Quantos convidados compareceram?"),
	new ProblemaSomaContexto("Uma costureira fez \\($a\\) blusas e \\($b\\) calças. Quantas peças ela costurou?"),
	new ProblemaSomaContexto("$nomeM guardou \\($a\\) moedas em um cofre e \\($b\\) em outro. Quantas moedas ele guardou?"),
	new ProblemaSomaContexto("Uma gráfica imprimiu \\($a\\) cartazes e \\($b\\) panfletos. Quantos impressos produziu?"),
	new ProblemaSomaContexto("Num galpão há \\($a\\) caixas de um lado e \\($b\\) do outro. Quantas caixas há no galpão?"),
	new ProblemaSomaContexto("$nomeF montou \\($a\\) peças do quebra-cabeça antes do almoço e \\($b\\) depois. Quantas peças ela montou?"),

	// --- cidade, trabalho e população ---
	new ProblemaSomaContexto("Uma cidade tem \\($a\\) moradores no bairro A e \\($b\\) moradores no bairro B. Qual é a população total dos dois bairros?"),
	new ProblemaSomaContexto("Um armazém recebeu \\($a\\) kg de arroz e \\($b\\) kg de feijão. Quantos kg de alimentos foram recebidos no total?"),
	new ProblemaSomaContexto("No primeiro semestre, uma empresa vendeu \\($a\\) produtos. No segundo semestre, vendeu \\($b\\) produtos. Quantos produtos foram vendidos no ano?"),
	new ProblemaSomaContexto("Uma escola tem \\($a\\) alunos no turno matutino e \\($b\\) alunos no turno vespertino. Quantos alunos a escola tem no total?"),
	new ProblemaSomaContexto("Uma fábrica produziu \\($a\\) peças no turno da manhã e \\($b\\) no turno da noite. Quantas peças produziu?"),
	new ProblemaSomaContexto("Uma empresa contratou \\($a\\) funcionários em um ano e \\($b\\) no seguinte. Quantos funcionários contratou ao todo?"),
	new ProblemaSomaContexto("Num prédio há \\($a\\) apartamentos na torre A e \\($b\\) na torre B. Quantos apartamentos há no prédio?"),
	new ProblemaSomaContexto("Uma obra usou \\($a\\) sacos de cimento na fundação e \\($b\\) nas paredes. Quantos sacos foram usados?"),
	new ProblemaSomaContexto("Um hospital atendeu \\($a\\) pacientes pela manhã e \\($b\\) à tarde. Quantos pacientes atendeu no dia?"),

	// --- cinema e eventos ---
	new ProblemaSomaContexto("Num cinema, a sala A tinha \\($a\\) espectadores e a sala B, \\($b\\). Quantos espectadores havia ao todo?"),
	new ProblemaSomaContexto("Um show teve \\($a\\) pessoas na pista e \\($b\\) na arquibancada. Quantas pessoas assistiram?"),
	new ProblemaSomaContexto("Um teatro vendeu \\($a\\) ingressos na quinta e \\($b\\) na sexta. Quantos ingressos vendeu?"),
	new ProblemaSomaContexto("Uma exposição recebeu \\($a\\) visitantes no sábado e \\($b\\) no domingo. Quantos visitantes recebeu?"),

	// --- variados ---
	new ProblemaSomaContexto("$nomeM juntou \\($a\\) selos de um país e \\($b\\) de outro. Quantos selos ele tem?"),
	new ProblemaSomaContexto("Uma turma arrecadou \\($a\\) latas de alimento numa campanha e \\($b\\) em outra. Quantas latas arrecadou?"),
	new ProblemaSomaContexto("Num cofrinho havia \\($a\\) reais e $nomeF colocou mais \\($b\\). Quanto há no cofrinho agora?"),
	new ProblemaSomaContexto("Uma biblioteca registrou \\($a\\) empréstimos numa semana e \\($b\\) na seguinte. Quantos empréstimos houve?"),
	new ProblemaSomaContexto("$nomeF plantou \\($a\\) flores no jardim da frente e \\($b\\) no dos fundos. Quantas flores ela plantou?"),
	new ProblemaSomaContexto("Uma escola arrecadou \\($a\\) brinquedos numa campanha e \\($b\\) em outra. Quantos brinquedos arrecadou?"),
	new ProblemaSomaContexto("Num laboratório foram preparadas \\($a\\) amostras de manhã e \\($b\\) à tarde. Quantas amostras foram preparadas?"),
	new ProblemaSomaContexto("$nomeM montou \\($a\\) caixas de manhã e \\($b\\) à tarde no depósito. Quantas caixas ele montou?"),

	};

	public static ProblemaSomaContexto getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
