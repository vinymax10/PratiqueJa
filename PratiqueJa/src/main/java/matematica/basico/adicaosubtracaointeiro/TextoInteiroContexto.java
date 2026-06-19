package matematica.basico.adicaosubtracaointeiro;

import java.util.Random;

// Biblioteca de enunciados de inteiros em contexto. Cada entrada traz o template (com $x
// inicial e $y magnitude) e o sentido da variação (+1 sobe/ganha, -1 desce/perde).
public class TextoInteiroContexto
{
	static ProblemaInteiroContexto problemas[] = {

	// --- temperatura ---
	new ProblemaInteiroContexto("A temperatura era de \\($x\\)°C e subiu \\($y\\)°C. Qual é a temperatura final?", +1),
	new ProblemaInteiroContexto("A temperatura era de \\($x\\)°C e caiu \\($y\\)°C. Qual é a temperatura final?", -1),
	new ProblemaInteiroContexto("Pela manhã a temperatura marcava \\($x\\)°C e aumentou \\($y\\)°C ao meio-dia. Qual a temperatura ao meio-dia?", +1),
	new ProblemaInteiroContexto("À noite a temperatura era \\($x\\)°C e diminuiu \\($y\\)°C de madrugada. Qual a temperatura de madrugada?", -1),
	new ProblemaInteiroContexto("Um termômetro marcava \\($x\\)°C e a temperatura elevou-se \\($y\\)°C. Qual a nova marca?", +1),
	new ProblemaInteiroContexto("Um congelador estava a \\($x\\)°C e a temperatura baixou \\($y\\)°C. Qual a temperatura agora?", -1),

	// --- finanças / saldo ---
	new ProblemaInteiroContexto("O saldo de uma conta era de \\($x\\) reais. Houve um depósito de \\($y\\) reais. Qual o novo saldo?", +1),
	new ProblemaInteiroContexto("O saldo de uma conta era de \\($x\\) reais. Houve um saque de \\($y\\) reais. Qual o novo saldo?", -1),
	new ProblemaInteiroContexto("$nomeF tinha \\($x\\) reais na conta e recebeu \\($y\\) reais. Com quanto ficou?", +1),
	new ProblemaInteiroContexto("$nomeM tinha \\($x\\) reais na conta e pagou \\($y\\) reais de conta. Com quanto ficou?", -1),
	new ProblemaInteiroContexto("Uma empresa tinha um caixa de \\($x\\) reais e teve uma receita de \\($y\\) reais. Qual o caixa atual?", +1),
	new ProblemaInteiroContexto("Uma empresa tinha um caixa de \\($x\\) reais e teve uma despesa de \\($y\\) reais. Qual o caixa atual?", -1),
	new ProblemaInteiroContexto("O saldo de $nomeF estava em \\($x\\) reais e ela depositou \\($y\\) reais. Qual o saldo final?", +1),
	new ProblemaInteiroContexto("O saldo de $nomeM estava em \\($x\\) reais e ele gastou \\($y\\) reais no cartão. Qual o saldo final?", -1),

	// --- altitude / profundidade ---
	new ProblemaInteiroContexto("Um mergulhador estava a \\($x\\) m em relação ao nível do mar e subiu \\($y\\) m. A que altura ficou?", +1),
	new ProblemaInteiroContexto("Um mergulhador estava a \\($x\\) m em relação ao nível do mar e desceu \\($y\\) m. A que altura ficou?", -1),
	new ProblemaInteiroContexto("Um submarino estava a \\($x\\) m do nível do mar e emergiu \\($y\\) m. A que altura ficou?", +1),
	new ProblemaInteiroContexto("Um submarino estava a \\($x\\) m do nível do mar e mergulhou \\($y\\) m. A que altura ficou?", -1),
	new ProblemaInteiroContexto("Um balão estava a \\($x\\) m de altitude e subiu \\($y\\) m. Qual a nova altitude?", +1),
	new ProblemaInteiroContexto("Um balão estava a \\($x\\) m de altitude e desceu \\($y\\) m. Qual a nova altitude?", -1),
	new ProblemaInteiroContexto("Um alpinista estava a \\($x\\) m de altitude e subiu mais \\($y\\) m. A que altitude chegou?", +1),
	new ProblemaInteiroContexto("Um peixe estava a \\($x\\) m em relação à superfície e nadou \\($y\\) m para cima. A que altura ficou?", +1),

	// --- elevador / andares ---
	new ProblemaInteiroContexto("Um elevador estava no andar \\($x\\) e subiu \\($y\\) andares. Em que andar parou?", +1),
	new ProblemaInteiroContexto("Um elevador estava no andar \\($x\\) e desceu \\($y\\) andares. Em que andar parou?", -1),
	new ProblemaInteiroContexto("Uma pessoa estava no andar \\($x\\) de um prédio e subiu \\($y\\) andares de escada. Em que andar chegou?", +1),
	new ProblemaInteiroContexto("Uma pessoa estava no andar \\($x\\) e desceu \\($y\\) andares até a garagem. Em que andar chegou?", -1),

	// --- jogos / pontos ---
	new ProblemaInteiroContexto("Um jogador tinha \\($x\\) pontos e ganhou \\($y\\) pontos na rodada. Qual o saldo de pontos?", +1),
	new ProblemaInteiroContexto("Um jogador tinha \\($x\\) pontos e perdeu \\($y\\) pontos na rodada. Qual o saldo de pontos?", -1),
	new ProblemaInteiroContexto("$nomeM estava com \\($x\\) pontos e marcou \\($y\\) pontos. Qual a pontuação atual?", +1),
	new ProblemaInteiroContexto("$nomeF estava com \\($x\\) pontos e tomou uma penalidade de \\($y\\) pontos. Qual a pontuação atual?", -1),
	new ProblemaInteiroContexto("Uma equipe tinha \\($x\\) pontos e conquistou \\($y\\) pontos. Qual o total?", +1),
	new ProblemaInteiroContexto("Uma equipe tinha \\($x\\) pontos e foi penalizada em \\($y\\) pontos. Qual o total?", -1),
	new ProblemaInteiroContexto("Num jogo de tabuleiro, $nomeM estava na casa \\($x\\) e avançou \\($y\\) casas. Em que casa parou?", +1),
	new ProblemaInteiroContexto("Num jogo de tabuleiro, $nomeF estava na casa \\($x\\) e retrocedeu \\($y\\) casas. Em que casa parou?", -1),

	// --- dívidas e créditos ---
	new ProblemaInteiroContexto("$nomeM devia \\($x\\) reais (saldo negativo) e recebeu \\($y\\) reais. Qual o saldo agora?", +1),
	new ProblemaInteiroContexto("$nomeF tinha um saldo de \\($x\\) reais e fez uma compra de \\($y\\) reais. Qual o saldo agora?", -1),
	new ProblemaInteiroContexto("Uma conta estava em \\($x\\) reais e foi creditada em \\($y\\) reais. Qual o saldo?", +1),
	new ProblemaInteiroContexto("Uma conta estava em \\($x\\) reais e foi debitada em \\($y\\) reais. Qual o saldo?", -1),
	new ProblemaInteiroContexto("$nomeM tinha uma dívida representada por \\($x\\) reais e pagou \\($y\\) reais dela. Qual o saldo?", +1),
	new ProblemaInteiroContexto("$nomeF tinha \\($x\\) reais e emprestou \\($y\\) reais a um amigo. Com quanto ficou?", -1),

	// --- tempo / linha do tempo ---
	new ProblemaInteiroContexto("Um evento aconteceu no ano \\($x\\) (negativo = a.C.) e outro \\($y\\) anos depois. Em que ano ocorreu o segundo?", +1),
	new ProblemaInteiroContexto("Um relógio em uma cidade marcava \\($x\\) horas em relação a um fuso e adiantou \\($y\\) horas. Qual a nova diferença?", +1),
	new ProblemaInteiroContexto("Um relógio marcava \\($x\\) horas em relação a um fuso e atrasou \\($y\\) horas. Qual a nova diferença?", -1),

	// --- esportes e variações ---
	new ProblemaInteiroContexto("Uma ação valia \\($x\\) pontos no índice e subiu \\($y\\) pontos. Qual o novo valor?", +1),
	new ProblemaInteiroContexto("Uma ação valia \\($x\\) pontos no índice e caiu \\($y\\) pontos. Qual o novo valor?", -1),
	new ProblemaInteiroContexto("O nível de um reservatório estava em \\($x\\) cm em relação à marca zero e subiu \\($y\\) cm. Qual o novo nível?", +1),
	new ProblemaInteiroContexto("O nível de um reservatório estava em \\($x\\) cm em relação à marca zero e baixou \\($y\\) cm. Qual o novo nível?", -1),
	new ProblemaInteiroContexto("A pontuação de $nomeM num app era \\($x\\) e aumentou \\($y\\). Qual a nova pontuação?", +1),
	new ProblemaInteiroContexto("A pontuação de $nomeF num app era \\($x\\) e diminuiu \\($y\\). Qual a nova pontuação?", -1),

	// --- mais contextos ---
	new ProblemaInteiroContexto("A temperatura de uma câmara fria era \\($x\\)°C e aumentou \\($y\\)°C ao abrir a porta. Qual a temperatura?", +1),
	new ProblemaInteiroContexto("A temperatura de uma câmara fria era \\($x\\)°C e reduziu \\($y\\)°C após fechar a porta. Qual a temperatura?", -1),
	new ProblemaInteiroContexto("Um drone voava a \\($x\\) m de altura e subiu \\($y\\) m. A que altura ficou?", +1),
	new ProblemaInteiroContexto("Um drone voava a \\($x\\) m de altura e desceu \\($y\\) m. A que altura ficou?", -1),
	new ProblemaInteiroContexto("$nomeM estava devendo \\($x\\) pontos numa brincadeira e ganhou \\($y\\) pontos. Qual o saldo?", +1),
	new ProblemaInteiroContexto("$nomeF estava com \\($x\\) pontos numa brincadeira e pagou \\($y\\) de prenda. Qual o saldo?", -1),
	new ProblemaInteiroContexto("Um caixa eletrônico mostrou saldo de \\($x\\) reais e a pessoa depositou \\($y\\) reais. Qual o saldo?", +1),
	new ProblemaInteiroContexto("Um caixa eletrônico mostrou saldo de \\($x\\) reais e a pessoa sacou \\($y\\) reais. Qual o saldo?", -1),
	new ProblemaInteiroContexto("A profundidade de um poço marcava \\($x\\) m em relação ao solo e a água subiu \\($y\\) m. Qual o novo nível?", +1),
	new ProblemaInteiroContexto("A profundidade de um poço marcava \\($x\\) m em relação ao solo e a água desceu \\($y\\) m. Qual o novo nível?", -1),
	new ProblemaInteiroContexto("Um carro de corrida estava \\($x\\) posições em relação ao líder e ganhou \\($y\\) posições. Qual a nova diferença?", +1),
	new ProblemaInteiroContexto("Um carro de corrida estava \\($x\\) posições em relação ao líder e perdeu \\($y\\) posições. Qual a nova diferença?", -1),
	new ProblemaInteiroContexto("Numa escala de humor de \\(-10\\) a \\(10\\), $nomeM marcou \\($x\\) e melhorou \\($y\\) pontos. Qual o novo valor?", +1),
	new ProblemaInteiroContexto("Numa escala de humor de \\(-10\\) a \\(10\\), $nomeF marcou \\($x\\) e piorou \\($y\\) pontos. Qual o novo valor?", -1),
	new ProblemaInteiroContexto("A temperatura de uma região era \\($x\\)°C e uma frente fria a fez subir \\($y\\)°C. Qual a temperatura?", +1),
	new ProblemaInteiroContexto("A temperatura de uma região era \\($x\\)°C e uma frente fria a fez cair \\($y\\)°C. Qual a temperatura?", -1),
	new ProblemaInteiroContexto("O saldo de gols de um time era \\($x\\) e ele teve um saldo de \\($y\\) a favor na rodada. Qual o novo saldo de gols?", +1),
	new ProblemaInteiroContexto("O saldo de gols de um time era \\($x\\) e ele teve um saldo de \\($y\\) contra na rodada. Qual o novo saldo de gols?", -1),
	new ProblemaInteiroContexto("Um teleférico estava a \\($x\\) m em relação à base e subiu \\($y\\) m. A que altura ficou?", +1),
	new ProblemaInteiroContexto("Um teleférico estava a \\($x\\) m em relação à base e desceu \\($y\\) m. A que altura ficou?", -1),
	new ProblemaInteiroContexto("A conta de $nomeF estava em \\($x\\) reais e ela recebeu um estorno de \\($y\\) reais. Qual o saldo?", +1),
	new ProblemaInteiroContexto("A conta de $nomeM estava em \\($x\\) reais e foi cobrada uma taxa de \\($y\\) reais. Qual o saldo?", -1),
	new ProblemaInteiroContexto("Um jogador de boliche tinha \\($x\\) pontos de bônus e somou \\($y\\) pontos. Qual o total?", +1),
	new ProblemaInteiroContexto("Um jogador de boliche tinha \\($x\\) pontos e levou uma falta de \\($y\\) pontos. Qual o total?", -1),
	new ProblemaInteiroContexto("A bateria de um carrinho marcava \\($x\\) em uma escala com negativos e carregou \\($y\\) unidades. Qual o novo valor?", +1),
	new ProblemaInteiroContexto("A bateria de um carrinho marcava \\($x\\) em uma escala com negativos e descarregou \\($y\\) unidades. Qual o novo valor?", -1),
	new ProblemaInteiroContexto("A temperatura de uma geladeira era \\($x\\)°C e subiu \\($y\\)°C na falta de energia. Qual a temperatura?", +1),
	new ProblemaInteiroContexto("A temperatura de uma geladeira era \\($x\\)°C e caiu \\($y\\)°C ao voltar a energia. Qual a temperatura?", -1),
	new ProblemaInteiroContexto("Um elevador panorâmico estava no nível \\($x\\) e subiu \\($y\\) níveis. Em que nível parou?", +1),
	new ProblemaInteiroContexto("Um elevador panorâmico estava no nível \\($x\\) e desceu \\($y\\) níveis. Em que nível parou?", -1),
	new ProblemaInteiroContexto("$nomeF tinha um saldo de \\($x\\) reais e recebeu um pagamento de \\($y\\) reais. Qual o saldo final?", +1),

	};

	public static ProblemaInteiroContexto getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
