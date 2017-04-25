package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Rocket;

public class rocketEnemy extends GameObject{

	Handler handler;
	int rocketX;
	private int timer = 250;
	Texture tex = Game.getInstance();
	private Animation enemyFloat;


	public rocketEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velY = -5;
		health = 100;
		enemyFloat = new Animation(10, tex.basicEnemy[14], tex.basicEnemy[15]);
	}
	

	public void tick() {
		
		if(id == ID.basicEnemy){
			enemyFloat.runAnimation();
			health = (int)Game.clamp(health, 0, 32);
			collision();
			die();
			timer--;
			
			if(timer <= 0){
				
				handler.addObject(new Rocket((int)x, (int) y, null, handler, rocketX));
				timer = 100;
				
			}
			
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.player){
					
					if(tempObject.getX() <= this.getX()){
						
						rocketX = -10;
						
					}else{
						
						rocketX = 10;
					}
					
				}
			}
		}
	}

	public void render(Graphics g) {
		if(id == ID.basicEnemy){
		enemyFloat.drawAnimation(g, (int)x,  (int)y, 32, 32);

			g.setColor(Color.white);
			g.drawRect((int)x, (int) y - 5, health, 3);
			g.setColor(Color.red);
			g.fillRect((int) x, (int) y - 5, health, 3);
		}else if(id == ID.dead){
			g.drawImage(tex.basicEnemy[23], (int)x, (int)y + 16, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 32);
	}
	
	public void collision(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.block){
				velY = 0;
			}
		}
		
	}
	
	public void die(){
		if(this.health <= 0){
			Achievements.kills++;

			this.id = ID.dead;
		}
	}

}
