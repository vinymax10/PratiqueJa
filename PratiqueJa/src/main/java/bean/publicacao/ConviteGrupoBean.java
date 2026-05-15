package bean.publicacao;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

import bean.ConfigBean;
import dao.publicacao.ConviteGrupoDAO;
import exceptions.RelacaoException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.auditoria.TipoEvento;
import modelo.publicacao.ConviteGrupo;

@Named
@ViewScoped
public class ConviteGrupoBean extends ConfigBean<ConviteGrupo, ConviteGrupoDAO>
{
	public ConviteGrupoBean()
	{
		super(ConviteGrupo.class, "Convite ao Grupo");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(ConviteGrupo entidade) throws RelacaoException {}

	public String getAnyConviteGrupo()
	{
		if(opcoesAtivas.size() > 0)
			return opcoesAtivas.get(ThreadLocalRandom.current().nextInt(opcoesAtivas.size())).getNome();
		else
			return "";
	}
}
