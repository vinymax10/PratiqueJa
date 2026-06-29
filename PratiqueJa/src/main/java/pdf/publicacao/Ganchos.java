package pdf.publicacao;

import java.util.Random;

/**
 * Pool de frases de gancho sorteadas por post (única parte sem dado no modelo).
 * Compartilhado pelos geradores de arte (InstagramFeed2, TikTok2, ...).
 */
public final class Ganchos
{
	private static final Random RANDOM = new Random();

	private static final String[] FRASES = {
		"90\\% ERRAM ISSO",
		"VOCÊ CONSEGUE EM 10s?",
		"SEM CALCULADORA!",
		"SÓ GÊNIOS ACERTAM",
		"RÁPIDO: 10 SEGUNDOS!",
		"PENSE ANTES DE VIRAR",
		"QUASE NINGUÉM ACERTA",
		"DESAFIO DO DIA",
		"PARECE FÁCIL, MAS NÃO É",
		"CUIDADO COM A PEGADINHA",
		"RESOLVE DE CABEÇA?",
		"ISSO CAI NO ENEM",
		"TESTE SEU CÉREBRO",
		"SÓ 1 EM 10 ACERTA",
		"MOSTRE QUE VOCÊ SABE",
		"ACERTE EM 1 TENTATIVA",
		"DESAFIO RELÂMPAGO",
		"VOCÊ AINDA LEMBRA?",
		"NÃO VALE CALCULADORA",
		"COMENTA SUA RESPOSTA"
	};

	private Ganchos()
	{
	}

	/** Gancho sorteado do pool. */
	public static String aleatorio()
	{
		return FRASES[RANDOM.nextInt(FRASES.length)];
	}
}
