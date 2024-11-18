package photon;

import java.util.Random;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class AudioPlayer implements Runnable{

    private boolean playing;

    public AudioPlayer(){
        this.playing = false;
    }

    @Override
    public void run(){
        while (playing) {
            Random rand = new Random();
            int randomTrackNumber = rand.nextInt(8) + 1; 
            String filePath = "game_audio//Track0" + randomTrackNumber + ".mp3";
            //Load class from jar file, then play
            try {
                // Path to the JAR file
                Path jarPath = Path.of("../lib/jlayer-1.0.1.jar");

                // Convert the JAR path to a URL
                URL jarUrl = jarPath.toUri().toURL();

                // Load the JAR file dynamically
                try (URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl})) {
                    
                    //Specify class name, constructor
                    String className = "javazoom.jl.player.Player";
                    Class<?> playerClass = loader.loadClass(className);
                    Constructor<?> constructor = playerClass.getConstructor(InputStream.class);
                    
                    //Dynamically create player object and invoke method
                    FileInputStream audioInput = new FileInputStream(filePath);
                    Object player = constructor.newInstance(audioInput);
                    Thread.sleep(11000);
                    Method method = playerClass.getMethod("play");
                    method.invoke(player);
                } catch(Exception e) {
                    e.printStackTrace();
                }
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

    public void stop() {
        this.playing = false;
    }


}
