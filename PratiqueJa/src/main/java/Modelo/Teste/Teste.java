package Modelo.Teste;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import Modelo.Entidade;
import Modelo.Usuario.Usuario;

@Entity
public class Teste implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private TestePadrao testePadrao;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "teste")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<EtapaTeste> etapasTeste = new ArrayList<EtapaTeste>();

	private boolean realizado = false;
	
	private boolean repetirAtePassar = false;
	
	private LocalDate realizacao;

	private double nota;

//	em segundos
	private double tempo;
	
//	em minutos
	private double duracao;
	
	private double notaMinima;
	
	@ManyToOne
	private Usuario usuario;

	public boolean aprovado()
	{
		if(realizado&&nota>=notaMinima)
			return true;
		else
			return false;
	}
	
	public void reduzTempo()
	{
		tempo-=1;
	}
	
	public Long getId()
	{
		return id;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public LocalDate getRealizacao()
	{
		return realizacao;
	}

	public void setRealizacao(LocalDate realizacao)
	{
		this.realizacao = realizacao;
	}

	public double getNota()
	{
		return nota;
	}

	public void setNota(double nota)
	{
		this.nota = nota;
	}

	public TestePadrao getTestePadrao()
	{
		return testePadrao;
	}

	public void setTestePadrao(TestePadrao testePadrao)
	{
		this.testePadrao = testePadrao;
	}

	public double getTempo()
	{
		return tempo;
	}
	
	public String getTempoString()
	{
		int min=(int)(tempo/60);
		int sec=(int)(tempo%60);
		return String.format("%02d:%02d", min, sec);
	}

	public void setTempo(double tempo)
	{
		this.tempo = tempo;
	}

	public double getDuracao()
	{
		return duracao;
	}

	public void setDuracao(double duracao)
	{
		this.duracao = duracao;
		this.tempo=duracao;
	}

	public double getNotaMinima()
	{
		return notaMinima;
	}

	public void setNotaMinima(double notaMinima)
	{
		this.notaMinima = notaMinima;
	}

	public List<EtapaTeste> getEtapasTeste()
	{
		return etapasTeste;
	}

	public void setEtapasTeste(List<EtapaTeste> etapasTeste)
	{
		this.etapasTeste = etapasTeste;
	}

	public boolean isRealizado()
	{
		return realizado;
	}

	public void setRealizado(boolean realizado)
	{
		this.realizado = realizado;
	}

	public boolean isRepetirAtePassar()
	{
		return repetirAtePassar;
	}

	public void setRepetirAtePassar(boolean repetirAtePassar)
	{
		this.repetirAtePassar = repetirAtePassar;
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
		Teste other = (Teste) obj;
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
		return "Teste: " + (id != null ? "id=" + id + ", " : "") + "realizado=" + realizado + ", "
		+ (realizacao != null ? "realizacao=" + realizacao + ", " : "") + "nota=" + nota + ", tempo=" + tempo
		+ ", duracao=" + duracao + ", notaMinima=" + notaMinima + ", " + (usuario != null ? "usuario=" + usuario : "");
	}

}