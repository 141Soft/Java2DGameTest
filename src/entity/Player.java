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
			up = ImageIO.read(getClass().getResourceAsStream("/player/up.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		//Movement
		move();
	}
	
	public void move() {
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
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		
		switch(direction) {
		case "up":
			image = up;
			break;
		case "down":
			image = down;
			break;
		case "left":
			image = left;
			break;
		case "right":
			image = right;
			break;
		}
		g2.drawImage(image, x, y, gp.charSpriteWidth, gp.tileSize, null);
	}
}
