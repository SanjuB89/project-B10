package com.hangman.client;

import com.hangman.UserPrompts;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class DoubleHangmanClient {

    public static void main(String[] args) {
        Clip clip = playSound();
        UserPrompts prompts = new UserPrompts();
        prompts.startUserPrompts();
        clip.stop();
    }

    //Added sound clip
    private static Clip playSound(){
        Clip result = null;
        try {
            File audioFile = new File("/Users/murpslaw/Code/StudentWork/IntmJ/workspace/project-B10_Hangman/src/com/hangman/txt/maintheme_1_the_combat_collection.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioInputStream);
            //audioClip.start();
            result = audioClip;

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return  result;
    }
}


