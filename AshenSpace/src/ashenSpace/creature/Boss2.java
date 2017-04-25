package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Bomb;

public class Boss2 extends GameObject{

	Handler handler;
	public int bombTimer = 100;
	
	public Boss2(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public void tick() {
		
		bombTimer--;
		if(bombTimer <= 0){
			handler.addObject(new Bomb(x + 32, y + 32, ID.bomb, handler));
			bombTimer = 100;
		}
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.drawRect((int) x, (int)y, 64, 64);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int)y, 64, 64);
	}

}
