package Engine;

import java.util.ArrayList;
import java.util.List;

public class Regras
{

	private List<Movimento> capturas;
	private int movDamaSemCaptura = 0;
	private Jogada ultimaJogada = null;

	/**
	 * método pra validar movimentação geral das peças, recebendo um tabuleiro
	 * ainda com as posições antigas, a peça a ser movimentada e as coordenadas
	 * com a posição nova
	 */
	public boolean validarMovimentacao(Peca peca, int x, int y, Tabuleiro t)
	{
		for (Movimento movimento : peca.getDono().getMovimentos())
		{
			if (movimento.getPeca().equals(peca)
					&& movimento.getPosFinalX() == x
					&& movimento.getPosFinalY() == y)
			{
				ultimaJogada = new Jogada(movimento);
				if (movimento.getPecasCapturadas() != null)
				{
					this.removerPecasCapturadas(t,
							movimento.getPecasCapturadas());
					this.movDamaSemCaptura = 0;
				} else
				{
					if (peca.getTipo().equals(Peca.TipoPeca.DAMA))
					{
						movDamaSemCaptura++;
					} else
					{
						movDamaSemCaptura = 0;
					}
				}
				return true;
			}
		}
		return false;
	}

	private void removerPecasCapturadas(Tabuleiro t, List<Peca> pecasCapturadas)
	{
		Jogador jog;
		for (Peca peca : pecasCapturadas)
		{
			jog = peca.getDono();
			jog.getArrayPecas().remove(peca);
			t.retiraPecaPosicao(peca.getPosXQuadrado(), peca.getPosYQuadrado());
		}
	}

	/**
	 * método privado para mapear movimentos comuns da dama acima e para a
	 * direita da posição atual da mesma retorna um array de movimentos comuns
	 * da dama para essas posições
	 */

	private List<Movimento> mapearMovimentosComunsDamaDireitaCima(Peca p,
			Tabuleiro t)
	{
		List<Movimento> movimentos = new ArrayList<Movimento>();
		int xProx, yProx;
		boolean encontrouPecaNoCaminho = false;
		xProx = p.getPosXQuadrado() + 1;
		yProx = p.getPosYQuadrado() + 1;
		while (xProx <= 7 && yProx <= 7 && !encontrouPecaNoCaminho)
		{
			if (t.getPeca(xProx, yProx) != null)
			{
				encontrouPecaNoCaminho = true;
			} else
			{
				Movimento mov = new Movimento();
				mov.setPeca(p);
				mov.setPosFinalX(xProx);
				mov.setPosFinalY(yProx);
				movimentos.add(mov);
			}
			xProx = xProx + 1;
			yProx = yProx + 1;
		}
		return movimentos;
	}

	private List<Movimento> mapearMovimentosComunsDamaEsquerdaCima(Peca p,
			Tabuleiro t)
	{
		List<Movimento> movimentos = new ArrayList<Movimento>();
		boolean encontrouPecaNoCaminho = false;
		int xProx, yProx;
		xProx = p.getPosXQuadrado() + 1;
		yProx = p.getPosYQuadrado() - 1;
		while ((xProx <= 7) && (yProx >= 0) && !encontrouPecaNoCaminho)
		{
			if (t.getPeca(xProx, yProx) != null)
			{
				encontrouPecaNoCaminho = true;
			} else
			{
				Movimento mov = new Movimento();
				mov.setPeca(p);
				mov.setPosFinalX(xProx);
				mov.setPosFinalY(yProx);
				movimentos.add(mov);
			}
			xProx = xProx + 1;
			yProx = yProx - 1;
		}
		return movimentos;
	}

	private List<Movimento> mapearMovimentosComunsDamaEsquerdaBaixo(Peca p,
			Tabuleiro t)
	{
		List<Movimento> movimentos = new ArrayList<Movimento>();
		boolean encontrouPecaNoCaminho = false;
		int xProx, yProx;
		xProx = p.getPosXQuadrado() - 1;
		yProx = p.getPosYQuadrado() - 1;
		while ((xProx >= 0) && (yProx >= 0) && !encontrouPecaNoCaminho)
		{
			if (t.getPeca(xProx, yProx) != null)
			{
				encontrouPecaNoCaminho = true;
			} else
			{
				Movimento mov = new Movimento();
				mov.setPeca(p);
				mov.setPosFinalX(xProx);
				mov.setPosFinalY(yProx);
				movimentos.add(mov);
			}
			xProx = xProx - 1;
			yProx = yProx - 1;
		}
		return movimentos;
	}

