package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.ComplexBullet;

public class rapidEnemy extends GameObject{

	private Handler handler;
	Random r = new Random();
	int BulletTimer = 300;
	int ShootTimer = 10;
	int ReloadTimer = 100;
	public static int health = 100;
	
	
	public rapidEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		facing = 1;
	}

	public void tick() {
		
		BulletTimer--;
		if(BulletTimer <= 0){
			
			ShootTimer--;
			ReloadTimer--;
			if(ShootTimer <= 0 && ReloadTimer > 0){
				handler.addObject(new ComplexBullet((int) x, (int) y + 16, (r.nextInt(2) - 1), facing, ID.enemyBullet, handler));
				ShootTimer = 10;
				
			}else if(ReloadTimer <= 0){
				BulletTimer = 200;
				ReloadTimer = 50;
			}
			
		}
		die();
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.pink);
		g.fillRect((int)x, (int) y, 32, 32);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 32);
	}
	
	public void die(){
		if (health <= 0){
			handler.removeObject(this);
		}
	}

}
