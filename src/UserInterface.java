import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private BattleEngine engine;

    public UserInterface() {
        this.sc = new Scanner(System.in);
        this.engine = new BattleEngine();
    }

    public void initGame() {

    }

    public void displayBattle() {

    }

    public void displayIfGameEnd(boolean didPlayerWin) {

    }

    public IAction inputPlayerAction(Player player) {
        return null;
    }
}
