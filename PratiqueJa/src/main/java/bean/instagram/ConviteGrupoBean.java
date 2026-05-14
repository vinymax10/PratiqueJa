package bean.instagram;

import java.util.EnumSet;
import java.util.Random;

import bean.ConfigBean;
import dao.instagram.ConviteGrupoDAO;
import exceptions.RelacaoException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.auditoria.TipoEvento;
import modelo.publicacao.ConviteGrupo;
import modelo.academico.Ano;

@Named
@ViewScoped
public class ConviteGrupoBean extends ConfigBean<ConviteGrupo, ConviteGrupoDAO>
{
	public ConviteGrupoBean()
	{
		super(ConviteGrupo.class, "Convite ao Grupo");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	protected void podeRemover(Ano entidade) throws RelacaoException
	{
//		if(entidade.getQuestoes().size() > 0)
//		throw new RelacaoException("Não foi possível remover o(a) " + nome + ". "
//		+ "Existem Questões relacionadas.");
	}

	public String getAnyConviteGrupo()
	{
		Random rand=new Random();
		if(opcoesAtivas.size()>0)
			return opcoesAtivas.get(rand.nextInt(opcoesAtivas.size())).getNome();
		else
			return "";
	}
	
}
