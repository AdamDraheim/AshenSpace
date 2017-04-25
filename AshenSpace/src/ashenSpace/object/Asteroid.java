package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Asteroid extends GameObject{

	private Handler handler;
	int velY = 7;
	float gravity = 0.5f;
	final int maxSpeed = 10;
	Random r = new Random();
	Texture tex = Game.getInstance();
	int velX = (r.nextInt(5) + 1);
	
	public Asteroid(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public void tick() {
		
		y += velY;
		x += velX;
		if(velY < maxSpeed){
			velY += gravity;
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.mapBlock || tempObject.getId() == ID.block){
				if(getBounds().intersects(tempObject.getBounds()))
					handler.removeObject(this);
			}
		}
		
		if(this.x > (handler.mapSizeX * 32) || this.x < 0){
			handler.removeObject(this);
		}
		
	}

	public void render(Graphics g) {
		
	g.drawImage(tex.misc[1], (int) x, (int) y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
