package bean.questao;

import java.sql.SQLException;
import java.util.EnumSet;

import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import bean.FilhoBean;
import dao.questao.ParagrafoResolucaoQuestaoDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.questao.ImagemFile;
import modelo.questao.ParagrafoResolucaoQuestao;
import modelo.questao.Questao;

/**
 * Editor admin dos parágrafos da resolução de uma {@link Questao}. Espelha {@link ParagrafoBean}
 * (parágrafos do enunciado): cada passo da resolução é um {@link ParagrafoResolucaoQuestao} (texto
 * LaTeX autocontido ou imagem). Substituiu a antiga textarea de String única.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class QuestaoResolucaoBean extends FilhoBean<ParagrafoResolucaoQuestao, ParagrafoResolucaoQuestaoDAO>
{
	private static final long serialVersionUID = 1L;

	@Inject
	private QuestaoBean questaoBean;

	private UploadedFile uploadedFile;

	public QuestaoResolucaoBean()
	{
		super(ParagrafoResolucaoQuestao.class, "Passo da resolução");
		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public String cadastrar()
	{
		entidade = new ParagrafoResolucaoQuestao();
		entidade.setQuestao(questaoBean.getEntidade());
		cadastro = true;
		return "";
	}

	public String adicionar()
	{
		return adicionar(() -> {
			Questao questao = questaoBean.getEntidade();
			entidade.setOrdem(questao.getResolucaoParagrafos().size());
			questao.getResolucaoParagrafos().add(entidade);
			if(questao.getId() != null)
			{
				questao = questaoBean.somenteSalvar();
				entidade = questao.getResolucaoParagrafos().get(entidade.getOrdem());
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
			questao.getResolucaoParagrafos().remove(entidade);
			if(questao.getId() != null)
			{
				questao = questaoBean.somenteSalvar();
				onRowReorder(questao.getResolucaoParagrafos());
			}
		});
	}

	/** Remoção direta pela linha da tabela (ícone na coluna Ação). */
	public String remover(ParagrafoResolucaoQuestao paragrafo)
	{
		this.entidade = paragrafo;
		return remover();
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
