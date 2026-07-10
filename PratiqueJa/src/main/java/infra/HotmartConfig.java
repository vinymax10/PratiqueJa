package infra;

public class HotmartConfig
{
	// Token enviado pelo Hotmart no header X-HOTMART-HOTTOK de cada webhook
	// (conta Hotmart > Ferramentas > Webhook). Preencher ao cadastrar o produto.
	static String hottok = "lTHYs7ywAc1m1PYTEQSuCA5SAAvN7K69818a4a-d366-4b41-993c-6570b4c2682e";

	public static boolean hottokValido(String recebido)
	{
		return hottok != null && !hottok.isBlank() && hottok.equals(recebido);
	}

	private HotmartConfig()
	{
	}
}
