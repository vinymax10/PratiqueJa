package arquitetura;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.ejb.Schedule;
import jakarta.ejb.Schedules;
import jakarta.ejb.Timeout;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

/**
 * O defeito que estes testes impedem de voltar: <b>trabalho em lote dentro de uma transação só</b>.
 *
 * <p>Um método de agendador ({@code @Schedule}/{@code @Timeout}) num EJB, sem
 * {@code @TransactionAttribute}, cai no default {@code REQUIRED}: o container abre uma transação
 * para ele e os {@code @Transactional} dos DAOs <b>entram nela</b> em vez de confirmar item a item.
 * A rodada inteira vira um commit no fim, e daí saem dois estragos, os dois silenciosos:</p>
 *
 * <ul>
 *   <li>os 300s de tempo limite do WildFly estouram quando o lote cresce, e tudo volta atrás;</li>
 *   <li>um único item com erro marca a transação para rollback — o {@code catch} de dentro do laço
 *       engole a exceção, os demais itens seguem "com sucesso" e o commit final descarta tudo.</li>
 * </ul>
 *
 * <p>Foi o que aconteceu na fila de e-mail, e o que estava por acontecer no rollover mensal de
 * créditos, nas três limpezas diárias e no {@code CleanupService}. A regra do projeto passou a ser:
 * <b>agendador não abre transação</b>; quem abre é cada unidade de trabalho, um item por vez.</p>
 *
 * <p>Este teste lê as classes compiladas em vez de uma lista fixa de nomes, para valer também para
 * o próximo agendador que alguém escrever.</p>
 */
@DisplayName("Agendadores não podem rodar num único commit")
class AgendadorSemTransacaoLongaTest
{
	private static final Path CLASSES = Path.of("target", "classes");

	@Test
	@DisplayName("todo @Schedule/@Timeout declara NOT_SUPPORTED (na classe ou no método)")
	void agendadoresRodamForaDeTransacao()
	{
		List<String> faltando = new ArrayList<>();
		int examinados = 0;

		for(Class<?> classe : classesDoProjeto())
		{
			for(Method metodo : metodosDeclarados(classe))
			{
				if(!ehAgendador(metodo))
					continue;

				examinados++;

				if(!rodaForaDeTransacao(classe, metodo))
					faltando.add(classe.getName() + "#" + metodo.getName() + "()");
			}
		}

		assertTrue(examinados > 0,
			"Nenhum @Schedule/@Timeout encontrado — o teste deixou de examinar o que devia "
			+ "(target/classes vazio? rode 'mvn test', não o teste isolado).");

		if(!faltando.isEmpty())
			fail("Agendador sem @TransactionAttribute(NOT_SUPPORTED) — a rodada inteira viraria um"
				+ " commit só, que um item com erro (ou os 300s do WildFly) descarta em silêncio."
				+ " Ponha NOT_SUPPORTED na classe e deixe cada item confirmar sozinho:\n  "
				+ String.join("\n  ", faltando));
	}

	private boolean ehAgendador(Method metodo)
	{
		return metodo.isAnnotationPresent(Schedule.class)
			|| metodo.isAnnotationPresent(Schedules.class)
			|| metodo.isAnnotationPresent(Timeout.class);
	}

	/** O do método vence o da classe, como manda a especificação de EJB. */
	private boolean rodaForaDeTransacao(Class<?> classe, Method metodo)
	{
		TransactionAttribute noMetodo = metodo.getAnnotation(TransactionAttribute.class);
		if(noMetodo != null)
			return noMetodo.value() == TransactionAttributeType.NOT_SUPPORTED
				|| noMetodo.value() == TransactionAttributeType.NEVER;

		TransactionAttribute naClasse = classe.getAnnotation(TransactionAttribute.class);
		return naClasse != null
			&& (naClasse.value() == TransactionAttributeType.NOT_SUPPORTED
				|| naClasse.value() == TransactionAttributeType.NEVER);
	}

	private Method[] metodosDeclarados(Class<?> classe)
	{
		try
		{
			return classe.getDeclaredMethods();
		}
		catch(Throwable e)
		{
			// Classe cujas assinaturas referenciam algo ausente do classpath de teste.
			return new Method[0];
		}
	}

	/**
	 * Carrega <b>sem inicializar</b> ({@code initialize = false}): estas classes têm estado estático
	 * e dependências de container que não devem subir num teste.
	 */
	static List<Class<?>> classesDoProjeto()
	{
		List<Class<?>> classes = new ArrayList<>();

		if(!Files.isDirectory(CLASSES))
			return classes;

		try(Stream<Path> arquivos = Files.walk(CLASSES))
		{
			for(Path arquivo : arquivos.filter(p -> p.toString().endsWith(".class")).toList())
			{
				String nome = CLASSES.relativize(arquivo).toString()
					.replace(java.io.File.separatorChar, '.')
					.replaceAll("\\.class$", "");

				try
				{
					classes.add(Class.forName(nome, false,
						AgendadorSemTransacaoLongaTest.class.getClassLoader()));
				}
				catch(Throwable e)
				{
					// Classe não carregável no classpath de teste; não é o que se está medindo.
				}
			}
		}
		catch(IOException e)
		{
			throw new IllegalStateException("Falha ao varrer " + CLASSES, e);
		}

		return classes;
	}
}
