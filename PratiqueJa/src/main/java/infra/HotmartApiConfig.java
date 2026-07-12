package infra;

public class HotmartApiConfig
{
	// Credenciais do App Hotmart (Hotmart > Ferramentas > Desenvolvedor > Credenciais),
	// com escopo de cancelamento de assinatura. Diferente do hottok do webhook (HotmartConfig).
	static String clientId = "bf2fbfd7-ac18-4be3-bfcf-56caaa6f2809";
	static String clientSecret = "d1cfa945-2bf0-4391-af52-27eeda076ea0";

	public static String getClientId()
	{
		return clientId;
	}

	public static String getClientSecret()
	{
		return clientSecret;
	}

	public static boolean configurado()
	{
		return clientId != null && !clientId.isBlank() && clientSecret != null && !clientSecret.isBlank();
	}

	private HotmartApiConfig()
	{
	}
}
