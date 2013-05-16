import java.awt.Color;

import javax.swing.JFrame;

public class Window {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("Mastermind");
		frame.setAutoRequestFocus(false);
		frame.setResizable(false);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null); //Center on screen
		frame.setVisible(true);
	}
	
}
