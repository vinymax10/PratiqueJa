package pdf.publicacao.estilo;

import modelo.publicacao.EstiloPost;

/**
 * Cada implementação gera o macro {@code \painel} usado por {@link pdf.publicacao.InstagramFeed2}
 * e {@link pdf.publicacao.TikTok2}: desenha a foto de fundo (sempre {@code background.png}, já
 * gravada no diretório antes da compilação — quem quiser um fundo liso basta escolher uma
 * imagem lisa) e a moldura/decoração da página, definindo as coordenadas PNW/PSE/PNE/PN/PS
 * (margem útil) em que o resto do layout (selo, chip, enunciado, resolução, CTA) é posicionado.
 * O {@link EstiloPost} define só a estrutura (moldura/decoração); a cor de destaque (chip do
 * assunto, texto do enunciado e a própria moldura) é recebida à parte, via
 * {@code ConfigPost.corDestaque}, para que o usuário combine estrutura e cor livremente — o
 * resto do layout (gancho em laranja, CTA em verde, selo de nível semáforo) é comum a todos,
 * propositalmente, já que carrega significado (atenção / ação / dificuldade) independente do
 * estilo e da cor escolhidos.
 */
public interface EstiloVisual
{
	/**
	 * Corpo do macro {@code \painel}: além do fundo/moldura, DEVE definir as coordenadas
	 * PNW/PSE/PNE/PN/PS a partir da margem {@code margemPx}, com o painel de conteúdo
	 * arredondado em {@code raioPt}.
	 */
	String painel(String margemPx, String raioPt);

	/** Nome da cor (já definida no preâmbulo) usada no chip do assunto e no texto do enunciado. */
	String corDestaque();

	static EstiloVisual de(EstiloPost estilo, String cor)
	{
		if(estilo == EstiloPost.MolduraAzul)
			return new MolduraAzulVisual(cor);
		if(estilo == EstiloPost.SombraCoral)
			return new SombraCoralVisual(cor);
		if(estilo == EstiloPost.CantosVivos)
			return new CantosVivosVisual(cor);
		if(estilo == EstiloPost.FaixaSuperior)
			return new FaixaSuperiorVisual(cor);
		return new FotoFundoVisual(cor);
	}
}
