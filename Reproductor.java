import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Reproductor extends Thread
{
    private static String currentDirectory = System.getProperty("user.dir");
    private boolean exit = false;
    private boolean isPlay = false;
    private String song = "";

    @Override
	public void run()
    {
        try
        {
            while(!exit)
            {
                Thread.sleep(100);
                if(isPlay)
                {
                    
                        String wavFilePath = currentDirectory+"/"+song;
                        File archivoWAV = new File(wavFilePath);

                        Clip clip = AudioSystem.getClip();
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoWAV);
                        clip.open(audioInputStream);
                        clip.start();
                        while (!clip.isRunning()) {
                            Thread.sleep(10);
                        }
                        while (clip.isRunning() & isPlay) {
                            Thread.sleep(10);
                        }
                        clip.close();
                        audioInputStream.close();
                
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("error" + e.getMessage());
        }
    }

    public void playSong(String song)
    {
        this.isPlay=true;
        this.song = song;
    }

    public void stopSong()
    {
        this.isPlay=false;
        this.song = "";
    }

    public void stopRepro()
    {
        this.isPlay=false;
        this.song = "";
        this.exit = true;
    }
}
