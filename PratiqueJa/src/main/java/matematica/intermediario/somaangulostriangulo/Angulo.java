package matematica.intermediario.somaangulostriangulo;


public class Angulo
{
	public int angulo;
	public boolean mostrar;
	public String nome;
	public Config config;
	public int ordemInsercao;

	public Angulo(Config config, int angulo, boolean mostrar, String nome)
	{
		this.config = config;
		this.angulo = angulo;
		this.mostrar = mostrar;
		this.nome = nome;
	}

	@Override
	public String toString()
	{
		return "Angulo:\tangulo: " + angulo + "\tmostrar: " + mostrar + "\t" + (nome != null ? "nome: " + nome : "");
	}

	public void mostrar()
	{
		this.mostrar = true;
		ordemInsercao = config.incrementaInsecao();
	}
}
