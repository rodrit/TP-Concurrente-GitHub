package tp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;   // este se usa para devolver la posicion del rectangulo de la figura del auto

public class AutosHorizontales {
	private static final int ANCHO = 15;
	int x = 0;
	int y = 140;
	int xa = 1;
	int ya = 0;
	private Game game;

	public AutosHorizontales(Game game) {
		this.game= game;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > game.getWidth() - ANCHO)
			xa = -1;
		
		
		if (collision()){
			ya = -1;
			y = game.auto.getTopY();
		}
		x = x + xa;
		
	}

	private boolean collision() {
		return game.auto.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, ANCHO, ANCHO);
		g.fillRect(x, y, ANCHO, ANCHO);
		g.setColor (Color.BLUE);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, ANCHO, ANCHO);
	}

	public int getTopY() {
		
		return y;
	}
}