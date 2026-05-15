package service.configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import util.FileAux;
import dao.configuracao.ConfigLatexDAO;
import modelo.configuracao.ConfigLatex;
import bean.download.Diretorio;

@ApplicationScoped
public class DiretorioService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<String> diretorios = new ArrayList<>();

	private ConfigLatex configLatex;

	@Inject
	private ConfigLatexDAO configLatexDAO;

	public Diretorio criarDiretorio()
	{
		String diretorioStr = gerarDiretorio();
		diretorios.add(diretorioStr);
		return new Diretorio(configLatex, diretorioStr);
	}

	public Diretorio criarDiretorioSemReserva()
	{
		return new Diretorio(configLatex, "");
	}

	public void freeDiretorio(Diretorio diretorio)
	{
		if(configLatex.isRemoveDiretorios())
			FileAux.limparPasta(configLatex.getEndereco());

		diretorios.remove(diretorio.getDiretorio());
	}

	private String gerarDiretorio()
	{
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		StringBuilder sb = new StringBuilder(20);
		for(int i = 0; i < 20; i++)
		{
			char c = (char)('A' + rand.nextInt(26));
			sb.append(rand.nextBoolean() ? c : Character.toLowerCase(c));
		}
		return sb.toString();
	}

	@PostConstruct
	public void init()
	{
		configLatex = configLatexDAO.buscar();
		if(configLatex == null)
		{
			configLatex = new ConfigLatex();
			configLatex.setEndereco("C:\\Users\\maximovrm\\Documents\\latex");
			configLatex.setNome("exercicio");
			configLatexDAO.salvar(configLatex);
		}
	}
}