	private List<Movimento> mapearMovimentosComunsDamaDireitaBaixo(Peca p,
			Tabuleiro t)
	{
		List<Movimento> movimentos = new ArrayList<Movimento>();
		boolean encontrouPecaNoCaminho = false;
		int xProx, yProx;
		xProx = p.getPosXQuadrado() - 1;
		yProx = p.getPosYQuadrado() + 1;
		while ((yProx <= 7) && (xProx >= 0) && !encontrouPecaNoCaminho)
		{
			if (t.getPeca(xProx, yProx) != null)
			{
				encontrouPecaNoCaminho = true;
			} else
			{
				Movimento mov = new Movimento();
				mov.setPeca(p);
				mov.setPosFinalX(xProx);
				mov.setPosFinalY(yProx);
				movimentos.add(mov);
			}
			xProx = xProx - 1;
			yProx = yProx + 1;
		}
		return movimentos;
	}

	public void gerarMovimentacoes(Jogador jogadorDaVez, Tabuleiro tabuleiro)
	{
		capturas = new ArrayList<Movimento>();
		jogadorDaVez.setMovimentos(new ArrayList<Movimento>());
		for (Peca peca : jogadorDaVez.getArrayPecas())
		{
			this.gerarMovimentosPossiveis(jogadorDaVez, peca, tabuleiro);
		}
		if (capturas != null && !capturas.isEmpty())
		{
			List<Movimento> capturasObrigatorias = new ArrayList<Movimento>();
			for (Movimento captura : capturas)
			{
				if (capturasObrigatorias.isEmpty())
				{
					capturasObrigatorias.add(captura);
				} else
				{
					if (captura.getPecasCapturadas().size() > capturasObrigatorias
							.get(0).getPecasCapturadas().size())
					{
						capturasObrigatorias.clear();
						capturasObrigatorias.add(captura);
					} else
					{
						if (captura.getPecasCapturadas().size() == capturasObrigatorias
								.get(0).getPecasCapturadas().size())
						{
							capturasObrigatorias.add(captura);
						}
					}
				}
			}
			jogadorDaVez.setMovimentos(capturasObrigatorias);
		}
	}

	private void mapearMovimentacoesDama(Peca p, Tabuleiro t)
	{
		List<Movimento> movimentosEsquerdaCima = mapearMovimentosComunsDamaEsquerdaCima(
				p, t);
		List<Movimento> movimentosEsquerdaBaixo = mapearMovimentosComunsDamaEsquerdaBaixo(
				p, t);
		List<Movimento> movimentosDireitaCima = mapearMovimentosComunsDamaDireitaCima(
				p, t);
		List<Movimento> movimentosDireitaBaixo = mapearMovimentosComunsDamaDireitaBaixo(
				p, t);
		List<Movimento> movimentos = new ArrayList<Movimento>();
		movimentos.addAll(movimentosEsquerdaCima);
		movimentos.addAll(movimentosEsquerdaBaixo);
		movimentos.addAll(movimentosDireitaCima);
		movimentos.addAll(movimentosDireitaBaixo);
		// capturas da dama
		List<Movimento> caps = gerarPossiveisCapturasDama(p, t,
				p.getPosXQuadrado(), p.getPosXQuadrado(), p.getPosYQuadrado(),
				p.getPosYQuadrado(), null);
		if (caps != null && !caps.isEmpty())
		{
			capturas.addAll(caps);
			movimentos.addAll(caps);
		}
		p.setMovimentacoesPossiveis(movimentos);
		Jogador j = p.getDono();
		j.getMovimentos().addAll(movimentos);
	}

