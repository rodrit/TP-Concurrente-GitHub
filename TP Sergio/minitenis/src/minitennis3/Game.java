package minitennis3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;               // JFrame
import javax.swing.JPanel;               // JPanel

@SuppressWarnings("serial")
public class Game extends JPanel {

	int x = 0;
	int y = 0;
	int xa = 1;                // xa e ya las uso para determinar las velocidades en el eje x e y, respectivamente
	int ya = 1;

	private void moveBall()           // en este metodo controlo la direccion de la bola
	{                 
		if (x + xa < 0)                        // para que no se salga del margen izquierdo
			xa = 1;
		if (x + xa > getWidth() - 30)          // para que no se salga del margen derecho
			xa = -1;
		if (y + ya < 0)                       // para que no se salga del borde superior
			ya = 1;
		if (y + ya > getHeight() - 30)        // para que no se salga del borde inferior
			ya = -1;
		
		x = x + xa;                         // doy la velocidad en x
		y = y + ya;                        // doy la velocidad en y
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillOval(x, y, 30, 30);

	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true)              // GAME LOOP
		{
			game.moveBall();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
