package Engine;

public class Rodada
{
	private Jogada jog1;
	private Jogada jog2;

	public Rodada(Jogada jogada1, Jogada jogada2)
	{
		this.jog1 = jogada1;
		this.jog2 = jogada2;
	}

	public Jogada getPrimeiraJogada()
	{
		return this.jog1;
	}

	public Jogada getSegundaJogada()
	{
		return this.jog2;
	}
}