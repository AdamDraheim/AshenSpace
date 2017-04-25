package ashenSpace.anim;

import java.awt.image.BufferedImage;

import ashenSpace.game.BufferedImageLoader;

public class Texture {

	public BufferedImage[] block = new BufferedImage[78];
	public BufferedImage[] basicEnemy = new BufferedImage[30];
	public BufferedImage[] player = new BufferedImage[8];
	public BufferedImage[] misc = new BufferedImage[22];
	public BufferedImage[] lava = new BufferedImage[20];
	public BufferedImage[] door = new BufferedImage[8];
	public BufferedImage[] danton = new BufferedImage[18];
	public BufferedImage[] dragon = new BufferedImage[14];
	public BufferedImage[] finalBoss = new BufferedImage[14];
	public BufferedImage[] flash = new BufferedImage[5];
	public BufferedImage[] hazSuit = new BufferedImage[6];
	public BufferedImage[] droid = new BufferedImage[6];
	public BufferedImage[] easteregg = new BufferedImage[3];
	public BufferedImage[] renderMan = new BufferedImage[12];
	Spritesheet bs, cs, ps, ms, ds, l, dr, fb, fl, hz, dm, ee, rm;
	private BufferedImage block_sheet = null;
	private BufferedImage creature_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage misc_sheet = null;
	private BufferedImage danton_sheet = null;
	private BufferedImage logo = null;
	private BufferedImage dragon_sheet = null;
	private BufferedImage final_sheet = null;
	private BufferedImage flash_sheet = null;
	private BufferedImage hazard_sheet = null;
	private BufferedImage droid_man = null;
	private BufferedImage easter_egg = null;
	private BufferedImage render_man = null;
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			block_sheet = loader.loadImage("/tex/PlanetScape.png");
			creature_sheet = loader.loadImage("/tex/Enemies.png");
			player_sheet = loader.loadImage("/tex/Astronaut.png");
			misc_sheet = loader.loadImage("/tex/Misc.png");
			danton_sheet = loader.loadImage("/tex/Danton.png");
			logo = loader.loadImage("/tex/Low Orbit Games.png");
			dragon_sheet = loader.loadImage("/tex/Dragon.png");
			final_sheet = loader.loadImage("/tex/Walker.png");
			flash_sheet = loader.loadImage("/tex/Flash.png");
			hazard_sheet = loader.loadImage("/tex/HazSuit.png");
			droid_man = loader.loadImage("/tex/Droidman.png");
			easter_egg = loader.loadImage("/tex/EasterEggs.png");
			render_man = loader.loadImage("/tex/renderMan.png");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		bs = new Spritesheet(block_sheet);
		cs = new Spritesheet(creature_sheet);
		ps = new Spritesheet(player_sheet);
		ms = new Spritesheet(misc_sheet);
		ds = new Spritesheet(danton_sheet);
		l = new Spritesheet(logo);
		dr = new Spritesheet(dragon_sheet);
		fb = new Spritesheet(final_sheet);
		fl = new Spritesheet(flash_sheet);
		hz = new Spritesheet(hazard_sheet);
		dm = new Spritesheet(droid_man);
		ee = new Spritesheet(easter_egg);
		rm = new Spritesheet(render_man);
		
