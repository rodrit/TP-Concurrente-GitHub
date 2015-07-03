package tp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Auto {
    
	private static final int WITH = 15;
	private static final int HEIGHT = 15;
	int x = 0;
	int xa = 0;
	int y = 280;
	int ya = 0;
	private Game game;

	public Auto (Game game) {
		this.game = game;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH)
			x = x + xa;
		if (y + ya > 0 && y + ya < game.getHeight() - HEIGHT)
		    y = y + ya;
		if (collision()){
			
			y = game.manzana.getTopY() ;
			x = game.manzana.getTopX() ;
			y = game.manzana2.getTopY()- HEIGHT ;
			x = game.manzana2.getTopX() - WITH ;
			y = game.manzana3.getTopY()- HEIGHT ;
			x = game.manzana3.getTopX() - WITH ;
			y = game.manzana4.getTopY()- HEIGHT ;
			x = game.manzana4.getTopX() - WITH ;
			
		}
		x = x + xa;
		y = y + ya;
	}
	private boolean collision() {
		
		return game.manzana.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, WITH, HEIGHT);
		g.setColor(Color.red);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -1;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 1;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			ya = -1;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			ya = 1;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WITH, HEIGHT);
	}

	public int getTopY() {
		return y - HEIGHT;
	}
}