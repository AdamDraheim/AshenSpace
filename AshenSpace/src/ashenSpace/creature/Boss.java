package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Achievements;
import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.ComplexBullet;
import ashenSpace.object.Explosion;
import ashenSpace.object.Flag;

public class Boss extends GameObject{

	Handler handler;
	int BulletTimer = 10;
	int velX = -5;
	public static int facing = 1;
	int MoveTimer = 10;
	public static int bossHealth;
	Texture tex = Game.getInstance();
	private Animation boss1RunRight, boss1RunLeft;
	public static boolean active = true;
	
	public Boss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		health = 300;
		active = true;
		boss1RunRight = new Animation(10, tex.basicEnemy[2]);
		boss1RunLeft = new Animation(10, tex.basicEnemy[1]);
	}

	public void tick() {
		
			bossHealth = health;

			x += velX;
			BulletTimer--;

			if(BulletTimer <= 0){
				sound.play("/sound/Shoot.wav");
				if(facing == -1){
					handler.addObject(new ComplexBullet((int) x + 72, (int) y + 64, 5, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 72, (int) y + 64, 2, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 72, (int) y + 64, 0, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 72, (int) y + 64, -2, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 72, (int) y + 64, -5, facing, ID.complexBullet, handler));
				}else if(facing == 1){
					handler.addObject(new ComplexBullet((int) x + 20, (int) y + 64, 5, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 20, (int) y + 64, 2, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 20, (int) y + 64, 0, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 20, (int) y + 64, -2, facing, ID.complexBullet, handler));
					handler.addObject(new ComplexBullet((int) x + 20, (int) y + 64, -5, facing, ID.complexBullet, handler));
				}
				if(health > 150){
					BulletTimer = 50;
				}else if(health <= 150){
					BulletTimer = 25;
					//handler.addObject(new Bomb((int) x, (int) y, id.bomb));
				
			}
		}
		boss1RunRight.runAnimation();
		boss1RunLeft.runAnimation();
		collision();
		die();
		
	}

	public void render(Graphics g) {
		
		if(facing == -1){
			boss1RunRight.drawAnimation(g, (int)x, (int)y, 96,96);
		}else if(facing == 1){
			boss1RunLeft.drawAnimation(g, (int)x, (int)y, 96, 96);
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
		
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.block){
				if(this.getBounds().intersects(tempObject.getBounds())){
					
					if(velX == 5){
						x -= 5;
						facing = 1;
					}else if(velX == -5){
						x += 5;
						facing = -1;
					}
					
					velX *= -1;
				}
			}
		}
	}
	
	public void die(){
		if (health <= 0){
			Achievements.kills++;
			HUD.firstBoss = false;

			for(int i = 0; i < 200; i++){
				handler.addObject(new Explosion((int)x + 32, (int) y + 32, null, handler, 255, 0, 0, "red", 5));
			}
			handler.removeObject(this);
			active = false;
			handler.addObject(new Flag(864, 384, ID.flagD, 0, handler));
			handler.addObject(new DeadBoss1(this.x, this.y, ID.dead, handler));
			HUD.danton = "Danton";
			HUD.dialogTimer = 500;
			HUD.dialog = "Dang, that was a tough bot. He appears more advanced than what you fought before. Looks like ";
			HUD.dialog2 = "communications cleared up. Whatever attacked your ship doesn't seem to be here. You don't appear";
			HUD.dialog3 = " on the ship's manifest, but you may be able to help me.. Name's Danton.";
			if(HUD.mode == 0){
				HUD.lives++;
			}
			
		}
	}

}
