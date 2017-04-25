package ashenSpace.ser;

import java.io.Serializable;

public class fileInfo implements Serializable{

	public boolean area1, area2, area3, area4, area5, area6, sequence, finalboss, danton, dead;
	public int keySetting, kills, character;
	
	public fileInfo(boolean area1, boolean area2, boolean area3, boolean area4, boolean area5, boolean area6, int keySetting, boolean sequence, int kills, 
			boolean finalboss, boolean danton, boolean dead, int character){
		
		this.area1 =area1;
		this.area2 = area2;
		this.area3 = area3;
		this.area4 = area4;
		this.area5 = area5;
		this.area6 = area6;
		this.keySetting = keySetting;
		this.sequence = sequence;
		this.kills = kills;
		this.finalboss = finalboss;
		this.danton = danton;
		this.dead = dead;
		this.character = character;
	}

	public boolean isArea1() {
		return area1;
	}

	public void setArea1(boolean area1) {
		this.area1 = area1;
	}

	public boolean isArea2() {
		return area2;
	}

	public void setArea2(boolean area2) {
		this.area2 = area2;
	}

	public boolean isArea3() {
		return area3;
	}

	public void setArea3(boolean area3) {
		this.area3 = area3;
	}

	public boolean isArea4() {
		return area4;
	}

	public void setArea4(boolean area4) {
		this.area4 = area4;
	}

	public boolean isArea5() {
		return area5;
	}

	public void setArea5(boolean area5) {
		this.area5 = area5;
	}

	public boolean isArea6() {
		return area6;
	}

	public void setArea6(boolean area6) {
		this.area6 = area6;
	}

	public int getKeySetting() {
		return keySetting;
	}

	public void setKeySetting(int keySetting) {
		this.keySetting = keySetting;
	}

	public boolean isSequence() {
		return sequence;
	}

	public void setSequence(boolean sequence) {
		this.sequence = sequence;
	}

	public boolean isFinalboss() {
		return finalboss;
	}

	public void setFinalboss(boolean finalboss) {
		this.finalboss = finalboss;
	}

	public boolean isDanton() {
		return danton;
	}

	public void setDanton(boolean danton) {
		this.danton = danton;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}
	

}
