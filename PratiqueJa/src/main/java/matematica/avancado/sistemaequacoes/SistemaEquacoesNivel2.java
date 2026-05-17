package matematica.avancado.sistemaequacoes;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SistemaEquacoesNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SistemaEquacoesNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(4);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel2package.Sistema" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SistemaEquacoesNivel2()
	{
	}

	public static void main(String[] args)
	{
		new SistemaEquacoesNivel2(1);
	}
}