	private void mapearMovimentacoesPecaComum(Jogador jogador, Peca peca,
			Tabuleiro tab)
	{
		int x;
		if (jogador.getOrientacaoJogador().equals(
				Jogador.OrientacaoJogador.CIMA))
		{
			x = peca.getPosXQuadrado() + 1;
		} else
		{
			x = peca.getPosXQuadrado() - 1;
		}
		int yDir = peca.getPosYQuadrado() + 1;
		int yEsq = peca.getPosYQuadrado() - 1;
		List<Movimento> movimentos = new ArrayList<Movimento>();
		if (x >= 0 && x <= 7)
		{
			// jogada para a direita
			if (yDir <= 7 && tab.getPeca(x, yDir) == null)
			{
				Movimento mov = new Movimento();
				mov.setPeca(peca);
				mov.setPosFinalX(x);
				mov.setPosFinalY(yDir);
				movimentos.add(mov);
			}
			// jogada para a esq
			if (yEsq >= 0 && tab.getPeca(x, yEsq) == null)
			{
				Movimento mov = new Movimento();
				mov.setPeca(peca);
				mov.setPosFinalX(x);
				mov.setPosFinalY(yEsq);
				movimentos.add(mov);
			}
		}
		// captura
		List<Movimento> lista = gerarPossiveisCapturasPecaComum(peca, tab,
				peca.getPosXQuadrado(), peca.getPosYQuadrado(), null);
		if (lista != null && !lista.isEmpty())
		{
			capturas.addAll(lista);
			movimentos.addAll(lista);
		}
		peca.setMovimentacoesPossiveis(movimentos);
		jogador.getMovimentos().addAll(movimentos);
	}

	private void gerarMovimentosPossiveis(Jogador jogador, Peca peca,
			Tabuleiro tab)
	{
		if (peca.getTipo().equals(Peca.TipoPeca.PECA_COMUM))
		{
			mapearMovimentacoesPecaComum(jogador, peca, tab);
		} else
		{
			mapearMovimentacoesDama(peca, tab);
		}
	}

