package matematica.avancado.leisenocosseno.config;

import static matematica.ConfigImagem.IMG_H;
import static matematica.ConfigImagem.IMG_W;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrianguloGerador {

    private static final int width = IMG_W;
    private static final int height = IMG_H;
    private static final int MIN_DISTANCE = 25;
    private static final int MIN_ANGLE = 30;
    private static final Random rand = new Random();
    
    public static class Triangulo {
        public Point p1;
        public Point p2;
        public Point p3;

        public Triangulo(Point p1, Point p2, Point p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }

        @Override
        public String toString() {
            return "new Triangulo(new Point(" + p1.x + ", " + p1.y + "), new Point(" + p2.x + ", " + p2.y + "), new Point(" + p3.x + ", " + p3.y + ")),";
        }
    }

    public static int[] mudaOrdem(int vet[])
    {
    	int aux,indexA,indexB;
    	
    	for(int i = 0; i < vet.length; i++)
		{
    		indexA=rand.nextInt(vet.length);
    		indexB=rand.nextInt(vet.length);
    		aux=vet[indexA];
    		vet[indexA]=vet[indexB];
    		vet[indexB]=aux;
		}
    	return vet;
    }
    
    public static double distancia(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public static double calcularAngulo(double a, double b, double c) {
        double cosC = (a * a + b * b - c * c) / (2 * a * b);
        if (cosC > 1.0) cosC = 1.0;
        if (cosC < -1.0) cosC = -1.0;
        return Math.toDegrees(Math.acos(cosC));
    }
    
    public static List<Triangulo> gerarTriangulos(int quantidade) 
    {
        List<Triangulo> triangulos = new ArrayList<>();
        
        while (triangulos.size() < quantidade) 
        {
        	int aleX=25+rand.nextInt(1200);
        	int aleY=25+rand.nextInt(700);
        	int xs[]= {25,1225,aleX};
        	int ys[]= {25,725,aleY};
        	xs=mudaOrdem(xs);
        	ys=mudaOrdem(ys);
        	
            Point p1 = new Point(xs[0],ys[0]);
            Point p2 = new Point(xs[1],ys[1]);
            Point p3 = new Point(xs[2],ys[2]);
            Triangulo triangulo = new Triangulo(p1, p2, p3);
            if(angulosValidos(triangulo))
            	triangulos.add(triangulo);
        }

        return triangulos;
    }

    private static boolean verticesDistintos(Triangulo t) {
        return !t.p1.equals(t.p2) && !t.p1.equals(t.p3) && !t.p2.equals(t.p3);
    }

    private static boolean dentroDasBordas(Triangulo t) {
        return t.p1.x >= MIN_DISTANCE && t.p1.x <= width - MIN_DISTANCE &&
               t.p1.y >= MIN_DISTANCE && t.p1.y <= height - MIN_DISTANCE &&
               t.p2.x >= MIN_DISTANCE && t.p2.x <= width - MIN_DISTANCE &&
               t.p2.y >= MIN_DISTANCE && t.p2.y <= height - MIN_DISTANCE &&
               t.p3.x >= MIN_DISTANCE && t.p3.x <= width - MIN_DISTANCE &&
               t.p3.y >= MIN_DISTANCE && t.p3.y <= height - MIN_DISTANCE;
    }

    private static boolean angulosValidos(Triangulo t) {
        double a = distancia(t.p2, t.p3);
        double b = distancia(t.p1, t.p3);
        double c = distancia(t.p1, t.p2);

        if (a == 0 || b == 0 || c == 0) return false; // Evita divisão por zero

        double anguloA = calcularAngulo(b, c, a);
        double anguloB = calcularAngulo(a, c, b);
        double anguloC = calcularAngulo(a, b, c);

        return anguloA >= MIN_ANGLE && anguloB >= MIN_ANGLE && anguloC >= MIN_ANGLE;
    }
    
    public void ajusta()
    {
    	
    }

    public static void main(String[] args) 
    {
//        List<Triangulo> oitentaTriangulos = gerarTriangulos(160);
//        for (Triangulo triangulo : oitentaTriangulos) {
//        }
    }
}
