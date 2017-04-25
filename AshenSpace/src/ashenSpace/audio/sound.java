package ashenSpace.audio;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class sound {
	
	public static ArrayList<Clip> clips = new ArrayList<Clip>();
	public static synchronized void play(final String fileName){
	
	 new Thread(new Runnable() { 
		public void run() {
             try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource(fileName));
                clip.open(inputStream);
                clip.start(); 
                clips.add(clip);
 
                FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                control.setValue(-30.0f);             
                
                
             } catch (Exception e) {
                 System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
             }
             
         }
		
     }).start();
 }
	
	public void tick(){

	}
	
	
}
