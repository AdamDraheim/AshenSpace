package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import ashenSpace.GameState.Charsel;
import ashenSpace.GameState.Loading;
import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.audio.sound;
import ashenSpace.game.Camera;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.game.Game.STATE;
import ashenSpace.input.KeyInput;
import ashenSpace.object.Barrier;
import ashenSpace.object.Bullet;
import ashenSpace.object.Enemybullet;
import ashenSpace.object.Flag;
import ashenSpace.object.heavyBullet;
import ashenSpace.object.reboundBullet;
import ashenSpace.ser.SerializationTut;
import ashenSpace.ser.fileInfoTut;
import ashenSpace.tutorial.tutHUD;

public class Player extends GameObject{

	private float width = 32, height = 32;
	float gravity = 0.38f;
	private final float maxSpeed = 10;
	private Handler handler;
	public static int facing = 1;
	Texture tex = Game.getInstance();
	private Animation playerRunRight, playerRunLeft;
	private Animation DantonRight, DantonLeft;
	private Animation hazRight, hazLeft;
	private Animation droidRight, droidLeft;
	private Animation sysRight, sysLeft;

	private int slowTimer = 7;
	public static int shieldDeathTimer = 200;
	public static boolean shieldActive;
	private Game game;
	public static int invincible = 5;
	public static int charge = 0;
	public static int chargeTimer = 500;
	SerializationTut sertut = new SerializationTut();
	
	public Player(float x, float y, Handler handler, Camera cam, ID id, Game game) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 0;
		this.game = game;
		
		
		playerRunRight = new Animation(10, tex.player[0], tex.player[1], tex.player[2], tex.player[3]);
		playerRunLeft = new Animation(10, tex.player[4], tex.player[5], tex.player[6], tex.player[7]);
		DantonRight = new Animation(10, tex.danton[1], tex.danton[3], tex.danton[1], tex.danton[5]);
		DantonLeft = new Animation(10, tex.danton[0], tex.danton[2], tex.danton[2], tex.danton[4]);
		hazRight = new Animation(10, tex.hazSuit[3], tex.hazSuit[4], tex.hazSuit[3], tex.hazSuit[5]);
		hazLeft = new Animation(10, tex.hazSuit[0], tex.hazSuit[1], tex.hazSuit[0], tex.hazSuit[2]);
		droidRight = new Animation(10, tex.droid[3], tex.droid[4], tex.droid[3], tex.droid[5]);
		droidLeft = new Animation(10, tex.droid[0], tex.droid[1], tex.droid[0], tex.droid[2]);
		
