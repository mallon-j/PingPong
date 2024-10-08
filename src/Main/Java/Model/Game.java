package Model;

import java.io.Serializable;

/**
 * The Game class represents a game environment with its properties and functionalities.
 */
public class Game implements Serializable{
    private double GAME_WIDTH = 600; // The width of the game area
    private double GAME_HEIGHT = 400; // The height of the game area
    private int scoreLimit = 10; // The score limit of the game
    
    private String player1Name = "Player 1"; // The name of player 1
    private String player2Name = "Player 2"; // The name of player 2
    
    private double paddleWidth = 15; // The width of the paddle
    private double paddleHeight = 80; // The height of the paddle

    private int ballSpeed = 2; // The speed of the ball
    private int ballDiameter = 20; // The diameter of the ball

    private Player player1; // Player 1 object
    private Player player2; // Player 2 object

    private Racket racket1; // Racket object for player 1
    private Racket racket2; // Racket object for player 2
    
    private Ball ball; // Ball object

    private Boolean paused = false; // Flag to indicate if the game is paused
    


    // Constructor
    public Game() {

    }

    /**
     * Pauses the game.
     */
    public void pause(){
        paused = true;
    }

    /**
     * Resumes the game.
     */
    public void resume(){
        paused = false;
    }

    /**
     * Checks if the game is paused.
     *
     * @return true if the game is paused, false otherwise
     */
    public boolean isPaused(){
        return paused;
    }


    // Getters

    public double getPaddleWidth(){
        return paddleWidth;
    }

    public double getPaddleHeight(){
        return paddleHeight;
    }

    public Ball getBall(){
        return ball;
    }

    public Racket getRacket1(){
        return racket1;
    }

    public Racket getRacket2(){
        return racket2;
    }

    public double getGAME_WIDTH(){
        return GAME_WIDTH;
    }

    public double getGAME_HEIGHT(){
        return GAME_HEIGHT;
    }

    public String getPlayer1Name() {
        return player1.getName();
    }

    public String getPlayer2Name() {
        return player2.getName();
    }

    public int getPlayer1Score() {
        return player1.getScore();
    }

    public int getPlayer2Score() {
        return player2.getScore();
    }

    public double getBallDiameter(){
        return ballDiameter;
    }

    public double getBallSpeed(){
        return ballSpeed;
    }

    public Player getPlayer1(){
        return this.player1;
    }
    public Player getPlayer2(){
        return this.player2;
    }

    public int getScoreLimit(){
        return this.scoreLimit;
    }

    // Setters

    public void setGameWidth(double width){
        this.GAME_WIDTH = width;
    }

    public void setGameHeight(double height) {
        this.GAME_HEIGHT = height;
    }

    public void setRacket1(Racket racket1) {
        this.racket1 = racket1;
    }

    public void setRacket2(Racket racket2) {
        this.racket2 = racket2;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void setPlayer1Name(String playerName){
        this.player1Name = playerName;
    }

    public void setPlayer2Name(String playerName){
        this.player2Name = playerName;
    }

    public void setScoreLimit(int score){
        this.scoreLimit = score;
    }

    public void setRacketSize(String size){
        switch (size) {
            case "Small":
                this.paddleHeight = 60;
                break;
            case "Medium":
                this.paddleHeight = 80;
                break;
            case "Large":
                this.paddleHeight = 100;
                break;
        }
    }

    public void setBallSpeed(String speed){
        switch (speed) {
            case "Slow":
                this.ballSpeed = 1;
                break;
            case "Medium":
                this.ballSpeed = 2;
                break;
            case "Fast":
                this.ballSpeed = 3;
                break;
        }
    }

    /**
     * Initializes the game environment by creating players, rackets, and ball objects.
     */
    public void instantiateGame(){
        double leftEdgeX = 0.0;
        double centerY = GAME_HEIGHT / 2.0;
        racket1 = new Racket(leftEdgeX, centerY - (paddleHeight / 2.0), paddleWidth, paddleHeight);
        racket2 = new Racket(GAME_WIDTH - paddleWidth, centerY - (paddleHeight / 2.0), paddleWidth, paddleHeight);
        
        ball = new Ball((GAME_WIDTH / 2), (GAME_HEIGHT / 2), ballDiameter, ballSpeed);

        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
    }

    /**
     * Restarts the game by resetting player scores, ball position, and racket positions.
     */
    public void restartGame(){
        player1.setScore(0);
        player2.setScore(0);
        ball.reset();
        racket1.setY(GAME_HEIGHT/2.0 - (paddleHeight / 2.0));
        racket2.setY(GAME_HEIGHT/2.0 - (paddleHeight / 2.0));
    }
}
