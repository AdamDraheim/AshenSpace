package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.audio.sound;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Missile extends GameObject{

	double velX, velY;
	Handler handler;
	int timer = 50;

	public Missile(float x, float y, ID id, Handler handler, double bulletVelX, double bulletVelY) {
		super(x, y, id);
		this.handler = handler;
		this.velY = bulletVelY;
		this.velX = bulletVelX;

	}

	public void tick() {
		
		timer--;

		x += velX / 10;
		y += velY / 10;
		Collision();

		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
		handler.addObject(new Path((int)x, (int)y, null, Color.red, 16, 16, 0.05f, handler));

		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 16, 16);
	}
	
	public void Collision(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.block || tempObject.getId() == ID.mapBlock || tempObject.getId()==ID.player){
				if(tempObject.getBounds().intersects(this.getBounds())){
					handler.removeObject(this);
					handler.addObject(new KaboomBox(x, y, ID.pDam, handler));
					sound.play("/sound/Explosion.wav");

					for(int ii = 0; ii < 15; ii++){
						handler.addObject(new Explosion(x, y, null, handler, 200, 0, 0, "red", 5));
						
					}
				}
			}
		}
		
	}
	
	public void die(){
		if(timer <= 0){
			handler.removeObject(this);
		}
	}
	

}
