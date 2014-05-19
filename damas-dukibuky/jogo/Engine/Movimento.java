package Engine;

import java.util.List;

public class Movimento
{

	private int posFinalX;
	private int posFinalY;
	private List<Peca> pecasCapturadas;
	private Peca peca;

	public List<Peca> getPecasCapturadas()
	{
		return pecasCapturadas;
	}

	public void setPecasCapturadas(List<Peca> pecasCapturadas)
	{
		this.pecasCapturadas = pecasCapturadas;
	}

	public Peca getPeca()
	{
		return peca;
	}

	public void setPeca(Peca peca)
	{
		this.peca = peca;
	}

	public int getPosFinalX()
	{
		return posFinalX;
	}

	public void setPosFinalX(int posFinalX)
	{
		this.posFinalX = posFinalX;
	}

	public int getPosFinalY()
	{
		return posFinalY;
	}

	public void setPosFinalY(int posFinalY)
	{
		this.posFinalY = posFinalY;
	}

}