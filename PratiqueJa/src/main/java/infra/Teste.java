package infra;

import matematica.Racional;

public class Teste
{
	public static void main(String[] args)
	{
//		System.out.println(BCrypt.hashpw("senha", BCrypt.gensalt(12)));
		Racional r=new Racional(102,100);
		r=r.pow(new Racional(5));
		System.out.println(r+" r: "+r.decimalStr());
	}
}
