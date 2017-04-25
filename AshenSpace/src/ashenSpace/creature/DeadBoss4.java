package ashenSpace.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class DeadBoss4 extends GameObject{

	private Handler handler;
	public float gravity = 0.1f;
	private Animation deadBossLeft, deadBossRight;
	Texture tex = Game.getInstance();
	int velX = 5;
	float velY = 0;
	int maxSpeed = 10;
	
	public DeadBoss4(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		deadBossRight = new Animation(10, tex.basicEnemy[17]);
		deadBossLeft = new Animation(10, tex.basicEnemy[18]);
		this.handler = handler;
	}

	public void tick() {
		
		deadBossLeft.runAnimation();
		deadBossRight.runAnimation();
		
	}

	public void render(Graphics g) {
		
		deadBossLeft.drawAnimation(g, (int)x, (int)y, 4, 4);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}
	

}
