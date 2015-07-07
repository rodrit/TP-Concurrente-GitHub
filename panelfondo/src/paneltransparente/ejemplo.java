package paneltransparente;

import java.awt.*;
import java.lang.*;

import javax.swing.*;
import Concurrencia.Monitor;




public class ejemplo extends JPanel {


private static JFrame frame;




public static void main(String[] args){
	
	
	Monitor[] monitores ;
	monitores = new Monitor[9];
	for (int i = 0 ; i<9;i++){
		monitores[i] = new Monitor(i);
	}
	
	
	Auto auto = new Auto (5,5,5,5,monitores);
	Thread c = new Thread(auto);
	c.start();
	TransparentPanel panel = new TransparentPanel(auto);
	createGUI(panel);
	
}

/**
 * Create the GUI and show it. For thread safety,
 * this method should be invoked from the
 * event dispatch thread.
 */

private static void createGUI( TransparentPanel panel) {

 // Create and set up the window.
 frame = new JFrame("TPF CONCURRENTE");
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 

 Component contents = panel;
 System.out.println("pre framesContent");
// frame.setLocation(screenWidth / 5, 0);                    //tratar de setear la location centrada!!
// frame.getContentPane().paintComponents(null);
 frame.getContentPane().add(contents, BorderLayout.CENTER);

 
 // Display the window.
 //frame.dispose();
 frame.pack();
 frame.setLocationByPlatform(true);
// frame.setFocusable(true);
 frame.setVisible(true);
 frame.setResizable(false);
 //frame.getContentPane().set
}}
