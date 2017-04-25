package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class DantonDef extends GameObject{

	int dir;
	Handler handler;
	float gravity = 0.2f;
	boolean speak = false;
	public DantonDef(float x, float y, ID id, int dir, Handler handler) {
		super(x, y, id);
		this.dir = dir;
		this.handler = handler;
	}

	public void tick() {
		
		y += velY;
		
		if(speak == true){
			if(HUD.dialogTimer > 1500 && HUD.dialogTimer <= 2000){
				
			}
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 32);
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){
				gravity = 0;
				velY = 0;
				y -= 1;
				speak = true;
				HUD.dialogTimer = 2000;

			}
		}
	}

}
