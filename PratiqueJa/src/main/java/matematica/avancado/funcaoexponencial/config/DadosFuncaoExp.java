package matematica.avancado.funcaoexponencial.config;

public class DadosFuncaoExp
{
	public double baseReal;    // valor real da base (ex: 2.0, 0.5)
	public boolean crescente;  // true se base > 1
	public int baseInt;        // base inteira visível (2, 3) mesmo para (1/2), (1/3)
	public int pontoX;         // x do ponto destacado
	public int pontoY;         // f(pontoX) como inteiro

	/** Base inteira crescente (2 ou 3), ponto (1, base) */
	public static DadosFuncaoExp crescente(int base)
	{
		DadosFuncaoExp d = new DadosFuncaoExp();
		d.baseReal  = base;
		d.crescente = true;
		d.baseInt   = base;
		d.pontoX    = 1;
		d.pontoY    = base;
		return d;
	}

	/** Base decrescente (1/base), ponto (-1, base) */
	public static DadosFuncaoExp decrescente(int den)
	{
		DadosFuncaoExp d = new DadosFuncaoExp();
		d.baseReal  = 1.0 / den;
		d.crescente = false;
		d.baseInt   = den;
		d.pontoX    = -1;
		d.pontoY    = den;
		return d;
	}

	/** Base inteira crescente com ponto (2, base²) */
	public static DadosFuncaoExp crescenteComPonto2(int base)
	{
		DadosFuncaoExp d = new DadosFuncaoExp();
		d.baseReal  = base;
		d.crescente = true;
		d.baseInt   = base;
		d.pontoX    = 2;
		d.pontoY    = base * base;
		return d;
	}
}
