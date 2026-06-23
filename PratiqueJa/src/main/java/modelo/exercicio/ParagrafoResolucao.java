package modelo.exercicio;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.questao.ImagemFile;

/**
 * Um passo/linha da resolução de um {@link Exercicio}. A resolução deixou de ser
 * uma única String com "\\" embutido (frágil — quebrava o modo matemático) e passou
 * a ser uma lista ordenada destes parágrafos. Cada parágrafo é LaTeX autocontido
 * (texto + matemática inline "\(...\)") ou uma imagem.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "imagemFile", "exercicio" })
@Data
@Entity
public class ParagrafoResolucao implements Serializable, Entidade, Comparable<ParagrafoResolucao>
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 4095)
	@Size(max = 4095)
	@AuditLabel(value = "texto")
	private String texto;

	@AuditLabel(value = "ordem")
	private int ordem;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemFile imagemFile;

	@DiffIgnore
	@ManyToOne
	private Exercicio exercicio;

	public boolean isTipoImagem()
	{
		return imagemFile != null;
	}

	@Override
	public int compareTo(ParagrafoResolucao o)
	{
		return ordem - o.ordem;
	}
}
