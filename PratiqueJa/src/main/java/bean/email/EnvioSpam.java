package bean.email;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EnvioSpam implements Runnable, Serializable
{
	private static final long serialVersionUID = 1L;

	private EnvioSpamBean envioSpamBean;

	public EnvioSpam(EnvioSpamBean envioSpamBean)
	{
		super();
		this.envioSpamBean = envioSpamBean;
	}
	private void dormir(int time)
	{
		try {
			Thread.sleep(time);
		}
		catch (InterruptedException e) {
			System.out.println("Acordado para trabalhar");
		}
	}

	@Override
    public void run()
	{
		while(true)
		{
			if(LocalDateTime.now().getHour()>8)
				envioSpamBean.enviarProgramacao();

			dormir(3600000);
//			dormir(1000);
		}
    }
}
