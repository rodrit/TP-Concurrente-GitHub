package tp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class manzana3  {
int x = 20;
int y =20;





public void paint(Graphics2D g) {
	
	g.fillRect(x, y+150, 100, 100);
	
}

public Rectangle getBounds() {
	return new Rectangle(x, y+150, 100, 100);
	
}

public int getTopY() {

	return y;
}

public int getTopX() {
	
	return x;
}
}
