package matematica.avancado.funcaomodular.config;

public class DadosFuncaoMod
{
	public int h;       // vértice x
	public int k;       // vértice y (>= 0)
	public int pontoX;  // x do ponto destacado
	public int pontoY;  // f(pontoX)

	/** Ponto destacado = vértice */
	public static DadosFuncaoMod comVertice(int h, int k)
	{
		DadosFuncaoMod d = new DadosFuncaoMod();
		d.h = h; d.k = k;
		d.pontoX = h;
		d.pontoY = k;
		return d;
	}

	/** Ponto destacado em pontoX diferente do vértice */
	public static DadosFuncaoMod comPonto(int h, int k, int pontoX)
	{
		DadosFuncaoMod d = new DadosFuncaoMod();
		d.h = h; d.k = k;
		d.pontoX = pontoX;
		d.pontoY = Math.abs(pontoX - h) + k;
		return d;
	}
}
