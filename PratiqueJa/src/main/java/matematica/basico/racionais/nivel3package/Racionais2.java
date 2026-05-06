package matematica.basico.racionais.nivel3package;



import jakarta.persistence.Entity;

import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;
import modelo.matematica.Conta;

@Entity
public class Racionais2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Racionais2(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		while(a==b)	b = 2 + rand.nextInt(20);

		int c = 1 + rand.nextInt(10);
		int d = 1 + rand.nextInt(10);
		while(c==d)	d = 2 + rand.nextInt(20);

		Racional aRacional=new Racional(a,b);
		Racional bRacional=new Racional(c,d);
		
		textLatex = aRacional.showDfrac() +"\\div"+ bRacional.showDfrac()+"=";

		Racional resultado=aRacional.div(bRacional);
		resultado.fatoracao(2);
		
		resultadoCorreto = resultado.toString();
		
		resolucaoLatex = ResolucaoRacionais.divisao(a, b, c, d);
	}

	public Racionais2()
	{
	}

	public static void main(String[] args)
	{
		new Racionais2(1);
	}

}
