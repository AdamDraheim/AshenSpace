package ashenSpace.creature;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Warrior extends GameObject{
	
	Texture tex = Game.getInstance();
	int facing = 0;
	Handler handler;
	String line1 = "I think I wound up in the wrong game";
	String line2 = "Where can I craft a sword and shield?";
	String line3 = "I heard a dragon was in this area.";
	String line4 = "Turned out to have left.";
	String line5 = "Some old fool keeps rambling about crafting";
	String line6 = "Neither weapons nor muscles can improve here.";
	String line7 = "You wish there were people to help you?";
	String line8 = "Well, it was hard enough to get you here.";
	String line9 = "What is your name again?";
	String line10 = "I honestly cannot remember";
	String line11 = "The dev should nerf the lava.";
	String line12 = "It is really overpowered.";
	int line = (int)(Math.random() * 5);
	boolean active = false;
	
	int timer = 300;
	
	public Warrior(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				if(tempObject.getX() > this.getX()){
					facing = 1;
				}else if(tempObject.getX() <= this.getX()){
					facing = 0;
				}
			}
		}
		collision();
		timer--;
		if(timer == 0){
			line = (int)(Math.random() * 6);
			timer = 300;
		}
		System.out.println(timer);
	}

	public void render(Graphics g) {
		if(facing == 0){
			g.drawImage(tex.easteregg[1], (int)x, (int)y, 64, 64, null);
		}else if(facing == 1){
			g.drawImage(tex.easteregg[2], (int)x, (int)y, 64, 64, null);
		}
		
		if(active){
			g.setColor(Color.gray);
			g.setFont(new Font("KodchiangUPC", 0, 22));
			g.fillRect((int)x + 70, (int)y - 60, 300, 70);
			g.setColor(Color.white);
			switch(line){
			
			case 0:
				g.drawString(line1, (int)x + 75, (int) y - 31);
				g.drawString(line2, (int)x + 75, (int) y - 10);
				break;
			case 1:
				g.drawString(line3, (int)x + 75, (int) y - 31);
				g.drawString(line4, (int)x + 75, (int) y - 10);
				break;
			case 2:
				g.drawString(line5, (int)x + 75, (int) y - 31);
				g.drawString(line6, (int)x + 75, (int) y - 10);
				break;
			case 3:
				g.drawString(line7, (int)x + 75, (int) y - 31);
				g.drawString(line8, (int)x + 75, (int) y - 10);
				break;
			case 4:
				g.drawString(line9, (int)x + 75, (int) y - 31);
				g.drawString(line10, (int)x + 75, (int) y - 10);
				break;
			case 5:
				g.drawString(line11, (int)x + 75, (int) y - 31);
				g.drawString(line12, (int)x + 75, (int) y - 10);
			}
		}
		
		g.drawImage(tex.easteregg[0], (int)x - 32, (int)y - 250, 128, 128, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x - 64, (int)y - 64, 192, 192);
	}

	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.player){
				if(tempObject.getId() == ID.player){
					if(tempObject.getBounds().intersects(this.getBounds())){
						active = true;
					}else{
						active = false;
					}
				}
			}
		}
	}
	
}
