package com.hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserPrompts {
    private int numberOfPlayers;
    private String player1Name;
    private String player2Name;
    private int difficultyLevel;

    Game newGame;

    //instantiate scanner class to prompt user
    Scanner scan = new Scanner(System.in);

    public void startUserPrompts() {
        welcome();
        askForPlayerNumber();
        askPlayerName();
        askNeedInstructions();
        askDifficultyLevel();
        createNewGame();
    }

    void welcome() {
        Thread thd = new Thread();
        File file = new File("/Users/murpslaw/Code/StudentWork/IntmJ/workspace/project-B10_Hangman/src/com/hangman/txt/Banner.txt");
        try {
            int charValue = 0;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            thd.start();
            while ((charValue = reader.read()) != -1) {
                char character = (char)charValue;
                if(character == '\0'){
                    System.out.println();
                }
                System.out.print(character);
                Thread.sleep(10);
            }
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void askForPlayerNumber() {
        System.out.print("\n \n \n How many players will play? (1/2) ");
        int numPlayer = scan.nextInt();
        setNumberOfPlayers(numPlayer);
    }

    private void askPlayerName() {
        if (getNumberOfPlayers() == 1) {
            System.out.print("\n Please enter name of player 1: ");
            setPlayer1Name(scan.next());
            System.out.println("\n Welcome to the Game " + getPlayer1Name() + " :)");
        } else if (getNumberOfPlayers() == 2){
            System.out.print("\n Please enter name of player 1: ");
            setPlayer1Name(scan.next());
            System.out.print("\n Please enter name of player 2: ");
            setPlayer2Name(scan.next());
        }
    }

    private void askNeedInstructions() {
        System.out.println("\n Do you know how to play Hangman? (y / n for help)");
        String needHelp = scan.next().toUpperCase();
        printInstructions(needHelp);
    }

    private void printInstructions(String needHelp) {
        if (needHelp.equals("N")) {
            System.out.println("\n A SUMMARY OF HANGMAN \n" +
                    "\n The computer will select a random word, and print a number of blanks " +
                    "\n equal to the number of letters in the word. Each turn you guess a letter." +
                    "\n If you guess correctly, the letter will be added to its proper place in the word," +
                    "\n but if you guess incorrectly, you will add a body part to the hangman." +
                    "\n If you complete the word, you win." +
                    "\n If you guess six incorrect letters, you lose. \n");
        }
    }

    private void askDifficultyLevel() {
        StringBuilder levelString = new StringBuilder()
                .append("DIFFICULTY LEVEL \n")
                .append("Please choose the difficulty level.\n")
                .append("1. Easy Level\n")
                .append("2. Intermediate Level\n")
                .append("3. Hard Level\n");
        System.out.println(levelString.toString());
        int level = scan.nextInt();
        setDifficultyLevel(level);
    }

    private void createNewGame(){
        if (getNumberOfPlayers() == 1){
            newGame = new Game(getNumberOfPlayers(), getDifficultyLevel(), new Player(getPlayer1Name()));
            newGame.startNewGame();
        } else if (getNumberOfPlayers()==2){
            newGame = new Game(getNumberOfPlayers(), getDifficultyLevel(), new Player(getPlayer1Name()),
                    new Player(getPlayer2Name()));
            newGame.startNewGame();
        }

    }

    // GETTERS AND SETTERS

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers == 1 || numberOfPlayers == 2){
            this.numberOfPlayers = numberOfPlayers;
        } else {
            System.out.println("Invalid input, please enter 1 or 2.");
            askForPlayerNumber();
        }
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        if (difficultyLevel == 1 || difficultyLevel == 2 || difficultyLevel == 3) {
            this.difficultyLevel = difficultyLevel;
        } else {
            System.out.println("Invalid input, please enter a number between 1-3.");
            askDifficultyLevel();
        }
    }
}
