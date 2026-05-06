package auxiliar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.primefaces.model.file.UploadedFile;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import matematica.Alinhamento;
import matematica.ParCor;
import net.coobird.thumbnailator.Thumbnails;

public class Graphics
{
	
	public static byte[] shapeImage(UploadedFile uploadedFile, int targetWidth, int targetHeight) throws IOException
	{
//		// resizeImage
//		BufferedImage bufferedImage = ImageIO.read(uploadedFile.getInputStream());
//		Image resultingImage = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
//		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
//		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
//
////	    compressao da imagem
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
////	    compressImage(outputImage, baos, 50);
//		ImageIO.write(outputImage, "png", baos);
//
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    
//	    Thumbnails.of(uploadedFile.getInputStream())
//	    .size(targetWidth, targetHeight)
//	    .keepAspectRatio(true)
//	    .outputFormat("png")
//	    .toOutputStream(baos);
	    
	    BufferedImage resizedImage = Thumbnails.of(uploadedFile.getInputStream())
        .size(targetWidth, targetHeight)
        .keepAspectRatio(false)
        .asBufferedImage();
	    
	    ImageIO.write(resizedImage, "png", baos);
	
		return baos.toByteArray();

	}
	
	public static BufferedImage resize(BufferedImage image, double scale)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage after = new BufferedImage((int) scale * w, (int) scale * h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(image, after);
		return after;
	}

	public static int widthLabel(String texto)
	{
		TeXFormula formula = new TeXFormula(text(texto));
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(18).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		return icon.getIconWidth();
	}

	public static int HeightLabel(String texto)
	{
		TeXFormula formula = new TeXFormula(text(texto));
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(18).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		return icon.getIconHeight();
	}
	
	public static String text(String texto)
	{
//		0.3, 0.3, 0.3
		String cor = CorAux.convertHexPorc(ColorHolder.getCOLOR());
		return "\\definecolor{cinza}{rgb}{"+cor+"}\\textcolor{cinza}{\\mathsf{"+texto+"}}";
	}
	
	public static void addLabel(Graphics2D g2, String texto, int x, int y, Alinhamento alinhamento)
	{
		TeXFormula formula = new TeXFormula(text(texto));
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(90).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		if(alinhamento == Alinhamento.BottomLeft || alinhamento == Alinhamento.BottomCenter
		|| alinhamento == Alinhamento.BottomRight)
			y -= icon.getIconHeight();

		if(alinhamento == Alinhamento.MiddleCenter || alinhamento == Alinhamento.MiddleLeft
		|| alinhamento == Alinhamento.MiddleRight)
			y -= icon.getIconHeight() / 2;

		if(alinhamento == Alinhamento.BottomCenter || alinhamento == Alinhamento.MiddleCenter
		|| alinhamento == Alinhamento.TopCenter)
			x -= icon.getIconWidth() / 2;

		if(alinhamento == Alinhamento.BottomRight || alinhamento == Alinhamento.MiddleRight
		|| alinhamento == Alinhamento.TopRight)
			x -= icon.getIconWidth();

		icon.paintIcon(null, g2, x, y);
	}

	public static void addLabel(Graphics2D g2, String texto, int x, int y, Alinhamento alinhamento, int size)
	{
		TeXFormula formula = new TeXFormula(text(texto));
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(size).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		if(alinhamento == Alinhamento.BottomLeft || alinhamento == Alinhamento.BottomCenter
		|| alinhamento == Alinhamento.BottomRight)
			y -= icon.getIconHeight();

		if(alinhamento == Alinhamento.MiddleCenter || alinhamento == Alinhamento.MiddleLeft
		|| alinhamento == Alinhamento.MiddleRight)
			y -= icon.getIconHeight() / 2;

		if(alinhamento == Alinhamento.BottomCenter || alinhamento == Alinhamento.MiddleCenter
		|| alinhamento == Alinhamento.TopCenter)
			x -= icon.getIconWidth() / 2;

		if(alinhamento == Alinhamento.BottomRight || alinhamento == Alinhamento.MiddleRight
		|| alinhamento == Alinhamento.TopRight)
			x -= icon.getIconWidth();

		icon.paintIcon(null, g2, x, y);
	}

