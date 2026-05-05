package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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
