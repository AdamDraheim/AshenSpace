package ashenSpace.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import ashenSpace.game.Game;
import ashenSpace.game.Game.STATE;

public class MusicCheck {

	private Clip clip;
	
	public MusicCheck(String music){
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(music));
			
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			
			clip.open(dais);
			 FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
             control.setValue(6.0f);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void play(){
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop(){
		if(clip.isRunning()){
			clip.stop();
		}
	}
	public void close(){
		stop();
		clip.close();
	}
	
	public void getPos(){
		if(clip.getFramePosition() == clip.getFrameLength()){
			stop();
			play();
		}
	}
	
}
