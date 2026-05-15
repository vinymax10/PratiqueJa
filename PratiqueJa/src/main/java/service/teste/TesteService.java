package service.teste;

import java.lang.reflect.InvocationTargetException;

import dao.teste.TesteDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.matematica.Conta;
import modelo.teste.ConteudoTeste;
import modelo.teste.EtapaTeste;
import modelo.teste.Teste;

@Named
@ApplicationScoped
public class TesteService
{
	@Inject
	private TesteDAO testeDAO;

	public void construirTeste(Teste teste)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException
	{
		Conta conta;
		int index = 1;
		for(ConteudoTeste conteudoTeste : teste.getTestePadrao().getConteudosTeste())
		{
			EtapaTeste etapaTeste = new EtapaTeste();
			etapaTeste.setTeste(teste);
			etapaTeste.setExercicioPadrao(conteudoTeste.getExercicioPadrao());

			for(int i = 0; i < conteudoTeste.getQuantidade(); i++)
			{
				do
				{
					conta = (Conta) Class.forName(conteudoTeste.getExercicioPadrao().getClasse())
						.getConstructor(Integer.TYPE).newInstance(index);
					conta.setEtapaTeste(etapaTeste);
					conta.setTipoExercicio(conteudoTeste.getExercicioPadrao().getTipoExercicio());
				}
				while(etapaTeste.getContas().contains(conta));
				etapaTeste.getContas().add(conta);
				index++;
			}
			teste.getEtapasTeste().add(etapaTeste);
		}

		teste.setDuracao(teste.getTestePadrao().getDuracao());
		teste.setTempo(teste.getTestePadrao().getDuracao() * 60);
		teste.setNotaMinima(teste.getTestePadrao().getNotaMinima());

		testeDAO.salvar(teste);
	}
}
