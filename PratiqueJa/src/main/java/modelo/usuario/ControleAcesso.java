package modelo.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "usuario" })
@Data
@Entity
public class ControleAcesso implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne
	private Usuario usuario;

	@AuditLabel(value = "data", genero = GeneroGramatical.FEMININO)
	private LocalDate data;

	@AuditLabel(value = "downloads de exercício")
	private int numDownloadExercicio;

	@AuditLabel(value = "downloads de questão", genero = GeneroGramatical.FEMININO)
	private int numDownloadQuestao;

	@AuditLabel(value = "downloads de questão em massa", genero = GeneroGramatical.FEMININO)
	private int numDownloadQuestaoMassa;

	@AuditLabel(value = "downloads em massa")
	private int numDownloadMassa;

	@AuditLabel(value = "resoluções de exercício", genero = GeneroGramatical.FEMININO)
	private int numResolucaoExercicio;

	@AuditLabel(value = "resoluções de questão", genero = GeneroGramatical.FEMININO)
	private int numResolucaoQuestao;

	@AuditLabel(value = "questões feitas", genero = GeneroGramatical.FEMININO)
	private int numQuestaoFeita;

	public void limpar()
	{
		data = LocalDate.now();
		numDownloadExercicio = 0;
		numDownloadQuestao = 0;
		numResolucaoExercicio = 0;
		numResolucaoQuestao = 0;
		numQuestaoFeita = 0;
		numDownloadQuestaoMassa = 0;
		numDownloadMassa = 0;
	}

	public void incrementaDownloadExercicio() { numDownloadExercicio++; }
	public void incrementaDownloadQuestao() { numDownloadQuestao++; }
	public void incrementaDownloadQuestaoMassa() { numDownloadQuestaoMassa++; }
	public void incrementaDownloadMassa() { numDownloadMassa++; }
	public void incrementaResolucaoExercicio() { numResolucaoExercicio++; }
	public void incrementaResolucaoQuestao() { numResolucaoQuestao++; }
	public void incrementaQuestaoFeita() { numQuestaoFeita++; }
}
