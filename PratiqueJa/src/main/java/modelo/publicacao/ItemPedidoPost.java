package modelo.publicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.academico.Assunto;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.exercicio.Nivel;

/** Um assunto dentro de um pedido de posts: quantos exercícios e em qual(is) formato(s). */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "pedidoPost", "assunto", "background", "padrao" })
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

	/** Níveis dos exercícios (ao menos um). Cada nível gera `quantidade` exercícios. */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "itempedidopost_nivel", joinColumns = @JoinColumn(name = "itempedidopost_id"))
	@Column(name = "nivel")
	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "níveis")
	private List<Nivel> niveis = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "formato")
	private FormatoPost formato = FormatoPost.Feed;

	@AuditLabel(value = "alternativa reel", genero = GeneroGramatical.FEMININO)
	private boolean alternativaReel = true;

	// Configuração de fundo por item (mesma da ProgramacaoPost): escolha/origem + imagem.
	@AuditLabel(value = "background aleatório")
	private boolean backgroundAleatorio = true;

	@AuditLabel(value = "base padrão", genero = GeneroGramatical.FEMININO)
	private boolean basePadrao = true;

	@DiffIgnore
	@ManyToOne
	private ImagemPost background;

	@DiffIgnore
	@ManyToOne
	private Background padrao;

	/** Quantos exercícios deste assunto gerar (cada um vira 1 post). */
	@AuditLabel(value = "quantidade")
	private int quantidade = 1;

	@AuditLabel(value = "ordem")
	private int ordem;

	/** Créditos (posts): cada nível gera `quantidade` exercícios (1 post cada). */
	public int getCreditos()
	{
		return quantidade * (niveis != null ? niveis.size() : 0);
	}

	/** Cópia rasa dos dados do item (sem id nem vínculo), para duplicar/editar. */
	public ItemPedidoPost copia()
	{
		ItemPedidoPost copia = new ItemPedidoPost();
		copia.assunto = this.assunto;
		copia.niveis = new ArrayList<>(this.niveis);
		copia.formato = this.formato;
		copia.quantidade = this.quantidade;
		copia.alternativaReel = this.alternativaReel;
		copia.backgroundAleatorio = this.backgroundAleatorio;
		copia.basePadrao = this.basePadrao;
		copia.background = this.background;
		copia.padrao = this.padrao;
		return copia;
	}
}
