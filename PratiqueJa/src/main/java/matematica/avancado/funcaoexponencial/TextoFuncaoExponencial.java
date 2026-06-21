package matematica.avancado.funcaoexponencial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static matematica.avancado.funcaoexponencial.TipoFuncaoExponencial.CRESCIMENTO;
import static matematica.avancado.funcaoexponencial.TipoFuncaoExponencial.JUROS;
import static matematica.avancado.funcaoexponencial.TipoFuncaoExponencial.MEIAVIDA;

// Biblioteca de enunciados de modelagem exponencial. Cada template é marcado com a
// variante (crescimento/meia-vida/juros) e, no crescimento, o fator (2 dobra, 3 triplica).
// getProblema(tipo) sorteia entre os enunciados daquela variante; os valores são gerados
// depois pelo Problema. getProblema() sem argumento serve à validação por reflexão.
public class TextoFuncaoExponencial
{
	static ProblemaFuncaoExponencial problemas[] = {

	// ===================== CRESCIMENTO  N(t) = N0 · b^t =====================

	// --- dobra (fator 2) ---
	new ProblemaFuncaoExponencial("Uma colônia de bactérias parte de \\($n0\\) células e dobra a cada hora. Quantas células haverá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma cultura tem \\($n0\\) micro-organismos que dobram a cada hora. Quantos haverá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma população de fungos começa com \\($n0\\) esporos e dobra a cada dia. Quantos esporos haverá após \\($t\\) dias?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("O número de vírus em uma amostra parte de \\($n0\\) e dobra a cada hora. Quantos vírus haverá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Micro-organismos em um lago somam \\($n0\\) e dobram a cada dia. Quantos haverá após \\($t\\) dias?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Bactérias em um alimento somam \\($n0\\) e dobram a cada hora. Quantas haverá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma cultura de levedura tem \\($n0\\) células e dobra a cada hora. Quantas células haverá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("As algas de um tanque somam \\($n0\\) e dobram a cada dia. Quantas algas haverá após \\($t\\) dias?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma colônia de plâncton parte de \\($n0\\) indivíduos e dobra a cada dia. Quantos haverá após \\($t\\) dias?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma cultura de células parte de \\($n0\\) unidades e dobra a cada hora. Quantas células haverá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Um vídeo tem \\($n0\\) visualizações e esse número dobra a cada dia. Quantas visualizações terá após \\($t\\) dias?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma publicação tem \\($n0\\) compartilhamentos que dobram a cada hora. Quantos compartilhamentos terá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Um perfil parte de \\($n0\\) seguidores e dobra esse número a cada dia em uma campanha. Quantos seguidores terá após \\($t\\) dias?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma planilha registra \\($n0\\) downloads que dobram a cada dia. Quantos downloads haverá após \\($t\\) dias?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("As amebas de uma lâmina somam \\($n0\\) e dobram a cada hora. Quantas haverá após \\($t\\) horas?", CRESCIMENTO, 2),
	new ProblemaFuncaoExponencial("Uma colônia de formigas tem \\($n0\\) indivíduos e dobra a cada mês. Quantas formigas haverá após \\($t\\) meses?", CRESCIMENTO, 2),

	// --- triplica (fator 3) ---
	new ProblemaFuncaoExponencial("Uma cultura de bactérias parte de \\($n0\\) células e triplica a cada hora. Quantas células haverá após \\($t\\) horas?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Uma colônia de fungos começa com \\($n0\\) esporos e triplica a cada dia. Quantos esporos haverá após \\($t\\) dias?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Uma população de microrganismos soma \\($n0\\) e triplica a cada dia. Quantos haverá após \\($t\\) dias?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Os insetos de uma plantação somam \\($n0\\) e triplicam a cada semana. Quantos haverá após \\($t\\) semanas?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("O número de esporos de um mofo parte de \\($n0\\) e triplica a cada dia. Quantos esporos haverá após \\($t\\) dias?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Uma cultura de levedura tem \\($n0\\) células que triplicam a cada hora. Quantas células haverá após \\($t\\) horas?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("O número de vírus em um meio parte de \\($n0\\) e triplica a cada hora. Quantos vírus haverá após \\($t\\) horas?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("As plantas aquáticas de um açude somam \\($n0\\) e triplicam a cada semana. Quantas haverá após \\($t\\) semanas?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("As algas de um reservatório somam \\($n0\\) e triplicam a cada dia. Quantas algas haverá após \\($t\\) dias?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Uma colônia de amebas parte de \\($n0\\) indivíduos e triplica a cada dia. Quantas haverá após \\($t\\) dias?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Os micróbios de uma amostra somam \\($n0\\) e triplicam a cada hora. Quantos haverá após \\($t\\) horas?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Uma corrente de mensagens parte de \\($n0\\) e triplica a cada dia. Quantas mensagens haverá após \\($t\\) dias?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Uma cultura de organismos parte de \\($n0\\) unidades e triplica a cada hora. Quantos haverá após \\($t\\) horas?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Os caramujos de um lago somam \\($n0\\) e triplicam a cada mês. Quantos haverá após \\($t\\) meses?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("As células de um tecido em cultura somam \\($n0\\) e triplicam a cada dia. Quantas haverá após \\($t\\) dias?", CRESCIMENTO, 3),
	new ProblemaFuncaoExponencial("Uma população de bactérias parte de \\($n0\\) e triplica a cada hora em laboratório. Quantas haverá após \\($t\\) horas?", CRESCIMENTO, 3),

	// ===================== MEIAVIDA  M = M0 · (1/2)^k =====================

	new ProblemaFuncaoExponencial("Uma substância radioativa tem meia-vida de \\($h\\) anos e massa inicial de \\($n0\\,\\text{g}\\). Qual será a massa restante após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um isótopo radioativo tem meia-vida de \\($h\\) dias e massa inicial de \\($n0\\,\\text{g}\\). Qual será a massa restante após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um material radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) horas. Qual será a massa restante após \\($t\\) horas?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de \\($n0\\,\\text{g}\\) de um radioisótopo tem meia-vida de \\($h\\) anos. Quanto restará após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um radioisótopo usado em exames tem meia-vida de \\($h\\) horas e massa inicial de \\($n0\\,\\text{g}\\). Qual será a massa restante após \\($t\\) horas?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma fonte de iodo radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) dias. Quanto restará após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um contaminante radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) dias. Qual será a massa restante após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de césio de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) anos. Quanto restará após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de cobalto radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) anos. Qual será a massa restante após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma fonte de tecnécio de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) horas. Quanto restará após \\($t\\) horas?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de estrôncio de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) anos. Qual será a massa restante após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de fósforo radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) dias. Quanto restará após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um resíduo radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) anos. Qual será a massa restante após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de trítio de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) anos. Quanto restará após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de enxofre radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) dias. Qual será a massa restante após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um elemento radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) anos. Quanto restará após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de polônio de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) dias. Qual será a massa restante após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um traçador radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) horas. Quanto restará após \\($t\\) horas?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de rádio de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) anos. Qual será a massa restante após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um poluente radioativo de \\($n0\\,\\text{g}\\) decai pela metade a cada \\($h\\) dias. Quanto restará após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma fonte radioativa de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) horas. Qual será a massa restante após \\($t\\) horas?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de iridio de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) dias. Quanto restará após \\($t\\) dias?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Um material de \\($n0\\,\\text{g}\\) usado em radioterapia tem meia-vida de \\($h\\) anos. Qual será a massa restante após \\($t\\) anos?", MEIAVIDA),
	new ProblemaFuncaoExponencial("Uma amostra de bário radioativo de \\($n0\\,\\text{g}\\) tem meia-vida de \\($h\\) horas. Quanto restará após \\($t\\) horas?", MEIAVIDA),

	// ===================== JUROS  M = C · (1 + i)^t =====================

	new ProblemaFuncaoExponencial("Um capital de \\(R\\$\\,$n0{,}00\\) é aplicado a juros compostos de \\($p\\%\\) ao período. Qual é o montante após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Um investimento de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao ano em juros compostos. Qual é o montante após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Uma aplicação em CDB de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao ano. Qual é o montante após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("$nomeM aplicou \\(R\\$\\,$n0{,}00\\) a juros compostos de \\($p\\%\\) ao ano. Quanto terá após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Um fundo de investimento de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao período. Qual é o montante após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Um empréstimo de \\(R\\$\\,$n0{,}00\\) é cobrado a juros compostos de \\($p\\%\\) ao mês. Qual é a dívida após \\($t\\) meses?", JUROS),
	new ProblemaFuncaoExponencial("Um financiamento de \\(R\\$\\,$n0{,}00\\) acumula juros compostos de \\($p\\%\\) ao período. Qual é o valor devido após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Um título de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao ano em juros compostos. Qual é o montante após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("$nomeF investiu \\(R\\$\\,$n0{,}00\\) a juros compostos de \\($p\\%\\) ao ano. Quanto terá após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Uma poupança especial de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao período. Qual é o saldo após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Um banco aplica \\(R\\$\\,$n0{,}00\\) a juros compostos de \\($p\\%\\) ao ano. Qual é o montante após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Uma reserva de \\(R\\$\\,$n0{,}00\\) cresce a juros compostos de \\($p\\%\\) ao período. Qual é o montante após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Um depósito de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao ano em juros compostos. Qual é o valor acumulado após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Uma dívida de \\(R\\$\\,$n0{,}00\\) acumula juros compostos de \\($p\\%\\) ao ano. Qual é o total devido após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("$nomeM tomou um empréstimo de \\(R\\$\\,$n0{,}00\\) a juros compostos de \\($p\\%\\) ao mês. Quanto deverá após \\($t\\) meses?", JUROS),
	new ProblemaFuncaoExponencial("Uma aplicação financeira de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao período. Qual é o montante após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Um investidor aplicou \\(R\\$\\,$n0{,}00\\) a juros compostos de \\($p\\%\\) ao ano. Qual é o montante após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Uma quantia de \\(R\\$\\,$n0{,}00\\) é aplicada a \\($p\\%\\) ao período em juros compostos. Qual é o montante após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("$nomeF guardou \\(R\\$\\,$n0{,}00\\) em uma aplicação que rende \\($p\\%\\) ao ano. Quanto terá após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Um certificado de depósito de \\(R\\$\\,$n0{,}00\\) rende \\($p\\%\\) ao ano. Qual é o montante após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Uma cooperativa remunera \\(R\\$\\,$n0{,}00\\) a juros compostos de \\($p\\%\\) ao período. Qual é o montante após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Um capital de \\(R\\$\\,$n0{,}00\\) cresce \\($p\\%\\) ao ano de forma composta. Qual é o montante após \\($t\\) anos?", JUROS),
	new ProblemaFuncaoExponencial("Um valor de \\(R\\$\\,$n0{,}00\\) é investido a juros compostos de \\($p\\%\\) ao período. Qual é o montante após \\($t\\) períodos?", JUROS),
	new ProblemaFuncaoExponencial("Uma empresa aplicou \\(R\\$\\,$n0{,}00\\) a juros compostos de \\($p\\%\\) ao ano. Qual é o montante após \\($t\\) anos?", JUROS),

	};

	public static ProblemaFuncaoExponencial getProblema(TipoFuncaoExponencial tipo)
	{
		Random rand = new Random();
		List<ProblemaFuncaoExponencial> filtrados = new ArrayList<>();
		for(ProblemaFuncaoExponencial p : problemas)
			if(p.tipo == tipo)
				filtrados.add(p);
		return filtrados.get(rand.nextInt(filtrados.size())).clone();
	}

	public static ProblemaFuncaoExponencial getProblema()
	{
		Random rand = new Random();
		return problemas[rand.nextInt(problemas.length)].clone();
	}
}
