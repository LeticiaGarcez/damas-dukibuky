package Engine;

import java.util.ArrayList;
import java.util.List;

public class Jogada
{
	private Movimento movJogada = new Movimento();

	public Jogada(Movimento movimentacao)
	{
		if (movimentacao != null)
		{
			movJogada.setPosFinalX(movimentacao.getPosFinalX());
			movJogada.setPosFinalY(movimentacao.getPosFinalY());
			movJogada.setPecasCapturadas(new ArrayList<Peca>());
			Peca pecaQueRealizouMovimento = new Peca(movimentacao.getPeca()
					.getDono(), movimentacao.getPeca().getTipo());
			pecaQueRealizouMovimento.setQuadrado(movimentacao.getPeca()
					.getPosXQuadrado(), movimentacao.getPeca()
					.getPosYQuadrado());
			pecaQueRealizouMovimento.setPosicaoNaTela(movimentacao.getPeca()
					.getPosXTela(), movimentacao.getPeca().getPosYTela());
			pecaQueRealizouMovimento.setMovimentacoesPossiveis(movimentacao
					.getPeca().getMovimentacoesPossiveis());
			movJogada.setPeca(pecaQueRealizouMovimento);
			if (movimentacao.getPecasCapturadas() != null)
			{
				List<Peca> PecasCapturadasJogada = new ArrayList<Peca>();
				for (int i = 0; i < movimentacao.getPecasCapturadas().size(); i++)
				{
					Peca pecaRetomada = movimentacao.getPecasCapturadas()
							.get(i);
					Peca pecaCapturada = new Peca(pecaRetomada.getDono(),
							pecaRetomada.getTipo());
					pecaCapturada.setPosicaoNaTela(pecaRetomada.getPosXTela(),
							pecaRetomada.getPosYTela());
					pecaCapturada.setQuadrado(pecaRetomada.getPosXQuadrado(),
							pecaRetomada.getPosYQuadrado());
					pecaCapturada.setMovimentacoesPossiveis(pecaRetomada
							.getMovimentacoesPossiveis());
					PecasCapturadasJogada.add(pecaCapturada);
				}
				movJogada.setPecasCapturadas(PecasCapturadasJogada);
			}
		}
	}

	public short getPosXInicial()
	{
		return (short) this.movJogada.getPeca().getPosXQuadrado();
	}

	public short getPosYInicial()
	{
		return (short) this.movJogada.getPeca().getPosYQuadrado();
	}

	public Movimento getMovimentoRealizado()
	{
		return this.movJogada;
	}
}