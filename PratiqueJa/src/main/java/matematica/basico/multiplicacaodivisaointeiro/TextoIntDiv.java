package matematica.basico.multiplicacaodivisaointeiro;

import java.util.Random;

// Biblioteca de enunciados de divisão de inteiros em contexto: uma grandeza total negativa
// de magnitude $t repartida igualmente entre $n partes (resultado = -(t/n)). $t múltiplo de $n.
public class TextoIntDiv
{
	static ProblemaIntDiv problemas[] = {

	// --- dívida repartida entre pessoas ---
	new ProblemaIntDiv("Uma dívida de \\($t\\) reais é dividida igualmente entre \\($n\\) pessoas. Quanto cada pessoa deve (saldo)?"),
	new ProblemaIntDiv("Um grupo de \\($n\\) amigos dividiu igualmente um prejuízo de \\($t\\) reais. Qual o saldo de cada um?"),
	new ProblemaIntDiv("Uma família tem uma dívida de \\($t\\) reais repartida igualmente entre \\($n\\) membros. Qual o saldo de cada um?"),
	new ProblemaIntDiv("$nomeM e mais \\($n\\) menos um colegas dividiram uma dívida de \\($t\\) reais entre \\($n\\) pessoas. Qual o saldo de cada um?"),
	new ProblemaIntDiv("Uma conta negativa de \\($t\\) reais foi dividida igualmente entre \\($n\\) sócios. Qual o saldo de cada sócio?"),
	new ProblemaIntDiv("Um prejuízo de \\($t\\) reais foi repartido igualmente entre \\($n\\) lojas. Qual o saldo de cada loja?"),
	new ProblemaIntDiv("Uma multa de \\($t\\) reais será paga igualmente por \\($n\\) responsáveis. Qual o saldo de cada um?"),
	new ProblemaIntDiv("Uma despesa de \\($t\\) reais foi dividida igualmente entre \\($n\\) participantes. Qual o saldo de cada participante?"),
	new ProblemaIntDiv("Um grupo de \\($n\\) pessoas dividiu igualmente o rombo de \\($t\\) reais de uma viagem. Qual o saldo de cada uma?"),
	new ProblemaIntDiv("Uma fatura negativa de \\($t\\) reais foi rateada igualmente entre \\($n\\) condôminos. Qual o saldo de cada um?"),
	new ProblemaIntDiv("Uma cooperativa repartiu igualmente um prejuízo de \\($t\\) reais entre \\($n\\) cooperados. Qual o saldo de cada um?"),
	new ProblemaIntDiv("$nomeF dividiu igualmente uma dívida de \\($t\\) reais entre \\($n\\) meses. Qual o saldo a abater por mês?"),

	// --- temperatura: variação total negativa ao longo de períodos ---
	new ProblemaIntDiv("A temperatura caiu \\($t\\)°C ao longo de \\($n\\) horas, de forma constante. Qual foi a variação por hora?"),
	new ProblemaIntDiv("Num resfriamento, a temperatura baixou \\($t\\)°C em \\($n\\) etapas iguais. Qual a variação por etapa?"),
	new ProblemaIntDiv("Durante \\($n\\) dias, a temperatura caiu \\($t\\)°C no total, igualmente. Qual a variação diária?"),
	new ProblemaIntDiv("Em \\($n\\) minutos, um freezer perdeu \\($t\\)°C de forma constante. Qual a variação por minuto?"),
	new ProblemaIntDiv("A temperatura de uma câmara caiu \\($t\\)°C em \\($n\\) horas iguais. Qual a variação horária?"),
	new ProblemaIntDiv("Ao longo de \\($n\\) ciclos iguais, a temperatura baixou \\($t\\)°C. Qual a variação por ciclo?"),

	// --- profundidade / altitude total ---
	new ProblemaIntDiv("Um mergulhador desceu \\($t\\) m em \\($n\\) minutos, de forma constante. Qual a variação de altura por minuto?"),
	new ProblemaIntDiv("Um submarino afundou \\($t\\) m em \\($n\\) etapas iguais. Qual a variação por etapa?"),
	new ProblemaIntDiv("Uma sonda desceu \\($t\\) m em \\($n\\) horas, igualmente. Qual a variação por hora?"),
	new ProblemaIntDiv("Um elevador de mina desceu \\($t\\) m em \\($n\\) paradas iguais. Qual a variação por parada?"),
	new ProblemaIntDiv("O nível de um reservatório baixou \\($t\\) cm em \\($n\\) dias iguais. Qual a variação diária?"),
	new ProblemaIntDiv("Uma broca avançou \\($t\\) m para baixo em \\($n\\) turnos iguais. Qual a variação por turno?"),

	// --- pontos / penalidades totais ---
	new ProblemaIntDiv("Uma equipe perdeu \\($t\\) pontos ao longo de \\($n\\) rodadas, igualmente. Qual a variação por rodada?"),
	new ProblemaIntDiv("$nomeM perdeu \\($t\\) pontos em \\($n\\) faltas iguais. Qual a variação por falta?"),
	new ProblemaIntDiv("Um jogador acumulou \\($t\\) pontos de penalidade em \\($n\\) erros iguais. Qual a penalidade por erro?"),
	new ProblemaIntDiv("Uma turma perdeu \\($t\\) pontos de comportamento em \\($n\\) ocorrências iguais. Qual a variação por ocorrência?"),
	new ProblemaIntDiv("Um competidor perdeu \\($t\\) posições em \\($n\\) provas iguais. Qual a variação por prova?"),
	new ProblemaIntDiv("Num game, $nomeF perdeu \\($t\\) vidas em \\($n\\) fases iguais. Qual a variação por fase?"),

	// --- consumo / perda total dividida ---
	new ProblemaIntDiv("Um tanque perdeu \\($t\\) litros em \\($n\\) horas, de forma constante. Qual a variação por hora?"),
	new ProblemaIntDiv("Uma vela diminuiu \\($t\\) cm em \\($n\\) horas iguais. Qual a variação por hora?"),
	new ProblemaIntDiv("Um celular descarregou \\($t\\)% em \\($n\\) horas iguais. Qual a variação por hora?"),
	new ProblemaIntDiv("Um estoque diminuiu \\($t\\) unidades em \\($n\\) dias iguais. Qual a variação por dia?"),
	new ProblemaIntDiv("Uma geleira recuou \\($t\\) m em \\($n\\) anos iguais. Qual a variação por ano?"),
	new ProblemaIntDiv("Uma represa baixou \\($t\\) cm em \\($n\\) horas iguais. Qual a variação por hora?"),
	new ProblemaIntDiv("Uma bateria perdeu \\($t\\) unidades de carga em \\($n\\) ciclos iguais. Qual a variação por ciclo?"),

	// --- finanças totais divididas ---
	new ProblemaIntDiv("Uma conta acumulou \\($t\\) reais de débitos em \\($n\\) meses iguais. Qual o débito por mês?"),
	new ProblemaIntDiv("Um financiamento gerou \\($t\\) reais de juros em \\($n\\) parcelas iguais. Qual o juro por parcela?"),
	new ProblemaIntDiv("$nomeM gastou \\($t\\) reais em \\($n\\) dias iguais com transporte. Qual a variação por dia?"),
	new ProblemaIntDiv("Uma assinatura descontou \\($t\\) reais em \\($n\\) meses iguais. Qual o desconto por mês?"),
	new ProblemaIntDiv("Uma empresa teve \\($t\\) reais de prejuízo em \\($n\\) trimestres iguais. Qual a variação por trimestre?"),
	new ProblemaIntDiv("Um cartão acumulou \\($t\\) reais de encargos em \\($n\\) faturas iguais. Qual o encargo por fatura?"),

	// --- mais repartições negativas ---
	new ProblemaIntDiv("Uma ação caiu \\($t\\) pontos em \\($n\\) pregões iguais. Qual a variação por pregão?"),
	new ProblemaIntDiv("Um avião perdeu \\($t\\) m de altitude em \\($n\\) minutos iguais. Qual a variação por minuto?"),
	new ProblemaIntDiv("Uma encosta cedeu \\($t\\) cm em \\($n\\) semanas iguais. Qual a variação por semana?"),
	new ProblemaIntDiv("Um poço secou \\($t\\) cm em \\($n\\) dias iguais. Qual a variação por dia?"),
	new ProblemaIntDiv("Uma maré baixou \\($t\\) cm em \\($n\\) horas iguais. Qual a variação por hora?"),
	new ProblemaIntDiv("Um lago perdeu \\($t\\) cm de nível em \\($n\\) semanas iguais. Qual a variação por semana?"),
	new ProblemaIntDiv("Um time perdeu \\($t\\) pontos em \\($n\\) derrotas iguais. Qual a variação por derrota?"),
	new ProblemaIntDiv("Uma sonda espacial perdeu \\($t\\) unidades de sinal em \\($n\\) intervalos iguais. Qual a variação por intervalo?"),

	// --- débitos repartidos diversos ---
	new ProblemaIntDiv("Foram lançados \\($t\\) reais de débitos divididos igualmente em \\($n\\) dias. Qual o débito por dia?"),
	new ProblemaIntDiv("Uma vaquinha precisa cobrir um rombo de \\($t\\) reais com \\($n\\) pessoas. Qual o saldo de cada uma?"),
	new ProblemaIntDiv("Um condomínio rateou um déficit de \\($t\\) reais entre \\($n\\) apartamentos. Qual o saldo de cada um?"),
	new ProblemaIntDiv("Uma turma dividiu igualmente um prejuízo de \\($t\\) reais da festa entre \\($n\\) alunos. Qual o saldo de cada um?"),
	new ProblemaIntDiv("Um clube rateou uma dívida de \\($t\\) reais entre \\($n\\) sócios. Qual o saldo de cada sócio?"),
	new ProblemaIntDiv("Uma empresa distribuiu igualmente um prejuízo de \\($t\\) reais entre \\($n\\) setores. Qual o saldo de cada setor?"),
	new ProblemaIntDiv("$nomeF parcelou uma dívida de \\($t\\) reais em \\($n\\) vezes iguais. Qual o valor (negativo) de cada parcela?"),
	new ProblemaIntDiv("Um grupo de \\($n\\) viajantes dividiu igualmente um custo extra de \\($t\\) reais. Qual o saldo de cada um?"),

	// --- temperatura/física extras ---
	new ProblemaIntDiv("A temperatura de um forno em resfriamento caiu \\($t\\)°C em \\($n\\) minutos iguais. Qual a variação por minuto?"),
	new ProblemaIntDiv("Uma câmara fria baixou \\($t\\)°C em \\($n\\) estágios iguais. Qual a variação por estágio?"),
	new ProblemaIntDiv("Um balão perdeu \\($t\\) m de altitude em \\($n\\) minutos iguais. Qual a variação por minuto?"),
	new ProblemaIntDiv("Uma correnteza arrastou um barco \\($t\\) m para trás em \\($n\\) minutos iguais. Qual a variação por minuto?"),
	new ProblemaIntDiv("Um robô recuou \\($t\\) casas em \\($n\\) comandos iguais. Qual a variação por comando?"),
	new ProblemaIntDiv("Uma represa liberou água baixando \\($t\\) cm em \\($n\\) horas iguais. Qual a variação por hora?"),

	// --- variados ---
	new ProblemaIntDiv("Uma geladeira perdeu \\($t\\)°C em \\($n\\) aberturas iguais de porta. Qual a variação por abertura?"),
	new ProblemaIntDiv("Uma dívida cresceu \\($t\\) reais em \\($n\\) meses iguais de atraso. Qual a variação por mês?"),
	new ProblemaIntDiv("Cada uma das \\($n\\) faltas tirou parte igual de \\($t\\) pontos de presença. Qual a variação por falta?"),
	new ProblemaIntDiv("Um elevador de carga desceu \\($t\\) m em \\($n\\) viagens iguais ao porão. Qual a variação por viagem?"),
	new ProblemaIntDiv("Uma fatura de \\($t\\) reais de encargos foi distribuída em \\($n\\) cobranças iguais. Qual a variação por cobrança?"),
	new ProblemaIntDiv("Um jogador perdeu \\($t\\) fichas em \\($n\\) apostas iguais. Qual a variação por aposta?"),
	new ProblemaIntDiv("Uma plantação perdeu \\($t\\) pés em \\($n\\) geadas iguais. Qual a variação por geada?"),
	new ProblemaIntDiv("Um aplicativo descontou \\($t\\) reais em \\($n\\) taxas iguais. Qual a variação por taxa?"),
	new ProblemaIntDiv("Um nadador desceu \\($t\\) m em \\($n\\) braçadas iguais. Qual a variação por braçada?"),
	new ProblemaIntDiv("Uma conta perdeu \\($t\\) reais em \\($n\\) tarifas iguais. Qual a variação por tarifa?"),
	new ProblemaIntDiv("Um prejuízo de \\($t\\) reais foi rateado igualmente entre \\($n\\) filiais. Qual o saldo de cada filial?"),
	new ProblemaIntDiv("A temperatura caiu \\($t\\)°C em \\($n\\) medições iguais. Qual a variação por medição?"),
	new ProblemaIntDiv("Um drone perdeu \\($t\\) m de altura em \\($n\\) descidas iguais. Qual a variação por descida?"),
	new ProblemaIntDiv("Uma dívida de \\($t\\) reais foi parcelada igualmente em \\($n\\) boletos. Qual o valor (negativo) de cada boleto?"),
	new ProblemaIntDiv("Um time perdeu \\($t\\) pontos em \\($n\\) jogos iguais. Qual a variação por jogo?"),

	};

	public static ProblemaIntDiv getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
