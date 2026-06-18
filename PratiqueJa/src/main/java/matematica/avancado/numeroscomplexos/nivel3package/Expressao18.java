package matematica.avancado.numeroscomplexos.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Scenarios: {r, n, thetaDenom, rPowN, cosNTheta, result}
		// θ = π/thetaDenom; nθ = n·π/thetaDenom
		int[][] sc = {
			{1, 3, 3, 1, -1, -1},  // r=1, n=3, θ=π/3 → nθ=π  → result=-1
			{1, 4, 4, 1, -1, -1},  // r=1, n=4, θ=π/4 → nθ=π  → result=-1
			{1, 6, 6, 1, -1, -1},  // r=1, n=6, θ=π/6 → nθ=π  → result=-1
			{1, 8, 4, 1,  1,  1},  // r=1, n=8, θ=π/4 → nθ=2π → result=1
			{2, 3, 3, 8, -1, -8},  // r=2, n=3, θ=π/3 → nθ=π  → result=-8
			{2, 4, 4, 16,-1,-16},  // r=2, n=4, θ=π/4 → nθ=π  → result=-16
		};
		String[] nThetaStrs = {"\\pi", "\\pi", "\\pi", "2\\pi", "\\pi", "\\pi"};

		int idx  = rand.nextInt(sc.length);
		int r    = sc[idx][0];
		int n    = sc[idx][1];
		int den  = sc[idx][2];
		int rPow = sc[idx][3];
		int cosV = sc[idx][4];
		int res  = sc[idx][5];

		String thetaStr = "\\dfrac{\\pi}{" + den + "}";
		String nThetaStr = nThetaStrs[idx];

		String zDisplay = r == 1
			? "\\cos " + thetaStr + " + i\\sin " + thetaStr
			: r + "\\left(\\cos " + thetaStr + " + i\\sin " + thetaStr + "\\right)";

		addParagrafo("Usando a Fórmula de De Moivre, calcule \\(z^{" + n + "}\\).");
		addParagrafo("\\(z = " + zDisplay + "\\)");

		String correct = "\\(" + res + "\\)";
		String iStr = rPow == 1 ? "i" : rPow + "i";
		List<String> dist = new ArrayList<>();
		dist.add("\\(" + (-res) + "\\)");
		dist.add("\\(" + iStr + "\\)");
		dist.add("\\(-" + iStr + "\\)");
		embaralharEAdicionarAlternativas(correct, dist);

		String resolucao = "Pela Fórmula de De Moivre: \\(\\\\\\)";
		resolucao += "\\([r(\\cos\\theta+i\\sin\\theta)]^n = r^n(\\cos n\\theta+i\\sin n\\theta)\\). \\(\\\\\\)";
		resolucao += "\\(r^n = " + r + "^{" + n + "} = " + rPow + "\\). \\(\\\\\\)";
		resolucao += "\\(n\\theta = " + n + " \\cdot \\dfrac{\\pi}{" + den + "} = " + nThetaStr + "\\). \\(\\\\\\)";
		resolucao += "\\(\\cos(" + nThetaStr + ") = " + cosV + ", \\quad \\sin(" + nThetaStr + ") = 0\\). \\(\\\\\\)";
		resolucao += "\\(z^{" + n + "} = " + rPow + " \\cdot (" + cosV + " + 0i) = \\mathbf{" + res + "}\\)";
		setResolucao(resolucao);
	}
}
