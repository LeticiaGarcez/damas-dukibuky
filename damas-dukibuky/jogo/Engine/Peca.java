package Engine;

import java.util.List;

/**
 * Classe que define os m�todos e fun��es associados a uma pe�a do jogo posXTela
 * e posYTela define as posi��es(em pixel, horizontal e vertical) do canto
 * superior esquerdo dessa pe�a espec�fica no painel do tabuleiro posLinha e
 * posColuna definem a posi��o da pe�a no quadrado do tabuleiro. Ex: PosLinha =
 * 4 e posColuna = 6, indica que a pe�a ocupa a posi��o do quadrado (4,6) no
 * jogo. Desabilita � um dado booleano que pode ser usado, por exemplo para
 * indicar que uma pe�a j� est� fora do jogo ou n�o sofre a a��o(no caso de
 * desabilita=true) definido por algum outro m�todo Jogador � um objeto que
 * define quem � o dono da pe�a(jogador1 ou jogador2), Tipo define o tipo da
 * pe�a, 'C', pe�a comum ou 'D' para dama
 */
public class Peca
{
	private int posXTela;
	private int posYTela;
	private int posLinha;
	private int posColuna;
	private TipoPeca tipo;
	private boolean desabilita;
	private Jogador donoDaPeca;
	private List<Movimento> movimentacoesPossiveis;

	public Peca()
	{
	}

	public enum TipoPeca
	{
		DAMA, PECA_COMUM;
	}

	public Peca(Jogador jogador, TipoPeca tipoPeca)
	{
		donoDaPeca = jogador;
		this.tipo = tipoPeca;
		desabilita = false;
	}

	public void setTipo(TipoPeca t)
	{
		tipo = t;
	}

	public void setDesabilitar(boolean des)
	{
		desabilita = des;
	}

	public boolean getDesabilitar()
	{
		return desabilita;
	}

	public TipoPeca getTipo()
	{
		return tipo;
	}

	public void setPosicaoNaTela(int x, int y)
	{
		posXTela = x;
		posYTela = y;
	}

	// seta em qual quadrado no tabuleiro se encontra a pe�a
	public void setQuadrado(int linha, int coluna)
	{
		posLinha = linha;
		posColuna = coluna;
	}

	public int getPosXQuadrado()
	{
		return posLinha;
	}

	public int getPosYQuadrado()
	{
		return posColuna;
	}

	public int getPosXTela()
	{
		return posXTela;
	}

	public int getPosYTela()
	{
		return posYTela;
	}

	public Jogador getDono()
	{
		return donoDaPeca;
	}

	public void setDono(Jogador j)
	{
		donoDaPeca = j;
	}

	public List<Movimento> getMovimentacoesPossiveis()
	{
		return movimentacoesPossiveis;
	}

	public void setMovimentacoesPossiveis(List<Movimento> movimentacoesPossiveis)
	{
		this.movimentacoesPossiveis = movimentacoesPossiveis;
	}

	public void copiarPeca(Peca p)
	{
		this.desabilita = p.desabilita;
		this.posColuna = p.posColuna;
		this.posLinha = p.posLinha;
		this.posXTela = p.posXTela;
		this.posYTela = p.posYTela;
		this.tipo = p.tipo;
	}

}