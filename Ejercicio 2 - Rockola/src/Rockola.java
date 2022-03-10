// Concredito: Ejercicio 2 - Rockola
// file using Clip Object
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
  
public class Rockola implements LineListener
{
    static boolean playCompleted;
    // to store current position
    Long currentFrame;
    Clip clip;
      
    // current status of clip
    String status;
      
    AudioInputStream audioInputStream;
    static String filePath;
    public int i=1; // Music counter var
    static int pauseVar=0; // Pause var
    // constructor to initialize streams and clip
    public Rockola()
        throws UnsupportedAudioFileException,
        IOException, LineUnavailableException 
    {
        // create AudioInputStream object
        audioInputStream = 
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
          
        // create clip reference
        clip = AudioSystem.getClip();
        
        // Listener
        clip.addLineListener(this); 

        // open audioInputStream to the clip
        clip.open(audioInputStream);
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
       

    }
  
    public static void main(String[] args) 
    {
        try
        {            
            filePath = "musica\\\\1.wav";
            Rockola audioPlayer = 
                            new Rockola();
              
            audioPlayer.play();
            Scanner sc = new Scanner(System.in);
            System.out.println("Now playing: Happy by Pharrell Williams.");  
            while (true)
            {
                if (pauseVar==0){
                    System.out.print("1. pause, ");
                }    
                else System.out.print("1. resume, ");
                System.out.println("2. restart, 3. next song, 4. stop");
                int c = sc.nextInt();
                audioPlayer.gotoChoice(c);
                if (c == 4) break;                
            }
            sc.close();
        }           
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();          
          }
    }
      
    // User selection
      
    private void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException 
    {
        switch (c) 
        {
            case 1:
                if (pauseVar==0){
                    System.out.println("Audio paused.");
                    pause();
                    break;
                }
                else{
                    resumeAudio();
                    System.out.println("Audio resumed.");
                    break;
                }
            case 2:
                status = "pause";
                restart();
                break;
            case 3:
                status = "pause";
                next();
                break;  
            case 4:
                stop();
                break;                    
        }
      
    }
      
    // Method to play the audio
    public void play() throws IOException, LineUnavailableException, UnsupportedAudioFileException 
    {       
        clip.start();                
        status = "play";
        pauseVar=0;            
    }
      
    // Method to pause the audio
    public void pause() 
    {
        status = "pause";
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        pauseVar=1;
    }
      
    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
                                IOException, LineUnavailableException 
    {
        if (status.equals("play")) 
        {
            System.out.println("Audio is already "+
            "being played");
            return;
        }
        status = "play";
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
      
    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
                                            UnsupportedAudioFileException 
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
      
    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
    IOException, LineUnavailableException 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
      

    // Method to play next song
    public void next() throws IOException, LineUnavailableException,
                                            UnsupportedAudioFileException
    {
        if (i<4){
            i=i+1;
        } 
        else i=1;
        clip.stop();
        clip.close();              
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath="musica\\\\"+i+".wav").getAbsoluteFile());
        clip.open(audioInputStream);
        clip.start();          
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
        switch (i)
        {
            case 1: System.out.println("Now playing: Happy by Pharrell Williams.");
            break;
            case 2: System.out.println("Now playing: Forever Young by Alphaville.");
            break;
            case 3: System.out.println("Now playing: Is this love by Whitesnake.");
            break;
            case 4: System.out.println("Now playing: Piano man by Billy Joel.");
            break;
        }
        status = "play";
    }
      
    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
                                            LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        status = "play";
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            //System.out.println("Playback started.");
            playCompleted = false;             
        } else if (type == LineEvent.Type.STOP) {
            //System.out.println("Playback completed.");
            playCompleted = true;            
            if(status!="pause"){
                try {
                    clip.stop();
                    clip.close(); 
                    next();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            } 
        }
    }     
}