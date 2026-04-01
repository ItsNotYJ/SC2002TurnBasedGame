import java.util.ArrayList;

public class BattleEngine {
    private Player player;
    private ITurnOrderStrategy currentTurnOrder;
    private int roundCounter;
    private Difficulty difficulty;
    private ArrayList<Combatant> activeCombatants;
    private boolean isBackupSpawned;

    public BattleEngine() {
        this.currentTurnOrder = new SpeedTurnOrder();
        this.roundCounter = 0;
        this.activeCombatants = new ArrayList<Combatant>();
        this.isBackupSpawned = false;
    }

    public ArrayList<Combatant> getActiveCombatants() {
        return activeCombatants;
    }

    public void setPlayer(Player player) {
        this.player = player;
        activeCombatants.add(player);
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        activeCombatants.addAll(difficulty.getInitialSpawn());
    }

    public void setCurrentTurnOrder(ITurnOrderStrategy turnOrderStrategy) { this.currentTurnOrder = turnOrderStrategy; }

    public void startGame() {
        if (player == null) {
            System.out.println("ERROR! Player is not set and returned null!");
            return;
        }

        // Every do-while loop is a full rotation of combatants (Every round)
        do {
            // First we determine the order of combatants based on their speed stat (SpeedTurnOrder)
            activeCombatants = currentTurnOrder.determineOrder(activeCombatants);

            // Apply any applicable active status effect for each combatant at the start
            for (Combatant c : activeCombatants) {
                c.updateStatusEffect();
            }

            // Loop through each combatant to execute their turns
            for (Combatant c : activeCombatants) {
                // First check if combatant's turn is skipped
                if (c.isTurnSkipped())
                    continue;

                if (c.isAlive())
                    // We differentiate between the enemy and player
                    if (c instanceof Enemy e) {
                        // Object instance of an Enemy
                        e.performTurn(activeCombatants);
                    } else {
                        // TODO: User Input and Object instance of a Player

                    }

                // We check the game ending condition after each action before moving to the next combatant
                WinCondition isWin = checkGameEndingCondition();
                if (isWin == WinCondition.WON || isWin == WinCondition.LOST)
                    break;
            }

            // We check if there is a need to spawn the backup enemies
            checkIfBackupSpawn();

            // Print out end of round summary
            roundCounter++;
            printRoundSummary(roundCounter);
        } while (checkGameEndingCondition() == WinCondition.UNDETERMINED);
    }

    public WinCondition checkGameEndingCondition() {
        if (player == null) {
            System.out.println("ERROR! Player is not set and returned null!");
            return WinCondition.UNDETERMINED;
        }

        // We do an immediate check to see if the player is still alive else the player has lost
        if (!player.isAlive())
            return WinCondition.LOST;

        // Then we loop through each combatant to check if the enemies are still alive, if alive return undetermined
        for (Combatant e : activeCombatants) {
            if (e instanceof Enemy && e.isAlive())
                return WinCondition.UNDETERMINED;
        }

        return WinCondition.WON;
    }

    public void checkIfBackupSpawn() {
        // Then we loop through each combatant to check if the enemies are still alive, if alive exit the backup spawn
        for (Combatant e : activeCombatants) {
            if (e instanceof Enemy && e.isAlive())
                return;
        }

        if (!isBackupSpawned && difficulty.hasBackupSpawn()) {
            System.out.println("The enemy has called in reinforcements!");
            System.out.println("...");
            System.out.println("The reinforcements have arrived!");

            // Add all the backup spawn enemies into the activeCombatantsList
            activeCombatants.addAll(difficulty.getBackupSpawn());

            isBackupSpawned = true;
        }
    }

    private void printRoundSummary(int currentRound) {
        System.out.printf("Round %d\n\n", currentRound);
        for (Combatant c : activeCombatants) {
            if (c instanceof Player && c.isAlive()) {
                System.out.printf("PLAYER: %s, HP: %d\n", c.getCombatantName(), c.getCurrentHP());
            } else if (c instanceof Enemy && c.isAlive()) {
                System.out.printf("ENEMY: %s, HP: %d\n", c.getCombatantName(), c.getCurrentHP());
            } else {
                System.out.printf("DEAD: %s\n", c.getCombatantName());
            }
        }
    }
}
