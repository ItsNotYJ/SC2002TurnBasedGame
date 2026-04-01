public class MainApp {
    public static void main(String[] args) throws Exception {
        UserInterface ui = new UserInterface();

        // Initialize the game parameters
        ui.initGame();

        // Start the battle loop via BattleEngine (check with others)
        // ui.getEngine().startBattle();
    }
}
