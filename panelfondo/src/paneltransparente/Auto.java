package paneltransparente;

import java.math.*;
import Concurrencia.Monitor;
import java.awt.*;

import javax.swing.*;
///////////////////////////////////////////////////////////////// Model
public class  Auto extends JPanel implements Runnable {
//... Constants
int DIAMETER;
private int i;

//... Instance variables
private int m_x;           // x and y coordinates upper left
private int m_y;
private int m_velocityX;   // Pixels to move each time move() is called.
private int m_velocityY;
private Image imageR,imageL,imageU,imageD, image;
private int m_rightBound;  // Maximum permissible x, y values.
private int m_bottomBound;
private Monitor monitor;
private Monitor[] monitores;
//private Rectangle Bounds;

//======================================================== constructor
public Auto(int x, int y, int velocityX, int velocityY, Monitor[] monitores) {
	m_x = x;
	m_y = y;
	m_velocityX = velocityX;
	m_velocityY = velocityY;	
	this.monitores = monitores;
	
	imageR = new ImageIcon("Coche_Right.png").getImage();
	imageL = new ImageIcon("Coche_Left.png").getImage();
	imageU = new ImageIcon("Coche_Up.png").getImage();
	imageD = new ImageIcon("Coche_Down.png").getImage();
	image = imageR;
	DIAMETER = image.getWidth(null);  //debe ser imagen cuadrada!! por el tema de cuando gira no haga colisiones
	this.setFocusable(true);
}

public void run(){}

public void setMonitor(Monitor m){
	monitor = m;
}

public Image getImage(){
	return image;
}


public void Izquierda (){
			image=imageL;				 
		    m_x -= m_velocityX;
}

public void Derecha (){		
			image= imageR;						
		    m_x += m_velocityX;
		    }

public void Abajo(){
			image= imageD;
	      	m_y += m_velocityY;

			}
public void Arriba(){
			image=imageU;
			m_y-=m_velocityY;
			}


//============================================= getDiameter, getX, getY
public int  getDiameter() { return DIAMETER;}
public int  getX()        { return m_x;}
public int  getY()        { return m_y;}

public Rectangle getBounds(){
	 return new Rectangle(m_x, m_y, image.getWidth(null), image.getHeight(null));
	
	}
//======================================================== setPosition
public void setPosition(int x, int y) {
m_x = x;
m_y = y;
}

}
