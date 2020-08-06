package com.hangman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class PuzzleEngine {

    String chooseWord(int level){
        List<String> list;
        Random rand = new Random();
        String word = "";

        switch(level){
            case 1:
                list = List.of("fish", "pear", "baby", "java");
                word = list.get(rand.nextInt(list.size()));
                break;
            case 2:
                list = List.of("piano", "python", "student"
                        , "hangman");
                word = list.get(rand.nextInt(list.size()));
                break;
            case 3:
                list = List.of("Mississippi", "hippopotamus"
                        , "hypothesis", "superfluous");
                word = list.get(rand.nextInt(list.size()));
                break;
        }

        return word;
    }
}