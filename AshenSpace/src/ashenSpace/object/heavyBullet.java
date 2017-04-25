package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.creature.Arbol;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class heavyBullet extends GameObject{

	
	private Handler handler;
	
	private int dirChange = 900;
	private float velX;
	Texture tex = Game.getInstance();
	private Animation bulletLeft, bulletRight;
	
	public heavyBullet(float x, float y, ID id, Handler handler, float velX) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.velX = velX;
		
		bulletRight = new Animation(5, tex.misc[6], tex.misc[7]);
		bulletLeft = new Animation(5, tex.misc[8], tex.misc[9]);
		
	}
	

	public void tick() {
		
		x += velX;
		bulletLeft.runAnimation();
		bulletRight.runAnimation();
		Collision();
		
	}

	public void render(Graphics g) {
		if(velX < 0){
			bulletLeft.drawAnimation(g, (int)x, (int) y + 16, 64, 64);
		}else if(velX > 0){
			bulletRight.drawAnimation(g, (int)x, (int) y + 16, 64, 64);

		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y + 16, 32, 32);
	}
	
	public void Collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					HUD.health -= 25;
					handler.removeObject(this);
					
				}
				
			}
			if(tempObject.getId() == ID.block  || tempObject.getId() == ID.mapBlock){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					handler.removeObject(this);
					sound.play("/sound/Explosion.wav");

					for(int ii = 0; ii < 200; ii++){
						handler.addObject(new Explosion((int) x + 10, (int) y + 32, null, handler, 0, 255, 0, "green", 5));

					}

					
					}
				
				}
		}
	}
	

}
