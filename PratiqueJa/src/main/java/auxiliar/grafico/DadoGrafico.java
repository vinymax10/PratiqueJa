package auxiliar.grafico;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DadoGrafico
{
	private List<String> labels = new ArrayList<String>();
	private List<Number> values= new ArrayList<Number>();
	private String titutlo;
	private String eixoY;

	public void addLabel(String label)
	{
		labels.add(label);
	}
	
	public void addValue(Number value)
	{
		values.add(value);
	}
	
	public List<Object> getValuesObject()
	{
		List<Object> objects=new ArrayList<>();
		for (Number number : values) {
			objects.add(number);
		}
		return objects;
	}
}
