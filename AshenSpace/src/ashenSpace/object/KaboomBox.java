package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.print.Paper;

import ashenSpace.creature.Player;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class KaboomBox extends GameObject{

	Handler handler;
	int hit = 1;
	int timer;
	public KaboomBox(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.timer = 10;
	}

	public void tick() {
		Collision();
		timer--;
		if(timer < 0){
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x - 48, (int) y - 48, 96, 96);
	}
	
	public void Collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.player){
				if(tempObject.getBounds().intersects(this.getBounds())){
					handler.removeObject(this);
					if(Player.invincible < 0){
						HUD.health -= 20;
						Player.invincible = 5;
					}
				}
			}else if(tempObject.getId() == ID.missileBoss){
				if(tempObject.getBounds().intersects(this.getBounds())){
					handler.decreaseHealth(tempObject, 50);
					handler.removeObject(this);
				}
			}
			
		}
	}

}