	public static void addLabel(Graphics2D g2, String texto, int x, int y, Alinhamento alinhamento, int size,
	double angle)
	{
		TeXFormula formula = new TeXFormula(text(texto));
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(size).build();
		icon.setInsets(new Insets(0, 0, 0, 0));
		if(alinhamento == Alinhamento.BottomLeft || alinhamento == Alinhamento.BottomCenter
		|| alinhamento == Alinhamento.BottomRight)
			y -= icon.getIconHeight();

		if(alinhamento == Alinhamento.MiddleCenter || alinhamento == Alinhamento.MiddleLeft
		|| alinhamento == Alinhamento.MiddleRight)
			y -= icon.getIconHeight() / 2;

		if(alinhamento == Alinhamento.BottomCenter || alinhamento == Alinhamento.MiddleCenter
		|| alinhamento == Alinhamento.TopCenter)
			x -= icon.getIconWidth() / 2;

		if(alinhamento == Alinhamento.BottomRight || alinhamento == Alinhamento.MiddleRight
		|| alinhamento == Alinhamento.TopRight)
			x -= icon.getIconWidth();

		g2.rotate(-angle * Math.PI / 180, x + icon.getIconWidth() / 2, y + icon.getIconHeight() / 2);
		icon.paintIcon(null, g2, x, y);
		g2.rotate(angle * Math.PI / 180, x + icon.getIconWidth() / 2, y + icon.getIconHeight() / 2);
	}

	public static void addAngleReto(Graphics2D g2, int x, int y, int lado)
	{
		Graphics2D g2Reto = g2;
		g2Reto.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, lado, lado);
		g2Reto.draw(rectangle2D);

