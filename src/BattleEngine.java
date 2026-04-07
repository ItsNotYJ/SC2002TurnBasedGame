import java.util.ArrayList;

public class BattleEngine {
    private Player player;
    private ITurnOrderStrategy currentTurnOrder;
    private int roundCounter;
    private Difficulty difficulty;
    private ArrayList<Combatant> activeCombatants;
    private boolean isBackupSpawned;
    private UserInterface userInterface;

    public BattleEngine() {
        this.currentTurnOrder = new SpeedTurnOrder();
        this.roundCounter = 0;
        this.activeCombatants = new ArrayList<>();
        this.isBackupSpawned = false;
        this.userInterface = null;
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

    public void setUserInterface(UserInterface ui) {
        userInterface = ui;
    }

    public void setCurrentTurnOrder(ITurnOrderStrategy turnOrderStrategy) { this.currentTurnOrder = turnOrderStrategy; }

    public int getRoundCounter() { return roundCounter; }

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
                        // We get the userinterface to get the player's action
                        IAction playerAction = userInterface.inputPlayerAction(player);
                        
                        // Based on the action then we decide what logic to execute
                        Combatant target = null;
                        if (playerAction instanceof ActionBasicAttack || playerAction instanceof ActionSpecialSkill) {
                            // Display enemies in field for player to choose who to attack
                            userInterface.displayEnemiesInField();

                            // Get player's target choice
                            target = userInterface.inputPlayerTarget();

                            // Then we execute the player's chosen action in this turn
                            playerAction.executeTurn(c, target, this);
                        } else if (playerAction instanceof ActionItemUse item) {
                            // If the player chooses to use the power stone, we need to ask for a target
                            // because it activates his/her special skill on a target
                            if (item.getUsingItem() instanceof ItemPowerStone) {
                                userInterface.displayEnemiesInField();
                                target = userInterface.inputPlayerTarget();
                            }
                            
                            // Then we execute the player's chosen action in this turn
                            // If no target based on the chosen item, it will default to null
                            playerAction.executeTurn(c, target, this);
                        } else {
                            // This is mainly for the defend action and item use action which doesn't require any specific combat logic
                            playerAction.executeTurn(c, target, this);
                        }
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
            userInterface.printRoundSummary(roundCounter);
        } while (checkGameEndingCondition() == WinCondition.UNDETERMINED);

        // If the game has ended we print a final game summary, can be win or loss
        userInterface.displayIfGameEnd(checkGameEndingCondition() == WinCondition.WON);
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
}
