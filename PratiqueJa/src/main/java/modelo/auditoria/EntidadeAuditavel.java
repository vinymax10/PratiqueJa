package modelo.auditoria;

import modelo.DocumentoFile;
import modelo.academico.Ano;
import modelo.academico.Assunto;
import modelo.academico.Banca;
import modelo.academico.Disciplina;
import modelo.academico.Orgao;
import modelo.configuracao.ConfigCleanup;
import modelo.configuracao.Config;
import modelo.configuracao.Edicao;
import modelo.email.Email;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.ParagrafoExercicio;
import modelo.publicacao.Background;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ConviteGrupo;
import modelo.publicacao.Cta;
import modelo.publicacao.Hashtag;
import modelo.publicacao.ImagemPost;
import modelo.publicacao.ProgramacaoPost;
import modelo.questao.Alternativa;
import modelo.questao.ImagemFile;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import modelo.questao.ResultadoQuestao;
import modelo.seguranca.Acesso;
import modelo.usuario.Contato;
import modelo.usuario.Imagem;
import modelo.usuario.Pagamento;
import modelo.usuario.Turma;
import modelo.usuario.Usuario;

public enum EntidadeAuditavel
{
//	Usuário
	Usuario(Usuario.class, "Usuário"),
	Acesso(Acesso.class, "Acesso"),
	Contato(Contato.class, "Contato"),
	Imagem(Imagem.class, "Imagem"),
	Pagamento(Pagamento.class, "Pagamento"),
	Turma(Turma.class, "Turma"),

//	Exercício
	ExercicioPadrao(ExercicioPadrao.class, "Exercício Padrão"),
	Exercicio(Exercicio.class, "Exercício"),
	ParagrafoExercicio(ParagrafoExercicio.class, "Parágrafo de Exercício"),
	AlternativaExercicio(AlternativaExercicio.class, "Alternativa de Exercício"),

//	Questão
	Questao(Questao.class, "Questão"),
	Alternativa(Alternativa.class, "Alternativa"),
	Paragrafo(Paragrafo.class, "Parágrafo"),
	ImagemFile(ImagemFile.class, "Arquivo de Imagem"),
	ResultadoQuestao(ResultadoQuestao.class, "Resultado de Questão"),

//	Configuração de Questão
	Ano(Ano.class, "Ano"),
	Banca(Banca.class, "Banca"),
	Orgao(Orgao.class, "Órgão"),
	Disciplina(Disciplina.class, "Disciplina"),

//	Assunto
	Assunto(Assunto.class, "Assunto"),

//	Instagram
	ConfigPost(ConfigPost.class, "Configuração de Post"),
	ProgramacaoPost(ProgramacaoPost.class, "Programação de Post"),
	ImagemPost(ImagemPost.class, "Imagem do Post"),
	Background(Background.class, "Background"),
	Cta(Cta.class, "CTA"),
	Hashtag(Hashtag.class, "Hashtag"),
	ConviteGrupo(ConviteGrupo.class, "Convite de Grupo"),

//	Email
	Email(Email.class, "Email"),
	DocumentoFile(DocumentoFile.class, "Arquivo de Documento"),

//	Configuração geral
	ConfigCleanup(ConfigCleanup.class, "Configuração de Limpeza"),
	Config(Config.class, "Configuração LaTeX"),
	Edicao(Edicao.class, "Edição");

	private final String nome;
	private final Class<?> classe;

	EntidadeAuditavel(Class<?> classe, String nome)
	{
		this.classe = classe;
		this.nome = nome;
	}

	public String getClasseNome()
	{
		return classe.getSimpleName();
	}

	public static String getNomePorSimpleName(String simpleName)
	{
		if(simpleName == null || simpleName.isBlank())
			return null;

		for(EntidadeAuditavel e : values())
		{
			if(e.classe.getSimpleName().equals(simpleName))
				return e.nome;
		}
		return null;
	}

	public String getNome()
	{
		return nome;
	}
}
