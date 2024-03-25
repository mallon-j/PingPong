package Model;

/**
 * The Player class represents a player in a game.
 */
public class Player {
    private String name; // The name of the player
    private int score = 0; // The score of the player

    /**
     * Constructs a new Player object with the given name.
     *
     * @param name The name of the player
     */
    Player(String name){
        this.name = name;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player
     */
    public String getName(){
        return name;
    }

    /**
     * Retrieves the score of the player.
     *
     * @return The score of the player
     */
    public int getScore(){
        return score;
    }

    /**
     * Increases the player's score by 1.
     */
    public void addScore(){
        score += 1;
    }

    /**
     * Increments the player's score by 1.
     */
    public void incrementScore(){
        this.score +=1;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The new name of the player
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the score of the player.
     *
     * @param score The new score of the player
     */
    public void setScore(int score){
        this.score = score;
    }
}
