package matematica.intermediario.sistemaequacoes;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SistemaEquacoesNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SistemaEquacoesNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel1package.Sistema" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SistemaEquacoesNivel1()
	{
	}

	public static void main(String[] args)
	{
		new SistemaEquacoesNivel1(1);
	}
}
