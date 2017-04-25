package ashenSpace.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ashenSpace.anim.Animation;
import ashenSpace.anim.BufferedImageLoader;
import ashenSpace.anim.Texture;
import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.Handler;
import ashenSpace.input.KeyInput;
import ashenSpace.ser.Serialization;
import ashenSpace.ser.fileInfo;
import ashenSpace.tutorial.tutHUD;

public class Charsel extends MouseAdapter{

	Game game;
	public static int character;
	public static int choice;
	public String name = "";
	
	Texture tex = game.getInstance();
	private BufferedImage button;
	
	private Animation PlayerRight, DantonRight, hazRight, droidRight, sysRight;
	
	public Charsel(Game game){
		this.game = game;
		
		PlayerRight = new Animation(10, tex.player[0], tex.player[1], tex.player[2], tex.player[3]);
		DantonRight = new Animation(10, tex.danton[1], tex.danton[3], tex.danton[1], tex.danton[5]);
		hazRight = new Animation(10, tex.hazSuit[3], tex.hazSuit[4], tex.hazSuit[3], tex.hazSuit[5]);
		droidRight = new Animation(10, tex.droid[3], tex.droid[4], tex.droid[3], tex.droid[5]);
		sysRight = new Animation(5, tex.renderMan[6], tex.renderMan[9], tex.renderMan[7], tex.renderMan[10], tex.renderMan[6], tex.renderMan[9], tex.renderMan[8], tex.renderMan[11]);

		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			button = loader.loadImage("/tex/Buttons.png");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.charsel){
			if(mouseOver(mx, my, 24, 24, 152, 77)){
				game.gameState = STATE.menu;
			}else if(mouseOver(mx, my, 315, 400, 150, 50)){
				if(choice == 0){
					character = choice;
					fileInfo file = new fileInfo(Handler.area1, Handler.area2, Handler.area3, Handler.area4, Handler.area5, 
							Handler.area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
							Achievements.danton, Achievements.dead, character);	
					Serialization.save(file);
				}else if(choice == 1 && Achievements.finalBoss){
					character = choice;
					fileInfo file = new fileInfo(Handler.area1, Handler.area2, Handler.area3, Handler.area4, Handler.area5, 
							Handler.area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
							Achievements.danton, Achievements.dead, character);	
					Serialization.save(file);
				}else if(choice == 2 && Achievements.danton){
					character = choice;
					fileInfo file = new fileInfo(Handler.area1, Handler.area2, Handler.area3, Handler.area4, Handler.area5, 
							Handler.area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
							Achievements.danton, Achievements.dead, character);	
					Serialization.save(file);
				}else if(choice == 3 && Achievements.dead){
					character = choice;
					fileInfo file = new fileInfo(Handler.area1, Handler.area2, Handler.area3, Handler.area4, Handler.area5, 
							Handler.area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
							Achievements.danton, Achievements.dead, character);	
					Serialization.save(file);
				}else if(choice == 4 && tutHUD.tutComplete){
					character = choice;
					fileInfo file = new fileInfo(Handler.area1, Handler.area2, Handler.area3, Handler.area4, Handler.area5, 
							Handler.area6, KeyInput.keySetting, Settings.sequence, Achievements.kills, Achievements.finalBoss,
							Achievements.danton, Achievements.dead, character);	
					Serialization.save(file);
				}
			}else if(mouseOver(mx, my, 210, 300, 64, 64)){
				choice--;
				if(choice == -1){
					choice = 4;
				}
			}else if(mouseOver(mx, my, 510, 300, 64, 64)){
				choice++;
				if(choice == 5){
					choice = 0;
				}
			}
		}
	}
	
	public void tick(){
		
		if(choice == 0){
			PlayerRight.runAnimation();
			name = "The Spaceman";
		}else if(choice == 2){
			DantonRight.runAnimation();
			name = "Danton";
		}else if(choice == 1){
			hazRight.runAnimation();
			name = "Hazard Gear";
		}else if(choice == 3){
			droidRight.runAnimation();
			name = "Starman";
		}else if(choice == 4){
			sysRight.runAnimation();
			name = "Sys-struct";
		}
		
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 70));
		g.drawString("Characters", 200, 100);
		
		g.setColor(Color.red);
		g.drawRect(24, 24, 152, 77);
		g.drawRect(314, 399, 152, 52);
		
		g.setColor(Color.green);
		g.drawImage(button, 25, 25, 150, 75, null);
		g.drawImage(button, 315, 400, 150, 50, null);

		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 65));
		g.drawString("Back", 30, 85);
		g.setFont(new Font("Arial", 0, 45));

		g.drawString("Select", 330, 440);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 30));
		
		g.drawImage(tex.misc[19], 210, 300, 64, 64, null);
		g.drawImage(tex.misc[20], 510, 300, 64, 64, null);


		if(choice == 0){
			PlayerRight.drawAnimation(g, game.width / 2 - 45, game.height / 2, 64, 64);
			g.drawString(name, 290, 290);

		}else if(choice == 2){
			if(Achievements.danton){
				DantonRight.drawAnimation(g, game.width / 2 - 45, game.height / 2, 64, 64);
				g.drawString(name, 340, 290);
			}else{
				g.drawImage(tex.misc[21], game.width / 2 - 45, game.height / 2, 64, 64, null);
				g.drawString("?????????", 310, 290);
			}
		}else if(choice == 1){
			if(Achievements.finalBoss){
				hazRight.drawAnimation(g, game.width / 2 - 45, game.height / 2, 64, 64);
				g.drawString(name, 300, 290);
			}else{
				g.drawImage(tex.misc[21], game.width / 2 - 45, game.height / 2, 64, 64, null);
				g.drawString("?????????", 310, 290);
			}
		}else if(choice == 3){
			if(Achievements.dead){
				droidRight.drawAnimation(g, game.width / 2 - 45, game.height / 2, 64, 64);
				g.drawString(name, 330, 290);
			}else{
				g.drawImage(tex.misc[21], game.width / 2 - 45, game.height / 2, 64, 64, null);
				g.drawString("?????????", 310, 290);
			}
		}else if(choice == 4){
			if(tutHUD.tutComplete){
				sysRight.drawAnimation(g, game.width / 2 - 45, game.height / 2, 64, 64);
				g.drawString(name, 320, 290);
			}else{
				g.drawImage(tex.misc[21], game.width / 2 - 45, game.height / 2, 64, 64, null);
				g.drawString("?????????", 310, 290);
			}
		}
		

		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
}
