package service.avaliacao;

import java.io.Serializable;
import java.util.List;

import modelo.avaliacao.FormatoAvaliacao;
import modelo.exercicio.Exercicio;
import pdf.exercicio.LayoutLista;

/**
 * Agrupa os exercícios gerados para um único ItemPedidoAvaliacao, junto com o
 * formato (alternativas ou discursiva) e o layout (Padrão/Espaçoso) que devem
 * ser usados ao renderizá-los.
 */
public class BlocoExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private final FormatoAvaliacao formato;
	private final LayoutLista layout;
	private final List<Exercicio> exercicios;

	public BlocoExercicio(FormatoAvaliacao formato, LayoutLista layout, List<Exercicio> exercicios)
	{
		this.formato = formato;
		this.layout = layout;
		this.exercicios = exercicios;
	}

	public FormatoAvaliacao getFormato()
	{
		return formato;
	}

	public LayoutLista getLayout()
	{
		return layout;
	}

	public List<Exercicio> getExercicios()
	{
		return exercicios;
	}

	public boolean isAlternativas()
	{
		return formato == FormatoAvaliacao.ALTERNATIVAS;
	}
}
