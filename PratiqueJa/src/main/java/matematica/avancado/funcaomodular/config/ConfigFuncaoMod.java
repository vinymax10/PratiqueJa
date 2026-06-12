package matematica.avancado.funcaomodular.config;

import java.awt.image.BufferedImage;

public class ConfigFuncaoMod
{
	public int          indice;
	public boolean      mostrarPonto;
	public String       labelPonto;
	public boolean      mostrarLinha;
	public int          yLinha;
	public String       labelLinha;
	public DadosFuncaoMod dados;

	public ConfigFuncaoMod(DadosFuncaoMod dados)
	{
		this.dados = dados;
	}

	public BufferedImage criarImagem()
	{
		BufferedImage image = new BufferedImage(1250, 1250, BufferedImage.TYPE_INT_ARGB);
		GraficoFuncaoMod g = new GraficoFuncaoMod(dados, indice);

		var g2 = g.carregamentoInicial(image);
		g.inserirEixos(g2);
		g.inserirCurva(g2);

		if (mostrarLinha)
			g.inserirLinhaHorizontal(g2, yLinha, labelLinha);

		if (mostrarPonto)
			g.inserirPontoDestacado(g2, labelPonto);

		return image;
	}
}
