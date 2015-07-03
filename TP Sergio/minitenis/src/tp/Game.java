package tp;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Game extends JPanel {

	
	Auto auto = new Auto(this);
	AutosHorizontales autos = new AutosHorizontales (this);
	AutosVerticales autosV  = new AutosVerticales (this);
	AutosVerticalesI autosVI = new AutosVerticalesI (this);
	manzanas manzana = new manzanas ();
    manzana2 manzana2 = new manzana2 ();
    manzana3 manzana3 = new manzana3 ();
    manzana4 manzana4 = new manzana4 ();
    
    
	public Game() {
		addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}

			
			public void keyReleased(KeyEvent e) {
				auto.keyReleased(e);
			}

			
			public void keyPressed(KeyEvent e) {
				auto.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() 
	{
		autosVI.move();
	    autosV.move();
		autos.move();
		auto.move();
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		auto.paint(g2d);
		autos.paint(g2d);
		autosV.paint(g2d);
		autosVI.paint(g2d);
		manzana.paint(g2d);
		manzana2.paint(g2d);
		manzana3.paint(g2d);
		manzana4.paint(g2d);		
        
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("PunillaLandia");
		Game game = new Game();
		frame.add(game);
		frame.setSize(350, 350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
