package bean.download;

import java.io.File;
import java.io.Serializable;

import lombok.Data;
import modelo.configuracao.Config;
import modelo.configuracao.SistemaOperacional;

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
		gerarDiretorios(converte(config.getEnderecoLatex()+"/"+diretorio+"/"));
		gerarDiretorios(converte(config.getEnderecoLatex()+"/"+diretorio+"/"+config.getImagens()));
		gerarDiretorios(converte(config.getEnderecoLatex()+"/"+diretorio+"/"+config.getImagensResolucao()));
	}
	
	private void gerarDiretorios(String endereco)
	{
		File theDir = new File(endereco);
		if(!theDir.exists())
			theDir.mkdirs();

		if(theDir.isDirectory())
		{
			File[] files = theDir.listFiles();
			if(files != null)
				for(File file : files)
					file.delete();
		}
	}
	
	public String getEnderecoPdf()
	{
		return converte(getEndereco()+"/"+config.getNome()+".pdf");
	}
	
	public String getEnderecoExercicioPNG()
	{
		return converte(getEndereco()+"/"+config.getNome()+"-1.png");
	}

	public String getEnderecoResolucaoPNG()
	{
		return converte(getEndereco()+"/"+config.getNome()+"-2.png");
	}
	
	public String getEnderecoLogo()
	{
		return converte(getEndereco()+"/logo.png");
	}
	
	public String getEnderecoBackground()
	{
		return converte(getEndereco()+"/background.png");
	}
	
	public String getEnderecoTex()
	{
		return converte(getEndereco()+"/"+config.getNome()+".tex");
	}
	
	public String getEndereco()
	{
		return converte(config.getEnderecoLatex()+"/"+diretorio);
	}
	
	public String getEndBackgroundServidor()
	{
		return converte(config.getEndBackground());
	}
	
	public String getEnderecoImagens()
	{
		return converte(getEndereco()+"/"+config.getImagens()+"/");
	}
	
	public String getEnderecoImagensResolucao()
	{
		return converte(getEndereco()+"/"+config.getImagensResolucao()+"/");
	}
	
	public String getEnderecoOutputLog()
	{
		return converte(getEndereco()+"/outputLog.log");
	}
	
	private String converte(String texto)
	{
		if(config.getSistemaOperacional()==SistemaOperacional.Windows)
		{
			return texto.replace("/", "\\");
		}
		return texto;	
	}
	
}