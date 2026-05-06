package bean.teste;

import java.io.Serializable;

public class Conometro implements Runnable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private FazerTesteBean fazerTesteBean;
	
	public Conometro(FazerTesteBean fazerTesteBean)
	{
		super();
		this.fazerTesteBean = fazerTesteBean;
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
		dormir(1000);
		while(	!fazerTesteBean.getTeste().isRealizado()
				&&fazerTesteBean.getTeste().getTempo()>0)
		{
			fazerTesteBean.reduzTempoTeste();
			dormir(1000);
		}
		fazerTesteBean.finalizarCronometro();

    }
}
