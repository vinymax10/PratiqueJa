package Modelo.Questao.Configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import Modelo.Entidade;
import Modelo.Configuracao.Opcao;
import Modelo.Questao.Questao;

@Entity
public class Disciplina implements Serializable, Entidade, Opcao
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 255)
	@Size(max = 255)
	private String nome;

	@OneToMany(orphanRemoval = true, mappedBy = "disciplina")
	private List<Assunto> assuntos = new ArrayList<Assunto>();

//  Volta
	@OneToMany(mappedBy = "disciplina")
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

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Assunto> getAssuntos()
	{
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos)
	{
		this.assuntos = assuntos;
	}

	public List<Questao> getQuestoes()
	{
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes)
	{
		this.questoes = questoes;
	}

	@Override
	public String toString()
	{
		return "Disciplina [id=" + id + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
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
		Disciplina other = (Disciplina) obj;
		if (nome == null)
		{
			if (other.nome != null)
				return false;
		}
		else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
