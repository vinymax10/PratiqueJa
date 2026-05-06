package modelo.questao.configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import modelo.Entidade;
import modelo.configuracao.Opcao;
import modelo.questao.Questao;

@Entity
public class Orgao implements Serializable, Entidade, Opcao
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	private String nome;

	@Column(length = 255)
	@Size(max = 255)
	private String sigla;

//  Volta
	@OneToMany(mappedBy = "orgao")
	private List<Questao> questoes = new ArrayList<Questao>();

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public String getNomeReduzido()
	{
		String str = sigla + " - " + nome;
		return str.substring(0, Math.min(str.length(), 80));
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Questao> getQuestoes()
	{
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes)
	{
		this.questoes = questoes;
	}

	public String getSigla()
	{
		return sigla;
	}

	public void setSigla(String sigla)
	{
		this.sigla = sigla.toUpperCase();
	}

	@Override
	public String toString()
	{
		return "Orgao [id=" + id + ", nome=" + nome + ", sigla=" + sigla + "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orgao other = (Orgao) obj;
		return Objects.equals(id, other.id);
	}

}
