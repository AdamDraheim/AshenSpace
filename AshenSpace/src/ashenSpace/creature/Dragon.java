package ashenSpace.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.ID;

public class Dragon extends GameObject{

	
	Texture tex = Game.getInstance();
	int stage = 0;
	int timer = 100;
	int headposx;
	int headposy;

	public Dragon(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
		System.out.println(stage);
		
		headposx = (int) (x + 5);
		headposy = (int) (y + 20);
		
		if(stage == 0){
			timer--;
			if(timer == 0){
			}
			if(timer < - 100){
				stage = 1;
			}
		}
		if(stage == 1){
			x += 5;
			if(x > 2000){
				x = 1344;
				y = 1500;
				stage = 2;
			}
		}
		
		if(stage == 2){
			y -= 3;
			if(y <  725){
				stage = 3;
				timer = 100;
			}
			
		}
		if(stage == 3){
			timer--;
			if(timer == 0){
				
			}
			
			if(timer < -100){
				stage = 4;
			}
		}
		
		if(stage == 4){
			x += 5;
			if(x > 2000){
				stage = 5;
				x = -1000;
				y = 256;
			}
		}
		
		if(stage == 5){
			x += 5;
			if(x > 0){
				stage = 6;
				timer = 100;
			}
		}
		
		if(stage == 6){
			timer--;
			if(timer == 0){
				
			}
			
			if(timer < -100){
				
				stage = 7;
			}
		}
		
		if(stage == 7){
			
			x -= 5;
			if(x < -1000){
				x = 0;
				y = 1500;
				stage = 8;
			}
			
		}
		
		if(stage == 8){
			
			y -= 3;
			if(y < 725){
				stage = 9;
				timer = 100;
			}
			
		}
		
		if(stage == 9){
			
			timer--;
			
			if(timer == 0){
				
			}
			
			if(timer < -100){
				stage = 10;
			}
			
		}
		
		if(stage == 10){
		 
			y += 3;
			
			if(y > 1500){
				
				x = 2000;
				y = 256;
				stage = 11;
			}
			
		}
		
		if(stage == 11){
			x -= 5;
			
			if(x < 896){
				timer = 100;
				stage = 0;
			}
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(tex.dragon[0], (int)x, (int)y - 92, 384, 256, null);
		g.setColor(Color.red);
		//g.fillRect((int)x + 5, (int)y + 20, 68, 56);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x + 5, (int)y + 20, 68, 56);
	}
	
	public void collision(){
		
	}
	
	public void die(){
		
	}

}
