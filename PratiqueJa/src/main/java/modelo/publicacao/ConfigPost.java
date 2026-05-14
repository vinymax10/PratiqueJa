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

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "nome", atributo = "nome")
	private String nome;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "cor da fonte", genero = GeneroGramatical.FEMININO)
	private String corFonte;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "cor do título")
	private String corTitulo;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "cor do nome")
	private String corNome;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "cor da fórmula", genero = GeneroGramatical.FEMININO)
	private String corFormula;

	@Column(length = 4096)
	@Size(max = 4096)
	@AuditLabel(value = "descrição do CTA", genero = GeneroGramatical.FEMININO)
	private String descricaoCTA;

	@AuditLabel(value = "último envio")
	private LocalDate ultimoEnvio = LocalDate.now();

	@AuditLabel(value = "ativo")
	private boolean ativo;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "finalidade do CTA", genero = GeneroGramatical.FEMININO)
	private FinalidadeCta finalidadeCta = FinalidadeCta.Ensino;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "perfil do criador")
	private PerfilCriador perfilCriador = PerfilCriador.Basico;

	@AuditLabel(value = "transparência", genero = GeneroGramatical.FEMININO)
	private int transparencia;

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

	public void setNome(String nome)
	{
		this.nome = nome.trim();
	}

	public double getTransparenciaPorc()
	{
		return (double) transparencia / 100;
	}

	public boolean podeGerar()
	{
		if(!ativo)
			return false;

		if(usuario.getEmail() == null || usuario.getEmail().equals(""))
			return false;

		if(logo == null)
			return false;

		if(nome == null)
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
