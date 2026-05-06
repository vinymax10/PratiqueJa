package modelo.spam;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import modelo.Entidade;
import modelo.exercicio.Nivel;

@Entity
public class ConfigSpam implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private LocalDate ultimoEnvio=LocalDate.now();
	
	private boolean ativo;
	
	private int diasIntervalo=3;
	
	private int qtnNivel1=3;

	private int qtnNivel2=3;

	private int qtnNivel3=3;

	@OneToMany(orphanRemoval = true, mappedBy = "configSpam")
	@OrderBy("ordem")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<ProgramacaoSpam> programacoesSpam = new ArrayList<ProgramacaoSpam>();
	
	public int qtn(Nivel nivel)
	{
		if(nivel==Nivel.Nivel1)
			return qtnNivel1;
		
		if(nivel==Nivel.Nivel2)
			return qtnNivel2;
		
		if(nivel==Nivel.Nivel3)
			return qtnNivel3;
		
		return 0;
	}
	
	public boolean podeGerar()
	{
		if(!ativo)
		{
			System.out.println("Não pode gerar ConfigSpam pois não está ativo.");
			return false;
		}
		
		return true;
	}
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public LocalDate getUltimoEnvio()
	{
		return ultimoEnvio;
	}

	public void setUltimoEnvio(LocalDate ultimoEnvio)
	{
		this.ultimoEnvio = ultimoEnvio;
	}

	public boolean isAtivo()
	{
		return ativo;
	}

	public void setAtivo(boolean ativo)
	{
		this.ativo = ativo;
	}

	public int getDiasIntervalo()
	{
		return diasIntervalo;
	}

	public void setDiasIntervalo(int diasIntervalo)
	{
		this.diasIntervalo = diasIntervalo;
	}

	public int getQtnNivel1()
	{
		return qtnNivel1;
	}

	public void setQtnNivel1(int qtnNivel1)
	{
		this.qtnNivel1 = qtnNivel1;
	}

	public int getQtnNivel2()
	{
		return qtnNivel2;
	}

	public void setQtnNivel2(int qtnNivel2)
	{
		this.qtnNivel2 = qtnNivel2;
	}

	public int getQtnNivel3()
	{
		return qtnNivel3;
	}

	public void setQtnNivel3(int qtnNivel3)
	{
		this.qtnNivel3 = qtnNivel3;
	}

	public List<ProgramacaoSpam> getProgramacoesSpam()
	{
		return programacoesSpam;
	}

	public void setProgramacoesSpam(List<ProgramacaoSpam> programacoesSpam)
	{
		this.programacoesSpam = programacoesSpam;
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
		ConfigSpam other = (ConfigSpam) obj;
		return Objects.equals(id, other.id);
	}

}
