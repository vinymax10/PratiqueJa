package matematica.avancado.funcaologaritmica;

import matematica.GeradorExercicio;

public class FuncaoLogaritmicaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String[] tipos = {
			".nivel2package.Image1", ".nivel2package.Image2", ".nivel2package.Image3", ".nivel2package.Image4",
			".nivel2package.Image5", ".nivel2package.Image6", ".nivel2package.Image7", ".nivel2package.Image8", ".nivel2package.Image9",
			".nivel2package.Expressao1", ".nivel2package.Expressao2", ".nivel2package.Expressao3", ".nivel2package.Expressao4",
			".nivel2package.Expressao5", ".nivel2package.Expressao6", ".nivel2package.Expressao7", ".nivel2package.Expressao8", ".nivel2package.Expressao9"};
		delegar(instanciar(tipos[rand.nextInt(tipos.length)]));
	}
}
