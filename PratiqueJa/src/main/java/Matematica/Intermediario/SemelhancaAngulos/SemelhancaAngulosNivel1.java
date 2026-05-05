package Matematica.Intermediario.SemelhancaAngulos;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SemelhancaAngulosNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SemelhancaAngulosNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone((Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Exercicio" + tipo)
			.getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
		| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SemelhancaAngulosNivel1()
	{
	}

	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto)
		|| respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

//	1- SemelhancaAngulos1 A, b | 
//	2- SemelhancaAngulos1 b, C | 
//	3- SemelhancaAngulos1 a, B | 
//	4- SemelhancaAngulos1 B, c | 
//	5- SemelhancaAngulos1 c, D | 
//	6- SemelhancaAngulos1 C, d | 
//	7- SemelhancaAngulos1 B, d | 
//	8- SemelhancaAngulos1 b, D | 
//	9- SemelhancaAngulos1 a, D | 
//	10- SemelhancaAngulos1 A, d | 
//	11- SemelhancaAngulos1 A, c | 
//	12- SemelhancaAngulos1 a, C | 
//	13- SemelhancaAngulos2 a, E | 
//	14- SemelhancaAngulos2 A, e | 
//	15- SemelhancaAngulos2 a, F | e
//	16- SemelhancaAngulos2 A, f | e
//	17- SemelhancaAngulos2 B, f | 
//	18- SemelhancaAngulos2 b, F | 
//	19- SemelhancaAngulos2 a, H | e
//	20- SemelhancaAngulos2 A, h | e
//	21- SemelhancaAngulos2 a, G | e
//	22- SemelhancaAngulos2 A, g | e
//	23- SemelhancaAngulos2 b, H | f
//	24- SemelhancaAngulos2 B, h | f
//	25- SemelhancaAngulos2 C, h | g
//	26- SemelhancaAngulos2 c, H | g
//	27- SemelhancaAngulos2 C, g | 
//	28- SemelhancaAngulos2 c, G | 
//	29- SemelhancaAngulos2 d, G | h 
//	30- SemelhancaAngulos2 D, g | c 
//	31- SemelhancaAngulos2 b, E | f
//	32- SemelhancaAngulos2 B, e | a
//	33- SemelhancaAngulos2 c, E | g
//	34- SemelhancaAngulos2 C, e | a
//	35- SemelhancaAngulos2 c, F | g
//	36- SemelhancaAngulos2 C, f | b
//	37- SemelhancaAngulos2 d, F | h
//	38- SemelhancaAngulos2 D, f | b
//	39- SemelhancaAngulos2 d, H |
//	40- SemelhancaAngulos2 D, h |
//	41- SemelhancaAngulos3 A, e | 
//	42- SemelhancaAngulos3 a, H | e 
//	43- SemelhancaAngulos3 D, f | b
//	44- SemelhancaAngulos3 c, E | g
//	45- SemelhancaAngulos3 d, G | h 
//	46- SemelhancaAngulos3 b, H | f
//	47- SemelhancaAngulos3 a, E | 
//	48- SemelhancaAngulos3 d, F | h
//	49- SemelhancaAngulos3 d, H |
//	50- SemelhancaAngulos3 a, F | e
//	51- SemelhancaAngulos3 B, e | a
//	52- SemelhancaAngulos3 A, h | e
//	53- SemelhancaAngulos3 C, f | b
//	54- SemelhancaAngulos3 B, h | f
//	55- SemelhancaAngulos3 A, f | e 
//	56- SemelhancaAngulos3 c, G | 
//	57- SemelhancaAngulos3 c, F | g
//	58- SemelhancaAngulos3 B, f | 
//	59- SemelhancaAngulos3 a, G | e
//	60- SemelhancaAngulos3 C, h | g
//	61- SemelhancaAngulos3 D, g | c 
//	62- SemelhancaAngulos3 C, g | 
//	63- SemelhancaAngulos3 D, h |
//	64- SemelhancaAngulos3 A, g | e
//	65- SemelhancaAngulos3 C, e | a
//	66- SemelhancaAngulos3 b, F | 
//	67- SemelhancaAngulos3 b, E | f
//	68- SemelhancaAngulos3 c, H | g

}
