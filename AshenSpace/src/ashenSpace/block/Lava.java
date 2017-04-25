package ashenSpace.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Lava extends GameObject{

	Handler handler;
	Texture tex = Game.getInstance();
	private int type = 3;
	
	public Lava(float x, float y, int type, ID id, Handler handler) {
		super(x, y, id);
		this.type = type;
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public void tick() {
	}

	public void render(Graphics g) {
		
		if(type == 0){
			g.drawImage(tex.lava[0], (int)x, (int)y, null);
		}
		if(type == 1){
			g.drawImage(tex.lava[1], (int)x, (int)y, null);
		}
		if(type == 2){
			g.drawImage(tex.lava[2], (int)x, (int)y, null);
		}
		if(type == 3){
			g.drawImage(tex.lava[3], (int)x, (int)y, null);
		}
		if(type == 4){
			g.drawImage(tex.lava[4], (int)x, (int)y, null);
		}
		if(type == 5){
			g.drawImage(tex.lava[5], (int)x, (int)y, null);
		}
		if(type == 6){
			g.drawImage(tex.lava[6], (int)x, (int)y, null);
		}
		if(type == 7){
			g.drawImage(tex.lava[7], (int)x, (int)y, null);
		}
		if(type == 8){
			g.drawImage(tex.lava[8], (int)x, (int)y, null);
		}
		if(type == 9){
			g.drawImage(tex.lava[9], (int)x, (int)y, null);
		}
		if(type == 10){
			g.drawImage(tex.lava[10], (int)x, (int)y, null);
		}
		if(type == 11){
			g.drawImage(tex.lava[11], (int)x, (int)y, null);
		}
		if(type == 12){
			g.drawImage(tex.lava[12], (int)x, (int)y, null);
		}
		if(type == 13){
			g.drawImage(tex.lava[13], (int)x, (int)y, null);
		}
		if(type == 14){
			g.drawImage(tex.lava[14], (int)x, (int)y, null);
		}
		if(type == 15){
			g.drawImage(tex.lava[15], (int)x, (int)y, null);
		}
		if(type == 16){
			g.drawImage(tex.lava[16], (int)x, (int)y, null);
		}
		if(type == 17){
			g.drawImage(tex.lava[17], (int)x, (int)y, null);
		}
		if(type == 18){
			g.drawImage(tex.lava[18], (int)x, (int)y, null);
		}
		if(type == 19){
			g.drawImage(tex.lava[19], (int)x, (int)y, null);
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y + 15, 32, 22);
	}
	
}
