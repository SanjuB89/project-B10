package com.hangman;

class Game {

    //FIELDS
    private int numberOfPlayer;
    private int level;
    private Player player;

    // CONSTRUCTORS
    public Game(int numberOfPlayer, int level, Player player) {
        setNumberOfPlayer(numberOfPlayer);
        setPlayer(player);
        setLevel(level);
    }

    public Game(int numberOfPlayer, int level, Player player1, Player player2){
        this(numberOfPlayer, level, player1);
        setPlayer(player2);
    }

    // BUSINESS METHODS
    void startNewGame(){
        player.generateWord(getLevel());
        player.generateGallows();
        player.play();
    }

    // GETTERS + SETTERS

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}