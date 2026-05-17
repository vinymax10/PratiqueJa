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

	@AuditLabel(value = "páginas baixadas", genero = GeneroGramatical.FEMININO)
	private int numPaginasBaixadas;

	@AuditLabel(value = "resoluções de exercício", genero = GeneroGramatical.FEMININO)
	private int numResolucaoExercicio;

	@AuditLabel(value = "resoluções de questão", genero = GeneroGramatical.FEMININO)
	private int numResolucaoQuestao;

	@AuditLabel(value = "questões feitas", genero = GeneroGramatical.FEMININO)
	private int numQuestaoFeita;

	public void limpar()
	{
		data = LocalDate.now().withDayOfMonth(1);
		numPaginasBaixadas = 0;
		numResolucaoExercicio = 0;
		numResolucaoQuestao = 0;
		numQuestaoFeita = 0;
	}

	public void incrementaPaginasBaixadas(int paginas) { numPaginasBaixadas += paginas; }
	public void incrementaResolucaoExercicio() { numResolucaoExercicio++; }
	public void incrementaResolucaoQuestao() { numResolucaoQuestao++; }
	public void incrementaQuestaoFeita() { numQuestaoFeita++; }
}