		getTextures();
	}
	
	private void getTextures(){
		
		//Plain World
		block[0] = bs.grabImage(1, 1, 32, 32);
		block[1] = bs.grabImage(1, 2, 32, 32);
		block[2] = bs.grabImage(1, 3, 32, 32);
		block[3] = bs.grabImage(2, 1, 32, 32);
		block[4] = bs.grabImage(2, 2, 32, 32);
		block[5] = bs.grabImage(2, 3, 32, 32);
		block[6] = bs.grabImage(3, 1, 32, 32);
		block[7] = bs.grabImage(3, 2, 32, 32);
		block[8] = bs.grabImage(3, 3, 32, 32);
		block[9] = bs.grabImage(4, 1, 32, 32);
		block[10] = bs.grabImage(4, 2, 32, 32);
		block[11] = bs.grabImage(5, 1, 32, 32);
		block[12] = bs.grabImage(5, 2, 32, 32);
		
		//Plain base
		
		block[13] = bs.grabImage(6, 1, 32, 32);
		block[14] = bs.grabImage(6, 2, 32, 32);
		block[15] = bs.grabImage(6, 3, 32, 32);
		block[16] = bs.grabImage(7, 1, 32, 32);
		block[17] = bs.grabImage(7, 2, 32, 32);
		block[18] = bs.grabImage(7, 3, 32, 32);
		block[19] = bs.grabImage(8, 1, 32, 32);
		block[20] = bs.grabImage(8, 2, 32, 32);
		block[21] = bs.grabImage(8, 3, 32, 32);
		
		
		//Windows
		
		block[22] = bs.grabImage(9, 2, 32, 32);
		block[23] = bs.grabImage(10, 2, 32, 32);
		block[24] = bs.grabImage(11, 2, 32, 32);
		
		//Terrain-base
		
		block[25] = bs.grabImage(12, 1, 32, 32);
		block[26] = bs.grabImage(12, 2, 32, 32);
		block[27] = bs.grabImage(12, 3, 32, 32);
		block[28] = bs.grabImage(13, 1, 32, 32);
		block[29] = bs.grabImage(13, 2, 32, 32);
		block[30] = bs.grabImage(13, 3, 32, 32);
		block[31] = bs.grabImage(14, 1, 32, 32);
		block[32] = bs.grabImage(14, 2, 32, 32);
		block[33] = bs.grabImage(14, 3, 32, 32);
		
		//Base-Terrain
		
		block[34] = bs.grabImage(15, 1, 32, 32);
		block[35] = bs.grabImage(15, 2, 32, 32);
		block[36] = bs.grabImage(15, 3, 32, 32);
		block[37] = bs.grabImage(16, 1, 32, 32);
		block[38] = bs.grabImage(16, 3, 32, 32);
		block[39] = bs.grabImage(17, 1, 32, 32);
		block[40] = bs.grabImage(17, 2, 32, 32);
		block[41] = bs.grabImage(17, 3, 32, 32);
		block[42] = bs.grabImage(4, 3, 32, 32);
		block[43] = bs.grabImage(5, 3, 32, 32);
		
		block[44] = bs.grabImage(18, 1, 32, 32);
		block[45] = bs.grabImage(18, 2, 32, 32);
		block[46] = bs.grabImage(19, 1, 32, 32);
		block[47] = bs.grabImage(19, 2, 32, 32);
		
		//Fade
		
		block[48] = bs.grabImage(34, 1, 32, 32);
		block[49] = bs.grabImage(34, 2, 32, 32);
		block[50] = bs.grabImage(34, 3, 32, 32);
		
		block[51] = bs.grabImage(35, 1, 32, 32);
		block[52] = bs.grabImage(35, 3, 32, 32);
		
		block[53] = bs.grabImage(36, 1, 32, 32);
		block[54] = bs.grabImage(36, 2, 32, 32);
		block[55] = bs.grabImage(36, 3, 32, 32);
		
		block[56] = bs.grabImage(37, 1, 32, 32);
		block[57] = bs.grabImage(37, 2, 32, 32);
		block[58] = bs.grabImage(37, 3, 32, 32);
		
		block[59] = bs.grabImage(38, 1, 32, 32);
		block[60] = bs.grabImage(38, 3, 32, 32);
		
		block[61] = bs.grabImage(39, 1, 32, 32);
		block[62] = bs.grabImage(39, 2, 32, 32);
		block[63] = bs.grabImage(39, 3, 32, 32);
		
		block[64] = bs.grabImage(40, 1, 32, 32);
		block[65] = bs.grabImage(40, 2, 32, 32);
		block[66] = bs.grabImage(41, 1, 32, 32);
		block[67] = bs.grabImage(41, 2, 32, 32);
		block[68] = bs.grabImage(42, 1, 32, 32);
		
		block[69] = bs.grabImage(43, 1, 32, 32);
		block[70] = bs.grabImage(43, 2, 32, 32);
		block[71] = bs.grabImage(44, 2, 32, 32);
		block[72] = bs.grabImage(45, 2, 32, 32);
		block[73] = bs.grabImage(46, 2, 32, 32);

		block[74] = bs.grabImage(43, 3, 32, 32);
		block[75] = bs.grabImage(46, 3, 32, 32);

		
		lava[0] = bs.grabImage(20, 1, 32, 32);
		lava[1] = bs.grabImage(20, 2, 32, 32);
		lava[2] = bs.grabImage(21, 1, 32, 32);
		lava[3] = bs.grabImage(21, 2, 32, 32);
		lava[4] = bs.grabImage(22, 1, 32, 32);
		lava[5] = bs.grabImage(22, 2, 32, 32);
		lava[6] = bs.grabImage(23, 1, 32, 32);
		lava[7] = bs.grabImage(24, 1, 32, 32);
		lava[8] = bs.grabImage(23, 2, 32, 32);
		lava[9] = bs.grabImage(23, 3, 32, 32);
		lava[10] = bs.grabImage(24, 2, 32, 32);
		lava[11] = bs.grabImage(24, 3, 32, 32);
		lava[12] = bs.grabImage(25, 1, 32, 32);
		lava[13] = bs.grabImage(25, 2, 32, 32);
		lava[14] = bs.grabImage(25, 3, 32, 32);
		lava[15] = bs.grabImage(26, 1, 32, 32);
		lava[16] = bs.grabImage(26, 3, 32, 32);
		lava[17] = bs.grabImage(27, 1, 32, 32);
		lava[18] = bs.grabImage(27, 2, 32, 32);
		lava[19] = bs.grabImage(27, 3, 32, 32);
		
		basicEnemy[0] = cs.grabImage(1, 1, 32, 32);
		basicEnemy[1] = cs.grabImage(3, 1, 32, 32);
		basicEnemy[2] = cs.grabImage(4, 1, 32, 32);
		basicEnemy[3] = cs.grabImage(5, 1, 32, 32);
		basicEnemy[4] = cs.grabImage(6, 1, 32, 32);
		basicEnemy[5] = cs.grabImage(7, 1, 32, 32);
		//Arbol
		basicEnemy[6] = cs.grabImage(1, 2, 32, 32);
		basicEnemy[7] = cs.grabImage(2, 2, 32, 32);
		basicEnemy[8] = cs.grabImage(3, 2, 32, 32);
		basicEnemy[9] = cs.grabImage(1, 3, 32, 32);
		basicEnemy[10] = cs.grabImage(2, 3, 32, 32);
		basicEnemy[11] = cs.grabImage(3, 3, 32, 32);
		
		//boss 2
		basicEnemy[12] = cs.grabImage(4, 2, 32, 32);
		basicEnemy[13] = cs.grabImage(5, 2, 32, 32);
		
		//rocket enemy
		
		basicEnemy[14] = cs.grabImage(6, 2, 32, 32);
		basicEnemy[15] = cs.grabImage(7, 2, 32, 32);
		
		//sniper
		
		basicEnemy[16] = cs.grabImage(8, 1, 32, 32);
		
		//Follow boss
		basicEnemy[17] = cs.grabImage(4, 3, 32, 32);
		basicEnemy[18] = cs.grabImage(5, 3, 32, 32);
		
		basicEnemy[19] = cs.grabImage(1, 4, 32, 32);
		basicEnemy[20] = cs.grabImage(2, 4, 32, 32);
		basicEnemy[21] = cs.grabImage(3, 4, 32, 32);
		basicEnemy[22] = cs.grabImage(4, 4, 32, 32);
		
		basicEnemy[23] = cs.grabImage(6, 3, 32, 32);
		
		basicEnemy[24] = cs.grabImage(9, 1, 32, 32);
		basicEnemy[25] = cs.grabImage(9, 2, 32, 32);
		
		basicEnemy[26] = cs.grabImage(8, 2, 32, 32);

		basicEnemy[27] = cs.grabImage(8, 3, 32, 32);
		basicEnemy[28] = cs.grabImage(9, 3, 32, 32);



		
		player[0] = ps.grabImage(2, 1, 32, 32);
		player[1] = ps.grabImage(2, 2, 32, 32);
		player[2] = ps.grabImage(2, 1, 32, 32);
		player[3] = ps.grabImage(2, 3, 32, 32);
		
		player[4] = ps.grabImage(1, 1, 32, 32);
		player[5] = ps.grabImage(1, 2, 32, 32);
		player[6] = ps.grabImage(1, 1, 32, 32);
		player[7] = ps.grabImage(1, 3, 32, 32);
		
		misc[0] = ms.grabImage(1, 1, 32, 32);
		misc[1] = ms.grabImage(2, 1, 32, 32);
		misc[2] = ms.grabImage(1, 2, 32, 32);
		misc[3] = ms.grabImage(2, 2, 32, 32);
		misc[4] = ms.grabImage(1, 3, 32, 32);
		misc[5] = ms.grabImage(2, 3, 32, 32);
		
		//green thingy
		
		misc[6] = ms.grabImage(3, 1, 32, 32);
		misc[7] = ms.grabImage(3, 2, 32, 32);
		misc[8] = ms.grabImage(4, 1, 32, 32);
		misc[9] = ms.grabImage(4, 2, 32, 32);
		
		//logo
		
		misc[10] = l.grabImage(1, 1, 32, 32);
		
		//Ship
		
		misc[11] = ms.grabImage(1, 4, 64, 32);
		
		misc[12] = ms.grabImage(5, 2, 32, 32);
		misc[13] = ms.grabImage(6, 2, 32, 32);
		misc[14] = ms.grabImage(7, 2, 32, 32);
		
		misc[15] = ms.grabImage(5, 3, 32, 32);
		misc[16] = ms.grabImage(6, 3, 32, 32);
		misc[17] = ms.grabImage(7, 3, 32, 32);

		misc[18] = ms.grabImage(5, 1, 32, 32);
		
		misc[19] = ms.grabImage(5, 4, 32, 32);
		misc[20] = ms.grabImage(6, 4, 32, 32);
		misc[21] = ms.grabImage(7, 4, 32, 32);
		
		door[0] = bs.grabImage(28, 1, 32, 32);
		door[1] = bs.grabImage(28, 2, 32, 32);
		door[2] = bs.grabImage(28, 3, 32, 32);
		door[3] = bs.grabImage(29, 3, 32, 32);
		door[4] = bs.grabImage(30, 3, 32, 32);
		door[5] = bs.grabImage(31, 3, 32, 32);
		door[6] = bs.grabImage(32, 3, 32, 32);
		door[7] = bs.grabImage(33, 3, 32, 32);
		
		//main
		danton[0] = ds.grabImage(1, 1, 32, 32);
		danton[1] = ds.grabImage(2, 1, 32, 32);
		danton[2] = ds.grabImage(1, 2, 32, 32);
		danton[3] = ds.grabImage(2, 2, 32, 32);
		danton[4] = ds.grabImage(1, 3, 32, 32);
		danton[5] = ds.grabImage(2, 3, 32, 32);
		danton[6] = ds.grabImage(1, 4, 32, 32);
		danton[7] = ds.grabImage(2, 4, 32, 32);
		
		//robo
		
		danton[8] = ds.grabImage(3, 1, 32, 32);
		danton[9] = ds.grabImage(4, 1, 32, 32);
		danton[10] = ds.grabImage(3, 2, 32, 32);
		danton[11] = ds.grabImage(4, 2, 32, 32);
		danton[12] = ds.grabImage(3, 3, 32, 32);
		danton[13] = ds.grabImage(4, 3, 32, 32);
		danton[14] = ds.grabImage(3, 4, 32, 32);
		danton[15] = ds.grabImage(4, 4, 32, 32);
		
		//def
		danton[16] = ds.grabImage(5, 1, 32, 32);
		danton[17] = ds.grabImage(5, 2, 32, 32);

		
		//final boss
		
		finalBoss[0] = fb.grabImage(1, 1, 32, 32);
		finalBoss[1] = fb.grabImage(1, 2, 32, 32);
		finalBoss[2] = fb.grabImage(2, 1, 32, 32);
		finalBoss[3] = fb.grabImage(4, 2, 32, 32);
		finalBoss[4] = fb.grabImage(4, 1, 32, 32);
		finalBoss[5] = fb.grabImage(3, 1, 32, 32);

		//arms
		finalBoss[6] = fb.grabImage(5, 1, 32, 32);
		finalBoss[7] = fb.grabImage(6, 1, 32, 32);
		finalBoss[8] = fb.grabImage(5, 2, 32, 32);
		finalBoss[9] = fb.grabImage(6, 2, 32, 32);

		finalBoss[10] = fb.grabImage(5, 3, 32, 32);
		finalBoss[11] = fb.grabImage(6, 3, 32, 32);
		
		finalBoss[12] = fb.grabImage(2, 2, 32, 32);
		finalBoss[13] = fb.grabImage(3, 2, 32, 32);

		
		flash[0] = fl.grabImage(1, 1, 32, 32);
		flash[1] = fl.grabImage(1, 2, 32, 32);
		flash[2] = fl.grabImage(1, 3, 32, 32);
		flash[3] = fl.grabImage(2, 1, 32, 32);
		flash[4] = fl.grabImage(2, 2, 32, 32);
		
		hazSuit[0] = hz.grabImage(1, 1, 32, 32);
		hazSuit[1] = hz.grabImage(1, 2, 32, 32);
		hazSuit[2] = hz.grabImage(1, 3, 32, 32);
		hazSuit[3] = hz.grabImage(2, 1, 32, 32);
		hazSuit[4] = hz.grabImage(2, 2, 32, 32);
		hazSuit[5] = hz.grabImage(2, 3, 32, 32);
		
		droid[0] = dm.grabImage(1, 1, 32, 32);
		droid[1] = dm.grabImage(1, 2, 32, 32);
		droid[2] = dm.grabImage(1, 3, 32, 32);
		droid[3] = dm.grabImage(2, 1, 32, 32);
		droid[4] = dm.grabImage(2, 2, 32, 32);
		droid[5] = dm.grabImage(2, 3, 32, 32);
		
		renderMan[0] = rm.grabImage(1, 1, 32, 32);
		renderMan[1] = rm.grabImage(1, 2, 32, 32);
		renderMan[2] = rm.grabImage(1, 3, 32, 32);
		renderMan[3] = rm.grabImage(2, 1, 32, 32);
		renderMan[4] = rm.grabImage(2, 2, 32, 32);
		renderMan[5] = rm.grabImage(2, 3, 32, 32);
		renderMan[6] = rm.grabImage(3, 1, 32, 32);
		renderMan[7] = rm.grabImage(3, 2, 32, 32);
		renderMan[8] = rm.grabImage(3, 3, 32, 32);
		renderMan[9] = rm.grabImage(4, 1, 32, 32);
		renderMan[10] = rm.grabImage(4, 2, 32, 32);
		renderMan[11] = rm.grabImage(4, 3, 32, 32);

		easteregg[0] = ee.grabImage(1, 1, 32, 32);
		easteregg[1] = ee.grabImage(2, 1, 32, 32);
		easteregg[2] = ee.grabImage(3, 1, 32, 32);

		
	}
	
}
