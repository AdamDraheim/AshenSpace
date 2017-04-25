package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class MortarShell extends GameObject{

	int velX;
	float velY = 200;
	int height;
	int timer = 0;
	Handler handler;
	float gravity = 40;
	
	public MortarShell(float x, float y, ID id, Handler handler, int velX, int height) {
		super(x, y, id);
		this.velX = velX;
		this.height = height;
		this.handler = handler;
	}

	public void tick() {
		
		timer++;
		double testTimer = ((double) timer) / 10;
		
		x += (velX / 12);
		y= (float) (-1 * (-0.5 * 40 * Math.pow(testTimer, 2) + (200 * testTimer)));
		
		collision();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block | tempObject.getId() == ID.player){
				if(this.getBounds().intersects(tempObject.getBounds())){
					
					handler.removeObject(this);
					handler.addObject(new KaboomBox(x, y, ID.pDam, handler));

					for(int ii = 0; ii < 100; ii++){
						handler.addObject(new Explosion(x, y, null, handler, 200, 0, 0, "red", 5));
					}
				}
			}
		}
	}

}
