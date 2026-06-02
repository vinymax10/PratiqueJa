package matematica;


public class MMC
{
	public static long mdc(long a, long b)
	{
		while(b != 0)
		{
			long r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	public static long mmc(long a, long b)
	{
		return a * (b / mdc(a, b));
	}

	public static long mmc(long a, long b, long c)
	{
		return mmc(a, mmc(b, c));
	}

	public static long mdc(long a, long b, long c)
	{
		return mdc(a, mdc(b, c));
	}

	public static void main(String[] args)
	{
	}
}
