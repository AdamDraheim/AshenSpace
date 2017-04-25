package ashenSpace.tutorial;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.ID;

public class tutBarrier extends GameObject{

	private Animation flow;
	
	Texture tex = Game.getInstance();
	
	public tutBarrier(float x, float y, ID id) {
		super(x, y, id);
		flow = new Animation(3, tex.misc[12], tex.misc[13], tex.misc[14]);

	}

	public void tick() {
		flow.runAnimation();

	}

	public void render(Graphics g) {
		flow.drawAnimation(g, (int)x, (int)y - 16, 32, 96);

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x - 16, (int)y, 32, 96);
	}

}
