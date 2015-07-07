package paneltransparente;

import java.awt.*;
public class cuadra {

	private Rectangle rectangulo;
	private Dimension dimension;
	private int posX, posY;
	
	public cuadra(int a,int b,int c,int d){
		
		rectangulo = new Rectangle (a,b,c,d);
		dimension = new Dimension(rectangulo.width,rectangulo.height);
		posX = a;
		posY = b;
	}
	
	public Dimension getDimension(){
		
		return dimension;
	}
	
	public Rectangle getRectangulo(){
		return rectangulo;
		
	}
}
