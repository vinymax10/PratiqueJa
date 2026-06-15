package pdf.exercicio;

/**
 * Perfil de layout para geração de listas PDF.
 *
 * <p>Cada perfil define o número de páginas de exercícios, quantos exercícios
 * cabem por página (sempre par, pois a grade tem 2 colunas) e a altura de linha
 * correspondente. As alturas foram calibradas empiricamente para preencher a
 * folha A4 (textheight ≈ 25,4 cm) com o cabeçalho compacto (\listheader).
 *
 * <p>O gabarito não usa altura fixa: cada linha acompanha o tamanho natural da
 * resolução, que varia bastante entre exercícios.
 *
 * <p>Para calibrar um novo perfil: ajuste a altura no template
 * {@code tex-new/listas/lista_template.tex} e compile com xelatex até as tabelas
 * preencherem cada página sem transbordar.
 */
public enum LayoutLista
{
	/**
	 * Layout padrão: 18 exercícios em 3 páginas de 6 (3 linhas por página).
	 * Indicado para exercícios com enunciado curto e alternativas simples.
	 */
	PADRAO(3, 6, "7.8cm"),

	/**
	 * Layout espaçoso: 12 exercícios em 3 páginas de 4 (2 linhas por página).
	 * Indicado para exercícios com enunciado longo, imagem ou resolução grande
	 * (ex.: geometria, sistemas de equações).
	 */
	ESPACOSO(4, 4, "11.7cm");

	/** Número de páginas de exercícios. */
	public final int paginas;

	/** Quantidade de exercícios por página (deve ser sempre par). */
	public final int exerciciosPorPagina;

	/** Altura de linha (ht) da grade de exercícios. */
	public final String alturaLinha;

	LayoutLista(int paginas, int exerciciosPorPagina, String alturaLinha)
	{
		this.paginas             = paginas;
		this.exerciciosPorPagina = exerciciosPorPagina;
		this.alturaLinha         = alturaLinha;
	}

	/** Total de exercícios gerados com este layout. */
	public int total()
	{
		return paginas * exerciciosPorPagina;
	}

	/** Número de linhas (rows) da grade de exercícios por página. */
	public int linhasPorPagina()
	{
		return exerciciosPorPagina / 2;
	}
}
