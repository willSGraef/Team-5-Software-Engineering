package photon;

import javax.sound.sampled.*;
import java.util.Random;
import java.io.FileInputStream; 
import java.io.File;
import javazoom.jl.player.Player;

public class AudioPlayer implements Runnable{

    public AudioPlayer(){

    }

    @Override
    public void run(){
        Random rand = new Random();
        int randomTrackNumber = rand.nextInt(8) + 1; 

        String filePath = "game_audio\\Track0" + randomTrackNumber + ".mp3";

        try {
            
            FileInputStream audioInput = new FileInputStream(filePath);
            Player player = new Player(audioInput);
            Thread.sleep(11000);
            player.play();
        } catch (Exception e) {
            System.out.println("error playing audio");
            e.printStackTrace();
        }

        
    }


}
