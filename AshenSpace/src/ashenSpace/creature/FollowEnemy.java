package ashenSpace.creature;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Explosion;
import ashenSpace.object.KaboomBox;

public class FollowEnemy extends GameObject{
	double velY;
	double velX;
	Handler handler;
	double calc;
	boolean spawn = false;
	Texture tex = Game.getInstance();
	int timer = 50;


	public FollowEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.id = id;
		health = 1;
		
	}

	public void tick() {

		follow();
		timer--;

		x += velX;
		y += velY;
		
		collision();

	}

	public void render(Graphics g) {
		
		if(timer > 25){
			if(velX >= 0){
				g.drawImage(tex.basicEnemy[21], (int)x, (int)y, 24, 24, null);
			}else if(velX < 0){
				g.drawImage(tex.basicEnemy[19], (int)x, (int)y, 24, 24, null);
			}
		}else if(timer <= 25){
			if(velX >= 0){
				g.drawImage(tex.basicEnemy[22], (int)x, (int)y, 24, 24, null);
			}else if(velX < 0){
				g.drawImage(tex.basicEnemy[20], (int)x, (int)y, 24, 24, null);
			}
		}
		if(timer < 0){
			timer = 50;
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 24, 24);
	}
	
	public void follow(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				
				double theta = Math.atan(-1 * ((tempObject.getY() - this.getY()) / (tempObject.getX() - this.getX())));
				
				if(tempObject.getX() > this.getX() && tempObject.getY() < this.getY()){
					velY = -4 * Math.sin(theta);
					velX = 4 * Math.cos(theta);
				}
				if(tempObject.getX() < this.getX() && tempObject.getY() < this.getY()){
					velY = 4 * Math.sin(theta);
					velX = -4 * Math.cos(theta);

				}
				if(tempObject.getX() > this.getX() && tempObject.getY() > this.getY()){
					velY = -4 * Math.sin(theta);
					velX = 4 * Math.cos(theta);

				}
				if(tempObject.getX() < this.getX() && tempObject.getY() > this.getY()){
					velY = 4 * Math.sin(theta);
					velX = -4 * Math.cos(theta);

				}				
				
				int playerDistance = (int)Math.sqrt(Math.pow(tempObject.getX() - this.getX(), 2) + Math.pow(tempObject.getY() - this.getY(),2));
	
				if(playerDistance >= 16000 || playerDistance < 5){
					velX = 0;
					velY = 0;
				}
			}
		}
	}

	
	
	public void collision(){
			
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block || tempObject.getId() == ID.player || tempObject.getId() == ID.bullet){
				if(this.getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					handler.addObject(new KaboomBox(x, y, ID.pDam, handler));
					sound.play("/sound/Explosion.wav");

					for(int ii = 0; ii < 20; ii++){
						handler.addObject(new Explosion(x, y, null, handler, 200, 0, 0, "red", 5));
					}

					for(int ii = 0; ii < handler.object.size(); ii++){
						GameObject tempObject2 = handler.object.get(ii);
						if(tempObject2.getId() == ID.enemySpawner){
							if(spawn == false){
								handler.addObject(new FollowEnemy(tempObject2.getX(), tempObject2.getY(), ID.basicEnemy, handler));
								spawn = true;
							}
						}
					}
				}
			}
		}
	}
}
