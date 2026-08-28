package arquitetura;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * O defeito que este teste impede de voltar: <b>efeito externo dentro de uma transação</b>.
 *
 * <p>Transação desfaz banco. Não desfaz e-mail enviado, arquivo gerado, nem assinatura cancelada na
 * Hotmart. Quando os dois ficam juntos, a falha combina o pior dos dois lados: o efeito de fora
 * acontece e o registro dele some — e quem chamou, não vendo registro, tenta de novo.</p>
 *
 * <p>Foi exatamente isso no webhook da Hotmart. O {@code @Transactional} envolvia uma chamada HTTP
 * de cancelamento: uma Hotmart lenta estourava os 300s do WildFly e desfazia o {@code Pagamento}, a
 * {@code Assinatura} e a validade do plano — <b>mas a assinatura já tinha sido cancelada lá</b>. A
 * Hotmart reenviava o webhook e o ciclo repetia. É o mesmo formato do envio por SMTP dentro de
 * transação, que fazia o mesmo e-mail sair de novo a cada minuto.</p>
 *
 * <p>A regra: o efeito externo acontece <b>fora</b> da transação — antes dela, ou depois do commit.</p>
 *
 * <h2>O que este teste alcança</h2>
 *
 * <p>Não basta olhar o corpo do método anotado: no caso da Hotmart a chamada estava três níveis
 * abaixo, num método privado. Então a busca segue as chamadas a partir de cada {@code @Transactional},
 * <b>dentro do mesmo arquivo</b>. Parar na fronteira do arquivo é uma limitação assumida: seguir
 * para outras classes exigiria um grafo de chamadas de verdade, e o preço de um falso positivo aqui
 * é uma compilação reprovada sem motivo. Por isso a lista de efeitos externos inclui, além das APIs
 * do Java, os colaboradores de fora deste projeto pelo nome do campo — quem acrescentar outro
 * cliente externo acrescenta uma linha ali.</p>
 */
@DisplayName("Efeito externo não pode ficar dentro de uma transação")
class EfeitoExternoEmTransacaoTest
{
	private static final Path FONTES = Path.of("src", "main", "java");

	/** Trecho procurado → por que ele não pode acontecer dentro de uma transação. */
	private static final Map<String, String> EFEITOS_EXTERNOS = Map.of(
		"httpClient.send(",          "chamada HTTP não tem rollback",
		"hotmartApiClient.",         "chamada à API da Hotmart não tem rollback (e é lenta)",
		"new ProcessBuilder(",       "processo externo não tem rollback (e é lento)",
		"ProcessoExterno.executar(", "processo externo não tem rollback (e é lento)",
		"CommonsEmail.mandarEmail(", "e-mail enviado não volta");

	/** Assinatura de método Java: ... nome(...) { — o suficiente para este código-fonte. */
	private static final Pattern METODO = Pattern.compile(
		"\\n\\t(?:@\\w+(?:\\([^)]*\\))?\\s*\\n\\t)*"      // anotações da linha de cima
		+ "(?:public|private|protected|static|final|synchronized|abstract|\\s)+"
		+ "[\\w<>\\[\\],.?\\s]+?"                          // tipo de retorno
		+ "\\b(\\w+)\\s*\\(");                             // nome + abre parênteses

	@Test
	@DisplayName("nenhum método @Transactional dispara efeito externo, nem através dos seus auxiliares")
	void metodosTransacionaisNaoFalamComOMundoDeFora()
	{
		List<String> achados = new ArrayList<>();
		int examinados = 0;

		for(Path fonte : EsperaSemPrazoTest.fontes())
		{
			String texto = EsperaSemPrazoTest.ler(fonte);
			if(!texto.contains("@Transactional"))
				continue;

			Map<String, String> corpos = corposPorMetodo(texto);
			Set<String> transacionais = metodosTransacionais(texto, corpos);

			for(String raiz : transacionais)
			{
				examinados++;

				for(String alcancado : alcancaveis(raiz, corpos))
					for(Map.Entry<String, String> efeito : EFEITOS_EXTERNOS.entrySet())
						if(corpos.get(alcancado).contains(efeito.getKey()))
							achados.add(FONTES.relativize(fonte) + ": " + raiz + "() → "
								+ alcancado + "() faz " + efeito.getKey()
								+ " — " + efeito.getValue());
			}
		}

		assertTrue(examinados > 0,
			"Nenhum método @Transactional encontrado — o teste deixou de examinar o que devia.");

		if(!achados.isEmpty())
			fail("Efeito externo alcançado a partir de uma transação: se ela voltar atrás, o efeito"
				+ " de fora já aconteceu e o registro dele some — e quem chamou tenta de novo."
				+ " Tire a chamada da transação (antes dela, ou depois do commit):\n  "
				+ String.join("\n  ", achados));
	}

	/** Nome do método → corpo, para todos os métodos declarados no arquivo. */
	private Map<String, String> corposPorMetodo(String texto)
	{
		Map<String, String> corpos = new LinkedHashMap<>();
		Matcher m = METODO.matcher(texto);

		while(m.find())
		{
			String corpo = corpoApos(texto, m.end());
			if(corpo != null)
				corpos.put(m.group(1), corpo);
		}

		return corpos;
	}

	/** Os que têm {@code @Transactional} na declaração. */
	private Set<String> metodosTransacionais(String texto, Map<String, String> corpos)
	{
		Set<String> transacionais = new HashSet<>();

		int marca = 0;
		while((marca = texto.indexOf("@Transactional", marca)) >= 0)
		{
			marca += "@Transactional".length();

			// O nome do método vem logo abaixo da anotação, antes da primeira chave.
			int abre = texto.indexOf('{', marca);
			if(abre < 0)
				continue;

			String assinatura = texto.substring(marca, abre);
			for(String nome : corpos.keySet())
				if(assinatura.contains(nome + "("))
					transacionais.add(nome);
		}

		return transacionais;
	}

	/** Fecho transitivo das chamadas a partir de {@code raiz}, limitado aos métodos do arquivo. */
	private Set<String> alcancaveis(String raiz, Map<String, String> corpos)
	{
		Set<String> vistos = new HashSet<>();
		List<String> pilha = new ArrayList<>(List.of(raiz));

		while(!pilha.isEmpty())
		{
			String atual = pilha.remove(pilha.size() - 1);
			if(!vistos.add(atual) || !corpos.containsKey(atual))
				continue;

			String corpo = corpos.get(atual);
			for(String candidato : corpos.keySet())
				if(!vistos.contains(candidato) && corpo.contains(candidato + "("))
					pilha.add(candidato);
		}

		vistos.retainAll(corpos.keySet());
		return vistos;
	}

	/**
	 * Corpo que começa na primeira chave depois de {@code posicao}, por contagem de chaves.
	 * Devolve {@code null} quando não consegue delimitar — é um teste, não um compilador: melhor
	 * deixar passar um caso do que reprovar a compilação por um formato inesperado.
	 */
	private String corpoApos(String texto, int posicao)
	{
		int abre = texto.indexOf('{', posicao);
		int pontoEVirgula = texto.indexOf(';', posicao);
		if(abre < 0 || (pontoEVirgula >= 0 && pontoEVirgula < abre))
			return null;   // declaração sem corpo (interface, abstrato) ou não era um método

		int profundidade = 0;
		for(int i = abre; i < texto.length(); i++)
		{
			char c = texto.charAt(i);
			if(c == '{')
				profundidade++;
			else if(c == '}')
			{
				profundidade--;
				if(profundidade == 0)
					return texto.substring(abre, i + 1);
			}
		}

		return null;
	}
}
