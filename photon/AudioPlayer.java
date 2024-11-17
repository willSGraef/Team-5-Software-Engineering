package photon;

import java.util.Random;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AudioPlayer implements Runnable{

    public AudioPlayer(){

    }

    @Override
    public void run(){
        Random rand = new Random();
        int randomTrackNumber = rand.nextInt(8) + 1; 
        String filePath = "game_audio\\Track0" + randomTrackNumber + ".mp3";
        //Load class from jar file, then play
        try {
            //Specify class name, constructor
            String className = "net.javazoom.JLayer.Player";
            Class<?> playerClass = Class.forName(className);
            Constructor<?> constructor = playerClass.getConstructor();
            
            //Dynamically create player object and invoke method
            FileInputStream audioInput = new FileInputStream(filePath);
            Object player = constructor.newInstance(audioInput);
            Thread.sleep(11000);
            Method method = playerClass.getMethod("play");
            method.invoke(player);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            
        } catch (Exception e) {
            System.out.println("error playing audio");
            e.printStackTrace();
        }

        
    }


}
