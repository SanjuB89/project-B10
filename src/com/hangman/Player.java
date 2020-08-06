package com.hangman;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import java.util.logging.FileHandler;

public class Player {

    //FIELDS
    private String name;
    private int numberOfMistakes = 0;
    private String wordGenerated;
    private Gallows gallows;
    private char guess;
    private List<Character> blanks;
    private List<Character> usedChars = new ArrayList<>();

    //CONSTRUCTOR
    Player(String name){
        setName(name);
    }

    //BUSINESS METHODS

    //-----------CALLED IN GAME CLASS------------------
    // This is called first in the Game class
    void generateWord(int level){
        PuzzleEngine puzzleEngine = new PuzzleEngine();
        setWordGenerated(puzzleEngine.chooseWord(level));
    }

    // This is called second in the Game class
    void generateGallows(){
        gallows = new Gallows();
    }

    // This is called third in the Game class
    // And calls all other methods.
    void play(){
        setUpBlanks();
        printBoard();
        continueTurns();
    }

    // ----------------PRINT METHODS---------------------
    void setUpBlanks(){
        //find the length of the assigned word
        int lengthOfWord = getWordGenerated().length();

        //create an array of blanks for each letter
        blanks = new ArrayList<>(lengthOfWord);
        for (int i = 0; i < lengthOfWord; i++) {
            blanks.add('_');
        }
    }

    void printGallows(){
        gallows.updateGallows(getNumberOfMistakes());
    }

    void printBlanks(){
        for(char c: blanks){
            System.out.print(" " + c + " ");
        }
    }

    void printBoard(){
        printGallows();
        printBlanks();
        System.out.println("\n Used Letters:" + usedChars);
        //Test output for our use only.
        System.out.println("Number of Mistakes:" + numberOfMistakes);
    }

    //------------------GUESS METHODS-------------------
    void continueTurns(){
        while (!isGameOver()){
            promptAndSetGuess();
        }
    }

    void promptAndSetGuess(){
        System.out.print("\n Please enter your guess.");
        Scanner scan = new Scanner(System.in);
        String newGuess = scan.next().toUpperCase();
        char guessChar = newGuess.charAt(0);
        setGuess(guessChar);
        findMatchOrMistake();
        printBoard();
    }

    private void findMatchOrMistake() {
        usedChars.add(getGuess());

        //create a character array to access its indexes
        char[] charArray = getWordGenerated().toCharArray();

        if(isCharacterMatch(getGuess(), charArray)){
            //stores the indexes of the characters in a word
            List<Integer> validIndexes = new ArrayList<>();

            //iterate through the character array and find the matching character's
            //indexes and dump it into the validIndexes list
            for(int i = 0;i < charArray.length; i++){
                if(guess == charArray[i]){
                    validIndexes.add(i);
                }
            }

            //iterating through the blankList to update the blanks into character values
            for(int i = 0; i < blanks.size(); i++){
                if(validIndexes.contains(i)){
                    blanks.set(i, guess);
                }
            }
        } else { numberOfMistakes++;}
    }

    private boolean isCharacterMatch(char guess, char[] list){
        boolean result = false;
        for (char c : list) {
            if (guess == c){
                result = true;
            }
        }
        return result;
    }

    // Maybe in Game class?
    boolean isDead(){
        boolean result = false;
        if(numberOfMistakes >= 6){
            result = true;
        }
        return result;
    }

    // Maybe in Game class?
    boolean isWordComplete(){
        boolean result = false;
        if (!blanks.contains('_')){
            result = true;
        }
        return result;
    }

    // Maybe in Game class?
    boolean isGameOver(){
        boolean result = false;

        if (isDead()){
            System.out.println("Game Over!");
            result = true;
        } else if(isWordComplete()){
            System.out.println("You win!");
            result = true;
        }
        return result;
    }

    //SETTERS AND GETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWordGenerated() {
        return wordGenerated;
    }

    public void setWordGenerated(String wordGenerated) {
        this.wordGenerated = wordGenerated.toUpperCase();
    }

    public char getGuess() {
        return guess;
    }

    public void setGuess(char guess) {
        if (!usedChars.contains(guess)) {
            this.guess = guess;
        } else {
            System.out.println("The character you entered has already been played. Please try a new character.");
            numberOfMistakes++;
            printBoard(); // Print after incremented
            continueTurns(); //Keep going if game is not over.
        }
    }

    public int getNumberOfMistakes() {
        return numberOfMistakes;
    }

// TODO: We're not currently using this when we increment and decrement.
    public void setNumberOfMistakes(int numberOfMistakes) {
        this.numberOfMistakes = numberOfMistakes;
    }
}