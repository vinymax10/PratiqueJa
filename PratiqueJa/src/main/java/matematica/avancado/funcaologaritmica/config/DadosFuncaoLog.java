package matematica.avancado.funcaologaritmica.config;

public class DadosFuncaoLog
{
	public double  base;       // valor real da base (ex: 2.0, 0.333...)
	public boolean crescente;  // true se base > 1
	public int     baseInt;    // denominador visível (2, 3, 4) mesmo para (1/2), (1/3)
	public int     pontoX;     // x do ponto destacado (sempre positivo)
	public int     pontoY;     // log_base(pontoX) como inteiro

	/** Base crescente (a > 1), ponto (a, 1) */
	public static DadosFuncaoLog crescente(int base)
	{
		DadosFuncaoLog d = new DadosFuncaoLog();
		d.base      = base;
		d.crescente = true;
		d.baseInt   = base;
		d.pontoX    = base;
		d.pontoY    = 1;
		return d;
	}

	/** Base crescente (a > 1), ponto (a^2, 2) */
	public static DadosFuncaoLog crescenteComPonto2(int base)
	{
		DadosFuncaoLog d = new DadosFuncaoLog();
		d.base      = base;
		d.crescente = true;
		d.baseInt   = base;
		d.pontoX    = base * base;
		d.pontoY    = 2;
		return d;
	}

	/** Base decrescente (0 < 1/den < 1), ponto (den, -1) */
	public static DadosFuncaoLog decrescente(int den)
	{
		DadosFuncaoLog d = new DadosFuncaoLog();
		d.base      = 1.0 / den;
		d.crescente = false;
		d.baseInt   = den;
		d.pontoX    = den;
		d.pontoY    = -1;
		return d;
	}
}
