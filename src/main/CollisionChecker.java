package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
		int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
		int entityTopWorldY = entity.worldY + entity.collisionArea.y;
		int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break; 
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				//entity collision area position
				entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
				entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
				//object collision area position
				gp.obj[i].collisionArea.x = gp.obj[i].worldX + gp.obj[i].collisionArea.x;
				gp.obj[i].collisionArea.y = gp.obj[i].worldY + gp.obj[i].collisionArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.collisionArea.y -= entity.speed;
					if(entity.collisionArea.intersects(gp.obj[i].collisionArea)) {
						if(gp.obj[i].collision == true) { entity.collisionOn = true; }
						if(player == true) { index = i; }
					}
					break;
				case "down":
					entity.collisionArea.y += entity.speed;
					if(entity.collisionArea.intersects(gp.obj[i].collisionArea)) {
						if(gp.obj[i].collision == true) { entity.collisionOn = true; }
						if(player == true) { index = i; }
					}
					break;
				case "left":
					entity.collisionArea.x -= entity.speed;
					if(entity.collisionArea.intersects(gp.obj[i].collisionArea)) {
						if(gp.obj[i].collision == true) { entity.collisionOn = true; }
						if(player == true) { index = i; }
					}
					break;
				case "right":
					entity.collisionArea.x += entity.speed;
					if(entity.collisionArea.intersects(gp.obj[i].collisionArea)) {
						if(gp.obj[i].collision == true) { entity.collisionOn = true; }
						if(player == true) { index = i; }
					}
					break;
				}
				//reset values
				entity.collisionArea.x = entity.collisionAreaDefaultX;
				entity.collisionArea.y = entity.collisionAreaDefaultY;
				gp.obj[i].collisionArea.x = gp.obj[i].collisionAreaDefaultX;
				gp.obj[i].collisionArea.y = gp.obj[i].collisionAreaDefaultY;
			}
		}
		
		return index;
	}
}
