package Matematica.Intermediario.SomaAngulosTriangulo;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SomaAngulosTrianguloNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SomaAngulosTrianguloNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(39);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SomaAngulosTrianguloNivel2()
	{
	}

	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto) || respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

//	1- Trapezio A, b, c | d, e
//	2- Trapezio a, B, c | d, e
//	3- Trapezio a, b, C | d, e
//	4- Trapezio A, b, e | d
//	5- Trapezio a, B, e | d
//	6- Trapezio c, D | e
//	7- TrianguloBipartido1 C, e, f | d
//	8- TrianguloBipartido1 a, b, D | c
//	9- TrianguloBipartido1 a, B, d | c
//	10- TrianguloBipartido1 A, b, d | c
//	11- TrianguloBipartido1 c, e, F | d
//	12- TrianguloBipartido1 c, E, f | d
//	13- Retangulo B, e, g | a, f
//	14- Retangulo C, e, g | d
//	15- Retangulo b, D, e | c
//	16- Retangulo b, E, g | c, d
//	17- Retangulo d, F | g
//	18- Retangulo a, e, G | f
//	19- Retangulo B, d, e | c
//	20- Retangulo B, e, f | a
//	21- Retangulo A, c | b
//	22- Retangulo A, e, g | f
//	23- Retangulo A, d, e | c, b
//	24- Retangulo C, a | b
//	25- Retangulo C, e, f | g, d
//	26- Retangulo a, D, e | b, c
//	27- Retangulo D, f | g
//	28- Retangulo b, E, f | a
//	29- Retangulo a, d, E | g, f
//	30- Retangulo b, e, F | a
//	31- Retangulo a, c, d, F | e
//	32- Retangulo b, e, G | a, f
//	33- Retangulo c, e, G | d
//	34- TrianguloBipartido2 C, e, f | d
//	35- TrianguloBipartido2 a, b, D | c
//	36- TrianguloBipartido2 a, B, d | c
//	37- TrianguloBipartido2 c, E, f | d
//	38- TrianguloBipartido2 c, e, F | d
//	39- TrianguloBipartido2 A, b, d | c

}
