package bean.instagram;

import java.io.Serializable;

public class EnvioPost implements Runnable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private EnvioPostBean envioPostBean;
	
	public EnvioPost(EnvioPostBean envioPostBean)
	{
		super();
		this.envioPostBean = envioPostBean;
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
			envioPostBean.enviarProgramacao();
			dormir(3600000);
//			dormir(5000);
		}
    }
}
