package ashenSpace.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.ID;

public class mapBlock extends GameObject{
	

	public mapBlock(float x, float y, ID id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	
	
}
