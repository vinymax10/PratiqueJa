package bean.download;

import java.io.File;
import java.io.Serializable;

import lombok.Data;
import modelo.configuracao.ConfigLatex;
import modelo.configuracao.SistemaOperacional;

@Data
public class Diretorio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ConfigLatex configLatex;
	
	private String diretorio;
	
	public Diretorio(ConfigLatex configLatex, String diretorio)
	{
		this.configLatex = configLatex;
		this.diretorio = diretorio;
	}

	public void limparDiretorios()
	{
		gerarDiretorios(converte(configLatex.getEndereco()+"/"+diretorio+"/"));
		gerarDiretorios(converte(configLatex.getEndereco()+"/"+diretorio+"/"+configLatex.getImagens()));
		gerarDiretorios(converte(configLatex.getEndereco()+"/"+diretorio+"/"+configLatex.getImagensResolucao()));
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
		return converte(getEndereco()+"/"+configLatex.getNome()+".pdf");
	}
	
	public String getEnderecoExercicioPNG()
	{
		return converte(getEndereco()+"/"+configLatex.getNome()+"-000001.png");
	}
	
	public String getEnderecoResolucaoPNG()
	{
		return converte(getEndereco()+"/"+configLatex.getNome()+"-000002.png");
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
		return converte(getEndereco()+"/"+configLatex.getNome()+".tex");
	}
	
	public String getEndereco()
	{
		return converte(configLatex.getEndereco()+"/"+diretorio);
	}
	
	public String getEndBackgroundServidor()
	{
		return converte(configLatex.getEndBackground());
	}
	
	public String getEnderecoImagens()
	{
		return converte(getEndereco()+"/"+configLatex.getImagens()+"/");
	}
	
	public String getEnderecoImagensResolucao()
	{
		return converte(getEndereco()+"/"+configLatex.getImagensResolucao()+"/");
	}
	
	public String getEnderecoOutputLog()
	{
		return converte(getEndereco()+"/outputLog.log");
	}
	
	private String converte(String texto)
	{
		if(configLatex.getSistemaOperacional()==SistemaOperacional.Windows)
		{
			return texto.replace("/", "\\");
		}
		return texto;	
	}
	
}