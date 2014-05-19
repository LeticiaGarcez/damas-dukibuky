package Engine;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Classe que define propriedades e métodos associados a um determinado jogador
 * no jogo. nomeJogador refere-se a um nome de identificação do jogador
 * iconePecaComum guarda a imagem da peça comum do jogador iconeDama guarda a
 * imagem da dama do jogador arrayPecas refere-se as peças que aquele jogador
 * ainda tem no jogo, cada posição do array(de 1 até 12), armazena uma peça
 * totalPecas refere-se ao numero total de peças do jogador tipoJogador
 * refere-se ao fato de que o jogador pode ser um humano, 'H' ou a máquina, 'M'
 */
public class Jogador
{

	private String nomeJogador;
	private ImageIcon iconePecaComum;
	private ImageIcon iconeDama;
	private TipoJogador tipoJogador;
	private ArrayList<Peca> arrayPecas;
	private OrientacaoJogador orientacao;
	private List<Movimento> movimentos;

	public Jogador()
	{
	}

	// construtor da classe jogador com as opções setadas por ele
	public Jogador(String nome, ImageIcon imagemPecaComum,
			ImageIcon imagemDama, TipoJogador tipoJogador, OrientacaoJogador o)
	{
		nomeJogador = nome;
		iconePecaComum = imagemPecaComum;
		iconeDama = imagemDama;
		this.tipoJogador = tipoJogador;
		arrayPecas = new ArrayList();
		this.orientacao = o;
	}

	public ArrayList<Peca> getArrayPecas()
	{
		return arrayPecas;
	}

	public void setArrayPecas(ArrayList<Peca> arrayPecas)
	{
		this.arrayPecas = arrayPecas;
	}

	public List<Movimento> getMovimentos()
	{
		return movimentos;
	}

	public void setMovimentos(List<Movimento> movimentos)
	{
		this.movimentos = movimentos;
	}

	// Tipo de jogador, podendo ser humano ou inteligêngia artificial
	public enum TipoJogador
	{

		HUMANO, IA_FACIL, IA_DIFICIL;
	}

	public enum OrientacaoJogador
	{

		CIMA, BAIXO;
	}

	public Image getImagemPecaComum()
	{
		return iconePecaComum.getImage();
	}

	public Image getImagemDama()
	{
		return iconeDama.getImage();
	}

	public void setPeca(Peca p)
	{
		getArrayPecas().add(p);
	}

	public TipoJogador getTipoJogador()
	{
		return tipoJogador;
	}

	public OrientacaoJogador getOrientacaoJogador()
	{
		return orientacao;
	}

	public void setOrientacaoJogador(OrientacaoJogador o)
	{
		orientacao = o;
	}

	public void setTipoJogador(TipoJogador tipoJogador)
	{
		this.tipoJogador = tipoJogador;
	}

	public String getNome()
	{
		return nomeJogador;
	}

	public Peca getPecaDeNumero(int num)
	{
		if (num >= 0 & num <= arrayPecas.size() - 1)
		{
			return arrayPecas.get(num);
		}
		return null;
	}

	public ArrayList<Peca> getPecas()
	{
		return arrayPecas;
	}

	public short totalPecasNoJogo()
	{
		return (short) arrayPecas.size();
	}

	/**
	 * copia um jogador e suas peças sem colocar o dono da peca em cada peca e
	 * sem colocar a lista de movimentos do jogador nem da peça
	 */
	public void copiarJogador(Jogador jogador)
	{
		this.arrayPecas = new ArrayList<Peca>();
		for (int i = 0; i < jogador.arrayPecas.size(); i++)
		{
			arrayPecas.add(new Peca());
			arrayPecas.get(i).copiarPeca(jogador.getArrayPecas().get(i));
		}
		this.iconeDama = jogador.iconeDama;
		this.iconePecaComum = jogador.iconePecaComum;
		this.nomeJogador = jogador.nomeJogador;
		this.orientacao = jogador.orientacao;
		this.tipoJogador = jogador.tipoJogador;
	}
}