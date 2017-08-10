package core.util; 
 
import java.io.File; 
import java.io.IOException; 
 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 
 
 
 
public class SoundPlayer { 
 
	private Clip clip;

    public SoundPlayer(File file)  {
    	try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }

    public void play() {
		clip.setFramePosition(0);
		clip.start();
    }

    public void loop() {
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
    	clip.stop();
    }
 
} 