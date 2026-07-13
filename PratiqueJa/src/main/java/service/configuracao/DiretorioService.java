package service.configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import util.FileAux;
import dao.configuracao.ConfigDAO;
import modelo.configuracao.Config;
import bean.download.Diretorio;

@ApplicationScoped
public class DiretorioService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<String> diretorios = new ArrayList<>();

	private Config config;

	@Inject
	private ConfigDAO configDAO;

	public Diretorio criarDiretorio()
	{
		String diretorioStr = gerarDiretorio();
		diretorios.add(diretorioStr);
		return new Diretorio(config, diretorioStr);
	}

	public Diretorio criarDiretorioSemReserva()
	{
		return new Diretorio(config, "");
	}

	public void freeDiretorio(Diretorio diretorio)
	{
		if(config.isRemoveDiretorios())
			FileAux.limparPasta(config.getEnderecoLatex());

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
		config = configDAO.buscar();
		if(config == null)
		{
			// Endereço-raiz ainda não configurado; fica em branco até o admin defini-lo em
			// /administracao/configuracao/config/config.xhtml — os demais caminhos derivam dele.
			config = new Config();
			configDAO.salvar(config);
		}
	}
}
