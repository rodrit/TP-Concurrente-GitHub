package minitennis4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class KeyboardExample extends JPanel {
	
	public KeyboardExample() {
		KeyListener listener = new MyListener();    // crea un objeto listener, de la interfaz KeyListener
		addKeyListener(listener);                   // Agrega el listener al JPanel
		setFocusable(true);                        // Esto es para que el JPanel pueda capturar eventos (¿?) --> investigar bien despues
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mini Tennis");
		KeyboardExample keyboardExample = new KeyboardExample();
		frame.add(keyboardExample);
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class MyListener implements KeyListener // Aca se define la clase MyListener que implementa los metodos de la interfaz KeyListener
	                                               // Dentro de una clase se puede definir otra clase, una cosa e loco !!
	{
		
		public void keyTyped(KeyEvent e) {
		}

		
		public void keyPressed(KeyEvent e)  // muestra la tecla presionada
		{
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		}
/*
 * el evento e tiene un metodo 'getKeyCode()' que devuelve el codigo de una tecla (no se si es binario o lo que sea. A seria 20, por ej.)
 * Luego el evento tiene un metodo 'getKeyText()' que recibe un codigo de una tecla y devuelve en la consola, la tecla presionada.
 * Todo esto se usa en los metodos keyPressed y keyReleased de la interfaz.
 */
	
		public void keyReleased(KeyEvent e)  // muestra la tecla liberada
		{
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
}