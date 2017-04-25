package ashenSpace.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Animation;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Barrier extends GameObject{

	public static int charge = 0;
	public static int chargereq;
	Handler handler;
	Game game;
	private Animation flow;
	
	Texture tex = Game.getInstance();

	
	public Barrier(float x, float y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		flow = new Animation(3, tex.misc[12], tex.misc[13], tex.misc[14]);
		
	}

	public void tick() {
		if(game.LEVEL == 28){
			chargereq = 3;
		}
		if(charge == chargereq){
			charge = 0;
			handler.removeObject(this);
		}
		
		flow.runAnimation();
	}

	public void render(Graphics g) {
		flow.drawAnimation(g, (int)x, (int)y - 16, 32, 96);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y - 16, 32, 96);
	}

}
