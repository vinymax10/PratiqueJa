package converter;

import java.io.Serializable;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import modelo.Entidade;

@FacesConverter("generic")
public class GenericConverter1 implements Converter<Object>, Serializable
{

	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext ctx, UIComponent component, String value)
	{
		if(value != null)
		{
			return component.getAttributes().get(value);
		}

		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value)
	{
		if(value != null && !"".equals(value))
		{
			Entidade entity = (Entidade) value;

			component.getAttributes().put(entity.getId().toString(), entity);

			Long codigo = entity.getId();
			if(codigo != null)
			{
				return String.valueOf(codigo);
			}
		}

		return (String) value;
	}

}