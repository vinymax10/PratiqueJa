package infra;

import java.util.List;

public class DadosGrafico
{
	private List<String> labels;
	private List<Number> values;
	private String titulo;
	private String indexAxis;
	private String tituloEixoX;
	private String tituloEixoY;

	public List<String> getLabels()
	{
		return labels;
	}

	public void setLabels(List<String> labels)
	{
		this.labels = labels;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public List<Number> getValues()
	{
		return values;
	}

	public void setValues(List<Number> values)
	{
		this.values = values;
	}

	public String getIndexAxis()
	{
		return indexAxis;
	}

	public void setIndexAxis(String indexAxis)
	{
		this.indexAxis = indexAxis;
	}

	public String getTituloEixoX()
	{
		return tituloEixoX;
	}

	public void setTituloEixoX(String tituloEixoX)
	{
		this.tituloEixoX = tituloEixoX;
	}

	public String getTituloEixoY()
	{
		return tituloEixoY;
	}

	public void setTituloEixoY(String tituloEixoY)
	{
		this.tituloEixoY = tituloEixoY;
	}
}
