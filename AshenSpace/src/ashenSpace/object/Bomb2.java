package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Bomb2 extends GameObject{

	int Timer = 10;
	Handler handler;
	int timer = 50;
	
	
	public Bomb2(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public void tick() {
		
		timer--;
		collision();
		if(timer <= 0)
			handler.removeObject(this);
		
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.orange);
		g.fillRect((int) x - 32, (int) y - 32, 64, 64);
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int) x - 32, (int) y - 32, 64, 64);
				
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					HUD.health -= 30;
					
				}
			}
		}
	}

}
