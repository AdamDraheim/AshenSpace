package ashenSpace.game;

import java.applet.Applet;
import java.awt.BorderLayout;

public class ASApplet extends Applet{

	private static final long serialVersionUID = 1L;

	private Game game = new Game();
	
	public void init(){
		setSize(800, 600);
		setLayout(new BorderLayout());
		add(game);
	}
	
	public void start(){
		game.start();
	}
	
	public void stop(){
		game.stop();
	}
	
}
