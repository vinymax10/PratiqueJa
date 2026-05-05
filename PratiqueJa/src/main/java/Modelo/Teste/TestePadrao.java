package Modelo.Teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import Modelo.Entidade;
import Modelo.AssuntoCurso.AssuntoCurso;

@Entity
public class TestePadrao implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 255)
	@Size(max = 255)
	private String nome;
	
	@OneToOne(mappedBy = "testePadrao")
	private AssuntoCurso assuntoCurso;
	
	@OneToMany(orphanRemoval = true, mappedBy = "testePadrao")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<ConteudoTeste> conteudosTeste = new ArrayList<ConteudoTeste>();
	
//	em minutos
	private double duracao;
	
	private double notaMinima;
	
	public Long getId()
	{
		return id;
	}

	public List<ConteudoTeste> getConteudosTeste()
	{
		return conteudosTeste;
	}

	public void setConteudosTeste(List<ConteudoTeste> conteudosTeste)
	{
		this.conteudosTeste = conteudosTeste;
	}

	public double getDuracao()
	{
		return duracao;
	}

	public void setDuracao(double duracao)
	{
		this.duracao = duracao;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public double getNotaMinima()
	{
		return notaMinima;
	}

	public void setNotaMinima(double notaMinima)
	{
		this.notaMinima = notaMinima;
	}
	
	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
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
		TestePadrao other = (TestePadrao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "TestePadrao: " + (id != null ? "id=" + id + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
		+ "duracao=" + duracao + ", notaMinima=" + notaMinima;
	}

}