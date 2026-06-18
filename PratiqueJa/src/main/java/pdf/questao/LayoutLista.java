package pdf.questao;

/**
 * Perfil de layout para listas de questões em PDF.
 *
 * <p>Diferente do {@link pdf.exercicio.LayoutLista}: na lista de questões as
 * células não têm altura fixa, pois cada questão pode ser muito curta ou
 * muito longa. O conteúdo flui em duas colunas via {@code multicols} e o
 * número de páginas é decidido pelo TeX.
 *
 * <p>Logo, este enum guarda apenas o total de questões a incluir na lista.
 */
public enum LayoutLista
{
	PADRAO("Padrão", 20);

	public final String nome;

	public final int total;

	LayoutLista(String nome, int total)
	{
		this.nome  = nome;
		this.total = total;
	}

	public String getNome()
	{
		return nome;
	}

	public int total()
	{
		return total;
	}
}
