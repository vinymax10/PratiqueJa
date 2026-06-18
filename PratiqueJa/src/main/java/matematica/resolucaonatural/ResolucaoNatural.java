package matematica.resolucaonatural;


public class ResolucaoNatural
{
	public static String soma(int a, int b, boolean resolucao)
	{
		Adicao adicao=new Adicao();
		return adicao.adicionar(a, b,resolucao);
	}
	
	public static String subtracao(int a, int b, boolean resolucao)
	{
		Subtracao subtrcao=new Subtracao();
		return subtrcao.subtrair(a, b, resolucao);
	}
	
	public static String multiplicacao(int a, int b, boolean resolucao)
	{
		Multiplicacao multiplicacao=new Multiplicacao();
		return multiplicacao.multiplicar(a, b, resolucao);
	}
	
	public static String divisao(int a, int b, boolean resolucao)
	{
		Divisao divisao=new Divisao();
		return divisao.dividir(a, b, resolucao);
	}
}