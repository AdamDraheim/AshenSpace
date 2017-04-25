package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.creature.Player;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Shield extends GameObject{

	Handler handler;
	
	Texture tex = Game.getInstance();
	
	public Shield(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
		Player.shieldDeathTimer--;
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//if(Player.facing == 1){
			//g.drawImage(tex.misc[2], (int) x - 24, (int) y, null);
		/*}else if(Player.facing == -1){
			g.drawImage(tex.misc[3], (int)x, (int)y, null);
		}*/
		
	}

	public Rectangle getBounds() {
		return null;
	}
	public void die(){
	
	}
}
