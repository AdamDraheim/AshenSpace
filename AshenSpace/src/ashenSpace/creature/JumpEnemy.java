package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Enemybullet;
import ashenSpace.object.FourWayBullet;

public class JumpEnemy extends GameObject{

	int velX = 5;
	float velY;
	float gravity = 0.5f;
	Handler handler;
	public JumpEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		health = 100;
		this.handler = handler;
		
	}

	public void tick() {
		
		velY += gravity;
		x += velX;
		y += velY;
		if(velY == 0){
			handler.addObject(new FourWayBullet((int) x + 32, (int)y + 16, 5, 0, ID.enemyBullet, handler));
			handler.addObject(new FourWayBullet((int) x, (int)y + 16, -5, 0, ID.enemyBullet, handler));
			handler.addObject(new FourWayBullet((int) x + 16, (int)y + 32, 0, 5, ID.enemyBullet, handler));
			handler.addObject(new FourWayBullet((int) x + 16, (int)y, 0, -5, ID.enemyBullet, handler));

		}
		collision();
		die();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 32);
		g.fillRect((int) x + 8, (int) y + 31, 16, 1);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getBottomBounds(){
		return new Rectangle((int) x + 8, (int) y + 31, 16, 1);
	}
	public Rectangle getLeftBounds(){
		return new Rectangle((int)x, (int) y + 8, 1, 16);
	}
	public Rectangle getRightBounds(){
		return new Rectangle((int)x + 31, (int) y + 8, 1, 16);
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){
				if(getBottomBounds().intersects(tempObject.getBounds())){
					velY = -15;
					setY(getY() - 0.5f);
				}
				if(getLeftBounds().intersects(tempObject.getBounds())){
					velX *= -1;
					setX(getX() + 5);
				}
				if( getRightBounds().intersects(tempObject.getBounds())){
					velX *= -1;
					setX(getX() - 5);
				}
			}
		}
	}
	public void die(){
		if(health <= 0){
			handler.removeObject(this);
		}
	}
}
