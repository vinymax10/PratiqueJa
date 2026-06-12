package matematica.avancado.funcaologaritmica.config;

import java.awt.image.BufferedImage;

public class ConfigFuncaoLog
{
	public int          indice;
	public boolean      mostrarPonto;
	public String       labelPonto;
	public DadosFuncaoLog dados;

	public ConfigFuncaoLog(DadosFuncaoLog dados)
	{
		this.dados = dados;
	}

	public BufferedImage criarImagem()
	{
		BufferedImage image = new BufferedImage(1250, 1250, BufferedImage.TYPE_INT_ARGB);
		GraficoFuncaoLog g = new GraficoFuncaoLog(dados, indice);

		var g2 = g.carregamentoInicial(image);
		g.inserirEixos(g2);
		g.inserirCurva(g2);

		if (mostrarPonto)
			g.inserirPontoDestacado(g2, labelPonto);

		return image;
	}
}
