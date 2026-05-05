package Matematica;

public enum Alinhamento
{
	TopLeft(1), TopCenter(2), TopRight(3), MiddleLeft(4), MiddleCenter(5), MiddleRight(6), BottomLeft(7), BottomCenter(8), BottomRight(9);

	private int number;

	Alinhamento(int number)
	{
		this.number = number;
	}

	public boolean equals(Alinhamento x)
	{
		return this.number == x.number;
	}

}
