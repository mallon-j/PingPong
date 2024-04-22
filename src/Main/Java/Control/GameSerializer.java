package Control;
import java.io.*;
import Model.Game;

public class GameSerializer {
    private static GameSerializer instance;

    private GameSerializer() {}

    public static GameSerializer getInstance() {
        if (instance == null) {
            instance = new GameSerializer();
        }
        return instance;
    }

    public void serialize(Game game, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Game deserialize(String fileName) {
        Game game = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Game class not found");
            c.printStackTrace();
        }
        return game;
    }
}