		sysRight = new Animation(5, tex.renderMan[6], tex.renderMan[9], tex.renderMan[7], tex.renderMan[10], tex.renderMan[6], tex.renderMan[9], tex.renderMan[8], tex.renderMan[11]);
		sysLeft = new Animation(5, tex.renderMan[0], tex.renderMan[3], tex.renderMan[1], tex.renderMan[4], tex.renderMan[0], tex.renderMan[3], tex.renderMan[2], tex.renderMan[5]);

	}

	public void tick() {
		
		if(Charsel.character == 0){
			playerRunRight.runAnimation();
			playerRunLeft.runAnimation();
		}else if(Charsel.character == 2){
			DantonRight.runAnimation();
			DantonLeft.runAnimation();
		}else if(Charsel.character == 1){
			hazRight.runAnimation();
			hazLeft.runAnimation();
		}else if(Charsel.character == 3){
			droidRight.runAnimation();
			droidLeft.runAnimation();
		}else if(Charsel.character == 4){
			sysRight.runAnimation();
			sysLeft.runAnimation();
		}
		
		getInput();
		
		chargeTimer--;
		invincible--;
		
		if(shieldActive == true)
			shieldDeathTimer--;
		if(shieldDeathTimer <= 0){
			shieldActive = false;
			shieldDeathTimer = 200;
		}
		slowTimer--;
		if(slowTimer == 0){
			KeyInput.shieldTimer++;
			slowTimer = 7;
			if(KeyInput.shieldTimer > 62){
				KeyInput.shieldTimer = 62;
			}
		}
		
		x += velX;
		y += velY;
		if(jumping || falling){
			velY += gravity;
			if(velY > maxSpeed){
				velY = maxSpeed;

			}
		}
		
		Collision(handler.object);

		die();
		
	}

	public void render(Graphics g) {
		
		if(Charsel.character == 0){
			if(velX != 0){
				if(facing == 1){
					playerRunRight.drawAnimation(g, (int)x, (int)y, 32, 32);
				}else if(facing == -1){
					playerRunLeft.drawAnimation(g, (int)x, (int)y, 32, 32);
				}
			}else{
				if(facing == 1)
					g.drawImage(tex.player[0], (int) x, (int) y, null);
				else
					g.drawImage(tex.player[4], (int) x, (int) y, null);
			}
		}else if(Charsel.character == 2){
			if(velX != 0){
				if(facing == 1){
					DantonRight.drawAnimation(g, (int)x, (int)y, 32, 32);
				}else if(facing == -1){
					DantonLeft.drawAnimation(g, (int)x, (int)y, 32, 32);
				}
			}else{
				if(facing == 1)
					g.drawImage(tex.danton[1], (int) x, (int) y, null);
				else
					g.drawImage(tex.danton[0], (int) x, (int) y, null);
			}
		}else if(Charsel.character == 1){
			if(velX != 0){
				if(facing == 1){
					hazRight.drawAnimation(g, (int)x, (int)y, 32, 32);
				}else if(facing == -1){
					hazLeft.drawAnimation(g, (int)x, (int)y, 32, 32);
				}
			}else{
				if(facing == 1)
					g.drawImage(tex.hazSuit[3], (int) x, (int) y, null);
				else
					g.drawImage(tex.hazSuit[0], (int) x, (int) y, null);
			}
		}else if(Charsel.character == 3){
			if(velX != 0){
				if(facing == 1){
					droidRight.drawAnimation(g, (int)x, (int)y, 32, 32);
				}else if(facing == -1){
					droidLeft.drawAnimation(g, (int)x, (int)y, 32, 32);
				}
			}else{
				if(facing == 1)
					g.drawImage(tex.droid[3], (int) x, (int) y, null);
				else
					g.drawImage(tex.droid[0], (int) x, (int) y, null);
			}
		}else if(Charsel.character == 4){
			if(velX != 0){
				if(facing == 1){
					sysRight.drawAnimation(g, (int)x, (int)y, 32, 32);
				}else if(facing == -1){
					sysLeft.drawAnimation(g, (int)x, (int)y, 32, 32);
				}
			}else{
				if(facing == 1)
					
					g.drawImage(tex.renderMan[((int)(Math.random() * 2) * 3) + 6], (int) x, (int) y, null);
				else
					g.drawImage(tex.renderMan[(int)(Math.random() * 2) * 3], (int) x, (int) y, null);
			}
			
		}
		
		if(shieldActive == true && facing == 1){
			g.drawImage(tex.misc[2],(int)x + 12, (int) y, null);
		}else if(shieldActive == true && facing == -1){
			g.drawImage(tex.misc[3],(int) x - 10, (int) y, null);
		}
	}

	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int)(x + (width / 2) - ((width/2)/2)), (int)(y + (height / 2)), (int)width / 2, (int)height / 2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + ((width/2) - ((width / 2) / 2))), (int)y, (int)width / 2, (int)height / 2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 4), (int)y + 5, (int)5, (int)height - 10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
	}
	
	public Rectangle shieldBound(){
		if(shieldActive == true && facing == 1){
			return new Rectangle((int)x + 32, (int) y, 15, 32);
		}else if(shieldActive == true && facing == -1){
			return new Rectangle((int) x - 10, (int) y, 10, 32);
		}else{
			return new Rectangle((int) x, (int) y, 1, 1);
		}
	}
	
	private void getInput(){
		velX = 0;
	
		if(KeyInput.left){
			velX = -5;
			facing = -1;
		}
		if(KeyInput.right){
			velX = 5;
			facing = 1;
		}
	}
	
	private void Collision(LinkedList<GameObject> object){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.shield){
				if(shieldDeathTimer <= 0){
					handler.removeObject(tempObject);
					shieldActive = false;
					shieldDeathTimer = 200;
				}
			}
			
			if(tempObject.getId() == ID.block || tempObject.getId() == ID.removable || tempObject.getId() == ID.tutEnemy){
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					
					velY = 0;
					falling = false;
					jumping = true;
					setY(getY() + 2);
					
				}else if(getBoundsBottom().intersects(tempObject.getBounds())){
					
					velY = 0;
					falling = false;
					jumping = false;
					setY(getY() - 1);
					
				}else if(getBoundsRight().intersects(tempObject.getBounds())){
					
					velX = 0;
					setX(getX() - 5);
				
				}else if(getBoundsLeft().intersects(tempObject.getBounds())){
						
						velX = 0;
						setX(getX() + 5);	
					
				}else
					
					falling = true;
			}else if(tempObject.getId() == ID.asteroid){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.health -= 3;
					
					
				}
			}else if(tempObject.getId() == ID.basicEnemy || tempObject.getId() == ID.boss ||tempObject.getId() == ID.rapidEnemy || tempObject.getId() == ID.sniperEnemy || tempObject.getId() == ID.arbol || tempObject.getId() == ID.missileBoss){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.health -= 5;
					
				}
			}else if(tempObject.getId() == ID.flagA){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.switchLevel();

				}
			}else if(tempObject.getId() == ID.flagB){
				if(getBounds().intersects(tempObject.getBounds())){
					game.LEVEL = 100;
					handler.switchLevel();

				}
			}else if(tempObject.getId() == ID.flagC){
				if(getBounds().intersects(tempObject.getBounds())){
					game.LEVEL = 23;
					handler.switchLevel();

				}
			}else if(tempObject.getId() == ID.flagD){
				if(getBounds().intersects(tempObject.getBounds())){
					Loading.imageChoice = (int)(Math.random() * 3);
					game.gameState = STATE.loading;
				}
			}else if(tempObject.getId() == ID.flagE){
				if(getBounds().intersects(tempObject.getBounds())){
					game.gameState = STATE.menu;
					KeyInput.right = false;
					KeyInput.left = false;
					
					fileInfoTut fileInfoTut = new fileInfoTut(true);
					sertut.save(fileInfoTut);

					handler.object.clear();
					
					
					
				}
			
			}else if(tempObject.getId() == ID.medkit){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.medkit++;
					handler.removeObject(tempObject);
					sound.play("/sound/Health.wav");
					
					if(game.gameState == STATE.tutorial){
						tutHUD.dialog = "Great! You are fully healed. Testing is now complete. There are no errors.";
						tutHUD.dialog2 = "We are ending this simulation. You are a worthy unit for this task. None better.";
						tutHUD.dialog3 = "Terminal exit port now spawning... Use it to leave the  simulation.";
						tutHUD.dialog4 = "Oh, and a new suit customization is available in your menu interface.";
						handler.addObject(new Flag((45*32) - 2, 6*32, ID.flagE, 0, handler));
					}


				}
			}else if(tempObject.getId() == ID.lava){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.health = 0;
					tutHUD.health = 0;
				}
			}else if(tempObject.getId() == ID.enemyBullet || tempObject.getId() == ID.complexBullet || tempObject.getId() == ID.sniperBullet || tempObject.getId() == ID.missile){
				if(shieldBound().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					sound.play("/sound/Deflect.wav");

				}
			}else if(tempObject.getId() == ID.heavyBullet)
				if(shieldBound().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					if(facing == 1){
						
						handler.addObject(new reboundBullet((int)x, (int) y - 24, ID.reboundBullet, handler, 5));
						
					}else if(facing == -1){
						
						handler.addObject(new reboundBullet((int)x, (int) y - 24, ID.reboundBullet, handler, -5));
						
					}
				}
		}
	}
	
	public void die(){
		if(HUD.health <= 0){
			handler.removeObject(this);
			HUD.lives--;
			if(HUD.lives >= 1){
				HUD.health = 100;
				game.LEVEL--;
				handler.switchLevel();
				Arbol.animDecrease = 1350;
				Arbol.dirChange = 900;
				Arbol.darkness = false;
				Arbol.spawnTimer = 0;
				Barrier.charge = 0;
				Arbol.ArbolActive = false;
				Boss.active = false;
				FollowBoss.active = false;
				Danton.bossHealth = 0;
				
			}else if(HUD.lives <= 0){
				game.gameState = STATE.menu;
				Arbol.animDecrease = 1350;
				Arbol.dirChange = 900;
				Arbol.darkness = false;
				Arbol.spawnTimer = 0;
				Barrier.chargereq = 0;
				Barrier.charge = 0;
				Arbol.ArbolActive = false;
				Boss.active = false;
				Danton.bossHealth = 0;
				Danton.active = false;
				FollowBoss.active = false;
				HUD.followBossActive = false;
				HUD.firstBoss = false;

				game.music.stop();
				game.OpenMusic.play();
				
			}
		}
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	


}
