package matematica.intermediario.semelhancaangulos;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import matematica.expressao.MyExpression;
import matematica.basico.somaangulostriangulo.Angulo;


public class AuxSemelhancaAngulos
{
	
	
	public static String gerarExpressao(int resultado, int angle)
	{
		Random rand=new Random();
		int a = 1 + rand.nextInt(9);
		int b=angle-(a*resultado);
		MyExpression expressao = new MyExpression(a + "x+" + b);
		String str = expressao.imprimir();
		return str;
	}
	
	public static String resolucao(String instrucao, Object config)
	{
		String resolucaoLatex = "";
		String list[] = instrucao.split(",");
		String list2[];
		String list3[];
		MyExpression resolucao;
		Angulo angulo;
		String expressao="";
		String incognita;
		for(int i = 0; i < list.length; i++)
		{
			list[i] = list[i].trim();
			list2 = list[i].split("=");
			switch(list2[0])
			{
				case "i":
				{
					angulo = getAngulo(config, list2[1]);
					expressao = angulo.nome + "=" + angulo.angulo;
				}break;

				case "c":
				{
					list3 = list2[1].split("->");
					incognita=list3[0];
					list3 = list3[1].split("\\+");
					expressao = "";
					for(int j = 0; j < list3.length; j++)
					{
						angulo = getAngulo(config, list3[j]);
						if(list3[j].equals(incognita))
							expressao += angulo.nome;
						else
							expressao += angulo.angulo;
						
						if(j == (list3.length - 1))
							expressao += "=180";
						else
							expressao += "+";
					}
				}break;
				
				case "s":
				{
					list3 = list2[1].split("->");
					incognita=list3[0];
					
					String parte1[]=list3[1].split("\\+");
					String parte2[]=list3[2].split("\\+");
					expressao = "";
					if(parte1.length<parte2.length)
					{
						angulo = getAngulo(config, parte1[0]);
						if(incognita.equals(parte1[0]))
							expressao += angulo.nome;
						
						expressao+="=";
						
						angulo = getAngulo(config, parte2[0]);
						if(incognita.equals(parte2[0]))
							expressao += angulo.nome;
						else
							expressao += angulo.angulo;
						
						expressao+="+";
						
						angulo = getAngulo(config, parte2[1]);
						if(incognita.equals(parte2[1]))
							expressao += angulo.nome;
						else
							expressao += angulo.angulo;
					}
					else
					{
						angulo = getAngulo(config, parte1[0]);
						if(incognita.equals(parte1[0]))
							expressao += angulo.nome;
						else
							expressao += angulo.angulo;
						
						expressao+="+";
						
						angulo = getAngulo(config, parte1[1]);
						if(incognita.equals(parte1[1]))
							expressao += angulo.nome;
						else
							expressao += angulo.angulo;
						
						expressao+="=";
						
						angulo = getAngulo(config, parte2[0]);
						if(incognita.equals(parte2[0]))
							expressao += angulo.nome;
						else
							expressao += angulo.angulo;
						
					}
					
				}break;
			}
			
			resolucao = new MyExpression(expressao);
			resolucaoLatex += resolucao.resolverLatex();
			if(i<(list.length-1))
				resolucaoLatex +="\\\\";
		}
		return resolucaoLatex;
	}
	
	public static void mostrarAngulos(String angleImage, Object config)
	{
		if(!angleImage.equals(""))
		{
			String list[] = angleImage.split(",");
			String list2[];
			Angulo angulo = null;
			
			for(int i = 0; i < list.length; i++)
			{
				
				list[i] = list[i].trim();
				list2 = list[i].split("=");
				
				angulo = getAngulo(config, list2[0]);
				angulo.mostrar();
				if(list2.length > 1)
					angulo.nome = list2[1];
			}
		}
	}
	
	public static void mostrarAngulosExpressao(String angleImage, Object config, int resultado)
	{
		if(!angleImage.equals(""))
		{
			String list[] = angleImage.split(",");
			String list2[];
			Angulo angulo = null;
			
			for(int i = 0; i < list.length; i++)
			{
				
				list[i] = list[i].trim();
				list2 = list[i].split("=");
				
				angulo = getAngulo(config, list2[0]);
				angulo.mostrar();
				if(list2.length > 1)
				{
					angulo.nome = gerarExpressao(resultado,angulo.angulo);
				}
			}
		}
	}

	public static Angulo getAngulo(Object config, String name)
	{
		Class<? extends Object> c = config.getClass();
		Field[] fields = c.getDeclaredFields();
		Angulo angle = null;
		try
		{
			for(int i = 0; i < fields.length; i++)
			{
				fields[i].setAccessible(true);
				if(fields[i].getName().equals(name))
					angle = (Angulo) fields[i].get(config);
			}
		}
		catch(IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return angle;
	}

	public static Angulo getAnguloLabel(String angleImage, Object config, String name)
	{
		String list[] = angleImage.split(",");
		String list2[];
		Angulo angulo = null;
		for(int i = 0; i < list.length; i++)
		{
			list2 = list[i].split("=");
			if(list2.length > 1 && list2[1].equals(name))
				angulo = getAngulo(config, list2[0]);
		}
		return angulo;
	}

}
