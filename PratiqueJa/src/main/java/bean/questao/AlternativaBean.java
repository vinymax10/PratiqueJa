package bean.questao;

import java.util.EnumSet;

import bean.FilhoBean;
import dao.questao.AlternativaDAO;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.questao.Alternativa;
import modelo.questao.Questao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class AlternativaBean extends FilhoBean<Alternativa, AlternativaDAO>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private QuestaoBean questaoBean;

	public AlternativaBean()
	{
		super(Alternativa.class, "Alternativa");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public String cadastrar()
	{
		entidade = new Alternativa();
		entidade.setQuestao(questaoBean.getEntidade());
		cadastro = true;
		return "";
	}

	public String adicionar()
	{
		return adicionar(() -> {
			Questao questao = questaoBean.getEntidade();
			entidade.setOrdem(questao.getAlternativas().size());
			questao.getAlternativas().add(entidade);
			if(questao.getId() != null)
			{
				questao = questaoBean.somenteSalvar();
				entidade = questao.getAlternativas().get(entidade.getOrdem());
			}
		});
	}

	public String salvar()
	{
		return salvar(
		() -> {
			mapper.update(entidade, entidadeOriginal);
		},
		() -> {
			Questao questao = questaoBean.getEntidade();
			if(questao.getId() != null)
				questaoBean.somenteSalvar();
		});
	}

	public String remover()
	{
		return remover(() -> {
			Questao questao = questaoBean.getEntidade();
			questao.getAlternativas().remove(entidade);
			if(questao.getId() != null)
			{
				questao = questaoBean.somenteSalvar();
				onRowReorder(questao.getAlternativas());
			}
		});
	}
}
