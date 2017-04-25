package ashenSpace.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ashenSpace.audio.sound;
import ashenSpace.creature.Arbol;
import ashenSpace.creature.Boss;
import ashenSpace.creature.Danton;
import ashenSpace.creature.FollowBoss;
import ashenSpace.creature.Player;
import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;
import ashenSpace.game.GameObject;
import ashenSpace.game.HUD;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;
import ashenSpace.object.Bullet;
import ashenSpace.tutorial.tutHUD;

public class KeyInput extends KeyAdapter{

	Handler handler;
	public static int keySetting = 0;
	public static int shieldTimer = 0;
	public int reload = 5;
	
	public static boolean left, right;
		
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.player){
				if(keySetting == 1){
					
					
					if(key == KeyEvent.VK_A){
						left = true;
					}
					
					if(key == KeyEvent.VK_D){
						right = true;
					}
					if(key == KeyEvent.VK_SPACE  && !tempObject.isJumping()){
						tempObject.setVelY(-10);
						tempObject.setFalling(true);
						tempObject.setJumping(true);
					}
					
					if(key == KeyEvent.VK_H){
						if(Game.gameState == STATE.game){
							if(HUD.medkit > 0){
								HUD.medkit--;
								HUD.health += 50;
							}
						}else if(Game.gameState == STATE.tutorial){
							if(tutHUD.medkit > 0){
								tutHUD.medkit--;
								tutHUD.health += 50;
							}
						}
					}
					if(key == KeyEvent.VK_ENTER){
						if(Game.gameState == STATE.game){
						
							if(HUD.bullet > 0){
								if(Player.facing == 1){
									handler.addObject(new Bullet(tempObject.getX() + 33, tempObject.getY() + 13, ID.bullet, handler));
								}else if(Player.facing == -1){
									handler.addObject(new Bullet(tempObject.getX() - 2, tempObject.getY() + 13, ID.bullet, handler));
								}	
								HUD.bullet--;
								
							}
						}else if(Game.gameState == STATE.tutorial){
							if(tutHUD.bullet > 0){
								if(Player.facing == 1){
									handler.addObject(new Bullet(tempObject.getX() + 33, tempObject.getY() + 13, ID.bullet, handler));
								}else if(Player.facing == -1){
									handler.addObject(new Bullet(tempObject.getX() - 2, tempObject.getY() + 13, ID.bullet, handler));
								}	
								tutHUD.bullet--;
								
							}
						}
					}
					
					if(key == KeyEvent.VK_R){
						HUD.bullet = 10;
						tutHUD.bullet = 10;
					}
					
					if(key == KeyEvent.VK_S){
						if(shieldTimer == 62){
							Player.shieldActive = true;
							Player.shieldDeathTimer = 150;
							shieldTimer = 0;
							
						}

					}
					
				}else if(keySetting == 2){
					
					if(key == KeyEvent.VK_A){
						left = true;
					}
					
					if(key == KeyEvent.VK_D){
						right = true;
					}
					
					if(key == KeyEvent.VK_W && !tempObject.isJumping()){
						tempObject.setVelY(-10);
						tempObject.setJumping(true);
					}
					
					if(key == KeyEvent.VK_H){
						if(Game.gameState == STATE.game){
							if(HUD.medkit > 0){
								HUD.medkit--;
								HUD.health += 50;
							}
						}else if(Game.gameState == STATE.tutorial){
							if(tutHUD.medkit > 0){
								tutHUD.medkit--;
								tutHUD.health += 50;
							}
						}
					}
					if(key == KeyEvent.VK_SPACE){
						if(Game.gameState == STATE.game){
							if(HUD.bullet > 0){
								if(Player.facing == 1){
									handler.addObject(new Bullet(tempObject.getX() + 35, tempObject.getY() + 13, ID.bullet, handler));
								}else if(Player.facing == -1){
									handler.addObject(new Bullet(tempObject.getX() - 2, tempObject.getY() + 13, ID.bullet, handler));
								}
								
								HUD.bullet--;
								sound.play("/sound/Bullet.wav");
	
							}
						}else if(Game.gameState == STATE.tutorial){
							if(tutHUD.bullet > 0){
								if(Player.facing == 1){
									handler.addObject(new Bullet(tempObject.getX() + 35, tempObject.getY() + 13, ID.bullet, handler));
								}else if(Player.facing == -1){
									handler.addObject(new Bullet(tempObject.getX() - 2, tempObject.getY() + 13, ID.bullet, handler));
								}
								
								tutHUD.bullet--;
								sound.play("/sound/Bullet.wav");
	
							}
						}
					}
					
					if(key == KeyEvent.VK_R){
						HUD.bullet = 10;
						tutHUD.bullet = 10;
					}
					
					if(key == KeyEvent.VK_S){
						if(shieldTimer == 62){
							Player.shieldActive = true;
							Player.shieldDeathTimer = 150;
							shieldTimer = 0;
							
						}

					}
					
				} if(key == KeyEvent.VK_ESCAPE){
					Game.gameState = STATE.menu;
					Game.music.stop();
					Game.OpenMusic.play();
					Arbol.ArbolActive = false;
					Boss.active = false;
					FollowBoss.active = false;
					Danton.active = false;
				
				}
			}
		}
	}
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.player){
				//key event
				
				if(key == KeyEvent.VK_A){
					left = false;
				}
				
				if(key == KeyEvent.VK_D){
					right = false;
				}
				
				if(key == KeyEvent.VK_S){
					if(shieldTimer == 62){
						Player.shieldActive = true;
						Player.shieldDeathTimer = 100;
					}

				}
				
			}
		}
		
	}
}
