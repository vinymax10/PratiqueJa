package matematica.intermediario.somaangulostriangulo;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SomaAngulosTrianguloNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SomaAngulosTrianguloNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(18);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SomaAngulosTrianguloNivel3()
	{
	}

	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto) || respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

	public static void main(String[] args)
	{
//		new Image3(1,true,"somaAngulosTriangulo.PNG");
	}

//	1- Triangulo4 a, B | 
//	2- Triangulo4 A, b | 
//	3- Triangulo4 A, c | 
//	4- Triangulo5 b, D | 
//	5- Triangulo5 c, D | 
//	6- Triangulo6 D, E | 
//	7- Triangulo6 a, D | 
//	8- Triangulo6 c, E | 
//	9- Triangulo6 b, E | 
//	10- Triangulo6 b, D | 
//	11- Triangulo7 a, B | 
//	12- Triangulo8 a, C | 
//	13- Triangulo9 a, b, C | 
//	14- Triangulo9 a, B, c | 
//	15- Triangulo10 a, c, D | 
//	16- Triangulo10 A, c, d | 
//	17- Triangulo11 a, b, C | 
//	18- Triangulo11 A, b, c | 

}
