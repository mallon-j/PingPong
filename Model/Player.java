package Model;

public class Player {
    private String name;
    private int score = 0;

    Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }

    public void addScore(){
        score += 1;
    }

    public void incrementScore(){
        this.score +=1;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score = score;
    }


}
