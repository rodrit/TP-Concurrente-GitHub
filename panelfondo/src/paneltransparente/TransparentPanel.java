package paneltransparente;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;

import javax.swing.*;

import java.lang.*;
//import mainball.BallInBox.TimerAction;
 
public class TransparentPanel extends JPanel implements KeyListener {
	 
	 private Image bgImage;
	 private Auto auto;
	 private int   m_interval  = 20;  
	 private Timer m_timer;   
	 private cuadra cuadra1,cuadra2,cuadra3,cuadra4;
	 private boolean  interR,interL,interU,interD;     //para ver de que lado chocó (arregla el tema de banderas)
	 private Dimension dimension;
	
	 
 public TransparentPanel(Auto auto) {
	  super();
	  this.auto = auto;
	  m_timer = new Timer(m_interval, new TimerAction());
	  bgImage = new ImageIcon("city2.png").getImage();
	  dimension = new Dimension(bgImage.getWidth(null),bgImage.getHeight(null));
	  setBackgroundImage(bgImage);
	  this.setSize(dimension);
	  // Hacemos que el panel sea transparente
	  this.setOpaque(false);
	  this.setFocusable(true);
	  
	  interL = interU = interD = interR = false;
		 
	 cuadra1 = new cuadra (111,83,219,191);
	 cuadra2 = new cuadra (454,83,219,191);
	 cuadra3 = new cuadra (111,352,219,191);
	 cuadra4 = new cuadra (454,352,219,191);
	
	  this.addKeyListener(this);
	  
	  m_timer.start();
	
	 }
	 
	 /**
	  * Lo utilizaremos para establecerle su imagen de fondo.
	  * @param bgImage La imagen en cuestion
	  */
	 public void setBackgroundImage(Image bgImage) {
	  this.bgImage = bgImage;
	 
	 }
	  
	// @Override
	 public void paint(Graphics g) {
	 
	  // Pintamos la imagen de fondo
	  g.drawImage(bgImage, 0, 0, null);
	  g.drawImage(auto.getImage(),auto.getX(), auto.getY(), null); //dibujo auto
	 super.paint(g); 
	 g.drawRect (cuadra1.getRectangulo().x,cuadra1.getRectangulo().y,cuadra1.getRectangulo().width,cuadra1.getRectangulo().height);
	 g.drawRect (cuadra2.getRectangulo().x,cuadra2.getRectangulo().y,cuadra2.getRectangulo().width,cuadra2.getRectangulo().height);
	 g.drawRect (cuadra3.getRectangulo().x,cuadra3.getRectangulo().y,cuadra3.getRectangulo().width,cuadra3.getRectangulo().height);
	 g.drawRect (cuadra4.getRectangulo().x,cuadra4.getRectangulo().y,cuadra4.getRectangulo().width,cuadra4.getRectangulo().height);
	 //rectangulo q rodea las cuadras, solo es un dibujo(no influye en la colision)	 
	 }
	 

	 public void keyPressed(KeyEvent e) {

		 int c = e.getKeyCode();
			if ((c==KeyEvent.VK_RIGHT) && ((auto.getX() + auto.getDiameter()) < dimension.width) && !interR)
				    {	
					auto.Derecha();
					interL = false;
					interR= Intersecta();
					}
			
			 if ((c==KeyEvent.VK_LEFT) && (auto.getX() >0) && !interL) 
			       {
					auto.Izquierda();
					interR = false;
					interL = Intersecta();
					}
		
			 if ((c==KeyEvent.VK_DOWN) &&  ((auto.getY() + auto.getDiameter()) < dimension.height) && !interD)
			       {
					auto.Abajo();
					interU = false;
					interD = Intersecta();
					}
			
			 if ((c==KeyEvent.VK_UP) &&  (auto.getY() >0) && !interU)
				   {		
					auto.Arriba();
					interD = false;
					interU= Intersecta();
					}
			 	
		}
	 
	 public boolean Intersecta(){
		 
		 if (auto.getBounds().intersects(cuadra1.getRectangulo()) || auto.getBounds().intersects(cuadra2.getRectangulo()) ||
			 auto.getBounds().intersects(cuadra3.getRectangulo()) || auto.getBounds().intersects(cuadra4.getRectangulo()))
		 {return  true;}
		 else{ return false;}
		 
		
	 }
		public void keyReleased(KeyEvent event) {}

		public Dimension getPreferredSize() {
		    return new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
		}

		public void keyTyped(KeyEvent event) {}
		
	 class TimerAction implements ActionListener {
	        //================================================== actionPerformed
	        /** ActionListener of the timer.  Each time this is called,
	         *  the ball's position is updated, creating the appearance of
	         *  movement.
	         *@param e This ActionEvent parameter is unused.
	         */
	        public void actionPerformed(ActionEvent e) { 
	           repaint();      // Repaint indirectly calls paintComponent.
	        }}}