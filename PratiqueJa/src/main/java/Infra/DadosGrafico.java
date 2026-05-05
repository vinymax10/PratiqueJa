package Infra;

import java.util.List;

public class DadosGrafico
{
	private List<String> labels;
	private List<Number> values;
	private String titutlo;

	public List<String> getLabels()
	{
		return labels;
	}

	public void setLabels(List<String> labels)
	{
		this.labels = labels;
	}

	public String getTitutlo()
	{
		return titutlo;
	}

	public void setTitutlo(String titutlo)
	{
		this.titutlo = titutlo;
	}

	public List<Number> getValues()
	{
		return values;
	}

	public void setValues(List<Number> values)
	{
		this.values = values;
	}

}
