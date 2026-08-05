// O ID token vai inteiro para o servidor, que confere assinatura, aud, iss e validade
// (GoogleTokenService). Decodificar aqui e mandar e-mail/sub soltos não provaria nada:
// qualquer um poderia postar os mesmos campos e entrar na conta de outra pessoa.
function handleCredentialResponse(response)
{
    sendDadosUser([
        {
            name : 'credential',
            value : response.credential
        }
    ]);
}

window.onload = function () {
  google.accounts.id.initialize({
    client_id: "404469863896-q3dl3oechaqfocos3ho3k986igu3g6o8.apps.googleusercontent.com",
    callback: handleCredentialResponse
  });
  google.accounts.id.renderButton(
    document.getElementById("buttonDiv"),
    { theme: "outline", size: "large" }  // customization attributes
  );
  google.accounts.id.prompt(); // also display the One Tap dialog
}
