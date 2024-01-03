package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import constants.WindowSize;
import lib.Keyboard;
import shooting.Main;

public class GameFrame extends JFrame {
	public MainPanel panel;
		
	public GameFrame() {
		
		this.addKeyListener(new Keyboard());
		
		this.setTitle("Shooting Game");
		this.setSize(WindowSize.x, WindowSize.y);
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		panel = new MainPanel();
		this.setLayout(null);
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(panel);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				Main.loop = false;
			}
		});
	}
}
