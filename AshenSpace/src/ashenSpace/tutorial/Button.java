package ashenSpace.tutorial;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ashenSpace.anim.BufferedImageLoader;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Button extends GameObject{

	Handler handler;
	
	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage button = null;
	
	public Button(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		try{
			button = loader.loadImage("/tex/Button.png");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void tick() {
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.bullet){
				if(tempObject.getBounds().intersects(this.getBounds())){
					for(int ii = 0; ii < handler.object.size(); ii++){
						GameObject t2 = handler.object.get(ii);
						if(t2.getId() == ID.removable){
							
							tutHUD.shieldOnline = true;
							handler.removeObject(t2);
							tutHUD.dialog = "Well done! Now, we will test against an enemy who fights back.";
							tutHUD.dialog2 = "You have a couple of choices. Try using 's' to activate your shield.";
							tutHUD.dialog3 = "This will protect you against bullets, and in bio-organical cases reflect";
							tutHUD.dialog4 = "the incoming projectile. Try to beat the enemy.";
							
							
						}
					}
				}
			}
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(button, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x + 16, (int)y, 16, 32);
	}

}
