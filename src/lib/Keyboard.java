package lib;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Keyboard  extends KeyAdapter{
	
	private static List<Integer> pressedButtons = new ArrayList<Integer>();
	
	public static boolean isKeyPressed(int keyCode) {
		return pressedButtons.contains(keyCode);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		int keyCode = e.getKeyCode();
		if(!pressedButtons.contains(keyCode)) pressedButtons.add(keyCode);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		pressedButtons.remove((Integer)e.getKeyCode());
	}
}
