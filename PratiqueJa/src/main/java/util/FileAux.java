package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileAux
{
	public static void gravarFile(String pasta,String nome,byte[] bytes)
	{
		System.out.println("pasta: "+pasta);
		File theDir = new File(pasta);
		System.out.println("theDir: "+theDir.getPath());
		System.out.println("theDir.exists(): "+theDir.exists());
		if(!theDir.exists())
		{
			boolean criou= theDir.mkdirs();
			System.out.println("conseguiu criar a pasta: "+criou);
		}
		System.out.println("theDir.exists(): "+theDir.exists());

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
			e.printStackTrace();
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
