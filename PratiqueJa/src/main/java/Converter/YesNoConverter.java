package Converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "simNaoConverter")
public class YesNoConverter implements Converter<Object>
{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		Boolean condition = (Boolean) value;
		if(condition)
		{
			return "Sim";
		}
		else
		{
			return "Não";
		}
	}
}
