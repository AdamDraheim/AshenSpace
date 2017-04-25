package ashenSpace.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;

public class Music {

	public static synchronized void play(final String fileName){
			
        new Thread(new Runnable() { 
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource(fileName));
                    clip.open(inputStream);
                    clip.start(); 
                    clip.loop(clip.LOOP_CONTINUOUSLY);
                    FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    control.setValue(6.0f);
                    
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();
    }
	
}
