package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.audio.sound;
import ashenSpace.creature.Arbol;
import ashenSpace.creature.Danton;
import ashenSpace.creature.Player;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Bullet extends GameObject{

	
	private Handler handler;
	
	int velX;
	int dir = Player.facing;
	
	public Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}
	

	public void tick() {
		if(dir == 1)
			velX = 10;
		if(dir == -1)
			velX = -10;
		x += velX;
		Collision();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int)y, 4, 4);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 4, 4);
	}
	
	public void Collision(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.basicEnemy || tempObject.getId() == ID.boss || tempObject.getId() == ID.sniperEnemy || tempObject.getId() == ID.tutEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.decreaseHealth(tempObject, 10);
					handler.removeObject(this);
				}
			}
		}
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.mapBlock || tempObject.getId() == ID.block || tempObject.getId() == ID.flagA){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);

					for(int ii = 0; ii < 5; ii++){
						handler.addObject(new Explosion(x, y, null, handler, 230, 255, 255, "red", -5));
						
					}
					
				}
			}if(tempObject.getId() == ID.arbol){
				if(getBounds().intersects(tempObject.getBounds())){
					if(Arbol.invincible == false){
						handler.decreaseHealth(tempObject, 5);
						handler.removeObject(this);

					}
				}
			}if(tempObject.getId() == ID.missileBoss){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.decreaseHealth(tempObject, 25);
					handler.removeObject(this);

				}
			}if(tempObject.getId() == ID.danton){
				if(getBounds().intersects(tempObject.getBounds())){
					if(Danton.phase == 1 || Danton.phase == 3){
						handler.decreaseHealth(tempObject, 25);

					}else if(Danton.phase == 2){
						if(Danton.invincible == false){
							handler.decreaseHealth(tempObject, 25);

						}
					}
					handler.removeObject(this);

				}
			}
		}
	}
}
