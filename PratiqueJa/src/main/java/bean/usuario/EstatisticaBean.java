package bean.usuario;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import dao.usuario.ControleAcessoDAO;
import dao.usuario.UsuarioDAO;
import infra.DadosGrafico;
import infra.GraficoPeriodo;
import modelo.usuario.Acesso;
import modelo.usuario.ControleAcesso;
import modelo.usuario.Usuario;

@Named
@SessionScoped
public class EstatisticaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ControleAcessoDAO controleAcessoDAO;

	private Usuario usuario;
	
	@Inject
	private UsuarioBean usuarioBean;
	
	private UsuarioDAO usuarioDAO;
	
	private void setUsuario()
	{
		usuario = usuarioBean.getUsuario();
		usuarioDAO=usuarioBean.getUsuarioDAO();
		if(usuario!=null)
			usuario = usuarioDAO.carrega(usuario.getId());
	}
	
	public String grafico(Periodo periodo, 
	AtributoControleAcesso atributo, String titulo)
	{
		setUsuario();
		System.out.println("periodo: "+periodo+" atributoControleAcesso: "+atributo);
		Field [] atribuilts=ControleAcesso.class.getDeclaredFields();
		Field field=atribuilts[atributo.getValor()];
		field.setAccessible(true);
		
		switch(periodo)
		{
			case semanal: return graficoControleAcessoSemanal(titulo, field);
			case mensal: return graficoControleAcessoMensal(titulo, field);
			case anual: return graficoControleAcessoAnual(titulo, field);

			default: return graficoControleAcessoSemanal(titulo, field);
		}
	}
	
	public String graficoControleAcessoAnual(String titulo, Field field)
	{
		DadosGrafico dadosGrafico = new DadosGrafico();
		dadosGrafico.setTitutlo(titulo);

		LocalDate fim = LocalDate.now();
		LocalDate inicio = fim.plusDays(-365);

		List<ControleAcesso> controlesAcessos = controleAcessoDAO.listaControleAcesso(usuario, inicio, fim);

		LocalDate[] datas = new LocalDate[12];
		for(int i = 0; i < datas.length; i++)
		{
			datas[i] = LocalDate.now();
			datas[i] = datas[i].plusDays(-inicio.getDayOfMonth() + 1);
			datas[i] = datas[i].plusMonths(-10 + i);
		}

		dadosGrafico.setValues(histogramaControleAcesso(controlesAcessos, datas, field));

		List<String> labels = new ArrayList<>();
		for(int i = 0; i < datas.length - 1; i++)
			labels.add(datas[i].getMonthValue() + "/" + datas[i].getYear());

		dadosGrafico.setLabels(labels);

		return GraficoPeriodo.criarGraficoBarras(dadosGrafico);
	}
	
	public String graficoControleAcessoMensal(String titulo, Field field)
	{
		DadosGrafico dadosGrafico = new DadosGrafico();
		dadosGrafico.setTitutlo(titulo);

		int periodo = 7;
		int numeroBarras = 12;

		LocalDate fim = LocalDate.now();
		LocalDate inicio = fim.plusDays(-periodo * numeroBarras);

		List<ControleAcesso> controlesAcessos = controleAcessoDAO.listaControleAcesso(usuario, inicio, fim);

		LocalDate[] datas = new LocalDate[numeroBarras + 1];
		for(int i = 0; i < datas.length; i++)
		{
			datas[i] = LocalDate.now();
			datas[i] = datas[i].plusDays(1 - (periodo * (datas.length - 1 - i)));
		}

		dadosGrafico.setValues(histogramaControleAcesso(controlesAcessos, datas, field));

		List<String> labels = new ArrayList<>();

		for(int i = 0; i < datas.length - 1; i++)
			labels.add(datas[i].getDayOfMonth() + "/" + datas[i].getMonthValue());

		dadosGrafico.setLabels(labels);

		return GraficoPeriodo.criarGraficoBarras(dadosGrafico);
	}
	
	public String graficoControleAcessoSemanal(String titulo, Field field)
	{
		DadosGrafico dadosGrafico = new DadosGrafico();
		dadosGrafico.setTitutlo(titulo);

		int periodo = 1;
		int numeroBarras = 12;

		LocalDate fim = LocalDate.now();
		LocalDate inicio = fim.plusDays(-periodo * numeroBarras);

		List<ControleAcesso> controlesAcessos = controleAcessoDAO.listaControleAcesso(usuario, inicio, fim);

		LocalDate[] datas = new LocalDate[numeroBarras + 1];
		for(int i = 0; i < datas.length; i++)
		{
			datas[i] = LocalDate.now();
			datas[i] = datas[i].plusDays(1 - (periodo * (datas.length - 1 - i)));
		}
		
		field.setAccessible(true);
		dadosGrafico.setValues(histogramaControleAcesso(controlesAcessos, datas, field));

		List<String> labels = new ArrayList<>();
		for(int i = 0; i < datas.length - 1; i++)
			labels.add(datas[i].getDayOfMonth() + "/" + datas[i].getMonthValue());

		dadosGrafico.setLabels(labels);

		return GraficoPeriodo.criarGraficoBarras(dadosGrafico);
	}
	
	private List<Number> histogramaControleAcesso(List<ControleAcesso> controlesAcessos, 
	LocalDate[] dates, Field field)
	{
		List<Number> values = new ArrayList<>();

		for(int i = 0; i < dates.length - 1; i++)
			values.add(countControleAcesso(controlesAcessos, dates[i], dates[i + 1], field));

		return values;
	}
	
	private int countControleAcesso(List<ControleAcesso> controlesAcessos, 
	LocalDate a, LocalDate b, Field field)
	{
		int count = 0;
		for(ControleAcesso controleAcesso : controlesAcessos)
		{
			if((controleAcesso.getData().isAfter(a) || controleAcesso.getData().isEqual(a)) 
			&& controleAcesso.getData().isBefore(b))
			{
				try
				{
					count += field.getInt(controleAcesso);
				}
				catch(IllegalArgumentException | IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}
		return count;
	}	
	
//	---------------Acesso---------------
	public String graficoAcessoSemanal()
	{
		setUsuario();
		
		DadosGrafico dadosGrafico = new DadosGrafico();
		dadosGrafico.setTitutlo("Frequência diária");

		int periodo = 1;
		int numeroBarras = 12;

		LocalDate fim = LocalDate.now();
		LocalDate inicio = fim.plusDays(-periodo * numeroBarras);

		List<Acesso> acessos = usuarioDAO.listaAcessos(usuario, inicio, fim);

		LocalDate[] datas = new LocalDate[numeroBarras + 1];
		for(int i = 0; i < datas.length; i++)
		{
			datas[i] = LocalDate.now();
			datas[i] = datas[i].plusDays(1 - (periodo * (datas.length - 1 - i)));
		}

		dadosGrafico.setValues(histogramaAcesso(acessos, datas));

		List<String> labels = new ArrayList<>();
		for(int i = 0; i < datas.length - 1; i++)
			labels.add(datas[i].getDayOfMonth() + "/" + datas[i].getMonthValue());

		dadosGrafico.setLabels(labels);

		return GraficoPeriodo.criarGraficoBarras(dadosGrafico);
	}

	public String graficoAcessoMensal()
	{
		setUsuario();
		DadosGrafico dadosGrafico = new DadosGrafico();
		dadosGrafico.setTitutlo("Frequência Semanal");

		int periodo = 7;
		int numeroBarras = 12;

		LocalDate fim = LocalDate.now();
		LocalDate inicio = fim.plusDays(-periodo * numeroBarras);

		List<Acesso> acessos = usuarioDAO.listaAcessos(usuario, inicio, fim);

		LocalDate[] datas = new LocalDate[numeroBarras + 1];
		for(int i = 0; i < datas.length; i++)
		{
			datas[i] = LocalDate.now();
			datas[i] = datas[i].plusDays(1 - (periodo * (datas.length - 1 - i)));
		}

		dadosGrafico.setValues(histogramaAcesso(acessos, datas));

		List<String> labels = new ArrayList<>();

		for(int i = 0; i < datas.length - 1; i++)
			labels.add(datas[i].getDayOfMonth() + "/" + datas[i].getMonthValue());

		dadosGrafico.setLabels(labels);

		return GraficoPeriodo.criarGraficoBarras(dadosGrafico);
	}

	public String graficoAcessoAnual()
	{
		setUsuario();
		DadosGrafico dadosGrafico = new DadosGrafico();
		dadosGrafico.setTitutlo("Frequência Mensal");

		LocalDate fim = LocalDate.now();
		LocalDate inicio = fim.plusDays(-365);

		List<Acesso> acessos = usuarioDAO.listaAcessos(usuario, inicio, fim);

		LocalDate[] datas = new LocalDate[12];
		for(int i = 0; i < datas.length; i++)
		{
			datas[i] = LocalDate.now();
			datas[i] = datas[i].plusDays(-inicio.getDayOfMonth() + 1);
			datas[i] = datas[i].plusMonths(-10 + i);
		}

		dadosGrafico.setValues(histogramaAcesso(acessos, datas));

		List<String> labels = new ArrayList<>();
		for(int i = 0; i < datas.length - 1; i++)
			labels.add(datas[i].getMonthValue() + "/" + datas[i].getYear());

		dadosGrafico.setLabels(labels);

		return GraficoPeriodo.criarGraficoBarras(dadosGrafico);
	}

	private List<Number> histogramaAcesso(List<Acesso> acessos, LocalDate[] dates)
	{
		List<Number> values = new ArrayList<>();

		for(int i = 0; i < dates.length - 1; i++)
			values.add(countAcesso(acessos, dates[i], dates[i + 1]));

		return values;
	}

	private int countAcesso(List<Acesso> acessos, LocalDate a, LocalDate b)
	{
		int count = 0;
		for(Acesso acesso : acessos)
		{
			if((acesso.getData().isAfter(a) || acesso.getData().isEqual(a)) && acesso.getData().isBefore(b))
				count += acesso.getMinutos();
		}
		return count;
	}	
}
