package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.audio.sound;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class FourWayBullet extends GameObject{

	
	private Handler handler;
	
	int velX;
	int velY;
	
	public FourWayBullet(float x, float y, int velX, int velY, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
	}
	

	public void tick() {
			
		x += velX;
		y += velY;
		
		Collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int)y, 4, 4);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 4, 4);
	}
	
	public void Collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					HUD.health -= 15;
					handler.removeObject(this);
					
				}
			}
			if(tempObject.getId() == ID.mapBlock  || tempObject.getId() == ID.shield || tempObject.getId() == ID.block){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					handler.removeObject(this);
					
					}
					
						
				
				}
		}
	}

}
