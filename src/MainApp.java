public class MainApp {
    public static void main(String[] args) throws Exception {
        UserInterface ui = new UserInterface();
        int restartGame = 2;

        do {
            if (restartGame == 1) {
                System.out.println("Restarting the game...\n");
                ui.resetGame();
                ui.getEngine().startGame();
            } else if (restartGame == 2) {
                System.out.println("Starting a new game...\n");
                ui.initGame();
                ui.getEngine().startGame();
            }

            // Check if the player wants to restart or exit the game
            restartGame = ui.checkIfRestartGame();
        } while (restartGame != 3);
    }
}
