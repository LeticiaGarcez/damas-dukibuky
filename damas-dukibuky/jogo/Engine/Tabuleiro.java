package Engine;

/**
 * Essa classe define propriedades e métodos associados ao tabuleiro de damas
 * tamCasa define o tamanho(em pixels) de cada casa desse tabuleiro
 * matrizPecaTabuleiro[i][j], define a posição de uma peça no quadrado do
 * tabuleiro, começando pelo quadrado do canto superior esquerdo, que é o
 * quadrado [0][0]
 */
public class Tabuleiro
{

	private Peca[][] matrizPecasTabuleiro;
	private int tamCasa;

	public Tabuleiro(int tamanhoCasa)
	{
		matrizPecasTabuleiro = new Peca[8][8];
		for (int i = 0; i < matrizPecasTabuleiro.length; i++)
		{
			for (int j = 0; j < matrizPecasTabuleiro[0].length; j++)
			{
				matrizPecasTabuleiro[i][j] = null;
			}
		}
		tamCasa = tamanhoCasa;
	}

	// posiciona a peça específica p em algum quadrado do tabuleiro de posição
	// posX, posY
	public void posicionaPeca(Peca p, int posX, int posY)
	{
		matrizPecasTabuleiro[posX][posY] = p;
	}

	public boolean retiraPecaPosicao(int posX, int posY)
	{
		if ((posX >= 0 & posX <= 7) & (posY >= 0 & posY <= 7))
		{
			matrizPecasTabuleiro[posX][posY] = null;
			return true;
		} else
		{
			return false;
		}
	}

	public Peca getPeca(int posX, int posY)
	{
		return matrizPecasTabuleiro[posX][posY];
	}

	// identifica em que quadrado o usuario clicou
	// retorna um array de short com 2 posições que indicam a posição da
	// linha/coluna
	// do quadrado clicado
	public short[] identificaQuadrado(int pixelsX, int pixelsY)
	{
		short pos[] = null;
		short posX = (short) (pixelsX / tamCasa);
		short posY = (short) (pixelsY / tamCasa);
		if ((posX >= 0 & posX <= 7) & (posY >= 0 & posY <= 7))
		{
			pos = new short[2];
			pos[0] = posX;
			pos[1] = posY;
		}
		return pos;
	}

	public void copiarTabuleiro(Tabuleiro tabuleiro)
	{
		this.matrizPecasTabuleiro = new Peca[8][8];
		this.tamCasa = tabuleiro.tamCasa;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Peca p = tabuleiro.getPeca(i, j);
				if (p != null)
				{
					matrizPecasTabuleiro[i][j] = p;
				}
			}

		}
	}

	public int contaPecas()
	{
		int temp = 0;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (this.matrizPecasTabuleiro[i][j] != null)
				{
					if (this.matrizPecasTabuleiro[i][j].getDono()
							.getTipoJogador()
							.equals(Jogador.TipoJogador.HUMANO))
					{
						temp--;
					} else
					{
						temp++;
					}
				}
			}
		}
		return temp;
	}
}