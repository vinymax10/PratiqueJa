package modelo.publicacao;

import java.io.Serializable;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.Assunto;
import modelo.auditoria.AuditLabel;
import modelo.exercicio.Nivel;

/** Um assunto dentro de um pedido de posts: quantos exercícios e em qual(is) formato(s). */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "pedidoPost", "assunto" })
@Data
@Entity
public class ItemPedidoPost implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@DiffIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private PedidoPost pedidoPost;

	@ManyToOne
	@AuditLabel(value = "assunto")
	private Assunto assunto;

	/** Nível dos exercícios; null = todos os níveis. */
	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "nível")
	private Nivel nivel;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "formato")
	private FormatoPedidoPost formato = FormatoPedidoPost.AMBOS;

	/** Quantos exercícios deste assunto gerar (cada um vira 1 post; no formato "Feed e reel", 2). */
	@AuditLabel(value = "quantidade")
	private int quantidade = 1;

	@AuditLabel(value = "ordem")
	private int ordem;

	/** Créditos (posts) consumidos por este item: exercícios × formatos. */
	public int getCreditos()
	{
		return quantidade * (formato != null ? formato.getPostsPorExercicio() : 1);
	}

	/** Cópia rasa dos dados do item (sem id nem vínculo), para duplicar/editar. */
	public ItemPedidoPost copia()
	{
		ItemPedidoPost copia = new ItemPedidoPost();
		copia.assunto = this.assunto;
		copia.nivel = this.nivel;
		copia.formato = this.formato;
		copia.quantidade = this.quantidade;
		return copia;
	}
}
