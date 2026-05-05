package Matematica.Intermediario.Pitagoras.Nivel3Package;

import Matematica.Intermediario.Pitagoras.ResolucaoPitagoras;
import Matematica.Intermediario.Pitagoras.Dados.Dados;
import Matematica.Intermediario.Pitagoras.Dados.DadosBase;
import Matematica.Intermediario.Pitagoras.Dados.DadosHipotenusa;
import Matematica.Intermediario.Pitagoras.Dados.NoPitagoras;

public class ProblemaPitagoras
{
	public String texto;

	public TipoPitagoras tipoPitagoras;

	public NoPitagoras a,b,c;
	public Dados dados=null;
	
	public ProblemaPitagoras(String texto, TipoPitagoras tipoPitagoras)
	{
		super();
		this.texto = texto;
		this.tipoPitagoras = tipoPitagoras;
	}

	public void gerarValores()
	{
		
		switch(tipoPitagoras) 
		{
			case XBC: dados=new DadosBase(20);break;
			case AXC: dados=new DadosHipotenusa(20);break;
			case ABX: dados=new DadosBase(20);break;
		}
		
		a=dados.hipotenusa;
		b=dados.base;
		c=dados.altura;
		
	}

	public String resolucao()
	{
		switch(tipoPitagoras) {
			case XBC: return ResolucaoPitagoras.resolucaoXBC(dados); 
			case AXC: return ResolucaoPitagoras.resolucaoAXC(dados); 
			case ABX: return ResolucaoPitagoras.resolucaoABX(dados); 
		}
		return "";
	}

	public String resultado()
	{
		switch(tipoPitagoras) 
		{
			case XBC: return a.show();
			case AXC: return b.show();
			case ABX: return c.show();
		}

		return "";
	}

	public String getPergunta()
	{
		String pergunta = texto;
		if(pergunta.contains("$b"))
			pergunta = pergunta.replace("$b", "\\("+b.show()+"\\)");
		if(pergunta.contains("$c"))
			pergunta = pergunta.replace("$c", "\\("+c.show()+"\\)");
		if(pergunta.contains("$a"))
			pergunta = pergunta.replace("$a", "\\("+a.show()+"\\)");

		return pergunta;
	}

	public ProblemaPitagoras clone()
	{
		return new ProblemaPitagoras(texto, tipoPitagoras);
	}
}
