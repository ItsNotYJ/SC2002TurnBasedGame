public class MainApp {
    public static void main(String[] args) throws Exception {
        boolean restartGame = true;

        while (restartGame) {
            UserInterface ui = new UserInterface();
            ui.initGame();
            ui.getEngine().startGame();

            restartGame = ui.checkIfRestartGame();
        }
    }
}
