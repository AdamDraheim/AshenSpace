package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.audio.sound;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class LSniperBullet extends GameObject{

	Handler handler;
	
	double velX;
	double velY;
	
	public LSniperBullet(float x, float y, ID id, double velX, double velY, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
	}

	public void tick() {
		
		Collision();
		x += (velX / 10);
		y += (velY / 10);
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void Collision(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block || tempObject.getId() == ID.mapBlock  || tempObject.getId() == ID.shield){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					sound.play("/sound/Shoot.wav");

				}
			}else if(tempObject.getId() == ID.player){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.health -= 40;
					handler.removeObject(this);
					sound.play("/sound/Bullet.wav");

				}
			}
		}
		
	}

}
