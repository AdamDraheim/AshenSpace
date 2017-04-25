package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Flag;
import ashenSpace.object.Missile;

public class missileBoss extends GameObject{

	private Handler handler;
	private int velX = 10;
	private int velXMain = 10;
	private int swoopTimer = 200;
	private int coolDown = 100;
	private int dir = 1;
	double bulletVelX, bulletVelY;
	Texture tex = Game.getInstance();
	public static int bossHealth;
	double tangent;
	
	public missileBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.x = x -= 550;
		health = 500;
	}

	public void tick() {

		x += velX; 
		bossHealth = health;

		if(swoopTimer > 0){
			swoopTimer -= 4;
		}
		
		if(swoopTimer <= 0){
			coolDown--;
			velX = 0;
			if(coolDown == 0){
				swoopTimer = 200;
				coolDown = 100;
				velXMain *= -1;
				dir *= -1;
				velX = velXMain;
				
			}
		}
		
		
		
		
		if(swoopTimer != 0){
			
			y =  (-1 * (int)(0.01 * Math.pow(swoopTimer, 2) - (2 * swoopTimer)) + 300) - 150;
			
		}
		
		tangent = (-1 * (0.02 * swoopTimer) + 2);
		
		if(tangent == 0){
			tangent = (-1 * (0.02 * (swoopTimer - 1)) + 2);
		}
		
		
		track();
		collision();
		die();
	}

	public void render(Graphics g) {
		
		if(dir < 0){
			g.drawImage(tex.basicEnemy[12], (int)x, (int)y, 64, 64, null);
		}else if(dir > 0){
			g.drawImage(tex.basicEnemy[13], (int)x, (int)y, 64, 64, null);
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}
	
	public void collision(){
		
		
	}
	
	public void track(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.player){
				
double theta = Math.atan(-1 * ((tempObject.getY() - this.getY()) / (tempObject.getX() - this.getX())));
				
				if(tempObject.getX() > this.getX() && tempObject.getY() < this.getY()){
					bulletVelY = -100 * Math.sin(theta);
					bulletVelX = 100 * Math.cos(theta);
				}
				if(tempObject.getX() < this.getX() && tempObject.getY() < this.getY()){
					bulletVelY = 100 * Math.sin(theta);
					bulletVelX = -100 * Math.cos(theta);

				}
				if(tempObject.getX() > this.getX() && tempObject.getY() > this.getY()){
					bulletVelY = -100 * Math.sin(theta);
					bulletVelX = 100 * Math.cos(theta);

				}
				if(tempObject.getX() < this.getX() && tempObject.getY() > this.getY()){
					bulletVelY = 100 * Math.sin(theta);
					bulletVelX = -100 * Math.cos(theta);

				}				
				
				if(0.02 * swoopTimer - 2 == 0){
					
					sound.play("/sound/Bullet.wav");
					handler.addObject(new Missile(x, y, ID.missile, handler, bulletVelX, bulletVelY));
				}
				
			}
		}
			
	}
	
	public void die(){
		if(health <= 0){
			Achievements.kills++;

			handler.removeObject(this);
			handler.addObject(new Flag(896, 192, ID.flagD, 0, handler));
			handler.addObject(new DeadBoss2((int)x, (int)y, tangent, swoopTimer, ID.dead, handler));
			HUD.dialogTimer = 500;
			HUD.dialog = "Ok, I've looked into it and both of the command units are synced to a central network.";
			HUD.dialog2 = "If you are able to destroy more of them, I might be able to isolate their source.";
			HUD.dialog3 = "";
			
			if(HUD.mode == 0){
				HUD.lives++;
			}
		}
	}

}
