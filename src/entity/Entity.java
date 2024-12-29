package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public boolean spriteSwitch = true;
	
	public Rectangle collisionArea;
	public boolean collisionOn = false;
}
