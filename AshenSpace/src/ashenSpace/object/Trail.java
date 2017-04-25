package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Trail extends GameObject{

	int red = 255, green = 255, blue = 255;
	Handler handler;


	public Trail(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	public void tick() {
		
		red -= 10;
		blue -= 10;
		green -= 10;
		
		x += (int)(Math.random() * 5) - 2;
		y += (int) (Math.random() * 8) + 1;
		
		remove();

	}

	public void render(Graphics g) {
		
		int r = (int) (Math.random() * 10);
		g.setColor(new Color(red, green, blue));
		g.fillRect((int) x, (int) y, r, r);
	}

	public Rectangle getBounds() {
		return null;
	}
	
	public void remove(){
		if(red <= 20){
			handler.removeObject(this);
		}
	}

}
