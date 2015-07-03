package tp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;   // este se usa para devolver la posicion del rectangulo de la figura del auto

public class AutosVerticales {
	private static final int ANCHO = 15;
	int x = 150;
	int y = 0;
	int xa = 0;
	int ya = 0;
	private Game game;

	public AutosVerticales(Game game) {
		this.game= game;
	}

	void move() {
		if (y + ya <= 0)
			ya = 1;
		if (y + ya >= game.getWidth() )
			ya = -1;
		
		
		if (collision()){
			ya = -1;
			y = game.autos.getTopY();
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.auto.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, ANCHO, ANCHO);
		
		g.setColor (Color.BLUE);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, ANCHO, ANCHO);
	}
}