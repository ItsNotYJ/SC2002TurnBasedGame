import java.util.ArrayList;

public class BattleEngine {
    private final Player player;
    private final ITurnOrderStrategy currentTurnOrder;
    private int roundCounter;
    private final Difficulty difficulty;
    private ArrayList<Combatant> activeCombatants;
    private boolean isBackupSpawned;

    public BattleEngine(Player player, Difficulty difficulty) {
        ArrayList<Combatant> combatants = new ArrayList<>();
        combatants.add(player);
        combatants.addAll(difficulty.getInitialSpawn());

        this.player = player;
        this.currentTurnOrder = new SpeedTurnOrder();
        this.roundCounter = 0;
        this.activeCombatants = combatants;
        this.difficulty = difficulty;
        this.isBackupSpawned = false;
    }

    public void startGame() {
        // First we determine the order of combatants based on their speed stat (SpeedTurnOrder)
        activeCombatants = currentTurnOrder.determineOrder(activeCombatants);

        // Apply any status effects applicable before game starts

        // Every do-while loop is a full rotation of combatants (Every round)
        do {
            // Loop through each combatant to execute their turns
            for (Combatant c : activeCombatants) {
                // Only execute their turn if the combatant is alive
                if (c.isAlive() && !c.isTurnSkipped())
                    System.out.println(c.getCombatantName());

                // We check the game ending condition after each action before moving to the next combatant
                WinCondition isWin = checkGameEndingCondition();
                if (isWin == WinCondition.WON || isWin == WinCondition.LOST)
                    break;
            }

            // Print out end of round summary
            printRoundSummary(roundCounter);
            roundCounter++;
        } while (checkGameEndingCondition() != WinCondition.WON || checkGameEndingCondition() == WinCondition.LOST);
    }

    public WinCondition checkGameEndingCondition() {
        // We do an immediate check to see if the player is still alive else the player has lost
        if (!player.isAlive())
            return WinCondition.LOST;

        // Then we loop through each combatant to check if the enemies are still alive, if alive return undetermined
        for (Combatant e : activeCombatants) {
            if (e instanceof Enemy && e.isAlive())
                return WinCondition.UNDETERMINED;
        }

        // Else, if all enemies are dead and player is alive return won
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

            // TODO: Need to test and see ensure added backup enemies only start
            //  their turn the following round (Not the same round)

        }
    }

    private void printRoundSummary(int currentRound) {
        for (Combatant c : activeCombatants) {
            if (c instanceof Player && c.isAlive()) {
                System.out.printf("PLAYER: %s, HP: %d", c.getCombatantName(), c.getCurrentHP());
            } else if (c instanceof Enemy && c.isAlive()) {
                System.out.printf("ENEMY: %s, HP: %d", c.getCombatantName(), c.getCurrentHP());
            } else {
                System.out.printf("DEAD: %s", c.getCombatantName());
            }
        }
    }
}
