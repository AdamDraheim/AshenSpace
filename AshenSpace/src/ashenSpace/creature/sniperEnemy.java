package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.sniperBullet;

public class sniperEnemy extends GameObject{

	int reloadTimer = 50;
	Handler handler;
	int timer = 150;
	int playerDistance;
	float bulletVelX, bulletVelY;
	int velY = 1;
	Texture tex = Game.getInstance();
	
	
	public sniperEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		health = 50;
	}

	public void tick() {
		if(id == ID.sniperEnemy){
			health = (int)Game.clamp(health, 0, 32);
			y += velY;
			reloadTimer--;
			track();
			die();
		}
		
	}

	public void render(Graphics g) {

		if(id == ID.sniperEnemy){

			
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ID.player){
					playerDistance = (int)Math.sqrt(Math.pow((int)this.getX() - (int)tempObject.getX(), 2) + (Math.pow((int)this.getY() - (int)tempObject.getY(), 2)));
					if(playerDistance < 300 && timer > 0 && reloadTimer <= 0){
						g.setColor(Color.red);
						g.drawLine((int)this.getX() + 16, (int)this.getY() + 16, (int)tempObject.getX() + 16, (int)tempObject.getY() + 16);
					}
				}
			}
			
			g.drawImage(tex.basicEnemy[16], (int)x - 16, (int)y - 32, 64, 64, null);
			
			g.setColor(Color.white);
			g.drawRect((int)x, (int) y - 5, health, 3);
			g.setColor(Color.red);
			g.fillRect((int) x, (int) y - 5, health, 3);
		}else if(id == ID.dead){
			g.drawImage(tex.basicEnemy[26], (int)x - 16, (int)y - 32, 64, 64, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void track(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.player){
				
				playerDistance = (int)Math.sqrt(Math.pow((int)this.getX() - (int)tempObject.getX(), 2) + (Math.pow((int)this.getY() - (int)tempObject.getY(), 2)));
				if(playerDistance < 300){
					if(reloadTimer <= 0){
						timer--;
						if(timer <= 0){
							
							bulletVelX = tempObject.getX() - this.getX();
							bulletVelY = tempObject.getY() - this.getY();
							
							handler.addObject(new sniperBullet((int) x + 16, (int) y + 16, ID.sniperBullet, bulletVelX, bulletVelY, handler));
							timer = 150;
							reloadTimer = 50;
							sound.play("/sound/Shoot.wav");
						}
					}
					
				}
			}else if(tempObject.getId() == ID.block){
				if(this.getBounds().intersects(tempObject.getBounds())){
					velY = 0;
				}
			}
		}
	}
	
	public void die(){
		if (health <= 0){
			Achievements.kills++;

			this.id = ID.dead;
		}
	}

}
