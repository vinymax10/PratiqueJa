package Modelo.Spam;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import Modelo.Entidade;
import Modelo.AssuntoCurso.AssuntoCurso;

@Entity
public class ProgramacaoSpam implements Serializable, Entidade
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private LocalDate data;
	
	private int ordem;
	
	@ManyToOne
	private AssuntoCurso assuntoCurso;
	
	@ManyToOne
	private ConfigSpam configSpam;
	
	public ProgramacaoSpam clone()
	{
		ProgramacaoSpam clone=new ProgramacaoSpam();
		clone.assuntoCurso=this.assuntoCurso;
		clone.configSpam=this.configSpam;
		clone.data=this.data;
		clone.ordem=this.ordem;
		return clone;
	}
	
	
	public void updateData()
	{
		LocalDate ultimoEnvio=configSpam.getUltimoEnvio();
		
		data=ultimoEnvio.plusDays((ordem+1)*configSpam.getDiasIntervalo());
	}
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public LocalDate getData()
	{
		return data;
	}

	public void setData(LocalDate data)
	{
		this.data = data;
	}

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso assuntoCurso)
	{
		this.assuntoCurso = assuntoCurso;
	}

	public int getOrdem()
	{
		return ordem;
	}

	public void setOrdem(int ordem)
	{
		this.ordem = ordem;
	}

	public ConfigSpam getConfigSpam()
	{
		return configSpam;
	}

	public void setConfigSpam(ConfigSpam configSpam)
	{
		this.configSpam = configSpam;
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
		ProgramacaoSpam other = (ProgramacaoSpam) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString()
	{
		return "ProgramacaoSpam [id=" + id + ", data=" + data + ", ordem=" + ordem + ", assuntoCurso=" + assuntoCurso + "]";
	}

}
