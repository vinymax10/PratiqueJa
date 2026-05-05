package Infra;

import org.apache.tika.Tika;
import org.primefaces.model.file.UploadedFile;

public class ValidacaoFile
{
	public static boolean valida(UploadedFile uploadedFile) throws ExtensaoFileException
	{
		String nome = uploadedFile.getFileName();
		String list[] = nome.split("\\.");
		String extensao;
		
		boolean validadoExtensao=false;
		if(list.length > 1)
		{
			extensao = list[list.length - 1].toLowerCase().trim();
			if(extensao.equals("jpg") || extensao.equals("png") || extensao.equals("jpeg") || extensao.equals("pdf"))
				validadoExtensao = true;
		}
		
		boolean validadoContentType=false;
		String contentType=uploadedFile.getContentType();
		
		if(contentType.equals("image/jpeg")||contentType.equals("image/png")||contentType.equals("application/pdf"))
			validadoContentType=true;
		
	    Tika tika = new Tika();
	    String mimeType = tika.detect(uploadedFile.getContent());

		boolean validadoMime=false;
		if(mimeType.equals("image/jpeg")||mimeType.equals("image/png")||mimeType.equals("application/pdf"))
			validadoMime=true;
		
		if(!validadoContentType||!validadoExtensao||!validadoMime)
			throw new ExtensaoFileException("Tipo do arquivo incompatível. Tente utilizar os seguintes formatos: pdf, jpg, jpeg e png");
		
		return validadoContentType&&validadoExtensao&&validadoMime;
	}

	public static boolean validaImage(UploadedFile uploadedFile) throws ExtensaoFileException
	{
		String nome = uploadedFile.getFileName();
		String list[] = nome.split("\\.");
		String extensao;
		if(list.length > 1)
		{
			extensao = list[1].toLowerCase().trim();
			if(extensao.equals("jpg") || extensao.equals("png") || extensao.equals("jpeg"))
				return true;
		}

		throw new ExtensaoFileException("Extensão do arquivo incompatível. Tente utilizar os seguintes formatos: jpg, jpeg e png");
	}
	
	
//	Este metodo extrai o tipo do arquivo e retorna o mimeType dele 
	
	public static String getMimeType(String nome) 
	{
		String list[] = nome.split("\\.");
		String extensao;
		if(list.length > 1)
		{
			extensao = list[1].toLowerCase().trim();
			switch(extensao) 
			{
				case "pdf": return "application/pdf";
				
				case "jpeg": return "image/jpeg";
				
				case "png": return "image/jpeg";
				
				case "jpg": return "image/jpeg";
			}
		}
		
		return ""; 
	}
		
		

}
