package bean.exercicio;

import java.sql.SQLException;
import java.util.EnumSet;

import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import bean.FilhoBean;
import dao.matematica.ParagrafoExercicioDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.matematica.Exercicio;
import modelo.matematica.ParagrafoExercicio;
import modelo.questao.ImagemFile;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ExercicioParagrafoBean extends FilhoBean<ParagrafoExercicio, ParagrafoExercicioDAO>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ExercicioBean exercicioBean;

	private UploadedFile uploadedFile;

	public ExercicioParagrafoBean()
	{
		super(ParagrafoExercicio.class, "Parágrafo");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public String cadastrar()
	{
		entidade = new ParagrafoExercicio();
		entidade.setExercicio(exercicioBean.getEntidade());
		cadastro = true;
		return "";
	}

	public String adicionar()
	{
		return adicionar(() -> {
			Exercicio exercicio = exercicioBean.getEntidade();
			entidade.setOrdem(exercicio.getParagrafos().size());
			exercicio.getParagrafos().add(entidade);
			if(exercicio.getId() != null)
			{
				exercicio = exercicioBean.somenteSalvar();
				entidade = exercicio.getParagrafos().get(entidade.getOrdem());
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
			exercicio.getParagrafos().remove(entidade);
			if(exercicio.getId() != null)
			{
				exercicio = exercicioBean.somenteSalvar();
				onRowReorder(exercicio.getParagrafos());
			}
		});
	}

	public void uploadNovo(FileUploadEvent event)
	{
		uploadedFile = event.getFile();
		try
		{
			SerialBlob serialBlob = new SerialBlob(uploadedFile.getContent());
			ImagemFile imagemFile = new ImagemFile();
			imagemFile.setFile(serialBlob);
			imagemFile.setEndImagem(uploadedFile.getFileName());
			entidade.setImagemFile(imagemFile);
			entidade.setTexto(null);

			FacesContext.getCurrentInstance().addMessage("growl",
			new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Upload de " + uploadedFile.getFileName() + " realizado com sucesso.", ""));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void removerImagem()
	{
		entidade.setImagemFile(null);
	}
}
