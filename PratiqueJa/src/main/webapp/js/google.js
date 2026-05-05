function handleCredentialResponse(response) 
{
	const responsePayload = jwt_decode(response.credential);

    console.log("ID: " + responsePayload.sub);
    console.log('Full Name: ' + responsePayload.name);
    console.log('Given Name: ' + responsePayload.given_name);
    console.log('Family Name: ' + responsePayload.family_name);
    console.log("Image URL: " + responsePayload.picture);
    console.log("Email: " + responsePayload.email);
    console.log(responsePayload);
	
    sendDadosUser ([ 
        {
            name : 'email',
            value : responsePayload.email
       },   
       {
         name : 'name',
         value : responsePayload.name
       },
       {
           name : 'sub',
           value : responsePayload.sub
       },
       {
           name : 'picture',
           value : responsePayload.picture
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