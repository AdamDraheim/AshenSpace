package ashenSpace.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.ID;

public class DeadBasic extends GameObject{

	Texture tex = Game.getInstance();
	
	public DeadBasic(float x, float y, ID id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		
		g.drawImage(tex.basicEnemy[3], (int)x, (int) y, null);
		
	}

	public Rectangle getBounds() {
		return null;
	}

	
	
}
