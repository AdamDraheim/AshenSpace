package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.audio.sound;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Rocket extends GameObject{
	int velX;
	Handler handler;
	public Rocket(float x, float y, ID id, Handler handler, int velX) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.x = x;
		this.y = y;
	}

	public void tick() {
		
		collision();
		x += velX;
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				
				if(tempObject.getY() < this.getY()){
					this.setY(this.getY() - 3);
				}
				if(tempObject.getY() > this.getY()){
					this.setY(this.getY() + 3);
				}
				
			}
		}
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int) y, 16, 16);
		handler.addObject(new Path((int)x, (int)y, null, Color.red, 16, 16, 0.05f, handler));

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void collision(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				if(tempObject.getBounds().intersects(this.getBounds())){
					HUD.health -= 20;
					handler.removeObject(this);
					for(int ii = 0; ii < 30; ii++){
						handler.addObject(new Explosion((int) x, (int) y, null, handler, 200, 0, 0, "red", 5));
						}
				}
			}
				if(tempObject.getId() == ID.block || tempObject.getId() == ID.mapBlock){
				
					if(tempObject.getBounds().intersects(this.getBounds())){
						
						handler.removeObject(this);
						sound.play("/sound/Explosion.wav");

						for(int ii = 0; ii < 15; ii++){
							handler.addObject(new Explosion((int) x, (int) y, null, handler, 200, 0, 0, "red", 5));

						}
					}
				}
			}
		
	}

}
