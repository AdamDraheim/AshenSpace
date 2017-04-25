package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.ID;

public class Medkit extends GameObject{

	Texture tex = Game.getInstance();
	
	int hue = 1;
	int change = 1;
	
	public Medkit(float x, float y, ID id) {
		super(x, y, id);
		
	}

	public void tick() {
		hue += change;
		if(hue >= 200 || hue <= 0){
			change *= -1;
		}
	}

	public void render(Graphics g) {

		g.setColor(new Color(255, 0, 0, hue));
		g.fillOval((int)x, (int)y, 32, 32);
		g.drawImage(tex.misc[18], (int)x, (int)y, 32, 32, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + 8, (int) y + 8, 16, 16);
	}

}
