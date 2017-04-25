package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Bomb extends GameObject{

	int Timer = 10;
	Handler handler;
	float velY = 0;
	float gravity = 0.1f;
	int maxSpeed = 1;
	
	
	public Bomb(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public void tick() {
		
		velY += gravity;
		y += velY;
		if(velY >= 10){
			velY = maxSpeed;
		}
		collision();
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.orange);
		g.fillRect((int) x, (int) y, 4, 4);
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 4, 4);
				
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					HUD.health -= 5;
					
				}
			}
			
			if(tempObject.getId() == ID.block){
				
				if(getBounds().intersects(tempObject.getBounds())){
				
					handler.removeObject(this);
					handler.addObject(new Bomb2(x, y, ID.bomb, handler));
					
				}
			} if(tempObject.getId() == ID.mapBlock){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					handler.addObject(new Bomb2(x, y, ID.bomb, handler));
				}
			}
		}
	}

}
