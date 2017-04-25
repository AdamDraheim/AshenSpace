package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.MortarShell;

public class Mortar extends GameObject{

	public int timer = 300;
	Handler handler;
	
	public Mortar(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		timer--;
		if(timer == 0){
			
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.player){
					
					int initHeight = (int)(this.getY() - tempObject.getY());
					int mortarVelX = (int)(-1 * (this.getX() - tempObject.getX()) / 10);
					handler.addObject(new MortarShell((int)x, (int)y - 3, null , handler, mortarVelX, initHeight));					
				}
			
			timer = 300;
			}
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 64, 32);
	}

	public Rectangle getBounds() {
		return null;
	}

}
