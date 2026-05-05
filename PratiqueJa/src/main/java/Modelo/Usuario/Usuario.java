package Modelo.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import Modelo.Entidade;
import Modelo.Exercicio.Exercicio;
import Modelo.Exercicio.ResultadoExercicio;
import Modelo.Instagram.ConfigPost;
import Modelo.Questao.ResultadoQuestao;
import Modelo.Usuario.Enum.PerfilUsuario;

@Entity
public class Usuario implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	private String nome;

	@Size(max = 255)
	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	@Column(unique = true, length = 255)
	private String email;

	@Column(length = 255)
	@Size(max = 255)
	private String senha;

	private LocalDate nascimento;

	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<ResultadoExercicio> resultadosExercicios = new ArrayList<ResultadoExercicio>();

	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<ResultadoQuestao> resultadosQuestoes = new ArrayList<ResultadoQuestao>();

	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<Exercicio> exercicios = new ArrayList<Exercicio>();

	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<Contato> contatos = new ArrayList<Contato>();

	@Column(length = 255)
	@Size(max = 255)
	private String subGoogle;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Imagem imagem;

	@Enumerated(EnumType.STRING)
	private PerfilUsuario perfil = PerfilUsuario.Bronze;
	
	private boolean criador;
	
	private boolean recebeSpam;
	
	private LocalDate validadePlano;

	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<Acesso> acessos = new ArrayList<Acesso>();

	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<ControleAcesso> controlesAcessos = new ArrayList<ControleAcesso>();
	
	@OneToMany(orphanRemoval = true, mappedBy = "usuario")
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();
	
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Turma turma;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private ConfigPost configPost;
	
	public Long getId()
	{
		return id;
	}

	public String getNome()
	{
		return nome;
	}

	public String getFirstNome()
	{
		return nome.split(" ")[0];
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public LocalDate getNascimento()
	{
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento)
	{
		this.nascimento = nascimento;
	}

	public ConfigPost getConfigPost()
	{
		return configPost;
	}

	public void setConfigPost(ConfigPost configPost)
	{
		this.configPost = configPost;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
		+ (email != null ? "email=" + email + ", " : "") + (nascimento != null ? "nascimento=" + nascimento + ", " : "")
		+ (subGoogle != null ? "subGoogle=" + subGoogle + ", " : "") + (perfil != null ? "perfil=" + perfil : "");
	}

	public List<ResultadoExercicio> getResultadosExercicios()
	{
		return resultadosExercicios;
	}

	public void setResultadosExercicios(List<ResultadoExercicio> resultadosExercicios)
	{
		this.resultadosExercicios = resultadosExercicios;
	}

	public List<ResultadoQuestao> getResultadosQuestoes()
	{
		return resultadosQuestoes;
	}

	public void setResultadosQuestoes(List<ResultadoQuestao> resultadosQuestoes)
	{
		this.resultadosQuestoes = resultadosQuestoes;
	}

	public List<Exercicio> getExercicios()
	{
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios)
	{
		this.exercicios = exercicios;
	}

	public Imagem getImagem()
	{
		return imagem;
	}

	public void setImagem(Imagem imagem)
	{
		this.imagem = imagem;
	}

	public String getSubGoogle()
	{
		return subGoogle;
	}

	public void setSubGoogle(String subGoogle)
	{
		this.subGoogle = subGoogle;
	}

	public PerfilUsuario getPerfil()
	{
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil)
	{
		this.perfil = perfil;
	}

	public List<Contato> getContatos()
	{
		return contatos;
	}

	public void setContatos(List<Contato> contatos)
	{
		this.contatos = contatos;
	}

	public List<Acesso> getAcessos()
	{
		return acessos;
	}

	public void setAcessos(List<Acesso> acessos)
	{
		this.acessos = acessos;
	}

	public List<ControleAcesso> getControlesAcessos()
	{
		return controlesAcessos;
	}

	public void setControlesAcessos(List<ControleAcesso> controlesAcessos)
	{
		this.controlesAcessos = controlesAcessos;
	}

	public List<Pagamento> getPagamentos()
	{
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos)
	{
		this.pagamentos = pagamentos;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public LocalDate getValidadePlano()
	{
		return validadePlano;
	}

	public void setValidadePlano(LocalDate validadePlano)
	{
		this.validadePlano = validadePlano;
	}

	public boolean isCriador()
	{
		return criador;
	}

	public void setCriador(boolean criador)
	{
		this.criador = criador;
	}

	public boolean isRecebeSpam()
	{
		return recebeSpam;
	}

	public void setRecebeSpam(boolean recebeSpam)
	{
		this.recebeSpam = recebeSpam;
	}
	
}
