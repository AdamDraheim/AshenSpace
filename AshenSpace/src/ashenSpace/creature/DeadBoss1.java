package ashenSpace.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class DeadBoss1 extends GameObject{

	private Handler handler;
	public float gravity = 0.1f;
	private Animation deadBossLeft, deadBossRight;
	Texture tex = Game.getInstance();
	int velX = 5;
	float velY = 0;
	int maxSpeed = 10;
	
	public DeadBoss1(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		deadBossRight = new Animation(10, tex.basicEnemy[4]);
		deadBossLeft = new Animation(10, tex.basicEnemy[5]);
		this.handler = handler;
	}

	public void tick() {
		
		if(velY < maxSpeed)
			velY += gravity;
		y += velY;
		x += velX;
		Collision();
		deadBossLeft.runAnimation();
		deadBossRight.runAnimation();
		
	}

	public void render(Graphics g) {
		
		deadBossLeft.drawAnimation(g, (int)x, (int)y, 96,96);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}
	
	public void Collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){
				if(this.getBounds().intersects(tempObject.getBounds())){
					if(velX == 5){
						x -= 20;
						
					}else if(velX == -5){
						x += 20;
					}
					velX = 0;
				}
			}
			if(tempObject.getId() == ID.block){
				if(this.getBounds().intersects(tempObject.getBounds())){
					velY = 0;
					gravity = 0;
				}
			}
		}
	}

}
