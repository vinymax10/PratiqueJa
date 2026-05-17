package modelo.configuracao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Data
@Entity
public class ConfigControleAcesso implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@AuditLabel(value = "limite de páginas baixadas", genero = GeneroGramatical.FEMININO)
	private int limitePaginasBaixadas;

	@AuditLabel(value = "limite de resoluções de exercício", genero = GeneroGramatical.FEMININO)
	private int limiteResolucaoExercicio;

	@AuditLabel(value = "limite de resoluções de questão", genero = GeneroGramatical.FEMININO)
	private int limiteResolucaoQuestao;

	@AuditLabel(value = "limite de questões feitas", genero = GeneroGramatical.FEMININO)
	private int limiteQuestaoFeita;
}
