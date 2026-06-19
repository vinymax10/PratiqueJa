package matematica.basico.subtracaonatural;

import java.util.Random;

// Biblioteca de enunciados contextualizados de DIFERENÇA (quanto uma quantidade
// excede a outra). $a é sempre a quantidade maior e $b a menor.
public class TextoDiferenca
{
	static ProblemaDiferenca problemas[] = {

	// --- pessoas e pertences ---
	new ProblemaDiferenca("$nomeM tem \\($a\\) figurinhas e $nomeF tem \\($b\\). Quantas figurinhas a mais $nomeM tem?"),
	new ProblemaDiferenca("$nomeF colecionou \\($a\\) selos e $nomeM, \\($b\\). Quantos selos a mais ela tem?"),
	new ProblemaDiferenca("$nomeM tem \\($a\\) anos e seu irmão mais novo tem \\($b\\). Quantos anos ele é mais velho?"),
	new ProblemaDiferenca("Numa prova, $nomeF acertou \\($a\\) questões e $nomeM, \\($b\\). Quantas questões a mais ela acertou?"),
	new ProblemaDiferenca("$nomeM economizou \\($a\\) reais e $nomeF, \\($b\\). Quantos reais a mais ele economizou?"),
	new ProblemaDiferenca("$nomeF tem \\($a\\) adesivos e $nomeM tem \\($b\\). Qual a diferença entre as coleções?"),
	new ProblemaDiferenca("$nomeM marcou \\($a\\) pontos no jogo e $nomeF marcou \\($b\\). Por quantos pontos ele a superou?"),
	new ProblemaDiferenca("$nomeF leu \\($a\\) páginas e $nomeM leu \\($b\\). Quantas páginas a mais ela leu?"),
	new ProblemaDiferenca("$nomeM guardou \\($a\\) moedas e $nomeF guardou \\($b\\). Quantas moedas a mais ele tem?"),
	new ProblemaDiferenca("$nomeF tirou \\($a\\) fotos na viagem e $nomeM tirou \\($b\\). Quantas fotos a mais ela tirou?"),

	// --- escola e turmas ---
	new ProblemaDiferenca("A turma A tem \\($a\\) alunos e a turma B tem \\($b\\). Quantos alunos a mais tem a turma A?"),
	new ProblemaDiferenca("Uma escola matriculou \\($a\\) meninos e \\($b\\) meninas. Quantos meninos a mais há?"),
	new ProblemaDiferenca("Na biblioteca há \\($a\\) livros de ficção e \\($b\\) de poesia. Quantos livros de ficção a mais há?"),
	new ProblemaDiferenca("No primeiro ano há \\($a\\) estudantes e no segundo, \\($b\\). Qual a diferença entre os anos?"),
	new ProblemaDiferenca("Uma prova tinha \\($a\\) questões e $nomeM acertou \\($b\\). Quantas questões ele errou?"),
	new ProblemaDiferenca("Foram comprados \\($a\\) cadernos e distribuídos \\($b\\). Quantos cadernos sobraram?"),
	new ProblemaDiferenca("A escola tem \\($a\\) carteiras e \\($b\\) alunos. Quantas carteiras estão vazias?"),
	new ProblemaDiferenca("Um professor corrigiu \\($a\\) provas e faltam \\($b\\) por corrigir do total. Quantas já corrigiu a mais do que faltam?"),

	// --- comércio e dinheiro ---
	new ProblemaDiferenca("Uma loja vendeu \\($a\\) produtos na sexta e \\($b\\) no sábado. Qual a diferença de vendas?"),
	new ProblemaDiferenca("Uma empresa faturou \\($a\\) reais em janeiro e \\($b\\) em fevereiro. Qual foi a diferença de faturamento?"),
	new ProblemaDiferenca("$nomeF tinha \\($a\\) reais e gastou até ficar com \\($b\\). Quanto ela gastou?"),
	new ProblemaDiferenca("Um produto custava \\($a\\) reais e entrou em promoção por \\($b\\). De quanto foi o desconto?"),
	new ProblemaDiferenca("Uma loja tinha \\($a\\) peças em estoque e vendeu até restarem \\($b\\). Quantas peças foram vendidas?"),
	new ProblemaDiferenca("Um caixa registrou \\($a\\) reais pela manhã e \\($b\\) à tarde. Quanto a mais entrou pela manhã?"),
	new ProblemaDiferenca("Uma feira recebeu \\($a\\) clientes no domingo e \\($b\\) no sábado. Quantos clientes a mais no domingo?"),
	new ProblemaDiferenca("Um vendedor tinha a meta de \\($a\\) vendas e fez \\($b\\). Quantas vendas faltaram para a meta?"),

	// --- esportes e jogos ---
	new ProblemaDiferenca("Um time fez \\($a\\) pontos e o adversário fez \\($b\\). Por quantos pontos venceu?"),
	new ProblemaDiferenca("Na temporada, um jogador fez \\($a\\) gols e outro fez \\($b\\). Quantos gols a mais fez o primeiro?"),
	new ProblemaDiferenca("Um estádio comporta \\($a\\) torcedores e foram vendidos \\($b\\) ingressos. Quantos lugares ficaram vazios?"),
	new ProblemaDiferenca("$nomeM correu \\($a\\) metros e $nomeF correu \\($b\\). Quantos metros a mais ele correu?"),
	new ProblemaDiferenca("Um atleta treinou \\($a\\) minutos na segunda e \\($b\\) na terça. Quantos minutos a mais treinou na segunda?"),
	new ProblemaDiferenca("Numa competição, a equipe azul somou \\($a\\) pontos e a vermelha \\($b\\). Qual a diferença de pontos?"),
	new ProblemaDiferenca("Um ciclista pedalou \\($a\\) km e outro \\($b\\). Quantos km a mais pedalou o primeiro?"),

	// --- comida e cozinha ---
	new ProblemaDiferenca("Uma padaria assou \\($a\\) pães e vendeu \\($b\\). Quantos pães sobraram?"),
	new ProblemaDiferenca("Uma confeitaria fez \\($a\\) doces e \\($b\\) salgados. Quantos doces a mais foram feitos?"),
	new ProblemaDiferenca("No pomar foram colhidas \\($a\\) laranjas e \\($b\\) limões. Quantas laranjas a mais foram colhidas?"),
	new ProblemaDiferenca("Uma cozinha tinha \\($a\\) ovos e usou \\($b\\). Quantos ovos restaram?"),
	new ProblemaDiferenca("Uma pizzaria vendeu \\($a\\) pizzas no sábado e \\($b\\) no domingo. Qual a diferença de vendas?"),
	new ProblemaDiferenca("$nomeF preparou \\($a\\) brigadeiros e serviu \\($b\\). Quantos brigadeiros sobraram?"),
	new ProblemaDiferenca("Um restaurante tinha \\($a\\) reservas e \\($b\\) compareceram. Quantas reservas não compareceram?"),

	// --- viagem e transporte ---
	new ProblemaDiferenca("Um carro percorreu \\($a\\) km e outro percorreu \\($b\\). Qual a diferença de distância?"),
	new ProblemaDiferenca("Um ônibus saiu com \\($a\\) passageiros e chegou com \\($b\\). Quantos passageiros desceram?"),
	new ProblemaDiferenca("Numa viagem, no primeiro dia foram \\($a\\) km e no segundo \\($b\\). Quantos km a mais no primeiro dia?"),
	new ProblemaDiferenca("Um aeroporto teve \\($a\\) voos na sexta e \\($b\\) no domingo. Quantos voos a mais na sexta?"),
	new ProblemaDiferenca("Um estacionamento tinha \\($a\\) vagas e \\($b\\) estavam ocupadas. Quantas vagas estavam livres?"),
	new ProblemaDiferenca("Um trem levava \\($a\\) pessoas e \\($b\\) desembarcaram. Quantas pessoas continuaram a bordo?"),

	// --- natureza e animais ---
	new ProblemaDiferenca("Uma fazenda tem \\($a\\) galinhas e \\($b\\) patos. Quantas galinhas a mais há?"),
	new ProblemaDiferenca("Num parque foram plantadas \\($a\\) árvores e \\($b\\) sobreviveram à seca. Quantas árvores se perderam?"),
	new ProblemaDiferenca("Um aquário tinha \\($a\\) peixes e restaram \\($b\\). Quantos peixes foram retirados?"),
	new ProblemaDiferenca("Numa reserva há \\($a\\) aves de uma espécie e \\($b\\) de outra. Qual a diferença entre as espécies?"),
	new ProblemaDiferenca("Um apicultor esperava \\($a\\) potes de mel e colheu \\($b\\). Quantos potes faltaram?"),
	new ProblemaDiferenca("Numa trilha, $nomeM avistou \\($a\\) pássaros e $nomeF \\($b\\). Quantos a mais ele avistou?"),

	// --- cidade, trabalho e população ---
	new ProblemaDiferenca("A cidade A tem \\($a\\) habitantes e a cidade B tem \\($b\\). Quantos habitantes a mais tem a cidade A?"),
	new ProblemaDiferenca("Uma fábrica produziu \\($a\\) peças e \\($b\\) passaram no controle de qualidade. Quantas foram reprovadas?"),
	new ProblemaDiferenca("Um hospital atendeu \\($a\\) pacientes na segunda e \\($b\\) na terça. Quantos pacientes a mais na segunda?"),
	new ProblemaDiferenca("Um prédio tem \\($a\\) apartamentos e \\($b\\) estão ocupados. Quantos apartamentos estão vazios?"),
	new ProblemaDiferenca("Uma obra previa \\($a\\) sacos de cimento e usou \\($b\\). Quantos sacos sobraram?"),
	new ProblemaDiferenca("Uma empresa tinha \\($a\\) funcionários e \\($b\\) se aposentaram. Quantos funcionários permaneceram?"),
	new ProblemaDiferenca("Um armazém recebeu \\($a\\) caixas e despachou \\($b\\). Quantas caixas ficaram?"),

	// --- eventos e cultura ---
	new ProblemaDiferenca("Um show teve \\($a\\) pessoas no sábado e \\($b\\) no domingo. Qual a diferença de público?"),
	new ProblemaDiferenca("Um cinema vendeu \\($a\\) ingressos para um filme e \\($b\\) para outro. Quantos ingressos a mais para o primeiro?"),
	new ProblemaDiferenca("Uma exposição recebeu \\($a\\) visitantes na primeira semana e \\($b\\) na segunda. Quantos a mais na primeira?"),
	new ProblemaDiferenca("Um teatro tem \\($a\\) poltronas e \\($b\\) foram ocupadas. Quantas poltronas ficaram livres?"),
	new ProblemaDiferenca("Uma festa esperava \\($a\\) convidados e compareceram \\($b\\). Quantos convidados faltaram?"),

	// --- casa e objetos ---
	new ProblemaDiferenca("$nomeF tinha \\($a\\) contas para montar um colar e usou \\($b\\). Quantas contas sobraram?"),
	new ProblemaDiferenca("Uma costureira comprou \\($a\\) botões e usou \\($b\\). Quantos botões restaram?"),
	new ProblemaDiferenca("Num galpão havia \\($a\\) caixas e foram retiradas \\($b\\). Quantas caixas ficaram?"),
	new ProblemaDiferenca("$nomeM tinha \\($a\\) reais no cofrinho e gastou \\($b\\). Quanto sobrou?"),
	new ProblemaDiferenca("Uma gráfica imprimiu \\($a\\) cartazes e \\($b\\) foram entregues. Quantos cartazes restam?"),
	new ProblemaDiferenca("Um depósito guardava \\($a\\) garrafas e \\($b\\) foram distribuídas. Quantas garrafas sobraram?"),

	// --- tempo e medidas ---
	new ProblemaDiferenca("$nomeM estudou \\($a\\) minutos e $nomeF estudou \\($b\\). Quantos minutos a mais ele estudou?"),
	new ProblemaDiferenca("Uma viagem durou \\($a\\) minutos na ida e \\($b\\) na volta. Qual a diferença de tempo?"),
	new ProblemaDiferenca("Um reservatório tinha \\($a\\) litros e agora tem \\($b\\). Quantos litros foram consumidos?"),
	new ProblemaDiferenca("Uma sacola pesava \\($a\\) gramas e outra \\($b\\). Quantos gramas a mais pesava a primeira?"),
	new ProblemaDiferenca("Um rolo tinha \\($a\\) metros de fita e foram usados \\($b\\). Quantos metros sobraram?"),
	new ProblemaDiferenca("Uma planta cresceu até \\($a\\) cm e outra até \\($b\\) cm. Qual a diferença de altura?"),

	// --- mais variados ---
	new ProblemaDiferenca("Uma campanha arrecadou \\($a\\) alimentos numa semana e \\($b\\) na outra. Quantos a mais na primeira?"),
	new ProblemaDiferenca("Um zoológico recebeu \\($a\\) visitantes adultos e \\($b\\) crianças. Quantos adultos a mais?"),
	new ProblemaDiferenca("$nomeF resolveu \\($a\\) exercícios e $nomeM resolveu \\($b\\). Quantos a mais ela resolveu?"),
	new ProblemaDiferenca("Uma turma juntou \\($a\\) tampinhas e a meta era \\($b\\) a menos. Quantas passaram da meta inferior?"),
	new ProblemaDiferenca("Um músico ensaiou \\($a\\) horas no mês e \\($b\\) no anterior. Quantas horas a mais neste mês?"),

	};

	public static ProblemaDiferenca getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
