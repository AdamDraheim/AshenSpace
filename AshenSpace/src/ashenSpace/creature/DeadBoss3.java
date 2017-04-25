package ashenSpace.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class DeadBoss3 extends GameObject{

	private Handler handler;
	public float gravity = 0.1f;
	private Animation deadBossLeft, deadBossRight;
	Texture tex = Game.getInstance();
	double dir;
	int maxSpeed = 10;
	
	public DeadBoss3(float x, float y, ID id, double dir, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		deadBossRight = new Animation(10, tex.basicEnemy[24]);
		deadBossLeft = new Animation(10, tex.basicEnemy[25]);
		this.handler = handler;
		this.dir = dir;
	}

	public void tick() {
		
		Collision();
		deadBossLeft.runAnimation();
		deadBossRight.runAnimation();
		
	}

	public void render(Graphics g) {
		
		if(dir < 0){
			deadBossLeft.drawAnimation(g, (int)x, (int)y, 64, 64);
		}else if(dir > 0){
			deadBossRight.drawAnimation(g, (int)x, (int)y, 64, 64);
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
					if(velX == 5){
						x -= 20;
						
					}else if(velX == -5){
						x += 20;
					}
					velX = 0;
				}
			}
			if(tempObject.getId() == ID.mapBlock){
				if(this.getBounds().intersects(tempObject.getBounds())){
					velY = 0;
					gravity = 0;
					y -= 64;
				}
			}
		}
	}

}
