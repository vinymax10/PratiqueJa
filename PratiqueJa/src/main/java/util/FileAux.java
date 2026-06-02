package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileAux
{
	private static final Logger LOG = LoggerFactory.getLogger(FileAux.class);

	public static void gravarFile(String pasta,String nome,byte[] bytes)
	{
		LOG.debug("gravarFile pasta={} ({})", pasta, new File(pasta).getPath());
		File theDir = new File(pasta);
		if(!theDir.exists())
		{
			boolean criou= theDir.mkdirs();
			if(!criou)
				LOG.warn("Não foi possível criar a pasta: {}", theDir.getPath());
		}

		File outputFile = new File(pasta+nome);
		OutputStream outputStream;
		try
		{
			outputStream = new FileOutputStream(outputFile);
			outputStream.write(bytes);
			outputStream.close();
		}
		catch(IOException e)
		{
			LOG.error("Erro ao gravar arquivo {}", outputFile.getPath(), e);
		}
	}
	
	
	public static void limparPasta(String endereco)
	{
		File theDir = new File(endereco);
		if(theDir.isDirectory())
		{
			File[] files = theDir.listFiles();
			for(File file : files)
			{
				file.delete();
			}
			theDir.delete();
		}
	}
	
}
