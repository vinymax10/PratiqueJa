package Bean.Email;

import java.io.Serializable;

import Infra.CommonsEmail;
import Modelo.Email;

public class EnvioEmail implements Runnable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private EmailBean emailBean;
	
	public EnvioEmail(EmailBean emailBean)
	{
		super();
		this.emailBean = emailBean;
	}
	private void dormir(int time)
	{
		try {
			Thread.sleep(time);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void run() 
	{
		Email email;
		while(true)
		{
			if(emailBean.getEmails().size()>0)
			{
				for(int i = 0; i < emailBean.getEmails().size(); i++)
				{
					email=emailBean.getEmails().get(i);
					System.out.println("tentando enviar email: "+email);
					boolean enviou=CommonsEmail.mandarMultiPartEmail(email);
					if(enviou||email.getTentativaEnvio()>2000)
					{
						emailBean.remover(email);
						i--;
					}
					else
					{
						email.setTentativaEnvio(email.getTentativaEnvio()+1);
						emailBean.salvar(email);
					}
					
					dormir(email.getTempoEspera()*1000);
				}
			}
			
			dormir(60000);
		}
    }
}
