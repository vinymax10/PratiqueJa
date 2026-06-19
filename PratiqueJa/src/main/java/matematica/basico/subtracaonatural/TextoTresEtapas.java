package matematica.basico.subtracaonatural;

import java.util.Random;

// Biblioteca de enunciados de subtração em dois passos: de um total $a retiram-se $b e
// depois $c; pergunta-se quanto restou. $a é o total e $b, $c as duas retiradas.
public class TextoTresEtapas
{
	static ProblemaTresEtapas problemas[] = {

	// --- dinheiro e gastos ---
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) reais. Gastou \\($b\\) reais no mercado e \\($c\\) reais numa livraria. Quanto lhe sobrou?"),
	new ProblemaTresEtapas("$nomeF tinha \\($a\\) reais e pagou \\($b\\) de luz e \\($c\\) de água. Com quanto ela ficou?"),
	new ProblemaTresEtapas("Uma empresa tinha \\($a\\) reais em caixa. Pagou \\($b\\) de fornecedores e \\($c\\) de aluguel. Quanto restou em caixa?"),
	new ProblemaTresEtapas("$nomeM recebeu \\($a\\) reais e gastou \\($b\\) com transporte e \\($c\\) com alimentação. Quanto sobrou?"),
	new ProblemaTresEtapas("Uma família tinha \\($a\\) reais e gastou \\($b\\) no supermercado e \\($c\\) na farmácia. Quanto sobrou?"),
	new ProblemaTresEtapas("$nomeF juntou \\($a\\) reais e gastou \\($b\\) num presente e \\($c\\) num livro. Com quanto ficou?"),
	new ProblemaTresEtapas("Um caixa tinha \\($a\\) reais e foram retirados \\($b\\) e depois \\($c\\). Quanto restou no caixa?"),
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) reais na conta e sacou \\($b\\) e depois \\($c\\). Quanto sobrou na conta?"),

	// --- doces e comida ---
	new ProblemaTresEtapas("Uma caixa tinha \\($a\\) chocolates. Foram dados \\($b\\) para a turma A e \\($c\\) para a turma B. Quantos chocolates restaram?"),
	new ProblemaTresEtapas("$nomeF fez \\($a\\) brigadeiros, serviu \\($b\\) na festa e deu \\($c\\) para a vizinha. Quantos sobraram?"),
	new ProblemaTresEtapas("Uma padaria assou \\($a\\) pães, vendeu \\($b\\) pela manhã e \\($c\\) à tarde. Quantos pães restaram?"),
	new ProblemaTresEtapas("Havia \\($a\\) balas num pote. $nomeM comeu \\($b\\) e $nomeF comeu \\($c\\). Quantas balas sobraram?"),
	new ProblemaTresEtapas("Uma cozinha tinha \\($a\\) ovos. Foram usados \\($b\\) num bolo e \\($c\\) numa omelete. Quantos ovos sobraram?"),
	new ProblemaTresEtapas("Um pomar tinha \\($a\\) maçãs. Foram colhidas \\($b\\) na segunda e \\($c\\) na terça. Quantas maçãs ficaram?"),
	new ProblemaTresEtapas("Uma confeitaria tinha \\($a\\) doces. Vendeu \\($b\\) de manhã e \\($c\\) à tarde. Quantos doces restaram?"),
	new ProblemaTresEtapas("Um restaurante tinha \\($a\\) porções. Serviu \\($b\\) no almoço e \\($c\\) no jantar. Quantas porções sobraram?"),

	// --- escola e biblioteca ---
	new ProblemaTresEtapas("Uma biblioteca tinha \\($a\\) livros. Foram emprestados \\($b\\) na primeira semana e \\($c\\) na segunda. Quantos livros restaram?"),
	new ProblemaTresEtapas("Uma escola tinha \\($a\\) cadernos. Distribuiu \\($b\\) para o turno da manhã e \\($c\\) para o da tarde. Quantos sobraram?"),
	new ProblemaTresEtapas("Uma sala tinha \\($a\\) cadeiras. Foram levadas \\($b\\) para o auditório e \\($c\\) para o pátio. Quantas restaram?"),
	new ProblemaTresEtapas("Uma prova tinha \\($a\\) questões. $nomeM respondeu \\($b\\) na primeira hora e \\($c\\) na segunda. Quantas faltam?"),
	new ProblemaTresEtapas("A professora levou \\($a\\) folhas. Usou \\($b\\) numa atividade e \\($c\\) noutra. Quantas folhas sobraram?"),
	new ProblemaTresEtapas("Uma turma tinha \\($a\\) lápis de cor. Emprestou \\($b\\) e \\($c\\) para outras turmas. Quantos restaram?"),

	// --- comércio e estoque ---
	new ProblemaTresEtapas("Uma loja tinha \\($a\\) camisetas. Vendeu \\($b\\) na sexta e \\($c\\) no sábado. Quantas camisetas restaram?"),
	new ProblemaTresEtapas("Um mercado tinha \\($a\\) caixas de leite. Vendeu \\($b\\) e devolveu \\($c\\) vencidas. Quantas caixas ficaram?"),
	new ProblemaTresEtapas("Um depósito tinha \\($a\\) unidades. Entregou \\($b\\) na segunda e \\($c\\) na quarta. Quantas unidades restaram?"),
	new ProblemaTresEtapas("Uma farmácia tinha \\($a\\) caixas de remédio. Vendeu \\($b\\) e doou \\($c\\). Quantas caixas sobraram?"),
	new ProblemaTresEtapas("Uma fábrica produziu \\($a\\) peças. Despachou \\($b\\) e reprovou \\($c\\). Quantas peças ficaram em estoque?"),
	new ProblemaTresEtapas("Uma banca tinha \\($a\\) revistas. Vendeu \\($b\\) e devolveu \\($c\\). Quantas revistas restaram?"),
	new ProblemaTresEtapas("Um armazém tinha \\($a\\) sacos de arroz. Vendeu \\($b\\) e doou \\($c\\). Quantos sacos restaram?"),

	// --- transporte ---
	new ProblemaTresEtapas("Um ônibus saiu com \\($a\\) passageiros. Desceram \\($b\\) num ponto e \\($c\\) noutro. Quantos continuaram a bordo?"),
	new ProblemaTresEtapas("Um trem partiu com \\($a\\) pessoas. Desembarcaram \\($b\\) e depois \\($c\\). Quantas pessoas restaram?"),
	new ProblemaTresEtapas("Uma viagem tem \\($a\\) km. $nomeM percorreu \\($b\\) km de manhã e \\($c\\) km à tarde. Quantos km faltam?"),
	new ProblemaTresEtapas("Um estacionamento tinha \\($a\\) vagas. Foram ocupadas \\($b\\) e depois \\($c\\). Quantas vagas ficaram livres?"),
	new ProblemaTresEtapas("Um caminhão levava \\($a\\) caixas. Descarregou \\($b\\) numa loja e \\($c\\) noutra. Quantas caixas restaram?"),

	// --- animais e natureza ---
	new ProblemaTresEtapas("Uma fazenda tinha \\($a\\) animais. Vendeu \\($b\\) na feira de segunda e \\($c\\) na de quarta. Quantos animais restaram?"),
	new ProblemaTresEtapas("Um aquário tinha \\($a\\) peixes. Foram transferidos \\($b\\) e depois \\($c\\). Quantos peixes restaram?"),
	new ProblemaTresEtapas("Um viveiro tinha \\($a\\) mudas. Foram plantadas \\($b\\) num canteiro e \\($c\\) noutro. Quantas mudas sobraram?"),
	new ProblemaTresEtapas("Um galinheiro tinha \\($a\\) ovos. Foram vendidos \\($b\\) e usados \\($c\\). Quantos ovos restaram?"),
	new ProblemaTresEtapas("Um apiário tinha \\($a\\) potes de mel. Vendeu \\($b\\) e doou \\($c\\). Quantos potes sobraram?"),

	// --- casa e objetos ---
	new ProblemaTresEtapas("$nomeF tinha \\($a\\) contas. Usou \\($b\\) num colar e \\($c\\) numa pulseira. Quantas contas sobraram?"),
	new ProblemaTresEtapas("Um rolo tinha \\($a\\) metros de fita. Foram cortados \\($b\\) e depois \\($c\\). Quantos metros sobraram?"),
	new ProblemaTresEtapas("Uma caixa de peças tinha \\($a\\) parafusos. Foram usados \\($b\\) e \\($c\\). Quantos parafusos restaram?"),
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) blocos de montar. Usou \\($b\\) numa torre e \\($c\\) numa ponte. Quantos restaram?"),
	new ProblemaTresEtapas("Uma costureira tinha \\($a\\) botões. Usou \\($b\\) numa blusa e \\($c\\) numa camisa. Quantos botões sobraram?"),
	new ProblemaTresEtapas("Um depósito guardava \\($a\\) garrafas. Foram entregues \\($b\\) e \\($c\\). Quantas garrafas restaram?"),

	// --- coleções e pertences ---
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) figurinhas. Deu \\($b\\) para um amigo e trocou \\($c\\). Com quantas ficou?"),
	new ProblemaTresEtapas("$nomeF colecionava \\($a\\) selos. Perdeu \\($b\\) e doou \\($c\\). Quantos selos restaram?"),
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) cromos. Colou \\($b\\) num álbum e deu \\($c\\). Quantos cromos sobraram?"),
	new ProblemaTresEtapas("$nomeF tinha \\($a\\) adesivos. Colou \\($b\\) no caderno e \\($c\\) na mochila. Quantos sobraram?"),
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) moedas. Gastou \\($b\\) e guardou \\($c\\) no cofre. Quantas ficaram na carteira?"),

	// --- cidade, trabalho, produção ---
	new ProblemaTresEtapas("Uma cidade tinha \\($a\\) habitantes. \\($b\\) se mudaram e \\($c\\) emigraram. Quantos habitantes restaram?"),
	new ProblemaTresEtapas("Uma obra recebeu \\($a\\) sacos de cimento. Usou \\($b\\) na fundação e \\($c\\) nas paredes. Quantos sobraram?"),
	new ProblemaTresEtapas("Um hospital tinha \\($a\\) máscaras. Usou \\($b\\) num dia e \\($c\\) no outro. Quantas máscaras restaram?"),
	new ProblemaTresEtapas("Uma empresa tinha \\($a\\) funcionários. \\($b\\) se aposentaram e \\($c\\) pediram demissão. Quantos permaneceram?"),
	new ProblemaTresEtapas("Uma gráfica imprimiu \\($a\\) cartazes. Entregou \\($b\\) numa escola e \\($c\\) noutra. Quantos restaram?"),
	new ProblemaTresEtapas("Um prédio tinha um reservatório com \\($a\\) litros. Foram consumidos \\($b\\) e \\($c\\) litros. Quantos litros restaram?"),

	// --- eventos ---
	new ProblemaTresEtapas("Um teatro tinha \\($a\\) ingressos. Vendeu \\($b\\) na bilheteria e \\($c\\) pela internet. Quantos ingressos sobraram?"),
	new ProblemaTresEtapas("Uma festa tinha \\($a\\) lembrancinhas. Foram entregues \\($b\\) aos adultos e \\($c\\) às crianças. Quantas sobraram?"),
	new ProblemaTresEtapas("Um cinema tinha \\($a\\) lugares. Foram ocupados \\($b\\) na plateia e \\($c\\) no balcão. Quantos lugares ficaram vazios?"),
	new ProblemaTresEtapas("Um show tinha \\($a\\) ingressos. Foram usados \\($b\\) na pista e \\($c\\) na arquibancada. Quantos não foram usados?"),

	// --- tempo, medidas e variados ---
	new ProblemaTresEtapas("Um reservatório tinha \\($a\\) litros. Foram consumidos \\($b\\) pela manhã e \\($c\\) à tarde. Quantos litros restaram?"),
	new ProblemaTresEtapas("Um tanque tinha \\($a\\) litros de combustível. Gastou \\($b\\) numa viagem e \\($c\\) noutra. Quantos litros sobraram?"),
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) minutos de bateria. Usou \\($b\\) em jogos e \\($c\\) em vídeos. Quantos minutos sobraram?"),
	new ProblemaTresEtapas("Uma campanha tinha a meta de \\($a\\) doações. Recebeu \\($b\\) numa semana e \\($c\\) na outra. Quantas faltam?"),
	new ProblemaTresEtapas("Um pacote trazia \\($a\\) folhas. Foram usadas \\($b\\) numa impressão e \\($c\\) noutra. Quantas folhas restaram?"),
	new ProblemaTresEtapas("$nomeF tinha \\($a\\) páginas para ler. Leu \\($b\\) de dia e \\($c\\) à noite. Quantas páginas faltam?"),
	new ProblemaTresEtapas("Um jardim tinha \\($a\\) flores. Foram cortadas \\($b\\) e murcharam \\($c\\). Quantas flores restaram?"),
	new ProblemaTresEtapas("Uma turma juntou \\($a\\) tampinhas. Entregou \\($b\\) para reciclagem e \\($c\\) para arte. Quantas sobraram?"),
	new ProblemaTresEtapas("Um estoque tinha \\($a\\) unidades. Vendeu \\($b\\) e perdeu \\($c\\) por avaria. Quantas unidades restaram?"),
	new ProblemaTresEtapas("Uma sala tinha \\($a\\) lugares. Sentaram-se \\($b\\) alunos e \\($c\\) professores. Quantos lugares ficaram livres?"),
	new ProblemaTresEtapas("Um clube tinha \\($a\\) sócios. \\($b\\) cancelaram e \\($c\\) se transferiram. Quantos sócios restaram?"),
	new ProblemaTresEtapas("Uma horta tinha \\($a\\) pés de alface. Foram colhidos \\($b\\) e \\($c\\) em dois dias. Quantos pés restaram?"),
	new ProblemaTresEtapas("Um zoológico recebeu \\($a\\) visitantes. \\($b\\) saíram pela manhã e \\($c\\) à tarde. Quantos ainda estão no local?"),
	new ProblemaTresEtapas("Uma loja recebeu \\($a\\) brinquedos. Vendeu \\($b\\) e devolveu \\($c\\) com defeito. Quantos restaram?"),
	new ProblemaTresEtapas("Um caderno tinha \\($a\\) páginas. $nomeF preencheu \\($b\\) e $nomeM \\($c\\). Quantas páginas ficaram em branco?"),
	new ProblemaTresEtapas("Uma papelaria tinha \\($a\\) canetas. Vendeu \\($b\\) na segunda e \\($c\\) na terça. Quantas canetas restaram?"),
	new ProblemaTresEtapas("$nomeM tinha \\($a\\) bolinhas de gude. Perdeu \\($b\\) num jogo e \\($c\\) noutro. Quantas restaram?"),
	new ProblemaTresEtapas("Uma feira tinha \\($a\\) caixas de fruta. Foram vendidas \\($b\\) e \\($c\\) em dois turnos. Quantas caixas sobraram?"),
	new ProblemaTresEtapas("Um pomar tinha \\($a\\) laranjeiras. Foram cortadas \\($b\\) e replantadas \\($c\\) em outro lugar. Quantas ficaram?"),
	new ProblemaTresEtapas("Uma escola recebeu \\($a\\) uniformes. Entregou \\($b\\) no matutino e \\($c\\) no vespertino. Quantos sobraram?"),
	new ProblemaTresEtapas("Um supermercado tinha \\($a\\) garrafas de suco. Vendeu \\($b\\) e \\($c\\) em dois dias. Quantas garrafas restaram?"),
	new ProblemaTresEtapas("$nomeF tinha \\($a\\) fotos no álbum. Apagou \\($b\\) e imprimiu \\($c\\) para dar de presente. Quantas restaram no álbum?"),
	new ProblemaTresEtapas("Uma quitanda tinha \\($a\\) abacaxis. Vendeu \\($b\\) pela manhã e \\($c\\) à tarde. Quantos abacaxis sobraram?"),

	};

	public static ProblemaTresEtapas getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
