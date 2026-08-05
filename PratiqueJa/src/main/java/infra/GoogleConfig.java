package infra;

public class GoogleConfig
{
	// Client ID do projeto no Google Cloud (OAuth 2.0 Client IDs). Não é segredo — vai no
	// front (js/google.js) —, mas o servidor precisa dele para conferir o "aud" do ID token:
	// é o que garante que o token foi emitido para ESTE site, e não para outro app qualquer.
	static String clientId = "404469863896-q3dl3oechaqfocos3ho3k986igu3g6o8.apps.googleusercontent.com";

	public static String getClientId()
	{
		return clientId;
	}

	private GoogleConfig()
	{
	}
}
