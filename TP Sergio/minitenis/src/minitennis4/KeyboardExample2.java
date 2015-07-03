package minitennis4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/// esta clase hace exactamente lo mismo que la anterior, solo que usa el concepto de clase Anonima en el constructor
// para implementar la interfaz. Aparentemente es algo copado.

@SuppressWarnings("serial")
public class KeyboardExample2 extends JPanel {
	
	public KeyboardExample2() {
		KeyListener listener = new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}

			
			public void keyPressed(KeyEvent e) {
				System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
			}

			
			public void keyReleased(KeyEvent e) {
				System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
			}
		};
		addKeyListener(listener);
		setFocusable(true);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mini Tennis");
		KeyboardExample2 keyboardExample = new KeyboardExample2();
		frame.add(keyboardExample);
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}