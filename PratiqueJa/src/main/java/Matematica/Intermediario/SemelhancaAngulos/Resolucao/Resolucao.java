package Matematica.Intermediario.SemelhancaAngulos.Resolucao;

import java.util.ArrayList;
import java.util.List;

public abstract class Resolucao
{
	List<NoGraph> listNos = new ArrayList<NoGraph>();
	List<NoComplemento> listComplementos = new ArrayList<NoComplemento>();

	String graph[];
	String complementos[];

	public Resolucao(String graph[], String complementos[])
	{
		super();
		this.graph = graph;
		this.complementos = complementos;
		carregarList();
		construirEquivalenciaNos();
		construirComplementos();
	}

	public NoGraph buscar(String nome)
	{
		for(NoGraph noGraph : listNos)
		{
			if(noGraph.no.equals(nome))
				return noGraph;
		}
		return null;
	}

	public String solucaoIdentidades(String desconhecido, String conhecido,boolean facil)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		String str1 = desconhecido + "=x";
		for(NoGraph no : nosConhecidos)
			str1 += "," + no.no;

		String str2 = "";
		String str3 = "";
		boolean achou = false;
		for(NoGraph no : nosConhecidos)
		{
			for(NoEquivalencia noEq : no.equivalencia)
			{
				if(noEq.dois == null)
				{
					if(facil&&noEq.um.equals(noDesconhecido))
					{
						achou = true;
						str3 += "i=" + noDesconhecido.no;

						break;
					}
					else
					{
						for(NoEquivalencia noEq2 : noEq.um.equivalencia)
						{
							if(noEq2.um.equals(noDesconhecido) && noEq2.dois == null)
							{
								achou = true;
								str2 += noEq.um.no + "=y";
								str3 += "i=" + noEq.um.no + "," + "i=" + noDesconhecido.no;
								
								break;
							}
						}
						
					}
					if(achou)
						break;
				}
			}
		}
		if(achou)
			return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";
		else
			return null;
	}

	public String gerarSolucao1Conhecidos(boolean facil)
	{
		String solucoes = "//1Conhecido\n";
		String solucao;
		for(NoGraph no : listNos)
		{
			for(NoGraph no2 : listNos)
			{
				if(!no2.equals(no))
				{
					solucao=null;
					
					solucao = solucaoIdentidades(no.no, no2.no,facil);
					if(facil&&solucao == null)
						solucao = solucaoComplementoDireto(no.no, no2.no);
					if(solucao == null)
						solucao = solucaoComplementoDepois(no.no, no2.no);
					if(solucao == null)
						solucao = solucaoComplementoAntes(no.no, no2.no);
					
					if(solucao != null)
						solucoes += solucao + "\n";
				}
			}
		}

//		solucao = solucaoComplementoDireto("a", "c");
//		if(solucao != null)
//			solucoes += solucao + "\n";
		return solucoes;
	}

	public String gerarSolucao2Conhecidos()
	{
		String solucoes = "//2Conhecido\n";
		NoGraph no2, no3;
		String solucao;
		for(NoGraph no : listNos)
		{
			for(int i = 0; i < listNos.size(); i++)
			{
				no2 = listNos.get(i);
				for(int j = i + 1; j < listNos.size(); j++)
				{
					no3 = listNos.get(j);
					if(!no2.equals(no) && !no3.equals(no)
					&&!no.contemEquivalencia(no2)
					&&!no.contemEquivalencia(no3)
					&&!no2.contemEquivalencia(no3))
					{
						solucao=null;
						
						solucao = solucaoComplementoDepois2(no.no, no2.no + "," + no3.no);
						if(solucao == null)
							solucao = solucaoComplementoAntes2(no.no, no2.no + "," + no3.no);
						
						if(solucao == null)
							solucao = solucaoSomaInterna(no.no, no2.no+","+no3.no);

						if(solucao == null)
							solucao = solucaoSomaExterna(no.no, no2.no+","+no3.no);
						
						if(solucao != null)
							solucoes += solucao + "\n";
						
					}
				}
			}
		}
//		5- SemelhancaAngulos4 {"a=x,c,e", | 

//		solucao = solucaoComplementoDepois2("a", "c,e");
//		if(solucao != null)
//			solucoes += solucao + "\n";
		
		return solucoes;
	}

	private boolean muitoFacil(NoGraph noDesconhecido, List<NoGraph> nosConhecidos)
	{
		for(NoComplemento noComplemento : listComplementos)
		{
			if(noComplemento.nosFaltam(noDesconhecido, nosConhecidos).size() == 0)
				return true;
		}
		return false;
	}

	public String solucaoComplementoDireto(String desconhecido, String conhecido)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		boolean achou = false;
		String str1 = desconhecido + "=x";
		for(NoGraph no : nosConhecidos)
			str1 += "," + no.no;

		String str2 = "";
		String str3 = "";

		List<NoComplemento> listComplPromissores = new ArrayList<NoComplemento>();

		for(NoComplemento noComplemento : listComplementos)
		{
			if(noComplemento.contemUm(nosConhecidos))
				listComplPromissores.add(noComplemento);
		}

		NoGraph noFalta;
		for(NoComplemento noComplemento : listComplPromissores)
		{
			List<NoGraph> list=noComplemento.nosFaltam(nosConhecidos);
			if(list.size()==1)
			{
				noFalta = list.get(0);
				if(noDesconhecido.equals(noFalta))
				{
					achou = true;
					str3 += "c=" + noDesconhecido.no + "->" + noComplemento;
					
					break;
				}
				
			}
		}
		if(achou)
			return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";

		return null;
	}
	
	public String solucaoComplementoDepois(String desconhecido, String conhecido)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		boolean achou = false;
		if(!muitoFacil(noDesconhecido, nosConhecidos))
		{
			String str1 = desconhecido + "=x";
			for(NoGraph no : nosConhecidos)
				str1 += "," + no.no;

			String str2 = "";
			String str3 = "";

			List<NoComplemento> listComplPromissores = new ArrayList<NoComplemento>();

			for(NoComplemento noComplemento : listComplementos)
			{
				if(noComplemento.faltaSomenteUm(noDesconhecido, nosConhecidos))
					listComplPromissores.add(noComplemento);
			}

			NoGraph no;
			for(NoComplemento noComplemento : listComplPromissores)
			{
				no = noComplemento.nosFaltam(noDesconhecido, nosConhecidos).get(0);

				for(NoGraph noConhecido : nosConhecidos)
				{
					for(NoEquivalencia noEquivalencia : noConhecido.equivalencia)
					{
						if(noEquivalencia.um.equals(no) && noEquivalencia.dois == null)
						{
							achou = true;
							str2 += noEquivalencia.um.no + "=y";
							str3 += "i=" + noEquivalencia.um.no + ",";
							str3 += "c=" + noDesconhecido.no + "->" + noComplemento;

							break;
						}
					}
					if(achou)
						break;
				}
			}
			if(achou)
				return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";
		}

		return null;
	}

	public String solucaoComplementoAntes(String desconhecido, String conhecido)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		boolean achou = false;
		if(!muitoFacil(noDesconhecido, nosConhecidos))
		{
			String str1 = desconhecido + "=x";
			for(NoGraph no : nosConhecidos)
				str1 += "," + no.no;

			String str2 = "";
			String str3 = "";

			List<NoComplemento> listComplPromissores = new ArrayList<NoComplemento>();

			for(NoComplemento noComplemento : listComplementos)
			{
				if(noComplemento.contemUm(nosConhecidos))
					listComplPromissores.add(noComplemento);
			}

			List<NoGraph> nos;
			NoGraph noFalta;
			for(NoComplemento noComplemento : listComplPromissores)
			{
				nos = noComplemento.nosFaltam(noDesconhecido, nosConhecidos);
				if(nos.size() == 1)
				{
					noFalta = nos.get(0);
					for(NoEquivalencia noEquivalencia : noDesconhecido.equivalencia)
					{
						if(noEquivalencia.um.equals(noFalta) && noEquivalencia.dois == null)
						{
							achou = true;
							str2 += noEquivalencia.um.no + "=y";
							str3 += "c=" + noEquivalencia.um.no + "->" + noComplemento + ",";
							str3 += "i=" + noDesconhecido.no + ",";

							break;
						}
					}
					if(achou)
						break;
				}
			}
			if(achou)
				return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";
		}

		return null;
	}

	public String solucaoComplementoDepois2(String desconhecido, String conhecido)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		boolean achou = false;
		if(!muitoFacil(noDesconhecido, nosConhecidos))
		{
			String str1 = desconhecido + "=x";
			for(NoGraph no : nosConhecidos)
				str1 += "," + no.no;

			String str2 = "";
			String str3 = "";

			List<NoComplemento> listComplPromissores = new ArrayList<NoComplemento>();

			for(NoComplemento noComplemento : listComplementos)
			{
				if(noComplemento.contem(noDesconhecido))
				{
					listComplPromissores.add(noComplemento);
				}
			}

			NoGraph noFalta;
			for(NoComplemento noComplemento : listComplPromissores)
			{
				if(noComplemento.faltaSomenteUm(noDesconhecido, nosConhecidos))
				{
					noFalta = noComplemento.nosFaltam(noDesconhecido, nosConhecidos).get(0);

					for(NoGraph noConhecido : nosConhecidos)
					{
						for(NoEquivalencia noEquivalencia : noConhecido.equivalencia)
						{
							if(noEquivalencia.um.equals(noFalta) && noEquivalencia.dois == null)
							{
								achou = true;
								str2 += noEquivalencia.um.no + "=y";
								str3 += "i=" + noEquivalencia.um.no + ",";
								str3 += "c=" + noDesconhecido.no + "->" + noComplemento;

								break;
							}
							else if(noEquivalencia.dois != null && noEquivalencia.um.equals(noFalta)
							&& nosConhecidos.contains(noEquivalencia.dois))
							{
								achou = true;
								str2 += noEquivalencia.um.no + "=y";
								str3 += "s=" + noEquivalencia.um.no + "->" + noEquivalencia.um.no + "+"
								+ noEquivalencia.dois.no + "->" + noConhecido.no+",";
								str3 += "c=" + noDesconhecido.no + "->" + noComplemento;

								break;
							}
							else if(noEquivalencia.dois != null && noEquivalencia.dois.equals(noFalta)
							&& nosConhecidos.contains(noEquivalencia.um))
							{
								achou = true;
								str2 += noEquivalencia.dois.no + "=y";
								str3 += "s=" + noEquivalencia.dois.no + "->" + noEquivalencia.um.no + "+"
								+ noEquivalencia.dois.no + "->" + noConhecido.no + ",";
								str3 += "c=" + noDesconhecido.no + "->" + noComplemento;

								break;
							}

						}
						if(achou)
							break;
					}
				}
				if(achou)
					break;
			}

			if(!achou)
			{
				for(NoComplemento noComplemento : listComplPromissores)
				{
					List<NoGraph> nosFaltam = noComplemento.nosFaltam(noDesconhecido, nosConhecidos);

					NoGraph noConhecido1 = nosConhecidos.get(0);
					NoGraph noConhecido2 = nosConhecidos.get(1);

					for(NoEquivalencia noEquivalencia1 : noConhecido1.equivalencia)
					{
						for(NoEquivalencia noEquivalencia2 : noConhecido2.equivalencia)
						{
							if(noEquivalencia1.dois == null && noEquivalencia2.dois == null
							&& !noEquivalencia1.um.equals(noEquivalencia2.um))
							{
								if(nosFaltam.contains(noEquivalencia1.um) && nosFaltam.contains(noEquivalencia2.um))
								{
									achou = true;
									str2 += noEquivalencia1.um.no + "=y,";
									str2 += noEquivalencia2.um.no + "=z";
									str3 += "i=" + noEquivalencia1.um.no + ",";
									str3 += "i=" + noEquivalencia2.um.no + ",";
									str3 += "c=" + noDesconhecido.no + "->" + noComplemento;

									break;
								}
							}

						}
						if(achou)
							break;
					}
					if(achou)
						break;
				}

			}
			if(achou)
				return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";
		}

		return null;
	}

	public String solucaoComplementoAntes2(String desconhecido, String conhecido)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		boolean achou = false;
		if(!muitoFacil(noDesconhecido, nosConhecidos))
		{
			String str1 = desconhecido + "=x";
			for(NoGraph no : nosConhecidos)
				str1 += "," + no.no;

			String str2 = "";
			String str3 = "";

			List<NoComplemento> listComplPromissores = new ArrayList<NoComplemento>();

			for(NoComplemento noComplemento : listComplementos)
			{
				if(noComplemento.contemUm(nosConhecidos))
					listComplPromissores.add(noComplemento);
			}

			List<NoGraph> nos;
			NoGraph noFalta;
			for(NoComplemento noComplemento : listComplPromissores)
			{
				nos = noComplemento.nosFaltam(noDesconhecido, nosConhecidos);
				if(nos.size() == 1)
				{
					noFalta = nos.get(0);
					for(NoEquivalencia noEquivalencia : noDesconhecido.equivalencia)
					{
						if(noEquivalencia.um.equals(noFalta) && noEquivalencia.dois == null)
						{
							achou = true;
							str2 += noEquivalencia.um.no + "=y";
							str3 += "c=" + noEquivalencia.um.no + "->" + noComplemento + ",";
							str3 += "i=" + noDesconhecido.no;

							break;
						}
						else if(noEquivalencia.dois != null && noEquivalencia.um.equals(noFalta)
						&& nosConhecidos.contains(noEquivalencia.dois))
						{
							achou = true;
							str2 += noEquivalencia.um.no + "=y";
							str3 += "c=" + noEquivalencia.um.no + "->" + noComplemento + ",";
							str3 += "s=" + noDesconhecido.no + "->" + noDesconhecido.no + "->" + noEquivalencia.dois.no
							+ "+" + noEquivalencia.um.no;
							break;
						}
						else if(noEquivalencia.dois != null &&noEquivalencia.dois.equals(noFalta) && nosConhecidos.contains(noEquivalencia.um))
						{
							achou = true;
							str2 += noEquivalencia.dois.no + "=y";
							str3 += "c=" + noEquivalencia.dois.no + "->" + noComplemento + ",";
							str3 += "s=" + noDesconhecido.no + "->" + noDesconhecido.no + "->" + noEquivalencia.dois.no
							+ "+" + noEquivalencia.um.no;

							break;

						}
					}

					if(!achou)
					{
						for(NoGraph noGraph : nosConhecidos)
						{
							for(NoEquivalencia noEquivalencia : noGraph.equivalencia)
							{
								if(noEquivalencia.dois != null &&noEquivalencia.um.equals(noFalta) && noEquivalencia.dois.equals(noDesconhecido))
								{
									achou = true;
									str2 += noEquivalencia.um.no + "=y";
									str3 += "c=" + noEquivalencia.um.no + "->" + noComplemento + ",";
									str3 += "s=" + noDesconhecido.no + "->" + noEquivalencia.um.no + "+"
									+ noEquivalencia.dois.no + "->" + noGraph.no;

									break;
								}
								else if(noEquivalencia.dois != null &&noEquivalencia.dois.equals(noFalta) && noEquivalencia.um.equals(noDesconhecido))
								{
									achou = true;
									str2 += noEquivalencia.dois.no + "=y";
									str3 += "c=" + noEquivalencia.dois.no + "->" + noComplemento + ",";
									str3 += "s=" + noDesconhecido.no + "->" + noEquivalencia.um.no + "+" + noEquivalencia.dois.no + "->"
									+ noGraph.no;

									break;
								}
							}
						}
					}

					if(achou)
						break;
				}
			}
			if(achou)
				return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";
		}

		return null;
	}

	public String solucaoSomaInterna(String desconhecido, String conhecido)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		boolean achou = false;
		if(!muitoFacil(noDesconhecido, nosConhecidos))
		{
			String str1 = desconhecido + "=x";
			for(NoGraph no : nosConhecidos)
				str1 += "," + no.no;

			String str2 = "";
			String str3 = "";

			NoGraph noSomaPromissor = null, outro = null;
			for(NoGraph noGraph : nosConhecidos)
			{
				for(NoEquivalencia noEquivalencia : noGraph.equivalencia)
				{
					if(noEquivalencia.size() == 2 && noEquivalencia.contem(noDesconhecido))
					{
						noSomaPromissor = noGraph;
						outro = noEquivalencia.getOutro(noDesconhecido);
					}
				}
			}

			if(noSomaPromissor != null)
			{
				for(NoGraph noConhecido : nosConhecidos)
				{
					if(!noConhecido.equals(noSomaPromissor))
					{
						if(noConhecido.equals(outro))
						{
							achou = true;
							str3 += "s=" + noDesconhecido.no + "->" + noDesconhecido.no + "+" + outro.no + "->" + noSomaPromissor.no;

							break;
						}
						if(!achou)
						{
							for(NoEquivalencia noEquivalencia : noConhecido.equivalencia)
							{
								if(noEquivalencia.um.equals(outro) && noEquivalencia.dois == null)
								{
									achou = true;
									str2 += noEquivalencia.um.no + "=y";
									str3 += "i=" + noEquivalencia.um.no + ",";
									str3 += "s=" + noDesconhecido.no + "->" + noDesconhecido.no + "+" + outro.no + "->" + noSomaPromissor.no;

									break;
								}
							}
						}
					}
					if(achou)
						break;
				}
			}

			if(achou)
				return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";
		}

		return null;
	}

	public String solucaoSomaExterna(String desconhecido, String conhecido)
	{
		NoGraph noDesconhecido = buscar(desconhecido);
		String listStr[] = conhecido.split(",");
		List<NoGraph> nosConhecidos = new ArrayList<NoGraph>();
		for(int i = 0; i < listStr.length; i++)
			nosConhecidos.add(buscar(listStr[i]));

		boolean achou = false;
		if(!muitoFacil(noDesconhecido, nosConhecidos))
		{
			String str1 = desconhecido + "=x";
			for(NoGraph no : nosConhecidos)
				str1 += "," + no.no;

			String str2 = "";
			String str3 = "";

			NoGraph noSomaPromissor = null, outro1 = null, outro2 = null;
			for(NoEquivalencia noEquivalencia : noDesconhecido.equivalencia)
			{
				if(noEquivalencia.size() == 2)
				{
					noSomaPromissor = noDesconhecido;
					outro1 = noEquivalencia.um;
					outro2 = noEquivalencia.dois;
				}
			}

			if(noSomaPromissor != null)
			{
				for(NoGraph noConhecido1 : nosConhecidos)
				{
					for(NoGraph noConhecido2 : nosConhecidos)
					{
						if(!noConhecido1.equals(noConhecido2))
						{
							if(outro1.equals(noConhecido1) && outro2.equals(noConhecido2))
							{
								achou = true;
								str3 += "s="+ noDesconhecido.no + "->"  + noDesconhecido.no + "->" + noConhecido1.no + "+" + noConhecido2.no;

								break;
							}
							else
							{
								for(NoEquivalencia noEquivalencia : noConhecido1.equivalencia)
								{
									if(noEquivalencia.dois == null
									&& ((outro1.equals(noEquivalencia.um) && outro2.equals(noConhecido2))
									|| (outro2.equals(noEquivalencia.um) && outro1.equals(noConhecido2))))
									{
										achou = true;
										str2 += noEquivalencia.um.no + "=y";
										str3 += "i=" + noEquivalencia.um.no + ",";
										str3 += "s=" + noDesconhecido.no + "->" + noDesconhecido.no + "->" + noEquivalencia.um.no + "+"
										+ noConhecido2.no;

										break;
									}
								}

								for(NoEquivalencia noEquivalencia1 : noConhecido1.equivalencia)
								{
									if(noEquivalencia1.dois == null)
									{
										for(NoEquivalencia noEquivalencia2 : noConhecido2.equivalencia)
										{
											if(noEquivalencia2.dois == null && !noEquivalencia2.equals(noEquivalencia1))
											{
												if((outro1.equals(noEquivalencia1.um)
												&& outro2.equals(noEquivalencia2.um))
												|| (outro2.equals(noEquivalencia1.um)
												&& outro1.equals(noEquivalencia2.um)))
												{
													achou = true;
													str2 += noEquivalencia1.um.no + "=y,";
													str2 += noEquivalencia2.um.no + "=z";
													str3 += "i=" + noEquivalencia1.um.no + ",";
													str3 += "i=" + noEquivalencia2.um.no + ",";
													str3 += "s=" + noDesconhecido.no + "->" + noDesconhecido.no + "->" + noEquivalencia1.um.no
													+ "+" + noEquivalencia2.um.no;

													break;
												}
											}
										}
									}
								}
							}
						}
					}
					if(achou)
						break;
				}
			}

			if(achou)
				return "{\"" + str1 + "\", \"" + str2 + "\", \"" + str3 + "\"},";
		}

		return null;
	}

	@Override
	public String toString()
	{
		String str = "";
		for(NoGraph noGraph : listNos)
			str += noGraph + "\n";

		return str;
	}

	protected void carregarList()
	{
	}

	private void construirEquivalenciaNos()
	{
		String listStr[];
		String listEquivalencia[];
		String listEq2[];

		NoGraph no, noEquivalencia1, noEquivalencia2;
		for(int i = 0; i < graph.length; i++)
		{
			listStr = graph[i].split("->");
			no = buscar(listStr[0]);
			listEquivalencia = listStr[1].split(",");
			for(int j = 0; j < listEquivalencia.length; j++)
			{
				listEq2 = listEquivalencia[j].split("-");
				if(listEq2.length == 1)
				{
					noEquivalencia1 = buscar(listEquivalencia[j]);
					no.equivalencia.add(new NoEquivalencia(noEquivalencia1));
				}
				else
				{
					noEquivalencia1 = buscar(listEq2[0]);
					noEquivalencia2 = buscar(listEq2[1]);
					no.equivalencia.add(new NoEquivalencia(noEquivalencia1, noEquivalencia2));
				}
			}
		}
	}

	private void construirComplementos()
	{
		NoComplemento noComplemento = null;
		String list[];
		for(int i = 0; i < complementos.length; i++)
		{
			list = complementos[i].split(",");
			if(list.length == 2)
				noComplemento = new NoComplemento(buscar(list[0]), buscar(list[1]));
			else if(list.length == 3)
				noComplemento = new NoComplemento(buscar(list[0]), buscar(list[1]), buscar(list[2]));

			listComplementos.add(noComplemento);
		}
	}

	public static void main(String[] args)
	{
		String graph[] = { "a->b", "b->a", "c->d", "d->c"};

		String complementos[] = { "a,c", "c,b", "b,d", "d,a"};
		
		ResolucaoExercicio1 resolucaoExercicio = new ResolucaoExercicio1(graph,complementos);
		System.out.println(resolucaoExercicio.gerarSolucao1Conhecidos(false));
	}
}
