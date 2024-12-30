package main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		switch(code) {
			case KeyEvent.VK_W:
				upPressed = true;
				break;
			case KeyEvent.VK_UP:
				upPressed = true;
				break;
			case KeyEvent.VK_S:
				downPressed = true;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = true;
				break;
			case KeyEvent.VK_A:
				leftPressed = true;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				break;
			case KeyEvent.VK_D:
				rightPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		switch(code) {
			case KeyEvent.VK_W:
				upPressed = false;
				break;
			case KeyEvent.VK_UP:
				upPressed = false;
				break;
			case KeyEvent.VK_S:
				downPressed = false;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = false;
				break;
			case KeyEvent.VK_A:
				leftPressed = false;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
			case KeyEvent.VK_D:
				rightPressed = false;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				break;
		}
	}

}
