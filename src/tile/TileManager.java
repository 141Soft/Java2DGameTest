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
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		try {
				//with better resource structuring this could be a loop
				tile[0] = new Tile();
				tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
				tile[1] = new Tile();
				tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
				tile[2] = new Tile();
				tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();
			
			for(int i=0; i < gp.maxScreenRow; i++) {
				String numbers[] = line.split(" ");
				for(int j=0; j < gp.maxScreenCol; j++) {
					System.out.println(line);
					int num = Integer.parseInt(numbers[j]);
					mapTileNum[j][i] = num;
				}
				//For some reason this is how readline works
				//Call it again to advance to the next line
				line = br.readLine();
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		for(int i = 0; i < gp.maxScreenRow; i++) {
			for(int j = 0; j < gp.maxScreenCol; j++) {
				int tileNum = mapTileNum[j][i];
				//in here we can do a conditional that reads a map of tiles to select the correct tile array position
				g2.drawImage(tile[tileNum].image, 72*j, 72*i, gp.tileSize, gp.tileSize, null);
			}
		}
	}
}