	private Movimento pegarProxCapturaDamaEsquerdaBaixo(Peca p, Tabuleiro t,
			int x, int y, Movimento capturaAnterior)
	{
		boolean encontrouCaptura = false;
		int xProx = x - 1;
		int yProx = y - 1;
		Movimento mov = new Movimento();
		while ((xProx >= 0) && (yProx >= 0) && (!encontrouCaptura))
		{
			if (t.getPeca(xProx, yProx) == null)
			{
				xProx = xProx - 1;
				yProx = yProx - 1;
			} else if ((!t.getPeca(xProx, yProx).getDono().equals(p.getDono())
					&& xProx > 0 && yProx > 0)
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xProx, yProx))))
			{
				if (t.getPeca(xProx - 1, yProx - 1) == null)
				{
					mov.setPeca(p);
					mov.setPosFinalX(xProx - 1);
					mov.setPosFinalY(yProx - 1);
					encontrouCaptura = true;
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					mov.getPecasCapturadas().add(t.getPeca(xProx, yProx));

				} else
					yProx = -1;
			} else
			{
				xProx = xProx - 1;
				yProx = yProx - 1;
			}
		}
		return mov;
	}

	private Movimento pegarProxCapturaDamaEsquerdaCima(Peca p, Tabuleiro t,
			int x, int y, Movimento capturaAnterior)
	{
		boolean encontrouCaptura = false;
		int xProx = x + 1;
		int yProx = y - 1;
		Movimento mov = new Movimento();
		while ((xProx <= 7) && (yProx >= 0) && (!encontrouCaptura))
		{
			if (t.getPeca(xProx, yProx) == null)
			{
				xProx = xProx + 1;
				yProx = yProx - 1;
			} else if ((!t.getPeca(xProx, yProx).getDono().equals(p.getDono())
					&& xProx < 7 && yProx > 0)
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xProx, yProx))))
			{
				if (t.getPeca(xProx + 1, yProx - 1) == null)
				{
					mov.setPeca(p);
					mov.setPosFinalX(xProx + 1);
					mov.setPosFinalY(yProx - 1);
					encontrouCaptura = true;
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					mov.getPecasCapturadas().add(t.getPeca(xProx, yProx));

				} else
					xProx = 8;
			} else
			{
				xProx = xProx + 1;
				yProx = yProx - 1;
			}
		}
		return mov;
	}

	private Movimento pegarProxCapturaDamaDireitaCima(Peca p, Tabuleiro t,
			int x, int y, Movimento capturaAnterior)
	{
		boolean encontrouCaptura = false;
		int xProx = x + 1;
		int yProx = y + 1;
		Movimento mov = new Movimento();
		while ((xProx <= 7) && (yProx <= 7) && (!encontrouCaptura))
		{
			if (t.getPeca(xProx, yProx) == null)
			{
				xProx = xProx + 1;
				yProx = yProx + 1;
			} else if ((!t.getPeca(xProx, yProx).getDono().equals(p.getDono())
					&& xProx < 7 && yProx < 7)
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xProx, yProx))))
			{
				if (t.getPeca(xProx + 1, yProx + 1) == null)
				{
					mov.setPeca(p);
					mov.setPosFinalX(xProx + 1);
					mov.setPosFinalY(yProx + 1);
					encontrouCaptura = true;
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					mov.getPecasCapturadas().add(t.getPeca(xProx, yProx));

				} else
					yProx = 8;
			} else
			{
				xProx = xProx + 1;
				yProx = yProx + 1;
			}
		}
		return mov;
	}

	private Movimento pegarProxCapturaDamaDireitaBaixo(Peca p, Tabuleiro t,
			int x, int y, Movimento capturaAnterior)
	{
		boolean encontrouCaptura = false;
		int xProx = x - 1;
		int yProx = y + 1;
		Movimento mov = new Movimento();
		while ((xProx >= 0) && (yProx <= 7) && (!encontrouCaptura))
		{
			if (t.getPeca(xProx, yProx) == null)
			{
				xProx = xProx - 1;
				yProx = yProx + 1;
			} else if ((!t.getPeca(xProx, yProx).getDono().equals(p.getDono())
					&& xProx > 0 && yProx < 7)
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xProx, yProx))))
			{
				if (t.getPeca(xProx - 1, yProx + 1) == null)
				{
					mov.setPeca(p);
					mov.setPosFinalX(xProx - 1);
					mov.setPosFinalY(yProx + 1);
					encontrouCaptura = true;
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					mov.getPecasCapturadas().add(t.getPeca(xProx, yProx));
					xProx = -1;

				} else
				{
					yProx = 8;
				}
			} else
			{
				xProx = xProx - 1;
				yProx = yProx + 1;
			}
		}
		return mov;
	}

	private List<Movimento> mapearMovimentosPosCapturaDamaDireitaBaixo(int x,
			int y, Movimento mov, Peca p, Tabuleiro t)
	{
		boolean encontrouPecaNoCaminho = false;
		int xProx = x - 1;
		int yProx = y + 1;
		List<Movimento> movimentosParada = new ArrayList<Movimento>();
		while ((yProx <= 7) && (xProx >= 0) && (!encontrouPecaNoCaminho))
		{
			if (t.getPeca(xProx, yProx) != null)
				encontrouPecaNoCaminho = true;
			else
			{
				Movimento movAtual = new Movimento();
				movAtual.setPeca(p);
				movAtual.setPosFinalX(xProx);
				movAtual.setPosFinalY(yProx);
				movAtual.setPecasCapturadas(new ArrayList<Peca>());
				movAtual.getPecasCapturadas().addAll(mov.getPecasCapturadas());
				movimentosParada.add(movAtual);
			}
			xProx = xProx - 1;
			yProx = yProx + 1;
		}
		return movimentosParada;
	}

	private List<Movimento> mapearMovimentosPosCapturaDamaDireitaCima(int x,
			int y, Movimento mov, Peca p, Tabuleiro t)
	{
		boolean encontrouPecaNoCaminho = false;
		int xProx = x + 1;
		int yProx = y + 1;
		List<Movimento> movimentosParada = new ArrayList<Movimento>();
		while ((yProx <= 7) && (xProx <= 7) && (!encontrouPecaNoCaminho))
		{
			if (t.getPeca(xProx, yProx) != null)
				encontrouPecaNoCaminho = true;
			else
			{
				Movimento movAtual = new Movimento();
				movAtual.setPeca(p);
				movAtual.setPosFinalX(xProx);
				movAtual.setPosFinalY(yProx);
				movAtual.setPecasCapturadas(new ArrayList<Peca>());
				movAtual.getPecasCapturadas().addAll(mov.getPecasCapturadas());
				movimentosParada.add(movAtual);
			}
			xProx = xProx + 1;
			yProx = yProx + 1;
		}
		return movimentosParada;
	}

	private List<Movimento> mapearMovimentosPosCapturaDamaEsquerdaCima(int x,
			int y, Movimento mov, Peca p, Tabuleiro t)
	{
		boolean encontrouPecaNoCaminho = false;
		int xProx = x + 1;
		int yProx = y - 1;
		List<Movimento> movimentosParada = new ArrayList<Movimento>();
		while ((yProx >= 0) && (xProx <= 7) && (!encontrouPecaNoCaminho))
		{
			if (t.getPeca(xProx, yProx) != null)
				encontrouPecaNoCaminho = true;
			else
			{
				Movimento movAtual = new Movimento();
				movAtual.setPeca(p);
				movAtual.setPosFinalX(xProx);
				movAtual.setPosFinalY(yProx);
				movAtual.setPecasCapturadas(new ArrayList<Peca>());
				movAtual.getPecasCapturadas().addAll(mov.getPecasCapturadas());
				movimentosParada.add(movAtual);
			}
			xProx = xProx + 1;
			yProx = yProx - 1;
		}
		return movimentosParada;
	}

	private List<Movimento> mapearMovimentosPosCapturaDamaEsquerdaBaixo(int x,
			int y, Movimento mov, Peca p, Tabuleiro t)
	{
		boolean encontrouPecaNoCaminho = false;
		int xProx = x - 1;
		int yProx = y - 1;
		List<Movimento> movimentosParada = new ArrayList<Movimento>();
		while ((yProx >= 0) && (xProx >= 0) && (!encontrouPecaNoCaminho))
		{
			if (t.getPeca(xProx, yProx) != null)
				encontrouPecaNoCaminho = true;
			else
			{
				Movimento movAtual = new Movimento();
				movAtual.setPeca(p);
				movAtual.setPosFinalX(xProx);
				movAtual.setPosFinalY(yProx);
				movAtual.setPecasCapturadas(new ArrayList<Peca>());
				movAtual.getPecasCapturadas().addAll(mov.getPecasCapturadas());
				movimentosParada.add(movAtual);
			}
			xProx = xProx - 1;
			yProx = yProx - 1;
		}
		return movimentosParada;
	}

	private List<Movimento> registrarCapturasDamaVarrendoEspaçosVaziosDireitaCima(
			Peca p, Tabuleiro t, int x, int y, Movimento capAnterior)
	{
		int xProx = x;
		int yProx = y;
		List<Movimento> capturasProxEspaçosVazios = new ArrayList<Movimento>();
		List<Movimento> capDirecao = new ArrayList<Movimento>();
		boolean finalizarProcura = false;
		while ((xProx <= 7) && (yProx <= 7) && !finalizarProcura)
		{
			if (t.getPeca(xProx, yProx) != null)
				finalizarProcura = true;
			else
			{
				capturasProxEspaçosVazios = this.gerarPossiveisCapturasDama(p,
						t, xProx, xProx, yProx, yProx, capAnterior);
				if (!capturasProxEspaçosVazios.isEmpty())
				{
					capDirecao.addAll(capturasProxEspaçosVazios);

				}
			}
			xProx = xProx + 1;
			yProx = yProx + 1;
		}
		return capDirecao;
	}

	private List<Movimento> registrarCapturasDamaVarrendoEspaçosVaziosDireitaBaixo(
			Peca p, Tabuleiro t, int x, int y, Movimento capAnterior)
	{
		int xProx = x;
		int yProx = y;
		List<Movimento> capturasProxEspaçosVazios = new ArrayList<Movimento>();
		List<Movimento> capDirecao = new ArrayList<Movimento>();
		boolean finalizarProcura = false;
		while ((xProx >= 0) && (yProx <= 7) && !finalizarProcura)
		{
			if (t.getPeca(xProx, yProx) != null)
				finalizarProcura = true;
			else
			{
				capturasProxEspaçosVazios = this.gerarPossiveisCapturasDama(p,
						t, xProx, xProx, yProx, yProx, capAnterior);
				if (!capturasProxEspaçosVazios.isEmpty())
				{
					capDirecao.addAll(capturasProxEspaçosVazios);

				}
			}
			xProx = xProx - 1;
			yProx = yProx + 1;
		}
		return capDirecao;
	}

	private List<Movimento> registrarCapturasDamaVarrendoEspaçosVaziosEsquerdaBaixo(
			Peca p, Tabuleiro t, int x, int y, Movimento capAnterior)
	{
		int xProx = x;
		int yProx = y;
		List<Movimento> capturasProxEspaçosVazios = new ArrayList<Movimento>();
		List<Movimento> capDirecao = new ArrayList<Movimento>();
		boolean finalizarProcura = false;
		while ((xProx >= 0) && (yProx >= 0) && !finalizarProcura)
		{
			if (t.getPeca(xProx, yProx) != null)
				finalizarProcura = true;
			else
			{
				capturasProxEspaçosVazios = this.gerarPossiveisCapturasDama(p,
						t, xProx, xProx, yProx, yProx, capAnterior);
				if (!capturasProxEspaçosVazios.isEmpty())
				{
					capDirecao.addAll(capturasProxEspaçosVazios);
				}
			}
			xProx = xProx - 1;
			yProx = yProx - 1;
		}
		return capDirecao;
	}

	private List<Movimento> registrarCapturasDamaVarrendoEspaçosVaziosEsquerdaCima(
			Peca p, Tabuleiro t, int x, int y, Movimento capAnterior)
	{
		int xProx = x;
		int yProx = y;
		List<Movimento> capturasProxEspaçosVazios = new ArrayList<Movimento>();
		List<Movimento> capDirecao = new ArrayList<Movimento>();
		boolean finalizarProcura = false;
		while ((xProx <= 7) && (yProx >= 0) && !finalizarProcura)
		{
			if (t.getPeca(xProx, yProx) != null)
				finalizarProcura = true;
			else
			{
				capturasProxEspaçosVazios = this.gerarPossiveisCapturasDama(p,
						t, xProx, xProx, yProx, yProx, capAnterior);
				if (!capturasProxEspaçosVazios.isEmpty())
				{
					capDirecao.addAll(capturasProxEspaçosVazios);

				}
			}
			xProx = xProx + 1;
			yProx = yProx - 1;
		}
		return capDirecao;
	}

	private List<Movimento> gerarPossiveisCapturasDama(Peca p, Tabuleiro t,
			int xCima, int xBaixo, int yDir, int yEsq, Movimento capAnterior)
	{
		List<Movimento> capturasDaDama = new ArrayList<Movimento>();
		if (xCima <= 7)
		{// captura para cima
			if (yDir <= 7)
			{// captura para direita
				Movimento mov = new Movimento();
				mov.setPecasCapturadas(new ArrayList<Peca>());
				mov = pegarProxCapturaDamaDireitaCima(p, t, xCima, yDir,
						capAnterior);
				if (mov.getPecasCapturadas() == null)
				{
					mov = new Movimento();
					mov.setPecasCapturadas(new ArrayList<Peca>());
				} else
				{
					capturasDaDama.add(mov);
					List<Movimento> capturasNosProximosEspaçosVazios = registrarCapturasDamaVarrendoEspaçosVaziosDireitaCima(
							p, t, mov.getPosFinalX(), mov.getPosFinalY(), mov);
					List<Movimento> movimentosPosCaptura = mapearMovimentosPosCapturaDamaDireitaCima(
							mov.getPosFinalX(), mov.getPosFinalY(), mov, p, t);
					capturasDaDama.addAll(capturasNosProximosEspaçosVazios);
					capturasDaDama.addAll(movimentosPosCaptura);
				}
			}
			if (yEsq >= 0)
			{// captura para esquerda
				Movimento mov = new Movimento();
				mov.setPecasCapturadas(new ArrayList<Peca>());
				mov = pegarProxCapturaDamaEsquerdaCima(p, t, xCima, yEsq,
						capAnterior);
				if (mov.getPecasCapturadas() == null)
				{
					mov = new Movimento();
					mov.setPecasCapturadas(new ArrayList<Peca>());

				} else
				{
					capturasDaDama.add(mov);
					List<Movimento> capturasNosProximosEspaçosVazios = registrarCapturasDamaVarrendoEspaçosVaziosEsquerdaCima(
							p, t, mov.getPosFinalX(), mov.getPosFinalY(), mov);
					List<Movimento> movimentosPosCaptura = mapearMovimentosPosCapturaDamaEsquerdaCima(
							mov.getPosFinalX(), mov.getPosFinalY(), mov, p, t);
					capturasDaDama.addAll(capturasNosProximosEspaçosVazios);
					capturasDaDama.addAll(movimentosPosCaptura);
				}
			}
		}
		if (xBaixo >= 0)
		{// captura para baixo
			if (yDir <= 7)
			{// captura para direita
				Movimento mov = new Movimento();
				mov.setPecasCapturadas(new ArrayList<Peca>());
				mov = pegarProxCapturaDamaDireitaBaixo(p, t, xBaixo, yDir,
						capAnterior);
				if (mov.getPecasCapturadas() == null)
				{
					mov = new Movimento();
					mov.setPecasCapturadas(new ArrayList<Peca>());
				} else
				{
					capturasDaDama.add(mov);
					// List<Movimento>capturasSequencia =
					// this.gerarPossiveisCapturasDama(p, t,mov.getPosFinalX(),
					// mov.getPosFinalX(),mov.getPosFinalY(),mov.getPosFinalY(),
					// mov);
					List<Movimento> capturasNosProximosEspaçosVazios = registrarCapturasDamaVarrendoEspaçosVaziosDireitaBaixo(
							p, t, mov.getPosFinalX(), mov.getPosFinalY(), mov);
					List<Movimento> movimentosPosCaptura = mapearMovimentosPosCapturaDamaDireitaBaixo(
							mov.getPosFinalX(), mov.getPosFinalY(), mov, p, t);
					// .addAll(capturasSequencia);
					capturasDaDama.addAll(capturasNosProximosEspaçosVazios);
					capturasDaDama.addAll(movimentosPosCaptura);
				}
			}
			if (yEsq >= 0)
			{// captura para esquerda
				Movimento mov = new Movimento();
				mov.setPecasCapturadas(new ArrayList<Peca>());
				mov = pegarProxCapturaDamaEsquerdaBaixo(p, t, xBaixo, yEsq,
						capAnterior);
				if (mov.getPecasCapturadas() == null)
				{
					mov = new Movimento();
					mov.setPecasCapturadas(new ArrayList<Peca>());

				} else
				{
					capturasDaDama.add(mov);
					// List<Movimento>capturasSequencia =
					// this.gerarPossiveisCapturasDama(p,
					// t,mov.getPosFinalX(),mov.getPosFinalX(),
					// mov.getPosFinalY(),mov.getPosFinalY(), mov);
					List<Movimento> capturasNosProximosEspaçosVazios = registrarCapturasDamaVarrendoEspaçosVaziosEsquerdaBaixo(
							p, t, mov.getPosFinalX(), mov.getPosFinalY(), mov);
					List<Movimento> movimentosPosCaptura = mapearMovimentosPosCapturaDamaEsquerdaBaixo(
							mov.getPosFinalX(), mov.getPosFinalY(), mov, p, t);
					// capturasDaDama.addAll(capturasSequencia);
					capturasDaDama.addAll(capturasNosProximosEspaçosVazios);
					capturasDaDama.addAll(movimentosPosCaptura);
				}
			}
		}
		return capturasDaDama;
	}

	private List<Movimento> gerarPossiveisCapturasPecaComum(Peca p,
			Tabuleiro t, int x, int y, Movimento capturaAnterior)
	{
		int xCima = x + 1;
		int xBaixo = x - 1;
		int yDir = y + 1;
		int yEsq = y - 1;
		List<Movimento> capturasDaPeca = new ArrayList<Movimento>();
		// captura pra cima
		if (xCima < 7)
		{
			// captura pra direita
			if (yDir < 7
					&& t.getPeca(xCima, yDir) != null
					&& !t.getPeca(xCima, yDir).getDono().equals(p.getDono())
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xCima, yDir))))
			{
				if (t.getPeca(xCima + 1, yDir + 1) == null)
				{
					Movimento mov = new Movimento();
					mov.setPeca(p);
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
					{
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					}
					mov.getPecasCapturadas().add(t.getPeca(xCima, yDir));
					mov.setPosFinalX(xCima + 1);
					mov.setPosFinalY(yDir + 1);
					capturasDaPeca.add(mov);
					List<Movimento> capturasSequencia = this
							.gerarPossiveisCapturasPecaComum(p, t,
									mov.getPosFinalX(), mov.getPosFinalY(), mov);
					capturasDaPeca.addAll(capturasSequencia);
				}
			}
			// captura pra esq
			if (yEsq > 0
					&& t.getPeca(xCima, yEsq) != null
					&& !t.getPeca(xCima, yEsq).getDono().equals(p.getDono())
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xCima, yEsq))))
			{
				if (t.getPeca(xCima + 1, yEsq - 1) == null)
				{
					Movimento mov = new Movimento();
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
					{
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					}
					mov.setPeca(p);
					mov.getPecasCapturadas().add(t.getPeca(xCima, yEsq));
					mov.setPosFinalX(xCima + 1);
					mov.setPosFinalY(yEsq - 1);
					capturasDaPeca.add(mov);
					List<Movimento> capturasSequencia = this
							.gerarPossiveisCapturasPecaComum(p, t,
									mov.getPosFinalX(), mov.getPosFinalY(), mov);
					capturasDaPeca.addAll(capturasSequencia);
				}
			}
		}
		// captura pra baixo
		if (xBaixo > 0)
		{
			// captura pra direita
			if (yDir < 7
					&& t.getPeca(xBaixo, yDir) != null
					&& !t.getPeca(xBaixo, yDir).getDono().equals(p.getDono())
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xBaixo, yDir))))
			{
				if (t.getPeca(xBaixo - 1, yDir + 1) == null)
				{
					Movimento mov = new Movimento();
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
					{
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					}
					mov.setPeca(p);
					mov.getPecasCapturadas().add(t.getPeca(xBaixo, yDir));
					mov.setPosFinalX(xBaixo - 1);
					mov.setPosFinalY(yDir + 1);
					capturasDaPeca.add(mov);
					List<Movimento> capturasSequencia = this
							.gerarPossiveisCapturasPecaComum(p, t,
									mov.getPosFinalX(), mov.getPosFinalY(), mov);
					capturasDaPeca.addAll(capturasSequencia);
				}
			}
			// captura pra esq
			if (yEsq > 0
					&& t.getPeca(xBaixo, yEsq) != null
					&& !t.getPeca(xBaixo, yEsq).getDono().equals(p.getDono())
					&& (capturaAnterior == null || !capturaAnterior
							.getPecasCapturadas().contains(
									t.getPeca(xBaixo, yEsq))))
			{
				if (t.getPeca(xBaixo - 1, yEsq - 1) == null)
				{
					Movimento mov = new Movimento();
					mov.setPecasCapturadas(new ArrayList<Peca>());
					if (capturaAnterior != null)
					{
						mov.getPecasCapturadas().addAll(
								capturaAnterior.getPecasCapturadas());
					}
					mov.setPeca(p);
					mov.getPecasCapturadas().add(t.getPeca(xBaixo, yEsq));
					mov.setPosFinalX(xBaixo - 1);
					mov.setPosFinalY(yEsq - 1);
					capturasDaPeca.add(mov);
					List<Movimento> capturasSequencia = this
							.gerarPossiveisCapturasPecaComum(p, t,
									mov.getPosFinalX(), mov.getPosFinalY(), mov);
					capturasDaPeca.addAll(capturasSequencia);
				}
			}

		}
		return capturasDaPeca;
	}

	public List<Movimento> getCapturas()
	{
		return capturas;
	}

	public void setCapturas(List<Movimento> capturas)
	{
		this.capturas = capturas;
	}

	public int getMovDamaSemCaptura()
	{
		return movDamaSemCaptura;
	}

	public Jogada getUltimaJogadaFeita()
	{
		return ultimaJogada;
	}

	public void setMovDamaSemCaptura(int movDamaSemCaptura)
	{
		this.movDamaSemCaptura = movDamaSemCaptura;
	}
}