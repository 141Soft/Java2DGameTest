package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp =gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
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
		
		if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			spriteCounter++;
			if(spriteCounter >= 20) {
				spriteSwitch = !spriteSwitch;
				spriteCounter = 0;
			}
		}
		
		if(keyH.upPressed == true) {
			direction="up";
			y -= speed;
		}
		if(keyH.downPressed == true) {
			direction="down";
			y += speed;
		}
		if(keyH.leftPressed == true) {
			direction="left";
			x -= speed;
		}
		if(keyH.rightPressed == true) {
			direction="right";
			x += speed;
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
		g2.drawImage(image, x, y, gp.charSpriteWidth, gp.tileSize, null);
	}
}
