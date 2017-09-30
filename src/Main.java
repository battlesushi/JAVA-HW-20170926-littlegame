import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception{
        MainFrame mFrm=new MainFrame();
        mFrm.setVisible(true);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Touhou.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Thread.sleep(10000);
    }
}
