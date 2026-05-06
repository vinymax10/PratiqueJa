package matematica.avancado.numeroscomplexos.expressao;

import matematica.Racional;


public class NoExpressionNC
{
	Racional coeficiente;
	String literal;
	TipoNo tipoNo;

	public NoExpressionNC(String texto)
	{
		if(texto.equals("="))
		{
			literal = texto;
			tipoNo = TipoNo.Igual;
		}
		else if(texto.equals("+")||texto.equals("-")
		||texto.equals("*") || texto.equals("/")
		|| texto.equals("(")|| texto.equals(")"))
		{
			literal = texto;
			tipoNo = TipoNo.Operador;
		}
		else
		{
			if(texto.contains("i^2"))
			{
				literal = "i^2";
				texto=texto.replaceAll("i\\^2", "");
			}
			
			String coeficiente = texto.replaceAll("[^0-9|/]+", "");
			String str[] = coeficiente.split("/");
			if(str.length > 1)
			{
				this.coeficiente = new Racional(Integer.valueOf(str[0]), Integer.valueOf(str[1]));
			}
			else
			{
				if(coeficiente.length() > 0)
					this.coeficiente = new Racional(Integer.valueOf(coeficiente));
				else
					this.coeficiente = new Racional(1);
			}

			if(texto.charAt(0) == '-')
				this.coeficiente.numerador *= -1;

			if(literal==null)
				literal = texto.replaceAll("[^a-zA-Z]+", "");

			if(literal.length() > 0)
				tipoNo = TipoNo.Literal;
			else
				tipoNo = TipoNo.Numero;
		}
	}

	@Override
	public String toString()
	{
		return "NoExpressionNC:\tcoeficiente: " + coeficiente + "\t"
		+ (literal != null ? "literal: " + literal + "\t" : "") + (tipoNo != null ? "tipoNo: " + tipoNo : "");
	}

	public String texto(boolean comSinal)
	{
		String string = "";

		string += getSinal(comSinal);

		if(tipoNo == TipoNo.Literal)
		{
			if(Math.abs(coeficiente.numerador) == 1 && coeficiente.denominador == 1)
				string += literal;
			else
				string += coeficiente.showDfracSS() + literal;
		}
		else if(tipoNo == TipoNo.Numero)
			string += coeficiente.showDfracSS();

		else if(tipoNo == TipoNo.Operador || tipoNo == TipoNo.Igual)
		{
			if(literal.equals("*"))
				string += "\\cdot";
			else
				string += literal;
		}

		return string;
	}

	public boolean igual(NoExpressionNC x)
	{
		if(tipoNo == x.tipoNo)
			return true;
		else
			return false;
	}

	public String getSinal(boolean comSinal)
	{
		if(coeficiente == null)
			return "";

		if(coeficiente.positivo())
		{
			if(comSinal)
				return "+";
			return "";
		}
		else
			return "-";
		
	}

	public void trocaSinal()
	{
		this.coeficiente = this.coeficiente.trocaSinal();
	}

	public void juntar(NoExpressionNC x)
	{
		coeficiente = coeficiente.add(x.coeficiente);
		coeficiente.fatoracao(2);
	}

	public void multiplica(NoExpressionNC x)
	{
		coeficiente = coeficiente.mult(x.coeficiente);
		coeficiente.fatoracao(2);
		if(x.tipoNo==TipoNo.Literal&&tipoNo!=TipoNo.Literal)
		{
			tipoNo=TipoNo.Literal;
			literal=x.literal;
		}
		else if(x.tipoNo==TipoNo.Literal&&tipoNo==TipoNo.Literal)
		{
			tipoNo=TipoNo.Literal;
			literal=x.literal+"^2";
		}
	}

}
