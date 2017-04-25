package ashenSpace.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Flash extends GameObject{

	int timer = 90;
	Handler handler;
	Texture tex = Game.getInstance();

	
	public Flash(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		timer -= 5;
		
		if(timer <= 0){
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		if(timer > 80){
			g.drawImage(tex.flash[0], (int)x, (int)y, 256, 256, null);
		}else if(timer > 70 && timer <= 80){
			g.drawImage(tex.flash[3], (int)x, (int)y, 256, 256, null);
		}else if(timer > 60 && timer <= 70){
			g.drawImage(tex.flash[1], (int)x, (int)y, 256, 256, null);
		}else if(timer > 50 && timer <= 60){
			g.drawImage(tex.flash[4], (int)x, (int)y, 256, 256, null);
		}else if(timer > 40 && timer <= 50){
			g.drawImage(tex.flash[2], (int)x, (int)y, 256, 256, null);
		}else if(timer > 30 && timer <= 40){
			g.drawImage(tex.flash[4], (int)x, (int)y, 256, 256, null);
		}else if(timer > 20 && timer <= 30){
			g.drawImage(tex.flash[1], (int)x, (int)y, 256, 256, null);
		}else if(timer > 10 && timer <= 20){
			g.drawImage(tex.flash[3], (int)x, (int)y, 256, 256, null);
		}else if(timer > 0 && timer <= 10){
			g.drawImage(tex.flash[0], (int)x, (int)y, 256, 256, null);
		}
	}

	public Rectangle getBounds() {
		return null;
	}

}
