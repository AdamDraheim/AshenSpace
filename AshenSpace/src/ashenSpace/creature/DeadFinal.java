package ashenSpace.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.GameState.Difficulty;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.ID;

public class DeadFinal extends GameObject {

	Texture tex = Game.getInstance();

	
	public DeadFinal(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		if(Difficulty.difficulty == 1){
			HUD.dialogTimer = 500;
			HUD.objective = "Stop Danton";
			HUD.objectiveCont = "End this";
			HUD.dialog = "Hahaha! You did it! You killed the guardian! The vault is opening";
			HUD.dialog2 = "Leave now, I appreciate the help but now that the vault is open, I no";
			HUD.dialog3 = "longer have need of you. Be the herald of the era of order.";
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.finalBoss[13], (int)x, (int)y, 248, 248, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(1,1,1,1);
	}

}
