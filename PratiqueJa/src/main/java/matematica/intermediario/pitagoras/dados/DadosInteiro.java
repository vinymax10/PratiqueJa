package matematica.intermediario.pitagoras.dados;

public class DadosInteiro extends Dados
{
	public DadosInteiro()
	{
		super();
		ValorPitagoras valorPitagoras = ValoresPitagoras.getProblema();
		
		hipotenusa=new NoPitagoras(valorPitagoras.a, false);
		base=new NoPitagoras(valorPitagoras.b, false);
		altura=new NoPitagoras(valorPitagoras.c, false);
		
		porcent=altura.magnitude()/base.magnitude();
	}

}
