package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	final int originalTileSize = 16; //16 x 16
	final int scale = 3;
	final int tileSize = originalTileSize * scale; //48 x 48
	
	//4x3 ratio
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; //768px
	final int screenHeight = tileSize * maxScreenRow; //576px
	
	//FPS
	int maxFPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	// Player default
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	//constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		//game loop
		double drawInterval = 1000000000/maxFPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				//UPDATE
				update();
				//DRAW
				repaint();
				//CLOCK
				delta--;
				//FPS COUNTER
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				//FPS COUNTER
				System.out.println("FPS" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		//Movement
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		if(keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
	}
}
