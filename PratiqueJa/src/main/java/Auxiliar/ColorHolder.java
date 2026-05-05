package Auxiliar;

public final class ColorHolder
{
	private static final ThreadLocal<String> COLOR = ThreadLocal.withInitial(() -> "#4d4d4d");
	private static final ThreadLocal<String> FORMULA = ThreadLocal.withInitial(() -> "#4059e3");

	private ColorHolder()
	{
	}

	public static String getCOLOR()
	{
		return COLOR.get();
	}

	public static void setCOLOR(String color)
	{
		COLOR.set(color);
	}
	
	public static String getFORMULA()
	{
		return FORMULA.get();
	}

	public static void setFORMULA(String color)
	{
		FORMULA.set(color);
	}

	public static void clear()
	{
		COLOR.remove();
		FORMULA.remove();
	}
}
