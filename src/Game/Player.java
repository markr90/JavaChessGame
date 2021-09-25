package Game;

public class Player {
    private boolean isWhite;
    private boolean isChecked;

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

    public void setChecked(boolean value) {
        isChecked = value;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
