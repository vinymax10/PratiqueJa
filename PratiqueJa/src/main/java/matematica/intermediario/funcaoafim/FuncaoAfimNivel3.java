package matematica.intermediario.funcaoafim;

import matematica.GeradorExercicio;

public class FuncaoAfimNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String[] tipos = {
			".nivel3package.Image1", ".nivel3package.Image2", ".nivel3package.Image3", ".nivel3package.Image4",
			".nivel3package.Image5", ".nivel3package.Image6", ".nivel3package.Image7", ".nivel3package.Image8",
			".nivel3package.Image9", ".nivel3package.Image10",
			".nivel3package.Expressao1", ".nivel3package.Expressao2", ".nivel3package.Expressao3", ".nivel3package.Expressao4",
			".nivel3package.Expressao5", ".nivel3package.Expressao6", ".nivel3package.Expressao7", ".nivel3package.Expressao8"};
		delegar(instanciar(tipos[rand.nextInt(tipos.length)]));
	}
}
