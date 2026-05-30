package matematica.intermediario.sistemaequacoes;

import matematica.Racional;
import matematica.expressao.MyExpression;

public class EquacaoSE
{
	public int coeficienteX;
	public int coeficienteY;
	public int valor;
	
	public EquacaoSE multiplica(int fator)
	{
		EquacaoSE nova=new EquacaoSE();
		nova.coeficienteX=coeficienteX*fator;
		nova.coeficienteY=coeficienteY*fator;
		nova.valor=valor*fator;
		
		return nova;
	}
	
	public void calculaValor(int x, int y)
	{
		valor=(coeficienteX*x)+(coeficienteY*y);
	}
	
	public boolean equivalete(EquacaoSE equacao)
	{
		Racional xProp=new Racional(coeficienteX,equacao.coeficienteX);
		Racional yProp=new Racional(coeficienteY,equacao.coeficienteY);
		Racional vProp=new Racional(valor,equacao.valor);
		xProp.fatoracao(2);
		yProp.fatoracao(2);
		vProp.fatoracao(2);
		if(xProp.igual(yProp)&&yProp.igual(vProp))
			return true;
		
		return false;
	}
	
	public void simplicar()
	{
		int fator=2;
		long max=Math.max(Math.max(Math.abs(coeficienteX), Math.abs(coeficienteY)),valor);
		while(fator <= max)
		{
			if(coeficienteX % fator == 0 && coeficienteY % fator == 0&&valor % fator == 0 )
			{
				coeficienteX /= fator;
				coeficienteY /= fator;
				valor /= fator;
				max=Math.max(Math.max(Math.abs(coeficienteX), Math.abs(coeficienteY)),valor);
			}
			else
				fator++;
		}
	}
	
	public String latex()
	{
		MyExpression expressao = new MyExpression(coeficienteX+"x +"+coeficienteY+"y="+valor);
		return expressao.imprimir();
	}
	
	public String latexZ()
	{
		MyExpression expressao = new MyExpression(coeficienteX+"x +"+coeficienteY+"y=z");
		return expressao.imprimir();
	}
}
