package ashenSpace.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Pod extends GameObject{

	Handler handler;
	float velX, vely;
	int timer;
	Texture tex = Game.getInstance();

	
	public Pod(float x, float y, ID id, Handler handler, int timer, float velX, float velY) {
		super(x, y, id);
		this.handler = handler;
		this.timer = timer;
		this.velX = velX;
		this.velY = velY;
	}

	public void tick() {
		timer--;
		x += velX;
		y += velY;
		if(timer == 0){
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.basicEnemy[17], (int)x, (int)y, 16, 16, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
