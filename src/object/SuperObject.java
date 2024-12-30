package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int xOffset = gp.player.worldX + gp.player.screenX;
		int yOffset = gp.player.worldY + gp.player.screenY;
		
		int xInset = gp.player.worldX - gp.player.screenX;
		int yInset = gp.player.worldY - gp.player.screenY;
		
		int screenX = worldX - xInset;
		int screenY = worldY - yInset;
		
		if(worldX + gp.tileSize > xInset &&
			worldX - gp.tileSize < xOffset &&
			worldY + gp.tileSize > yInset &&
			worldY - gp.tileSize < yOffset) {
				g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
	}
}
