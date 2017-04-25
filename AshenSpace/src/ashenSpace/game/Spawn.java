package ashenSpace.game;

import java.util.Random;

import ashenSpace.creature.Boss;
import ashenSpace.object.Asteroid;

public class Spawn {

	private Handler handler;
	Random r = new Random();
	
	
	public Spawn(Handler handler){
		this.handler = handler;
	}
	
	public void tick(){
		
		if(Game.LEVEL == 2 || Game.LEVEL == 3 || Game.LEVEL == 4  || Game.LEVEL == 7 || Game.LEVEL == 8 || Game.LEVEL == 9 || Game.LEVEL == 10 || Game.LEVEL == 11 ||
				 (Game.LEVEL >= 21 && Game.LEVEL <= 26)){
			if(r.nextInt(25) == 0){
				handler.addObject(new Asteroid(r.nextInt(Game.width) + 100, -200, ID.asteroid, handler));
			}
		}
	}
	
}
