package bean.download;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import util.FileAux;
import dao.configuracao.ConfigLatexDAO;
import modelo.configuracao.ConfigLatex;

@Named
@ApplicationScoped
public class PoolNomesBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	List<String> diretorios=new ArrayList<String>();
	
	private ConfigLatex configLatex;
	
	@Inject
	private ConfigLatexDAO configLatexDAO;
	
	public Diretorio criarDiretorio()
	{
		String diretorioStr=gerarDiretorio();
		diretorios.add(diretorioStr);
		Diretorio diretorio=new Diretorio(configLatex,diretorioStr);
		return diretorio;
	}
	
	public Diretorio criarDiretorioSemReserva()
	{
		Diretorio diretorio=new Diretorio(configLatex,"");
		return diretorio;
	}
	
	public void freeDiretorio(Diretorio diretorio)
	{
		if(configLatex.isRemoveDiretorios())
			FileAux.limparPasta(configLatex.getEndereco());

		diretorios.remove(diretorio.getDiretorio());
	}
	
//	public void freeDiretorio(String diretorio)
//	{
//		diretorios.remove(diretorio);
//	}
	
//	public String criarDiretorio()
//	{
//		String diretorio=gerarDiretorio();
//		diretorios.add(diretorio);
//
//		return diretorio;
//	}
	
	public String gerarDiretorio()
	{
		Random rand=new Random();
		String diretorio="";
		int size=20;
		String caracter;
		for(int i = 0; i < size; i++)
		{
			caracter=String.valueOf((char)(65+rand.nextInt(26)));
			if(rand.nextBoolean())
				caracter=caracter.toLowerCase();
			
			diretorio+=caracter;
		}
		return diretorio;
	}
	
	@PostConstruct
	public void init()
	{
		configLatex = configLatexDAO.buscar();
		if(configLatex==null)
		{
			configLatex=new ConfigLatex();
			configLatex.setEndereco("C:\\Users\\maximovrm\\Documents\\latex");
			configLatex.setNome("exercicio");
			configLatexDAO.salvar(configLatex);
		}
	}
}