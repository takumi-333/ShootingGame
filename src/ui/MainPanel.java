package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import constants.WindowSize;

public class MainPanel extends JPanel {
	public BufferedImage image;
	
	public MainPanel() {
		super();
		this.image = new BufferedImage(WindowSize.x, WindowSize.y, BufferedImage.TYPE_INT_ARGB);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw() {
		this.repaint();
	}
}
