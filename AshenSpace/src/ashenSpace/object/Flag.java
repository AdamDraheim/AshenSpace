package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Flag extends GameObject{

	Texture tex = Game.getInstance();
	private int type;
	Handler handler;
	
	public Flag(float x, float y, ID id, int type, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.type = type;
		this.handler = handler;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		
		if(type == 0){
			g.drawImage(tex.door[0], (int)x, (int)y, null);
		}
		if(type == 1){
			g.drawImage(tex.door[1], (int)x, (int)y, null);
		}
		if(type == 2){
			g.drawImage(tex.door[2], (int)x, (int)y, null);
		}
		if(type == 3){
			g.drawImage(tex.door[3], (int)x, (int)y, null);
		}
		if(type == 4){
			g.drawImage(tex.door[4], (int)x, (int)y, null);
		}
		if(type == 5){
			g.drawImage(tex.door[5], (int)x, (int)y, null);
		}
		if(type == 6){
			g.drawImage(tex.door[6], (int)x, (int)y, null);
		}
		if(type == 7){
			g.drawImage(tex.door[7], (int)x, (int)y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}	
	
}
