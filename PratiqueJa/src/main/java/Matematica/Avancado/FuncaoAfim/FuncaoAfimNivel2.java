package Matematica.Avancado.FuncaoAfim;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class FuncaoAfimNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;
	
//	Removi a imagem 3 pois a resolução está muito grande.
	
	public FuncaoAfimNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public FuncaoAfimNivel2()
	{
	}

}
