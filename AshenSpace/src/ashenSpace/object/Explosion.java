package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Explosion extends GameObject{

	int red, green, blue, change;
	String colFoc;
	Handler handler;


	public Explosion(float x, float y, ID id, Handler handler, int red, int green, int blue, String colFoc, int change) {
		super(x, y, id);
		this.handler = handler;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.colFoc = colFoc;
		this.change = change;
	}

	public void tick() {
		if(colFoc == "red"){
			
			red -= change;
			if(red == 0 || red == 255){
				handler.removeObject(this);
			}
			
		}else if(colFoc == "green"){
			green -= change;
			if(green == 0 || green == 255){
				handler.removeObject(this);
			}
		}else if(colFoc == "blue"){
			blue -= change;
			if(blue == 0 || blue == 255){
				handler.removeObject(this);
			}
		}
		
		x += (int)(Math.random() * 10) - 5;
		y += (int) (Math.random() * 10) - 5;

	}

	public void render(Graphics g) {
		
		int r = (int) (Math.random() * 10);
		g.setColor(new Color(red, green, blue));
		g.fillRect((int) x, (int) y, r, r);
	}

	public Rectangle getBounds() {
		return null;
	}

}
