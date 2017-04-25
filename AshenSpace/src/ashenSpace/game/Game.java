package ashenSpace.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import ashenSpace.GameState.Achievements;
import ashenSpace.GameState.Background;
import ashenSpace.GameState.Charsel;
import ashenSpace.GameState.Checkpoint;
import ashenSpace.GameState.Credits;
import ashenSpace.GameState.Difficulty;
import ashenSpace.GameState.Foreground;
import ashenSpace.GameState.Loading;
import ashenSpace.GameState.MenuState;
import ashenSpace.GameState.Opening;
import ashenSpace.GameState.Sequence;
import ashenSpace.GameState.Settings;
import ashenSpace.GameState.numberCounter;
import ashenSpace.anim.Texture;
import ashenSpace.audio.MusicCheck;
import ashenSpace.audio.sound;
import ashenSpace.input.KeyInput;
import ashenSpace.ser.Serialization;
import ashenSpace.ser.SerializationTut;
import ashenSpace.ser.fileInfo;
import ashenSpace.tutorial.*;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -4230412986492122109L;
	
	private boolean running = false;
	private Thread thread;
	public static int width, height;
	HUD hud;
	Handler handler;
	Random r = new Random();
	Spawn spawner;
	Camera cam;	
	Foreground foreground;
	Checkpoint checkpoint;
	static Texture tex;
	Sequence sequence;
	Loading loading;
	Achievements achieve;
	tutHUD tutHUD;
	public static String stategame = "";
	
	public static final String root = "/Ashen Space";
	public static final String fileName = "/file.sav";
	public static final String fileTut = "/filetut.sav";
	
	
	MenuState menuState;
	Difficulty difficulty;
	Credits credits;
	Opening opening;
	Background background;
	Settings settings;
	numberCounter count;
	sound sound;
	Charsel charsel;
	private BufferedImage level1 = null;
	Serialization ser = new Serialization();
	SerializationTut sertut = new SerializationTut();
	public static List<fileInfo> fileInfo = new ArrayList<fileInfo>();
	
	public static MusicCheck music;
	public static MusicCheck theme;
	public static MusicCheck OpenMusic;



	
	public static int LEVEL = 0;
	
	public enum STATE{
		
		menu,
		game,
		difficulty,
		credits,
		opening,
		settings,
		checkpoint,
		sequence,
		achieve,
		charsel,
		loading, 
		tutorial
		
	}
	
	public static STATE gameState = STATE.opening;
	
	private void init(){
		
		
		width = getWidth();
		height = getHeight();
		
		handler = new Handler(cam, this);
		tex = new Texture();
		hud = new HUD();
		background = new Background(this, handler);
		r = new Random();
		spawner = new Spawn(handler);
		sequence = new Sequence(this, handler);
		achieve = new Achievements(this);
		sound = new sound();
		loading = new Loading(sound, handler);
		
		music = new MusicCheck("/sound/AshenGame.wav");
		theme = new MusicCheck("/sound/AshenThemes.wav");
		OpenMusic = new MusicCheck("/sound/AshenSpace Opening.wav");

		BufferedImageLoader loader = new BufferedImageLoader();
		
		cam = new Camera(0, 0, handler);
		menuState = new MenuState(this, handler);
		difficulty = new Difficulty(this, handler);
		credits = new Credits(this, handler);
		opening = new Opening(this, handler);
		settings = new Settings(this, handler);
		checkpoint = new Checkpoint(this, handler);
		foreground = new Foreground(this, handler);
		charsel = new Charsel(this);
		count = new numberCounter();
		gameState = STATE.opening;
		tutHUD = new tutHUD(handler);		
		OpenMusic.play();
		
		this.addMouseListener(difficulty);
		this.addMouseListener(menuState);
		this.addMouseListener(credits);
		this.addMouseListener(settings);
		this.addMouseListener(checkpoint);
		this.addMouseListener(achieve);
		this.addMouseListener(charsel);
		this.addKeyListener(new KeyInput(handler));
				
	}
	
	public synchronized void start(){
		
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
	public synchronized void stop(){
		if(!running) return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	public void tick(){
		if(gameState == STATE.opening){
			opening.tick();
			stategame = "Opening";
		}else if(gameState == STATE.game){
			handler.tick();
			background.tick();
			sound.tick();
			
			hud.tick();
			stategame = "Game";

			
			spawner.tick();
			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).getId() == ID.player){
					cam.tick(handler.object.get(i));
				}
			}
		}else if(gameState == STATE.sequence){
			sequence.tick();
			handler.tick();
			stategame = "Sequence";

		}else if(gameState == STATE.menu){
			menuState.tick();
			stategame = "Menu";
			
		}else if(gameState == STATE.loading){
			loading.tick();

		}else if(gameState == STATE.difficulty){
			difficulty.tick();
			stategame = "Diff";

		}else if(gameState == STATE.credits){
			credits.tick();
			stategame = "Credits";

		}else if(gameState == STATE.settings){
			settings.tick();
			stategame = "Setting";

		}else if(gameState == STATE.checkpoint){
			checkpoint.tick();
			stategame = "Checkpoint";

		}else if(gameState == STATE.achieve){
			achieve.tick();
			stategame = "Achieve";

		}else if(gameState == STATE.charsel){
			charsel.tick();
			stategame = "Charsel";

		}else if(gameState == STATE.tutorial){
			
			
			handler.tick();
			background.tick();
			sound.tick();
			
			tutHUD.tick();
			
			spawner.tick();
			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).getId() == ID.player){
					cam.tick(handler.object.get(i));
				}
			}
		}
	}
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
			
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		
		if(gameState == STATE.opening || gameState == STATE.menu || gameState == STATE.difficulty || gameState == STATE.credits || gameState == STATE.settings || gameState == STATE.checkpoint || gameState == STATE.sequence){
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			OpenMusic.getPos();
		}else{
			
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);

		}
		
		if(gameState == STATE.opening){
			opening.render(g);
		}else if(gameState == STATE.loading){
			loading.render(g);
		}
		else if(gameState == STATE.menu){
			menuState.render(g);
			
		}else if(gameState == STATE.difficulty){
			difficulty.render(g);
		
		}else if(gameState == STATE.game){
									
			g2d.translate(cam.getX(), cam.getY());//begin cam
			
			background.render(g);
			handler.render(g);
			
			g2d.translate(-cam.getX(), -cam.getY());
			
			foreground.render(g);

			hud.render(g);

			
		}else if(gameState == STATE.credits){
			credits.render(g);
		}else if(gameState == STATE.settings){
			settings.render(g);
		}else if(gameState == STATE.checkpoint){
			checkpoint.render(g);
		}else if(gameState == STATE.sequence){
			sequence.render(g);
			handler.render(g);
		}else if(gameState == STATE.achieve){
			achieve.render(g);
		}else if(gameState == STATE.charsel){
			charsel.render(g);
		}else if(gameState == STATE.tutorial){
			
			g2d.translate(cam.getX(), cam.getY());//begin cam
			
			background.render(g);
			handler.render(g);
			
			g2d.translate(-cam.getX(), -cam.getY());
			
			foreground.render(g);

			tutHUD.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static Texture getInstance(){
		return tex;
	}
	
	public static void main(String args[]){
		
		new Window(800, 600,"Ashen Space" , new Game());
	}
	
	public static String createDataFolder(){
		
		String home = System.getProperty("user.home");
		String OS = System.getProperty("os.name").toLowerCase();
		
		if(OS.contains("win")){
			home = System.getenv("appdata");
		}else if(OS.contains("mac")){
			home += "~/Library/Application Support";
		}else if(OS.contains("nix") || OS.contains("nux") || OS.contains("aix")){
			home += "~/.";
		}
		
		File dir = new File(home);
		dir = new File(dir, root);
		
		if(!dir.exists()){
			
			dir.mkdir();
			System.out.println("Creating folder...");
		}
		
		return dir.getAbsolutePath();
	}
	
	public static int clamp(int var, int min, int max){
		if(var >= max){
			return max;
		}else if(var <= min){
			return min;
		}else{
			return var;
		}
	}
}
