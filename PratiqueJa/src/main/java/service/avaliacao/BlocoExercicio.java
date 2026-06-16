package service.avaliacao;

import java.io.Serializable;
import java.util.List;

import modelo.avaliacao.FormatoAvaliacao;
import modelo.matematica.Exercicio;

/**
 * Agrupa os exercícios gerados para um único ItemPedidoAvaliacao, junto com o
 * formato (alternativas ou discursiva) que deve ser usado ao renderizá-los.
 */
public class BlocoExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private final FormatoAvaliacao formato;
	private final List<Exercicio> exercicios;

	public BlocoExercicio(FormatoAvaliacao formato, List<Exercicio> exercicios)
	{
		this.formato = formato;
		this.exercicios = exercicios;
	}

	public FormatoAvaliacao getFormato()
	{
		return formato;
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
