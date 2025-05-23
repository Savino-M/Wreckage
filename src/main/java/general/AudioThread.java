package general;

import javazoom.jl.player.Player;

import java.io.FileInputStream;

public class AudioThread extends Thread {

    private boolean isRunning = true;
    private FileInputStream fis;
    private Player playMP3;

    public void run() {

        while (isRunning) {
            try {
                fis = new FileInputStream("Java/Wreckage/res/sounds/audio.mp3");
                playMP3 = new Player(fis);
                playMP3.play();
            } catch (Exception e) {
                isRunning = false;
            }
        }
    }

    public void kill() {
        playMP3.close();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

}