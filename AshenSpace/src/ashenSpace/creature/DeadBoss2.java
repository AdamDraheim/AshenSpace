package ashenSpace.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class DeadBoss2 extends GameObject{

	private Handler handler;
	public float gravity = 0.1f;
	private Animation deadBossLeft, deadBossRight;
	Texture tex = Game.getInstance();

	int velX = 10;
	
	double velY;
	int maxSpeed = 10;
	double dir;
	int timer;
	
	public DeadBoss2(float x, float y, double velY, int timer, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.velY = velY;
		this.timer = timer;
		dir = velY;
		deadBossRight = new Animation(10, tex.basicEnemy[28]);
		deadBossLeft = new Animation(10, tex.basicEnemy[27]);
		this.handler = handler;
	}

	public void tick() {
		
		if(velY < maxSpeed)
			velY += gravity;
		y += velY;
		if(timer > 100){
			if(dir > 0){
				x += velX;
			}else if(dir < 0){
				x -= velX;
			}
		}else if(timer < 100){
			if(dir > 0){
				x -= velX;
			}else if(dir < 0){
				x += velX;
			}
		}
		Collision();
		deadBossLeft.runAnimation();
		deadBossRight.runAnimation();
		
	}

	public void render(Graphics g) {
		
		if(dir > 0){
			deadBossRight.drawAnimation(g, (int)x, (int)y, 96,96);
		}else if(dir < 0){
			deadBossLeft.drawAnimation(g, (int)x, (int)y, 96,96);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}
	
	public void Collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){
				if(this.getBounds().intersects(tempObject.getBounds())){
					
					gravity = 0;
					velY = 0;
					velX = 0;
					
				}
			}
		}
	}

}
