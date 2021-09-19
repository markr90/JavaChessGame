package Game;

public class Player {
    private boolean isWhite;

    public Player() {
    }

    public boolean IsWhite() {
        return isWhite;
    }

    public void SetWhite() {
        isWhite = true;
    }

    public String Name() {
        return isWhite ? "White" : "Black";
    }
}
