package modelo.publicacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.javers.core.metamodel.annotation.DiffIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.usuario.Usuario;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "imagensFundo", "ctas", "logo", "testeExeFeed", "testeResFeed", "testeExeReel", "testeResReel", "usuario", "programacoesPost" })
@Data
@Entity
public class ConfigPost implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	// Cores e transparência fixas (antes configuráveis no ConfigPost; agora constantes).
	public static final String COR_FONTE   = "#4d4d4d";
	public static final String COR_TITULO  = "#4059e3";
	public static final String COR_NOME    = "#4d4d4d";
	public static final String COR_FORMULA = "#4059e3";
	public static final double TRANSPARENCIA = 0.15;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 4096)
	@Size(max = 4096)
	@AuditLabel(value = "descrição do CTA", genero = GeneroGramatical.FEMININO)
	private String descricaoCTA;

	@AuditLabel(value = "último envio")
	private LocalDate ultimoEnvio = LocalDate.now();

	// Quantos dias cada assunto (o do topo da fila) permanece ativo publicando diariamente antes de
	// o ciclo rotacionar para o próximo assunto. Ex.: 7 → o mesmo assunto posta todo dia por 7 dias.
	@AuditLabel(value = "dias por assunto")
	private int qtdDias = 1;

	// Contador interno: dias já publicados do assunto atual no ciclo. Ao atingir qtdDias, rotaciona.
	@DiffIgnore
	private int diasNoCiclo = 0;

	@AuditLabel(value = "ativo")
	private boolean ativo;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "finalidade do CTA", genero = GeneroGramatical.FEMININO)
	private FinalidadeCta finalidadeCta = FinalidadeCta.Ensino;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "estilo do post", genero = GeneroGramatical.MASCULINO)
	private EstiloPost estilo = EstiloPost.FotoFundo;

	@Column(length = 7)
	@Size(max = 7)
	@AuditLabel(value = "cor de destaque", genero = GeneroGramatical.FEMININO)
	private String corDestaque = "#4059E3";

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "configPost")
	@OrderBy("ordem")
	private List<ImagemPost> imagensFundo = new ArrayList<ImagemPost>();

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "configPost")
	private List<Cta> ctas = new ArrayList<Cta>();

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost logo;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeExeFeed;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeResFeed;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeExeReel;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ImagemPost testeResReel;

	@DiffIgnore
	@OneToOne
	private Usuario usuario;

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "configPost")
	@OrderBy("ordem")
	private List<ProgramacaoPost> programacoesPost = new ArrayList<ProgramacaoPost>();

	public boolean podeGerar()
	{
		if(!ativo)
			return false;

		if(usuario.getEmail() == null || usuario.getEmail().equals(""))
			return false;

		if(logo == null)
			return false;

		return true;
	}

	public List<ImagemPost> getListTesteFeed()
	{
		List<ImagemPost> list = new ArrayList<ImagemPost>();
		if(testeExeFeed != null) list.add(testeExeFeed);
		if(testeResFeed != null) list.add(testeResFeed);
		return list;
	}

	public List<ImagemPost> getListTesteReel()
	{
		List<ImagemPost> list = new ArrayList<ImagemPost>();
		if(testeExeReel != null) list.add(testeExeReel);
		if(testeResReel != null) list.add(testeResReel);
		return list;
	}
}
