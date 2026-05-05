package Matematica.Intermediario.SemelhancaTriangulos;

import Matematica.Racional;


public class Arco
{
	public Racional arco;
	public String nome;

	public Arco(Racional arco, String nome)
	{
		this.arco = arco;
		this.nome = nome;
	}

	@Override
	public String toString()
	{
		return "Arco:\narco: " + arco + "\n" + (nome != null ? "nome: " + nome : "");
	}

}
