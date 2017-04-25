package ashenSpace.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.ID;

public class DeadFinal2 extends GameObject {

	Texture tex = Game.getInstance();

	
	public DeadFinal2(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.finalBoss[13], (int)x, (int)y, 96, 96, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(1,1,1,1);
	}

}
