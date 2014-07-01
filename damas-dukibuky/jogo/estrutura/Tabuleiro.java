package estrutura;
public class Tabuleiro
{

	char tabuleiro[][] = new char[8][8];

	public Tabuleiro()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				tabuleiro[i][j] = '.';
			}
		}
		colocarPecas();
	}

	public void colocarPecas()
	{
		for (int i = 7; i >= 5; i--)
		{
			for (int j = 0; j < 8; j++)
			{
				if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1))
					tabuleiro[i][j] = 'B';
			}
		}

		for (int i = 0; i <= 2; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1))
					tabuleiro[i][j] = 'N';
			}
		}
		for (int i = 0; i < 8; i++)
		{
			System.out.println(tabuleiro[i]);
		}
	}

	public void reiniciar()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				tabuleiro[i][j] = '.';
			}
		}
		for (int i = 7; i >= 5; i--)
		{
			for (int j = 0; j < 8; j++)
			{
				if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1))
					tabuleiro[i][j] = 'B';
			}
		}
		for (int i = 0; i <= 2; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1))
					tabuleiro[i][j] = 'N';
			}
		}
	}

	public char[][] getTabuleiro()
	{
		return tabuleiro;
	}

	public void setPeca(int f, int c, char letra)
	{
		tabuleiro[f][c] = letra;
	}

	/**
	 * 
	 */
	public void imprimir()
	{
		System.out.println(" ");
		for (int i = 0; i < 8; i++)
		{
			System.out.println(tabuleiro[i]);
		}
	}

}
