package matematica.intermediario.somaangulostriangulo;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SomaAngulosTrianguloNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SomaAngulosTrianguloNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(22);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SomaAngulosTrianguloNivel1()
	{
	}

	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto) || respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

	public static void main(String[] args)
	{
//		new Image5(1,true,"somaAngulosTriangulo.PNG");
	}

//	1- TrianguloRetangulo a, B | 
//	2- TrianguloRetangulo A, b | 
//	3- ConfigTriangulo1 A, b, c | 
//	4- ConfigTriangulo1 a, B, c | 
//	5- ConfigTriangulo1 a, b, C | 
//	6- ConfigTriangulo2 A, b, c | 
//	7- ConfigTriangulo2 a, B, c | 
//	8- ConfigTriangulo2 a, b, C | 
//	9- ConfigTriangulo3 A, b, c | 
//	10- ConfigTriangulo3 a, B, c | 
//	11- ConfigTriangulo3 a, b, C | 
//	12- ConfigRetangulo A, d, e | 
//	13- ConfigRetangulo a, D, e | 
//	14- ConfigRetangulo a, d, E | 
//	15- TrianguloBipartido1 a, b, e, F | 
//	16- TrianguloBipartido1 A, b, e, f | 
//	17- TrianguloBipartido1 a, B, e, f | 
//	18- TrianguloBipartido1 a, b, E, f | 
//	19- TrianguloBipartido2 A, b, e, f | 
//	20- TrianguloBipartido2 a, B, e, f | 
//	21- TrianguloBipartido2 a, b, E, f | 
//	22- TrianguloBipartido2 a, b, e, F | 

}
