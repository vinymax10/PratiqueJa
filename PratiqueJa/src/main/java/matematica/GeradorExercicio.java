package matematica;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.sql.rowset.serial.SerialBlob;

import infra.Graphics;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ParagrafoExercicio;
import modelo.exercicio.ParagrafoResolucao;
import modelo.exercicio.ResolucaoSplitter;
import modelo.questao.ImagemFile;

/**
 * Base para todos os geradores de exercício. O gerador NÃO é uma entidade: ele
 * apenas constrói um {@link Exercicio} por composição, usando os helpers
 * genéricos abaixo (que cuidam de ordem e do back-reference automaticamente),
 * evitando replicar essa lógica em cada construção.
 *
 * <p>Cada gerador concreto implementa {@link #construir(int)} e é instanciado
 * uma vez por geração (ver {@code ExercicioFactory}).
 */
public abstract class GeradorExercicio
{
	protected final Random rand = new Random();

	protected final Exercicio exercicio = new Exercicio();

	/** Lógica específica do exercício: usar addParagrafo / addAlternativa / setResolucao. */
	protected abstract void construir();

	public final Exercicio gerar()
	{
		construir();
		return exercicio;
	}

	/** Adiciona um parágrafo ao enunciado (ordem automática). Retorna-o para customização (ex.: imagem). */
	protected ParagrafoExercicio addParagrafo(String texto)
	{
		ParagrafoExercicio paragrafo = new ParagrafoExercicio();
		paragrafo.setOrdem(exercicio.getParagrafos().size());
		paragrafo.setTexto(texto);
		paragrafo.setExercicio(exercicio);
		exercicio.getParagrafos().add(paragrafo);
		return paragrafo;
	}

	/** Adiciona uma alternativa (ordem/letra automáticas). Retorna-a para customização. */
	protected AlternativaExercicio addAlternativa(String texto, boolean correta)
	{
		AlternativaExercicio alternativa = new AlternativaExercicio();
		alternativa.setOrdem(exercicio.getAlternativas().size());
		alternativa.setTexto(texto);
		alternativa.setCorreta(correta);
		alternativa.setExercicio(exercicio);
		exercicio.getAlternativas().add(alternativa);
		return alternativa;
	}

	/**
	 * Gera 4 alternativas inteiras: a correta + distratores não-negativos próximos,
	 * embaralhadas. Cobre o caso comum de respostas inteiras (aritmética).
	 */
	protected void gerarAlternativasInteiras(int correto)
	{
		gerarAlternativasInteiras(correto, 4);
	}

	protected void gerarAlternativasInteiras(int correto, int quantidade)
	{
		gerarAlternativasInteiras(correto, quantidade, true);
	}

	/**
	 * Distratores inteiros próximos da correta, embaralhados.
	 * {@code naoNegativos}=true descarta candidatos &lt; 0 (respostas naturais).
	 */
	protected void gerarAlternativasInteiras(int correto, int quantidade, boolean naoNegativos)
	{
		if(naoNegativos && correto < 0)
			naoNegativos = false;

		List<String> distratores = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(correto);

		while(distratores.size() < quantidade - 1)
		{
			int delta = 1 + rand.nextInt(quantidade);
			int candidato = rand.nextBoolean() ? correto + delta : correto - delta;
			if((!naoNegativos || candidato >= 0) && !usados.contains(candidato))
			{
				usados.add(candidato);
				distratores.add(formatarNumero(candidato));
			}
		}

		embaralharEAdicionarAlternativas(formatarNumero(correto), distratores);
	}

	/** Como {@link #gerarAlternativasInteiras(int)} mas permitindo distratores negativos (inteiros). */
	protected void gerarAlternativasInteirasComNegativos(int correto)
	{
		gerarAlternativasInteiras(correto, 4, false);
	}

	/** Alternativas de Sim/Não (questões de verdadeiro/falso). */
	protected void gerarAlternativasBoolean(boolean correta)
	{
		addAlternativa("Sim", correta);
		addAlternativa("Não", !correta);
	}

