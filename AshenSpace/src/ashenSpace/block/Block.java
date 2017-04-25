package ashenSpace.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ashenSpace.anim.Texture;
import ashenSpace.game.Camera;
import ashenSpace.game.Game;
import ashenSpace.game.GameObject;
import ashenSpace.game.Handler;
import ashenSpace.game.ID;

public class Block extends GameObject{

	Handler handler;
	Texture tex = Game.getInstance();
	private int type = 3;
	Camera cam;
	
	public Block(float x, float y, int type, ID id, Handler handler) {
		super(x, y, id);
		this.type = type;
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		
		//g.setColor(Color.GREEN);
		//g.drawRect((int) x + 9, (int) y + 14, 13, 17);
		if(type == 0){
			g.drawImage(tex.block[0], (int)x, (int)y, null);
		}
		if(type == 1){
			g.drawImage(tex.block[1], (int)x, (int)y, null);
		}
		if(type == 2){
			g.drawImage(tex.block[2], (int)x, (int)y, null);
		}
		if(type == 3){
			g.drawImage(tex.block[3], (int)x, (int)y, null);
		}
		if(type == 4){
			g.drawImage(tex.block[4], (int)x, (int)y, null);
		}
		if(type == 5){
			g.drawImage(tex.block[5], (int)x, (int)y, null);
		}
		if(type == 6){
			g.drawImage(tex.block[6], (int)x, (int)y, null);
		}
		if(type == 7){
			g.drawImage(tex.block[7], (int)x, (int)y, null);
		}
		if(type == 8){
			g.drawImage(tex.block[8], (int)x, (int)y, null);
		}
		if(type == 9){
			g.drawImage(tex.block[9], (int)x, (int)y, null);
		}
		if(type == 10){
			g.drawImage(tex.block[10], (int)x, (int)y, null);
		}
		if(type == 11){
			g.drawImage(tex.block[11], (int)x, (int)y, null);
		}
		if(type == 12){
			g.drawImage(tex.block[12], (int)x, (int)y, null);
		}
		if(type == 13){
			g.drawImage(tex.block[13], (int)x, (int)y, null);
		}
		if(type == 14){
			g.drawImage(tex.block[14], (int)x, (int)y, null);
		}
		if(type == 15){
			g.drawImage(tex.block[15], (int)x, (int)y, null);
		}
		if(type == 16){
			g.drawImage(tex.block[16], (int)x, (int)y, null);
		}
		if(type == 17){
			g.drawImage(tex.block[17], (int)x, (int)y, null);
		}
		if(type == 18){
			g.drawImage(tex.block[18], (int)x, (int)y, null);
		}
		if(type == 19){
			g.drawImage(tex.block[19], (int)x, (int)y, null);
		}
		if(type == 20){
			g.drawImage(tex.block[20], (int)x, (int)y, null);
		}
		if(type == 21){
			g.drawImage(tex.block[21], (int)x, (int)y, null);
		}
		if(type == 22){
			g.drawImage(tex.block[22], (int)x, (int)y, null);
		}
		if(type == 23){
			g.drawImage(tex.block[23], (int)x, (int)y, null);
		}
		if(type == 24){
			g.drawImage(tex.block[24], (int)x, (int)y, null);
		}
		if(type == 25){
			g.drawImage(tex.block[25], (int)x, (int)y, null);
		}
		if(type == 26){
			g.drawImage(tex.block[26], (int)x, (int)y, null);
		}
		if(type == 27){
			g.drawImage(tex.block[27], (int)x, (int)y, null);
		}
		if(type == 28){
			g.drawImage(tex.block[28], (int)x, (int)y, null);
		}
		if(type == 29){
			g.drawImage(tex.block[29], (int)x, (int)y, null);
		}
		if(type == 30){
			g.drawImage(tex.block[30], (int)x, (int)y, null);
		}
		if(type == 31){
			g.drawImage(tex.block[31], (int)x, (int)y, null);
		}
		if(type == 32){
			g.drawImage(tex.block[32], (int)x, (int)y, null);
		}
		if(type == 33){
			g.drawImage(tex.block[33], (int)x, (int)y, null);
		}
		if(type == 34){
			g.drawImage(tex.block[34], (int)x, (int)y, null);
		}
		if(type == 35){
			g.drawImage(tex.block[35], (int)x, (int)y, null);
		}
		if(type == 36){
			g.drawImage(tex.block[36], (int)x, (int)y, null);
		}
		if(type == 37){
			g.drawImage(tex.block[37], (int)x, (int)y, null);
		}
		if(type == 38){
			g.drawImage(tex.block[38], (int)x, (int)y, null);
		}
		if(type == 39){
			g.drawImage(tex.block[39], (int)x, (int)y, null);
		}
		if(type == 40){
			g.drawImage(tex.block[40], (int)x, (int)y, null);
		}
		if(type == 41){
			g.drawImage(tex.block[41], (int)x, (int)y, null);
		}
		if(type == 42){
			g.drawImage(tex.block[42], (int)x, (int)y, null);
		}
		if(type == 43){
			g.drawImage(tex.block[43], (int)x, (int)y, null);
		}
		if(type == 44){
			g.drawImage(tex.block[44], (int)x, (int)y, null);
		}
		if(type == 45){
			g.drawImage(tex.block[45], (int)x, (int)y, null);
		}
		if(type == 46){
			g.drawImage(tex.block[46], (int)x, (int)y, null);
		}
		if(type == 47){
			g.drawImage(tex.block[47], (int)x, (int)y, null);
		}
		
		if(type == 48){
			g.drawImage(tex.block[48], (int)x, (int)y, null);
		}
		if(type == 49){
			g.drawImage(tex.block[49], (int)x, (int)y, null);
		}
		if(type == 50){
			g.drawImage(tex.block[50], (int)x, (int)y, null);
		}
		if(type == 51){
			g.drawImage(tex.block[51], (int)x, (int)y, null);
		}
		if(type == 52){
			g.drawImage(tex.block[52], (int)x, (int)y, null);
		}
		if(type == 53){
			g.drawImage(tex.block[53], (int)x, (int)y, null);
		}
		if(type == 54){
			g.drawImage(tex.block[54], (int)x, (int)y, null);
		}
		if(type == 55){
			g.drawImage(tex.block[55], (int)x, (int)y, null);
		}
		if(type == 56){
			g.drawImage(tex.block[56], (int)x, (int)y, null);
		}
		if(type == 57){
			g.drawImage(tex.block[57], (int)x, (int)y, null);
		}
		if(type == 58){
			g.drawImage(tex.block[58], (int)x, (int)y, null);
		}
		if(type == 59){
			g.drawImage(tex.block[59], (int)x, (int)y, null);
		}
		if(type == 60){
			g.drawImage(tex.block[60], (int)x, (int)y, null);
		}
		if(type == 61){
			g.drawImage(tex.block[61], (int)x, (int)y, null);
		}
		if(type == 62){
			g.drawImage(tex.block[62], (int)x, (int)y, null);
		}
		if(type == 63){
			g.drawImage(tex.block[63], (int)x, (int)y, null);
		}
		if(type == 64){
			g.drawImage(tex.block[64], (int)x, (int)y, null);
		}
		if(type == 65){
			g.drawImage(tex.block[65], (int)x, (int)y, null);
		}
		if(type == 66){
			g.drawImage(tex.block[66], (int)x, (int)y, null);
		}
		if(type == 67){
			g.drawImage(tex.block[67], (int)x, (int)y, null);
		}
		if(type == 68){
			g.drawImage(tex.block[68], (int)x, (int)y, null);
		}
		if(type == 69){
			g.drawImage(tex.block[69], (int)x, (int)y, null);
		}
		if(type == 70){
			g.drawImage(tex.block[70], (int)x, (int)y, null);
		}
		if(type == 71){
			g.drawImage(tex.block[71], (int)x, (int)y, null);
		}
		if(type == 72){
			g.drawImage(tex.block[72], (int)x, (int)y, null);
		}
		if(type == 73){
			g.drawImage(tex.block[73], (int)x, (int)y, null);
		}
		if(type == 74){
			g.drawImage(tex.block[74], (int)x, (int)y, null);
		}
		if(type == 75){
			g.drawImage(tex.block[75], (int)x, (int)y, null);
		}
	}
	public Rectangle getBounds() {
		
		if(type == 0 || type == 13 || type == 25){
			return new Rectangle((int) x + 15, (int) y + 16, 16, 16);
		}else if(type == 1 || type == 14 || type == 26){
			return new Rectangle((int) x + 16, (int) y, 16, 32);
		}else if(type == 2 || type == 15 || type == 27){
			return new Rectangle((int) x + 16, (int) y, 16, 16);
		}else if(type == 3 || type == 16 || type == 28){
			return new Rectangle((int) x, (int) y + 16, 32, 16);
		}else if(type == 4 || type == 17 || type == 29){
			return new Rectangle((int) x, (int) y, 32, 32);
		}else if(type == 5 || type == 18 || type == 30){
			return new Rectangle((int) x, (int) y, 32, 16);
		}else if(type == 6 || type == 19 || type == 31){
			return new Rectangle((int) x, (int) y + 16, 16, 16);
		}else if(type == 7 || type == 20 || type == 32){
			return new Rectangle((int) x, (int) y, 16, 32);
		}else if(type == 8 || type == 21 || type == 33){
			return new Rectangle((int) x, (int) y, 16, 16);
		}else{
			return new Rectangle((int)x + 10000, (int)y + 10000, 1, 1);
		}
	}
	
}
