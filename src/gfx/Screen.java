package gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Screen {
	
	public static int[][] pixels = null;
	public static Dimension SIZE = new Dimension(800, 600);
	public Image icon = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
	
	static Canvas canvas;
	JFrame frame;
	
	public Screen() {
		canvas = new Canvas();
		canvas.setMinimumSize(SIZE);
		canvas.setMaximumSize(SIZE);
		canvas.setPreferredSize(SIZE);
		
		frame = new JFrame("Mastermind");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icon);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void render() {
		BufferStrategy buffer = canvas.getBufferStrategy();
		if (buffer == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		
		for (int i = 0; i < 800; i++) {
			for (int j = 0; i < 600; j++) {
				g.setColor(Color.RED);
				g.drawRect(i, j, 2, 2);
			}
		}
		g.setColor(Color.black);
		g.drawRect(0, 0, 100, 100);
		g.dispose();
		buffer.show();
	}
}
