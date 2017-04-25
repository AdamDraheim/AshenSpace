package ashenSpace.anim;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ashenSpace.game.BufferedImageLoader;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.ID;

public class Foreground extends GameObject{

	BufferedImage level1 = null;
	BufferedImageLoader loader = new BufferedImageLoader();
	Texture tex = Game.getInstance();
	
	public Foreground(float x, float y, ID id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		level1 = loader.loadImage("/Foreground/Foreground1-1.png");
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if(Game.LEVEL == 1){
			g.drawImage(level1, 0, 0, null);
		}
	}
	public Rectangle getBounds() {
		return new Rectangle(1,1,1,1);
	}

}
