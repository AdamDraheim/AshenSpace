package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Switch extends GameObject{

	Handler handler;
	
	private Animation flow;
	Texture tex = Game.getInstance();

	
	public Switch(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		flow = new Animation(3, tex.misc[15], tex.misc[16], tex.misc[17]);
	}

	public void tick() {
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.player){
				if(tempObject.getBounds().intersects(this.getBounds())){
					handler.removeObject(this);
					Barrier.charge++;
				}
			}
		}
		flow.runAnimation();
	}

	public void render(Graphics g) {
		flow.drawAnimation(g, (int)x, (int)y + 16, 32, 64);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y + 16, 32, 64);
	}

}
