package bean.exercicio;

import java.util.EnumSet;

import bean.FilhoBean;
import dao.matematica.AlternativaExercicioDAO;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ExercicioAlternativaBean extends FilhoBean<AlternativaExercicio, AlternativaExercicioDAO>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ExercicioBean exercicioBean;

	public ExercicioAlternativaBean()
	{
		super(AlternativaExercicio.class, "Alternativa");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public String cadastrar()
	{
		entidade = new AlternativaExercicio();
		entidade.setExercicio(exercicioBean.getEntidade());
		cadastro = true;
		return "";
	}

	public String adicionar()
	{
		return adicionar(() -> {
			Exercicio exercicio = exercicioBean.getEntidade();
			entidade.setOrdem(exercicio.getAlternativas().size());
			exercicio.getAlternativas().add(entidade);
			if(exercicio.getId() != null)
			{
				exercicio = exercicioBean.somenteSalvar();
				entidade = exercicio.getAlternativas().get(entidade.getOrdem());
			}
		});
	}

	public String salvar()
	{
		return salvar(
			() -> mapper.update(entidade, entidadeOriginal),
			() -> {
				Exercicio exercicio = exercicioBean.getEntidade();
				if(exercicio.getId() != null)
					exercicioBean.somenteSalvar();
			});
	}

	public String remover()
	{
		return remover(() -> {
			Exercicio exercicio = exercicioBean.getEntidade();
			exercicio.getAlternativas().remove(entidade);
			if(exercicio.getId() != null)
			{
				exercicio = exercicioBean.somenteSalvar();
				onRowReorder(exercicio.getAlternativas());
			}
		});
	}
}
