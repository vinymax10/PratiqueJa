package Bean.Teste;

import java.io.Serializable;
import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import Bean.Teste.Filtro.FiltroTeste;
import DAO.Teste.ResultadoTesteDAO;
import DAO.Teste.TesteDAO;
import Infra.Mensagem;
import Modelo.Matematica.Conta;
import Modelo.Teste.ConteudoTeste;
import Modelo.Teste.EtapaTeste;
import Modelo.Teste.ResultadoTeste;
import Modelo.Teste.Teste;

@Named
@SessionScoped
public class FazerTesteBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private TesteDAO testeDAO;
	
	@Inject
	private Teste teste;
	private String nome = "Teste";
	
	private DecimalFormat deci = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.FRANCE));

	@Inject
	private FiltroTeste filtroTeste;

	private Long id;

	private Thread thread;
	private Conometro conometro;
	
	@Inject 
	@Push(channel = "cronometro")
    private PushContext push;
	
	@Inject 
	private ResultadoTesteDAO resultadoTesteDAO;
	
	private void clone(Teste teste)
	{
		Teste clone=new Teste();
		Conta conta;
		int index=1;
		try
		{
			for(ConteudoTeste conteudoTeste : teste.getTestePadrao().getConteudosTeste())
			{
				EtapaTeste etapaTeste=new EtapaTeste();
				etapaTeste.setTeste(clone);
				etapaTeste.setExercicioPadrao(conteudoTeste.getExercicioPadrao());
				
				for(int i = 0; i < conteudoTeste.getQuantidade(); i++)
				{
					do
					{
						conta = (Conta) Class.forName(conteudoTeste.getExercicioPadrao().getClasse())
						.getConstructor(Integer.TYPE).newInstance(index);
						conta.setEtapaTeste(etapaTeste);
						conta.setTipoExercicio(conteudoTeste.getExercicioPadrao().getTipoExercicio());
					}
					while(etapaTeste.getContas().contains(conta));
					etapaTeste.getContas().add(conta);
					index++;
				}
				clone.getEtapasTeste().add(etapaTeste);
			}
			clone.setDuracao(teste.getDuracao());
			clone.setTempo(teste.getDuracao()*60);
			clone.setNotaMinima(teste.getNotaMinima());
			clone.setUsuario(teste.getUsuario());
			clone.setTestePadrao(teste.getTestePadrao());
			clone.setRepetirAtePassar(teste.isRepetirAtePassar());

			testeDAO.salvar(clone);
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void update()
	{
		try
		{
			testeDAO.salvar(teste);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a " + nome);
		}
	}
	
	public void reduzTempoTeste()
	{
		teste.reduzTempo();
		testeDAO.salvarSL(teste);
		push.send("update");
	}

	public void finalizarCronometro()
	{
		if(!teste.isRealizado())
			push.send("finalizar");
	}
	
	public synchronized String finalizar()
	{
		if(!teste.isRealizado())
		{
			int numCorretas = 0;
			int totalContas=0;
			for(EtapaTeste etapaTeste : teste.getEtapasTeste())
			{
				totalContas+=etapaTeste.getContas().size();
				
				for(Conta conta : etapaTeste.getContas())
				{
					conta.setRespondida(true);
					if(conta.isCorreta())
						numCorretas++;
				}
			}
			teste.setRealizado(true);
			teste.setRealizacao(LocalDate.now());
			teste.setNota(100*(double) numCorretas / totalContas);
			
			ResultadoTeste resultadoTeste = new ResultadoTeste();
			resultadoTeste.setNota(teste.getNota());
			resultadoTeste.setRealizacao(LocalDate.now());
			resultadoTeste.setTestePadrao(teste.getTestePadrao());
			resultadoTeste.setUsuario(teste.getUsuario());
			resultadoTesteDAO.salvar(resultadoTeste);
			
			if(teste.isRepetirAtePassar()&&teste.aprovado())
				clone(teste);
			
			try
			{
				testeDAO.salvar(teste);
//				thread.stop();
				Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " Finalizado com sucesso\nNota: " + deci.format(teste.getNota()));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível finalizar o " + nome);
			}
		}
		return "";
	}

	@PostConstruct
	public void init()
	{
		if(id != null&&!temTesteEmExecucao())
		{
			teste = testeDAO.carrega(id);
			if(!teste.isRealizado())
			{
				teste.setRealizacao(LocalDate.now());
				conometro=new Conometro(this);
				thread = new Thread(conometro);
				thread.start();
			}
		}
	}

	private boolean temTesteEmExecucao()
	{
		if(thread == null)
			return false;
		
		if(thread.getState()==State.TERMINATED)
			return false;
		
		return true;
	}
	
	public Teste getTeste()
	{
		return teste;
	}

	public void setTeste(Teste teste)
	{
		this.teste = teste;
	}


	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public FiltroTeste getFiltroTeste()
	{
		return filtroTeste;
	}

	public void setFiltroTeste(FiltroTeste filtroTeste)
	{
		this.filtroTeste = filtroTeste;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

}