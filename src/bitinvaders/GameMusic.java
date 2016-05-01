package bitinvaders;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


public class GameMusic implements Runnable 
{
	
    public GameMusic() { }

    public void play() 
    {
        Thread t = new Thread(this);
        t.start();
    }

    public void run ()
    {
        while(true)
        {
            playSound();
        }
    }

    private void playSound() 
    {
        //File soundFile = new File(BitInvaders.class.getResource("/spacepix/yugiMusic.wav").getFile());
        AudioInputStream audioInputStream = null;
        try 
        {
            audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("/spacepix/yugiMusic.wav"));
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        AudioFormat audioFormat = audioInputStream.getFormat();
        SourceDataLine line = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try 
        {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
        } 
        catch (LineUnavailableException e) 
        {
            e.printStackTrace();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        line.start();
        int nBytesRead = 0;
        byte[] abData = new byte[128000];
        while (nBytesRead != -1) 
        {
            try 
            {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) 
            {
                int nBytesWritten = line.write(abData, 0, nBytesRead);
            }
        }
        line.drain();
        line.close();
    }
}