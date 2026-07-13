package bean.download;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import lombok.Data;
import modelo.configuracao.Config;

@Data
public class Diretorio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Config config;

	private String diretorio;

	public Diretorio(Config config, String diretorio)
	{
		this.config = config;
		this.diretorio = diretorio;
	}

	public void limparDiretorios()
	{
		limparPasta(caminhoBase());
		limparPasta(caminhoBase().resolve(config.getImagens()));
		limparPasta(caminhoBase().resolve(config.getImagensResolucao()));
	}

	/** Garante que a pasta exista e remove os arquivos diretamente dentro dela (não recursivo). */
	private void limparPasta(Path pasta)
	{
		try
		{
			Files.createDirectories(pasta);
			try(Stream<Path> arquivos = Files.list(pasta))
			{
				arquivos.filter(Files::isRegularFile).forEach(this::apagar);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void apagar(Path arquivo)
	{
		try
		{
			Files.delete(arquivo);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getEnderecoExercicioPNG()
	{
		return caminhoBase().resolve(config.getNome() + "-1.png").toString();
	}

	public String getEnderecoResolucaoPNG()
	{
		return caminhoBase().resolve(config.getNome() + "-2.png").toString();
	}

	public String getEnderecoLogo()
	{
		return caminhoBase().resolve("logo.png").toString();
	}

	public String getEnderecoBackground()
	{
		return caminhoBase().resolve("background.png").toString();
	}

	public String getEnderecoTex()
	{
		return caminhoBase().resolve(config.getNome() + ".tex").toString();
	}

	public String getEndereco()
	{
		return caminhoBase().toString();
	}

	/** Termina com separador: usado pelos geradores de post via concatenação direta com o nome do arquivo. */
	public String getEnderecoImagens()
	{
		return caminhoBase().resolve(config.getImagens()).toString() + File.separator;
	}

	public String getEnderecoOutputLog()
	{
		return caminhoBase().resolve("outputLog.log").toString();
	}

	private Path caminhoBase()
	{
		return Path.of(config.getEnderecoLatex(), diretorio);
	}
}
