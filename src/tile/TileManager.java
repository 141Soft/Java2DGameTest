package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map1.txt");
	}
	
	public void getTileImage() {
		try {
				//with better resource structuring this could be a loop
				tile[0] = new Tile();
				tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
				tile[1] = new Tile();
				tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_faded.png"));
				tile[2] = new Tile();
				tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
				tile[2].collision = true;
				tile[3] = new Tile();
				tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_topper.png"));
				tile[3].collision = true;
				tile[4] = new Tile();
				tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/paving.png"));
				tile[5] = new Tile();
				tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
				tile[5].collision = true;
				tile[6] = new Tile();
				tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rock.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String path) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();
			
			for(int i=0; i < gp.maxWorldRow; i++) {
				String numbers[] = line.split(" ");
				for(int j=0; j < gp.maxWorldCol; j++) {
					int num = Integer.parseInt(numbers[j]);
					mapTileNum[j][i] = num;
				}
				//advances to next line
				line = br.readLine();
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int xOffset = gp.player.worldX + gp.player.screenX;
		int yOffset = gp.player.worldY + gp.player.screenY;
		
		int xInset = gp.player.worldX - gp.player.screenX;
		int yInset = gp.player.worldY - gp.player.screenY;
		
		
		for(int i = 0; i < gp.maxWorldRow; i++) {
			for(int j = 0; j < gp.maxWorldCol; j++) {
				int tileNum = mapTileNum[j][i];
				if(gp.tileSize*j + gp.tileSize > xInset &&
				   gp.tileSize*j - gp.tileSize < xOffset &&
				   gp.tileSize*i + gp.tileSize > yInset &&
				   gp.tileSize*i - gp.tileSize < yOffset) {
					//This conditional only draws tiles if they're within a screen boundary of the player
					//Draw method is actually a little backwards because I draw column by column
					g2.drawImage(tile[tileNum].image,gp.tileSize*j - xInset,gp.tileSize*i - yInset, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}
}
