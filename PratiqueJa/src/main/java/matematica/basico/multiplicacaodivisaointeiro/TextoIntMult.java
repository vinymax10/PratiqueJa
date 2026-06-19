package matematica.basico.multiplicacaodivisaointeiro;

import java.util.Random;

// Biblioteca de enunciados de multiplicação de inteiros em contexto: uma grandeza negativa
// de magnitude $m repetida $n vezes (resultado = -(m*n)). $m e $n positivos no enunciado.
public class TextoIntMult
{
	static ProblemaIntMult problemas[] = {

	// --- temperatura caindo ---
	new ProblemaIntMult("A temperatura cai \\($m\\)°C por hora. Qual é a variação total em \\($n\\) horas?"),
	new ProblemaIntMult("A temperatura de uma câmara fria diminui \\($m\\)°C a cada hora. Qual a variação em \\($n\\) horas?"),
	new ProblemaIntMult("Num inverno, a temperatura baixa \\($m\\)°C por dia. Qual a variação total em \\($n\\) dias?"),
	new ProblemaIntMult("Um congelador esfria \\($m\\)°C por minuto. Qual a variação após \\($n\\) minutos?"),
	new ProblemaIntMult("A temperatura de uma montanha cai \\($m\\)°C a cada \\(100\\) m de subida. Qual a variação após \\($n\\) trechos de \\(100\\) m?"),
	new ProblemaIntMult("Um freezer perde \\($m\\)°C por hora ao ser desligado. Qual a variação em \\($n\\) horas?"),

	// --- dívidas repetidas ---
	new ProblemaIntMult("Uma pessoa tem \\($n\\) dívidas de \\($m\\) reais cada. Qual é o saldo total?"),
	new ProblemaIntMult("$nomeM tem \\($n\\) parcelas em atraso de \\($m\\) reais cada. Qual o saldo devedor?"),
	new ProblemaIntMult("Uma empresa registrou \\($n\\) prejuízos de \\($m\\) reais. Qual o resultado total?"),
	new ProblemaIntMult("$nomeF assumiu \\($n\\) compromissos de \\($m\\) reais cada no cartão. Qual o saldo total?"),
	new ProblemaIntMult("Um clube teve \\($n\\) despesas de \\($m\\) reais cada. Qual a variação do caixa?"),
	new ProblemaIntMult("Uma loja teve \\($n\\) devoluções que custaram \\($m\\) reais cada. Qual o impacto no caixa?"),
	new ProblemaIntMult("$nomeM pagou \\($n\\) multas de \\($m\\) reais cada. Qual a variação no seu saldo?"),
	new ProblemaIntMult("Uma conta sofreu \\($n\\) descontos de \\($m\\) reais. Qual a variação total?"),

	// --- descida / profundidade ---
	new ProblemaIntMult("Um mergulhador desce \\($m\\) m por minuto. Qual a profundidade (em relação ao mar) após \\($n\\) minutos?"),
	new ProblemaIntMult("Um submarino afunda \\($m\\) m por minuto. Qual a altura em relação ao mar após \\($n\\) minutos?"),
	new ProblemaIntMult("Uma broca avança \\($m\\) m para baixo por hora. Qual a profundidade após \\($n\\) horas?"),
	new ProblemaIntMult("Um elevador de mina desce \\($m\\) m por minuto. A que altura em relação à superfície estará após \\($n\\) minutos?"),
	new ProblemaIntMult("O nível de um reservatório baixa \\($m\\) cm por dia na seca. Qual a variação em \\($n\\) dias?"),
	new ProblemaIntMult("Uma âncora desce \\($m\\) m por segundo. Qual a profundidade após \\($n\\) segundos?"),

	// --- pontos / penalidades repetidas ---
	new ProblemaIntMult("Num jogo, $nomeM leva \\($m\\) pontos de penalidade por rodada. Qual o saldo após \\($n\\) rodadas?"),
	new ProblemaIntMult("Um jogador perde \\($m\\) pontos a cada falta. Qual a variação após \\($n\\) faltas?"),
	new ProblemaIntMult("Uma equipe é penalizada em \\($m\\) pontos por erro. Qual o total após \\($n\\) erros?"),
	new ProblemaIntMult("$nomeF perde \\($m\\) vidas por fase difícil num game. Qual o saldo após \\($n\\) fases?"),
	new ProblemaIntMult("Cada resposta errada tira \\($m\\) pontos. Qual a variação após \\($n\\) erros?"),
	new ProblemaIntMult("Um piloto perde \\($m\\) posições por volta ruim. Qual a variação após \\($n\\) voltas?"),

	// --- consumo / perda por unidade de tempo ---
	new ProblemaIntMult("Um tanque perde \\($m\\) litros por hora por um vazamento. Qual a variação após \\($n\\) horas?"),
	new ProblemaIntMult("Uma vela diminui \\($m\\) cm por hora ao queimar. Qual a variação após \\($n\\) horas?"),
	new ProblemaIntMult("Um celular descarrega \\($m\\)% por hora. Qual a variação da carga após \\($n\\) horas?"),
	new ProblemaIntMult("Uma geleira recua \\($m\\) m por ano. Qual a variação após \\($n\\) anos?"),
	new ProblemaIntMult("Um estoque diminui \\($m\\) unidades por dia. Qual a variação após \\($n\\) dias?"),
	new ProblemaIntMult("Uma poça evapora \\($m\\) mm por hora. Qual a variação após \\($n\\) horas?"),
	new ProblemaIntMult("Uma bateria perde \\($m\\) unidades de carga por ciclo. Qual a variação após \\($n\\) ciclos?"),

	// --- finanças por período ---
	new ProblemaIntMult("Uma assinatura desconta \\($m\\) reais por mês. Qual a variação do saldo após \\($n\\) meses?"),
	new ProblemaIntMult("Um financiamento cobra \\($m\\) reais de juros por mês. Qual o total de juros em \\($n\\) meses?"),
	new ProblemaIntMult("$nomeM gasta \\($m\\) reais por dia com transporte. Qual a variação após \\($n\\) dias?"),
	new ProblemaIntMult("Uma conta de luz custa \\($m\\) reais por mês. Qual a variação no orçamento após \\($n\\) meses?"),
	new ProblemaIntMult("Um aluguel debita \\($m\\) reais por mês. Qual a variação após \\($n\\) meses?"),
	new ProblemaIntMult("Uma taxa de manutenção desconta \\($m\\) reais por semana. Qual a variação após \\($n\\) semanas?"),

	// --- mais contextos de queda/perda ---
	new ProblemaIntMult("Uma ação cai \\($m\\) pontos por dia na bolsa. Qual a variação após \\($n\\) dias?"),
	new ProblemaIntMult("A temperatura de um forno em resfriamento cai \\($m\\)°C por minuto. Qual a variação após \\($n\\) minutos?"),
	new ProblemaIntMult("Um avião perde \\($m\\) m de altitude por segundo na descida. Qual a variação após \\($n\\) segundos?"),
	new ProblemaIntMult("Um time perde \\($m\\) pontos por jogo perdido. Qual a variação após \\($n\\) derrotas?"),
	new ProblemaIntMult("Um nadador desce \\($m\\) m por braçada num mergulho. Qual a profundidade após \\($n\\) braçadas?"),
	new ProblemaIntMult("Uma encosta cede \\($m\\) cm por semana. Qual a variação após \\($n\\) semanas?"),
	new ProblemaIntMult("Um poço seca \\($m\\) cm por dia. Qual a variação do nível após \\($n\\) dias?"),
	new ProblemaIntMult("Um termômetro registra queda de \\($m\\)°C a cada medição. Qual a variação após \\($n\\) medições?"),

	// --- repetição de débitos diversos ---
	new ProblemaIntMult("Foram lançados \\($n\\) débitos de \\($m\\) reais numa conta. Qual a variação do saldo?"),
	new ProblemaIntMult("Uma fatura tem \\($n\\) itens de \\($m\\) reais cada a pagar. Qual o total devido?"),
	new ProblemaIntMult("$nomeF tem \\($n\\) boletos de \\($m\\) reais cada. Qual o total a pagar?"),
	new ProblemaIntMult("Um grupo dividiu \\($n\\) gastos iguais de \\($m\\) reais. Qual a variação total?"),
	new ProblemaIntMult("Uma empresa teve \\($n\\) cobranças de \\($m\\) reais cada. Qual o impacto no saldo?"),
	new ProblemaIntMult("Um cartão registrou \\($n\\) compras de \\($m\\) reais cada. Qual a variação do limite?"),
	new ProblemaIntMult("Uma escola teve \\($n\\) despesas de \\($m\\) reais com reparos. Qual a variação do caixa?"),
	new ProblemaIntMult("Um aplicativo descontou \\($n\\) taxas de \\($m\\) reais. Qual a variação do saldo?"),

	// --- mais temperatura / física ---
	new ProblemaIntMult("Numa câmara, a temperatura abaixa \\($m\\)°C por estágio de resfriamento. Qual a variação após \\($n\\) estágios?"),
	new ProblemaIntMult("Um balão perde \\($m\\) m de altitude por minuto ao esvaziar. Qual a variação após \\($n\\) minutos?"),
	new ProblemaIntMult("A correnteza empurra um barco \\($m\\) m para trás por minuto. Qual a variação após \\($n\\) minutos?"),
	new ProblemaIntMult("Uma represa baixa \\($m\\) cm por hora ao abrir as comportas. Qual a variação após \\($n\\) horas?"),
	new ProblemaIntMult("Um robô recua \\($m\\) casas por comando de erro. Qual a variação após \\($n\\) comandos?"),
	new ProblemaIntMult("Uma sonda desce \\($m\\) km por hora em direção ao fundo. Qual a profundidade após \\($n\\) horas?"),

	// --- pontuação / jogos extras ---
	new ProblemaIntMult("Cada vida perdida custa \\($m\\) pontos. Qual a variação após perder \\($n\\) vidas?"),
	new ProblemaIntMult("Um competidor leva \\($m\\) pontos de desconto por etapa falhada. Qual o total após \\($n\\) etapas?"),
	new ProblemaIntMult("Num quiz, cada chute errado tira \\($m\\) pontos. Qual a variação após \\($n\\) chutes errados?"),
	new ProblemaIntMult("Uma equipe perde \\($m\\) pontos por atraso. Qual a variação após \\($n\\) atrasos?"),
	new ProblemaIntMult("$nomeM perde \\($m\\) moedas por armadilha num jogo. Qual a variação após \\($n\\) armadilhas?"),
	new ProblemaIntMult("Cada cartão amarelo tira \\($m\\) pontos do fair play. Qual a variação após \\($n\\) cartões?"),

	// --- variados ---
	new ProblemaIntMult("Um termômetro de geladeira marca queda de \\($m\\)°C a cada porta aberta. Qual a variação após \\($n\\) aberturas?"),
	new ProblemaIntMult("Uma dívida cresce \\($m\\) reais por mês sem pagamento. Qual a variação após \\($n\\) meses?"),
	new ProblemaIntMult("Um lago perde \\($m\\) cm de nível por semana. Qual a variação após \\($n\\) semanas?"),
	new ProblemaIntMult("Cada falta na escola tira \\($m\\) pontos de presença. Qual a variação após \\($n\\) faltas?"),
	new ProblemaIntMult("Um elevador de carga desce \\($m\\) m por viagem ao porão. Qual a posição após \\($n\\) viagens consecutivas?"),
	new ProblemaIntMult("Uma fatura de cartão acumula \\($n\\) encargos de \\($m\\) reais. Qual a variação do saldo?"),
	new ProblemaIntMult("A maré baixa \\($m\\) cm por hora. Qual a variação após \\($n\\) horas?"),
	new ProblemaIntMult("Um drone perde \\($m\\) m de altura por minuto ao pousar. Qual a variação após \\($n\\) minutos?"),
	new ProblemaIntMult("Uma conta perde \\($m\\) reais por dia de taxa. Qual a variação após \\($n\\) dias?"),
	new ProblemaIntMult("A temperatura de um laboratório cai \\($m\\)°C por etapa do experimento. Qual a variação após \\($n\\) etapas?"),
	new ProblemaIntMult("Um jogador perde \\($m\\) fichas por aposta errada. Qual a variação após \\($n\\) apostas?"),
	new ProblemaIntMult("Um poço perde \\($m\\) m de água por dia de estiagem. Qual a variação após \\($n\\) dias?"),
	new ProblemaIntMult("Cada atraso na entrega desconta \\($m\\) pontos do fornecedor. Qual a variação após \\($n\\) atrasos?"),

	};

	public static ProblemaIntMult getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
