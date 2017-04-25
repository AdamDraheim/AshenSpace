package ashenSpace.creature;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Explosion;
import ashenSpace.object.Flag;
import ashenSpace.object.KaboomBox;

public class FollowBoss extends GameObject{
	double velY;
	double velX;
	Handler handler;
	double calc;
	boolean spawn = false;
	Texture tex = Game.getInstance();
	int timer = 30;
	public static boolean active = false;;


	public FollowBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.id = id;
		health = 1;
		
	}

	public void tick() {

		timer--;
		follow();

		x += velX;
		y += velY;
		
		collision();
		die();

	}

	public void render(Graphics g) {
		
		if(timer >= 15){
			g.drawImage(tex.basicEnemy[17], (int)x, (int)y - 30, 5,5, null);
		}else if(timer < 15){
			g.drawImage(tex.basicEnemy[18], (int)x, (int)y - 30, 5,5, null);
		}
		if(timer < 0){
			timer = 30;
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y - 30, 5, 5);
	}
	
	public void follow(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				
				double theta = Math.atan(-1 * ((tempObject.getY() - this.getY()) / (tempObject.getX() - this.getX())));
				
				if(tempObject.getX() > this.getX() && tempObject.getY() < this.getY()){
					velY = -2 * Math.sin(theta);
					velX = 2 * Math.cos(theta);
				}
				if(tempObject.getX() < this.getX() && tempObject.getY() < this.getY()){
					velY = 2 * Math.sin(theta);
					velX = -2 * Math.cos(theta);

				}
				if(tempObject.getX() > this.getX() && tempObject.getY() > this.getY()){
					velY = -2 * Math.sin(theta);
					velX = 2 * Math.cos(theta);

				}
				if(tempObject.getX() < this.getX() && tempObject.getY() > this.getY()){
					velY = 2 * Math.sin(theta);
					velX = -2 * Math.cos(theta);

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
			if(tempObject.getId() == ID.followBoss){
				if(tempObject.getBounds().intersects(this.getBounds())){
					this.x += (Math.random() * 5) - 2;
					this.y += (Math.random() * 5) - 2;
				}
			}else if(tempObject.getId() == ID.bullet){
				if(tempObject.getBounds().intersects(this.getBounds())){
					HUD.followBossHealth -= 15;
					handler.removeObject(tempObject);
				}
			}else if(tempObject.getId() == ID.player){
				if(tempObject.getBounds().intersects(this.getBounds())){
					HUD.health -= 0.0001f;
				}
			}
		}
	}
	
	public void die(){
		if(HUD.followBossHealth <= 0){

			active = false;
			handler.removeObject(this);
			HUD.followBossActive = false;
			for(int i = 0; i < 5; i++){
				handler.addObject(new Explosion(x, y - 60, null, handler, 200, 0, 0, "red", 5));
			}
			
			HUD.dialogTimer = 500;
			HUD.objective = "Leave APDVF";
			HUD.objectiveCont = "";
			HUD.dialog = "Perfect. The overlord is at the top of the tower. Kill him and I can end this...";
			HUD.dialog2 = "Strike down the guardian and we are in the clear. Then, you are done.";
			HUD.dialog3 = "";
			
			handler.addObject(new Flag((27 * 32), 26 * 32, ID.flagD, 0, handler));

		}
	}
}