		Graphics2D ponto = (Graphics2D) g2;
		ponto.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Shape shape = new Ellipse2D.Double(rectangle2D.getCenterX() - (lado / 10),
		rectangle2D.getCenterY() - (lado / 10), lado / 5, lado / 5);
		g2Reto.fill(shape);
	}

	public static void setLineTracejada(Graphics2D g2, double iniX, double iniY, double endX, double endY,
	boolean inicio, boolean fim)
	{
		g2.setStroke(new BasicStroke(5));

		Line2D line;
		AffineTransform tx;
		double angle;
		line = new Line2D.Double(-25, 0, 25, 0);
		tx = new AffineTransform();

		if(inicio)
		{
			tx.setToIdentity();
			angle = Math.atan2(endY - iniY, endX - iniX);
			tx.translate(iniX, iniY);
			tx.rotate(angle - Math.PI / 2d);

			g2.setTransform(tx);
			g2.draw(line);
		}

		if(fim)
		{
			tx.setToIdentity();
			angle = Math.atan2(endY - iniY, endX - iniX);
			tx.translate(endX, endY);
			tx.rotate(angle - Math.PI / 2d);

			g2.setTransform(tx);
			g2.draw(line);
		}

		tx.setToIdentity();
		g2.setTransform(tx);

		Stroke s = new BasicStroke(5.0f, // Width
		BasicStroke.CAP_SQUARE, // End cap
		BasicStroke.JOIN_MITER, // Join style
		10.0f, // Miter limit
		new float[] { 20.0f, 30.0f }, // Dash pattern
		0.0f); // Dash phase

		g2.setStroke(s);

		g2.draw(new Line2D.Double(iniX, iniY, endX, endY));

		g2.setStroke(new BasicStroke(10));
	}

	public static void setHint(Graphics2D g2)
	{
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	}

	public static void arrow(Graphics2D g2, int iniX, int iniY, int endX, int endY, boolean setaIni, boolean setaEnd)
	{
		g2.drawLine(iniX, iniY, endX, endY);
		Polygon arrowHead;
		AffineTransform tx;
		double angle;

		if(setaEnd)
		{
			arrowHead = new Polygon();
			tx = new AffineTransform();

			arrowHead.addPoint(0, 25);
			arrowHead.addPoint(-15, -25);
			arrowHead.addPoint(15, -25);

			tx.setToIdentity();
			angle = Math.atan2(endY - iniY, endX - iniX);
			tx.translate(endX, endY);
			tx.rotate(angle - Math.PI / 2d);

			g2.setTransform(tx);
			g2.fill(arrowHead);

			tx.setToIdentity();
			g2.setTransform(tx);
		}

//	        --------------------
		if(setaIni)
		{
			arrowHead = new Polygon();
			tx = new AffineTransform();

			arrowHead.addPoint(0, 25);
			arrowHead.addPoint(-15, -25);
			arrowHead.addPoint(15, -25);

			tx.setToIdentity();
			angle = Math.atan2(endY - iniY, endX - iniX);
			tx.translate(iniX, iniY);
			tx.rotate(Math.PI + angle - Math.PI / 2d);

			g2.setTransform(tx);
			g2.fill(arrowHead);

			tx.setToIdentity();
			g2.setTransform(tx);
		}
	}

	public static void ladoEquivalente(Graphics2D g2, int iniX, int iniY, int endX, int endY)
	{
		Line2D line;
		AffineTransform tx;
		double angle;
		line = new Line2D.Double(-25, 0, 25, 0);
		tx = new AffineTransform();

		tx.setToIdentity();
		angle = Math.atan2(endY - iniY, endX - iniX);
		tx.translate(Math.min(endX, iniX) + Math.abs((endX - iniX) / 2),
		Math.min(endY, iniY) + Math.abs((endY - iniY) / 2));
		tx.rotate(angle - Math.PI / 2d);

		g2.setTransform(tx);
		g2.draw(line);

		tx.setToIdentity();
		g2.setTransform(tx);
	}

	public static void setAngle(Graphics2D g2, int x, int y, int raio, int inicioAngle, int angle, ParCor p1, ParCor p2)
	{
		Arc2D arc = new Arc2D.Double(x, y, raio, raio, inicioAngle, angle, Arc2D.PIE);

		g2.setColor(Color.decode(p1.getCorFraca()));

		g2.fill(arc);

		g2.setColor(Color.decode(p2.getCorForte()));

		g2.draw(arc);
	}

	public static void setArco(Graphics2D g2, int x, int y, int raio, int inicioAngle, int angle, ParCor p1, ParCor p2)
	{
		Arc2D arc = new Arc2D.Double(x, y, raio, raio, inicioAngle, angle, Arc2D.OPEN);
		g2.setColor(Color.decode(p1.getCorForte()));
		g2.setStroke(new BasicStroke(20));
		g2.draw(arc);

		g2.setColor(Color.decode(p2.getCorForte()));
		g2.setStroke(new BasicStroke(10));
	}

	public static void setAngleSemBorda(Graphics2D g2, int x, int y, int raio, int inicioAngle, int angle, ParCor p1,
	ParCor p2)
	{
		Arc2D arc = new Arc2D.Double(x, y, raio, raio, inicioAngle, angle, Arc2D.PIE);

		g2.setColor(Color.decode(p1.getCorFraca()));
		g2.fill(arc);

		g2.setColor(Color.decode(p2.getCorForte()));
	}

	public static ByteArrayOutputStream salvar(BufferedImage image, boolean file, String nome)
	{
		if(file)
		{
			File outputfile = new File(nome);
			try
			{
				ImageIO.write(image, "png", outputfile);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		else
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			try
			{
				ImageIO.write(image, "png", baos);
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}

			return baos;
		}
	}

	public static void setAngleSemBorda(Graphics2D g2, Point um, Point dois, Point tres, int raio, ParCor p1, ParCor p2)
	{
		double startDeg = calculaStartAngle(um, dois, tres);
		double angleDeg = calculaAngle(um, dois, tres);
		Arc2D arc = new Arc2D.Double(um.x - raio / 2, um.y - raio / 2, raio, raio, startDeg, angleDeg, Arc2D.PIE);

		g2.setColor(Color.decode(p1.getCorFraca()));
		g2.fill(arc);

		g2.setColor(Color.decode(p2.getCorForte()));
	}

	public static double calculaAngle(Point um, Point dois, Point tres)
	{
		double v1x = dois.x - um.x;
		double v1y = dois.y - um.y;
		double v2x = tres.x - um.x;
		double v2y = tres.y - um.y;

		// Produto escalar
		double dot = v1x * v2x + v1y * v2y;

		// Módulos dos vetores
		double mag1 = Math.sqrt(v1x * v1x + v1y * v1y);
		double mag2 = Math.sqrt(v2x * v2x + v2y * v2y);

		// Cálculo do ângulo em radianos e depois em graus
		double cosTheta = dot / (mag1 * mag2);
		double angleRad = Math.acos(cosTheta);
		double angleDeg = Math.toDegrees(angleRad);
		
		return angleDeg;
	}

	public static double calculaStartAngle(Point um, Point dois, Point tres)
	{
		double v1x = dois.x - um.x;
		double v1y = dois.y - um.y;
		double v2x = tres.x - um.x;
		double v2y = tres.y - um.y;

		double startRad1 = Math.atan2(-v1y, v1x);
		double startDeg1 = Math.toDegrees(startRad1);

		double startRad2 = Math.atan2(-v2y, v2x);
		double startDeg2 = Math.toDegrees(startRad2);

		if(startDeg1 < 0)
			startDeg1 += 360;
		if(startDeg2 < 0)
			startDeg2 += 360;

		double max = Math.max(startDeg1, startDeg2);
		double min = Math.min(startDeg1, startDeg2);

		double startDeg;
		if((max - min) < (360 - max + min))
			startDeg = min;
		else
			startDeg = max;
		return startDeg;
	}

	public static double calculaMedianAngle(Point um, Point dois, Point tres)
	{
		double v1x = dois.x - um.x;
		double v1y = dois.y - um.y;
		double v2x = tres.x - um.x;
		double v2y = tres.y - um.y;

		// Produto escalar
		double dot = v1x * v2x + v1y * v2y;

		// Módulos dos vetores
		double mag1 = Math.sqrt(v1x * v1x + v1y * v1y);
		double mag2 = Math.sqrt(v2x * v2x + v2y * v2y);

		// Cálculo do ângulo em radianos e depois em graus
		double cosTheta = dot / (mag1 * mag2);
		double angleRad = Math.acos(cosTheta);
		double angleDeg = Math.toDegrees(angleRad);

		double startRad1 = Math.atan2(-v1y, v1x);
		double startDeg1 = Math.toDegrees(startRad1);

		double startRad2 = Math.atan2(-v2y, v2x);
		double startDeg2 = Math.toDegrees(startRad2);

		if(startDeg1 < 0)
			startDeg1 += 360;
		if(startDeg2 < 0)
			startDeg2 += 360;

		double max = Math.max(startDeg1, startDeg2);
		double min = Math.min(startDeg1, startDeg2);

		double startDeg;
		if((max - min) < (360 - max + min))
			startDeg = min;
		else
			startDeg = max;

		double mediana=startDeg + (angleDeg / 2);
		mediana = mediana%360;
		
		return mediana;
	}

	public static double calculaPerpendicularAngle(Point um, Point dois)
	{
	    double dx = dois.x - um.x;
        double dy = um.y - dois.y;
        // Vetor perpendicular (por exemplo, rotacionando 90°)
        double perpDx = -dy;
        double perpDy = dx;

        // Calculando os ângulos
//        double angleOriginal = Math.atan2(dy, dx); // Ângulo da reta original
        double anglePerp = Math.atan2(perpDy, perpDx); // Ângulo do vetor perpendicular

        // Calculando a diferença (em radianos)
//        double angleDifference = Math.abs(angleOriginal - anglePerp);

        // Convertendo para graus
//        double angleDifferenceDegrees = Math.toDegrees(angleDifference);
        
        return Math.toDegrees(anglePerp);
	}

	
	public static void addLabel(Graphics2D g2, String texto, Point um, Point dois, Point tres, int raio)
	{
		TeXFormula formula = new TeXFormula(text(texto));
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(90).build();
		icon.setInsets(new Insets(0, 0, 0, 0));

		double mediana = calculaMedianAngle(um, dois, tres);
		double angleInRadians = Math.toRadians(mediana);

		int x = (int) (um.x + ((raio / 2) + ((icon.getIconWidth() / 2))) * Math.cos(angleInRadians));
		int y = (int) (um.y - ((raio / 2) + (icon.getIconHeight() / 2)) * Math.sin(angleInRadians));

		x -= (icon.getIconWidth() / 2);
		y -= (icon.getIconHeight() / 2);

		icon.paintIcon(null, g2, x, y);
	}

	public static void addLabel(Graphics2D g2, String texto, Point um, Point dois, Point tres)
	{
		TeXFormula formula = new TeXFormula(text(texto));
		TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(90).build();
		icon.setInsets(new Insets(0, 0, 0, 0));

		double mediana = calculaMedianAngle(um, dois, tres);
//		double angleInRadians = Math.toRadians(mediana);

		int x2 = (int) ((dois.x + tres.x) / 2.0);
		int y2 = (int) ((dois.y + tres.y) / 2.0);

		double angleperpendicular =calculaPerpendicularAngle(dois,tres);
		double angleperpendicularOposto =(angleperpendicular+180)%360;

		if(distancia(mediana,angleperpendicular)>distancia(mediana,angleperpendicularOposto))
			angleperpendicular=angleperpendicularOposto;
		
		double angleInRadians = Math.toRadians(angleperpendicular);

//		Ellipse2D circulo = new Ellipse2D.Double(x2, y2, 10, 10);
//		g2.draw(circulo);
		
		int x =	(int)(x2 + ((icon.getIconWidth() / 2)+10) * Math.cos(angleInRadians));
		int y =	(int)(y2 - ((icon.getIconHeight() / 2)+10) * Math.sin(angleInRadians));

//		circulo = new Ellipse2D.Double(x, y, 10, 10);
//		g2.draw(circulo);
		
		x -= (icon.getIconWidth() / 2);
		y -= (icon.getIconHeight() / 2);
		
		icon.paintIcon(null, g2, x, y);
	}

	private static double distancia(double a, double b)
	{
		double diferenca= Math.min(Math.abs(a-b),360-Math.abs(a-b));
		return diferenca;
	}
	
	public static double calcularDistancia(Point p1, Point p2)
	{
		double deltaX = p2.x - p1.x;
		double deltaY = p2.y - p1.y;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

}
