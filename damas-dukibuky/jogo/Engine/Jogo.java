package Engine;

import java.util.ArrayList;

public class Jogo
{
	private ArrayList<Rodada> rodadas;

	public Jogo()
	{
		rodadas = new ArrayList<Rodada>();
	}

	public void incluirRodada(Rodada rodada)
	{
		rodadas.add(rodada);
	}

	public Rodada getRodada(int i)
	{
		return this.rodadas.get(i);
	}

	public Rodada excluirRodada(int i)
	{
		return this.rodadas.remove(i);
	}

	public void substituirRodada(int i, Rodada rodada)
	{
		this.rodadas.set(i, rodada);
	}

	public ArrayList<Rodada> getRodadas()
	{
		return this.getRodadas();
	}

	public void incluirRodadaNaPosicao(int i, Rodada rodada)
	{
		this.rodadas.add(i, rodada);
	}

	public int getRodadasJogadas()
	{
		return this.rodadas.size();
	}

	public void setRodadas(ArrayList<Rodada> rodadasJogadas)
	{
		rodadas = rodadasJogadas;
	}
}