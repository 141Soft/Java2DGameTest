package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp =gp;
		this.keyH = keyH;
		
		screenX = gp.dimensions[0]/2 - (gp.tileSize/2);
		screenY = gp.dimensions[1]/2 - (gp.tileSize/2);
		
		//Based on 72 given that the player width is actually smaller than a tile
		collisionArea = new Rectangle();
		collisionArea.x = 3;
		collisionArea.y = 24;
		collisionArea.width = 42;
		collisionArea.height = 44;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 19;
		worldY = gp.tileSize * 19;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			down = ImageIO.read(getClass().getResourceAsStream("/player/down.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			up = ImageIO.read(getClass().getResourceAsStream("/player/up.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void move() {
		collisionOn = false;
		gp.collChecker.checkTile(this);
		
		//Animations
		if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			spriteCounter++;
			if(spriteCounter >= 20) {
				spriteSwitch = !spriteSwitch;
				spriteCounter = 0;
			}
		
		//Collisions
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
				}
			}
		//Movement direction setting
		if(keyH.upPressed == true) {
			direction="up";
			}
		else if(keyH.downPressed == true) {
				direction="down";
			}
		else if(keyH.leftPressed == true) {
			direction="left";
			}
		else if(keyH.rightPressed == true) {
				direction="right";
			}
		}
	}
	
	public void update() {
		move();
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(!keyH.upPressed) { image = up; }
			else if(spriteSwitch) { image = up1; }
			else { image = up2; }
			break;
		case "down":
			if(!keyH.downPressed) { image = down; }
			else if(spriteSwitch) { image = down1;}
			else { image = down2; }
			break;
		case "left":
			if(!keyH.leftPressed) { image = left; }
			else if(spriteSwitch) { image = left1; }
			else { image = left2; }
			break;
		case "right":
			if(!keyH.rightPressed) { image = right; }
			else if(spriteSwitch) { image = right1; }
			else { image = right2; }
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.charSpriteWidth, gp.tileSize, null);
	}
}
