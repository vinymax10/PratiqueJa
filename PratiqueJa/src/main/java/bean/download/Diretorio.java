package bean.download;

import java.io.File;
import java.io.Serializable;

import modelo.configuracao.ConfigLatex;
import modelo.configuracao.SistemaOperacional;

public class Diretorio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ConfigLatex configLatex;
	
	private String diretorio;
	
	public Diretorio(ConfigLatex configLatex, String diretorio)
	{
		super();
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
			for(File file : files)
			{
				file.delete();
			}
		}
	}
	
//	public String criarDiretorio()
//	{
//		diretorio=poolNomesBean.gerarDiretorio();
//		return diretorio;
//	}
//	
//	public void freeDiretorio()
//	{
//		if(configLatex.isRemoveDiretorios())
//			removerEndereco(configLatex.getEndereco());
//		
//		poolNomesBean.freeDiretorio(diretorio);
//	}
	
	
	
	
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
		return converte(getEndereco()+"/logo2.png");
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
			return texto.replaceAll("/", "\\\\");
		}
		return texto;	
	}
	
	public ConfigLatex getConfigLatex() {
		return configLatex;
	}

	public void setConfigLatex(ConfigLatex configLatex) {
		this.configLatex = configLatex;
	}

	public String getDiretorio()
	{
		return diretorio;
	}

	public void setDiretorio(String diretorio)
	{
		this.diretorio = diretorio;
	}

}