package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class reboundBullet extends GameObject{

	Handler handler;
	public float velX;
	
	Texture tex = Game.getInstance();
	
	private Animation bulletLeft, bulletRight;
	
	public reboundBullet(float x, float y, ID id, Handler handler, float velX) {
		super(x, y, id);
		this.velX = velX;
		bulletRight = new Animation(5, tex.misc[6], tex.misc[7]);
		bulletLeft = new Animation(5, tex.misc[8], tex.misc[9]);

	}

	public void tick() {
		
		x += velX;
		collision();
		bulletLeft.runAnimation();
		bulletRight.runAnimation();
	}

	public void render(Graphics g) {
		
		if(velX < 0){
			bulletLeft.drawAnimation(g, (int)x, (int) y + 16, 64, 64);
		}else if(velX > 0){
			bulletRight.drawAnimation(g, (int)x, (int) y + 16, 64, 64);

		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y + 16, 32, 32);
	}
	
	public void collision(){
		
	}

}
