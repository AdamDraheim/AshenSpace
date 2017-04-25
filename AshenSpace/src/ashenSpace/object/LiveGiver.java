package ashenSpace.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class LiveGiver extends GameObject{

	Handler handler;
	
	public boolean given = false;
	public LiveGiver(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				if(tempObject.getBounds().intersects(this.getBounds())){
					if(given == false){
						HUD.lives++;
						given = true;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 64);
	}

}
