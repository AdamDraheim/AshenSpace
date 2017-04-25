package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Graphics;

import ashenSpace.creature.Arbol;
import ashenSpace.game.Game;
import ashenSpace.game.Handler;

public class Foreground {

	Game game;
	Handler handler;
	
	public Foreground(Game game, Handler handler){
	
	this.game = game;
	this.handler = handler;
	}
	
	public void render(Graphics g){
		if(game.LEVEL >= 14 && game.LEVEL <= 19){
			g.setColor(new Color(0, 0, 0, 160));
			g.fillRect(0, 0, 900, 700);
		}
		if(game.LEVEL == 20 && Arbol.darkness == true){
			g.setColor(new Color(0, 0, 0, 160));
			g.fillRect(0, 0, 900, 700);
		}
	}
	
}
