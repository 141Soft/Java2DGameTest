package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//Getting machine screen size
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	//Screen Settings
	final int originalTileSize = 24; //24x24
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; //72x72
	public final int charSpriteWidth = 16 * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int[] dimensions = getScreenSize(maxScreenCol, maxScreenRow);
	
	//World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	//FPS
	int maxFPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker collChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	
	//constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public int[] getScreenSize(int maxCol, int maxRow) {
		//Ensures that window never exceeds a certain number of tiles, 
		//while allowing shrinkage
		
		final int maxScreenCol = (int) Math.round(width / tileSize) - 1;
		final int maxScreenRow = (int) Math.round(height / tileSize) - 1;
		int[] sizes = new int[2];
		
		if(maxScreenCol > maxCol) {sizes[0] = maxCol * tileSize;}
		else {sizes[0] = maxScreenCol * tileSize;}
		
		if(maxScreenRow > maxRow) {sizes[1] = maxRow * tileSize;}
		else {sizes[1] = maxScreenRow * tileSize;}
		
		return sizes;
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
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}
