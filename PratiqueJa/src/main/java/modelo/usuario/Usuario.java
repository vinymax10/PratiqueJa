package modelo.usuario;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import modelo.Ativo;
import modelo.Entidade;
import modelo.auditoria.AuditLabel;
import modelo.auditoria.GeneroGramatical;
import modelo.exercicio.ResultadoExercicio;
import modelo.avaliacao.PlanoAvaliacao;
import modelo.publicacao.ConfigPost;
import modelo.questao.ResultadoQuestao;
import modelo.seguranca.Acesso;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(exclude = { "resultadosExercicios", "resultadosQuestoes", "exercicios", "contatos", "imagem", "acessos", "controlesAcessos", "pagamentos", "turma", "configPost" })
@Data
@Entity
public class Usuario extends Ativo implements Entidade
{
	private static final long serialVersionUID = 1L;

	@DiffIgnore
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "nome")
	private String nome;

	@Size(max = 255)
	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	@Column(unique = true, length = 255)
	@AuditLabel(value = "email")
	private String email;

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "senha", genero = GeneroGramatical.FEMININO)
	private String senha;

	@AuditLabel(value = "nascimento")
	private LocalDate nascimento;

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<ResultadoExercicio> resultadosExercicios = new ArrayList<ResultadoExercicio>();

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<ResultadoQuestao> resultadosQuestoes = new ArrayList<ResultadoQuestao>();

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<Contato> contatos = new ArrayList<Contato>();

	@Column(length = 255)
	@Size(max = 255)
	@AuditLabel(value = "sub Google")
	private String subGoogle;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Imagem imagem;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "perfil")
	private PerfilUsuario perfil = PerfilUsuario.Bronze;

	@Enumerated(EnumType.STRING)
	@AuditLabel(value = "plano de avaliações")
	private PlanoAvaliacao planoAvaliacao;

	@AuditLabel(value = "criador")
	private boolean criador;

	@AuditLabel(value = "recebe spam")
	private boolean recebeSpam;

	@AuditLabel(value = "validade do plano", genero = GeneroGramatical.FEMININO)
	private LocalDate validadePlano;

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<Acesso> acessos = new ArrayList<Acesso>();

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<ControleAcesso> controlesAcessos = new ArrayList<ControleAcesso>();

	@DiffIgnore
	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

	@DiffIgnore
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Turma turma;

	@DiffIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ConfigPost configPost;
	
	@DiffIgnore
	private boolean resetSenha;

	public String getFirstNome()
	{
		return nome.split(" ")[0];
	}
}