	/**
	 * Embaralha a correta junto com os distratores e adiciona como alternativas
	 * (ordem/letra automáticas). Remove distrátores que coincidam com a correta ou
	 * entre si para evitar C2 (2+ corretas) e C3 (alternativas duplicadas).
	 */
	protected void embaralharEAdicionarAlternativas(String correta, List<String> distratores)
	{
		List<String> textos = new ArrayList<>();
		for(String d : distratores)
			if(d != null && !d.equals(correta) && !textos.contains(d))
				textos.add(d);
		textos.add(correta);
		Collections.shuffle(textos, rand);

		for(String texto : textos)
			addAlternativa(texto, texto.equals(correta));
	}

	/** Envolve um número em LaTeX inline (\( \)) para renderização via MathJax. */
	protected String formatarNumero(int valor)
	{
		return "\\(" + valor + "\\)";
	}

	/**
	 * Junta itens em texto "a, b, c e d" para enunciados de seleção
	 * (ex.: "Qual dos números a, b, c e d é primo?"), de modo que o enunciado
	 * seja autossuficiente — funcione discursivo ou com alternativas.
	 */
	protected String listar(List<String> itens)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < itens.size(); i++)
		{
			if(i > 0)
				sb.append(i == itens.size() - 1 ? " e " : ", ");
			sb.append(itens.get(i));
		}
		return sb.toString();
	}

	/** Lista embaralhada de todos os candidatos (correta + distratores) para o enunciado. */
	protected String listarOpcoes(String correta, List<String> distratores)
	{
		List<String> todas = new ArrayList<>(distratores);
		todas.add(correta);
		Collections.shuffle(todas, rand);
		return listar(todas);
	}

	/** Overload para Racional: simplifica e delega para a versão String. */
	protected void gerarAlternativas(Racional resultado)
	{
		if(resultado == null) return;
		resultado.fatoracao(2);
		gerarAlternativas(resultado.toString());
	}

	/**
	 * Gera alternativas a partir de uma resposta textual: inteiro → distratores
	 * inteiros; fração/decimal/porcentagem → distratores via {@link Racional}
	 * (lógica herdada dos geradores antigos). A correta vai embaralhada.
	 */
	protected void gerarAlternativas(String resultadoCorreto)
	{
		try
		{
			// inteiro (permitindo negativos, pois respostas algébricas podem ser < 0)
			gerarAlternativasInteiras(Integer.parseInt(resultadoCorreto.trim()), 4, false);
			return;
		}
		catch(NumberFormatException naoInteiro)
		{
			// não é inteiro: segue para distratores racionais/percentuais
		}

		try
		{
			String correta = "\\(" + resultadoCorretoLatex(resultadoCorreto) + "\\)";
			List<String> distratores = new ArrayList<>();
			int tentativas = 0;
			while(distratores.size() < 3 && tentativas++ < 50)
			{
				String alt = "\\(" + alternativaProxima(resultadoCorreto) + "\\)";
				if(!alt.equals(correta) && !distratores.contains(alt))
					distratores.add(alt);
			}

			embaralharEAdicionarAlternativas(correta, distratores);
		}
		catch(RuntimeException formatoNaoSuportado)
		{
			// formato fora do previsto (não Racional/%): mantém ao menos a correta
			addAlternativa("\\(" + resultadoCorreto + "\\)", true);
		}
	}

	private String resultadoCorretoLatex(String resultadoCorreto)
	{
		if(resultadoCorreto.contains("%"))
			return resultadoCorreto;

		return Racional.toConvert(resultadoCorreto).showDfrac();
	}

	private String alternativaProxima(String resultadoCorreto)
	{
		if(resultadoCorreto.contains("%"))
		{
			String numStr = resultadoCorreto.replace("\\%", "").replace("%", "").trim();
			try
			{
				long numerador = Long.parseLong(numStr);
				do
					numerador += rand.nextBoolean() ? 1 + rand.nextInt(10) : -1 - rand.nextInt(10);
				while(numerador <= 0);
				return numerador + "\\%";
			}
			catch(NumberFormatException e)
			{
				// Porcentagem decimal (ex.: "12.68%"): perturbar como double
				double valor = Double.parseDouble(numStr);
				double delta;
				do
					delta = (rand.nextBoolean() ? 1.0 : -1.0) * (1 + rand.nextInt(5));
				while(valor + delta <= 0);
				return String.format(Locale.US, "%.2f", valor + delta) + "\\%";
			}
		}

		Racional correta = Racional.toConvert(resultadoCorreto);
		Racional alternativa = correta.numeroProximo(20);
		while(alternativa.igual(correta))
			alternativa = correta.numeroProximo(20);
		return alternativa.showDfrac();
	}

	/**
	 * Adiciona um parágrafo de imagem ao enunciado a partir de um {@link BufferedImage}
	 * (gera um {@link ImagemFile} persistível). Retorna o parágrafo para customização.
	 */
	protected ParagrafoExercicio addParagrafoImagem(BufferedImage imagem)
	{
		ParagrafoExercicio paragrafo = new ParagrafoExercicio();
		paragrafo.setOrdem(exercicio.getParagrafos().size());
		paragrafo.setExercicio(exercicio);
		try
		{
			ByteArrayOutputStream baos = Graphics.salvar(imagem, false, "");
			ImagemFile imagemFile = new ImagemFile();
			imagemFile.setFile(new SerialBlob(baos.toByteArray()));
			imagemFile.setEndImagem("exercicio.png");
			paragrafo.setImagemFile(imagemFile);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		exercicio.getParagrafos().add(paragrafo);
		return paragrafo;
	}

	/**
	 * Usado pelos dispatchers (XxxNivelN): executa a folha e move o conteúdo gerado
	 * (parágrafos, alternativas, resolução) para o exercício deste gerador.
	 */
	protected void delegar(GeradorExercicio folha)
	{
		Exercicio gerado = folha.gerar();

		for(ParagrafoResolucao paragrafo : gerado.getResolucaoParagrafos())
		{
			paragrafo.setExercicio(exercicio);
			paragrafo.setOrdem(exercicio.getResolucaoParagrafos().size());
			exercicio.getResolucaoParagrafos().add(paragrafo);
		}

		for(ParagrafoExercicio paragrafo : gerado.getParagrafos())
		{
			paragrafo.setExercicio(exercicio);
			paragrafo.setOrdem(exercicio.getParagrafos().size());
			exercicio.getParagrafos().add(paragrafo);
		}

		for(AlternativaExercicio alternativa : gerado.getAlternativas())
		{
			alternativa.setExercicio(exercicio);
			alternativa.setOrdem(exercicio.getAlternativas().size());
			exercicio.getAlternativas().add(alternativa);
		}
	}

	/** Instancia um gerador-folha pelo sufixo de classe relativo ao pacote do dispatcher. */
	protected GeradorExercicio instanciar(String sufixoClasse)
	{
		try
		{
			return (GeradorExercicio) Class.forName(getClass().getPackage().getName() + sufixoClasse)
				.getDeclaredConstructor().newInstance();
		}
		catch(ReflectiveOperationException e)
		{
			throw new IllegalStateException("Não foi possível instanciar o gerador: " + sufixoClasse, e);
		}
	}

	/**
	 * Adiciona um passo de resolução (API preferida). O texto é fatiado em parágrafos de
	 * forma robusta via {@link ResolucaoSplitter} — move "\\" de dentro de "\(...\)" para
	 * o modo texto e preserva "\\" de matriz/array. Portanto é seguro passar uma linha OU
	 * um trecho com várias linhas (ex.: helper que devolve passos com "\\").
	 */
	protected void addResolucao(String texto)
	{
		if(texto == null)
			return;
		for(String linha : ResolucaoSplitter.split(texto))
			addLinhaResolucao(linha, null);
	}

	/** Adiciona um passo de resolução com imagem (a partir de um {@link BufferedImage}). */
	protected void addResolucaoImagem(BufferedImage imagem)
	{
		try
		{
			ByteArrayOutputStream baos = Graphics.salvar(imagem, false, "");
			ImagemFile imagemFile = new ImagemFile();
			imagemFile.setFile(new SerialBlob(baos.toByteArray()));
			imagemFile.setEndImagem("resolucao.png");
			addLinhaResolucao(null, imagemFile);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void addLinhaResolucao(String texto, ImagemFile imagemFile)
	{
		ParagrafoResolucao paragrafo = new ParagrafoResolucao();
		paragrafo.setOrdem(exercicio.getResolucaoParagrafos().size());
		paragrafo.setTexto(texto);
		paragrafo.setImagemFile(imagemFile);
		paragrafo.setExercicio(exercicio);
		exercicio.getResolucaoParagrafos().add(paragrafo);
	}
}
