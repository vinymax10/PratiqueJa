package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClasseAux
{
	public static boolean possuiAtributo(Class<?> classe, String nomeAtributo)
	{
		Class<?> atual = classe;

		while(atual != null && atual != Object.class)
		{
			try
			{
				atual.getDeclaredField(nomeAtributo);
				return true;
			}
			catch(NoSuchFieldException e)
			{
				atual = atual.getSuperclass();
			}
		}
		return false;
	}

	public static Field getField(Class<?> classe, String nomeCampo)
    {
        Class<?> atual = classe;
        while(atual != null)
        {
            try
            {
                return atual.getDeclaredField(nomeCampo);
            }
            catch(NoSuchFieldException e)
            {
                atual = atual.getSuperclass();
            }
        }
        return null;
    }

    public static Object getValorAtributo(Object obj, String caminho)
    {
        if(obj == null || caminho == null || caminho.isBlank())
            return null;

        try
        {
            String[] partes = caminho.split("\\.");
            Object valorAtual = obj;

            for(String parte : partes)
            {
                if(valorAtual == null)
                    return null;

                Field field = getField(valorAtual.getClass(), parte);

                if(field != null)
                {
                    field.setAccessible(true);
                    valorAtual = field.get(valorAtual);
                    continue;
                }

                // tenta getter
                String metodoNome = "get" + Character.toUpperCase(parte.charAt(0)) + parte.substring(1);
                Method metodo = valorAtual.getClass().getMethod(metodoNome);
                valorAtual = metodo.invoke(valorAtual);
            }

            return valorAtual;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
