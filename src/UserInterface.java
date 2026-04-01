import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    private Scanner sc;
    private BattleEngine engine;

    public UserInterface() {
        this.sc = new Scanner(System.in);
        this.engine = new BattleEngine();
    }

    public void initGame() {
        System.out.println("========================================");
        System.out.println("       TURN-BASED COMBAT ARENA");
        System.out.println("========================================");
        System.out.println("Loading Game...\n");

        // 1. Role Selection
        System.out.println("--- SELECT YOUR ROLE ---");
        System.out.println("1. Warrior");
        System.out.println("   Attributes: HP: 260 | Attack: 40 | Defense: 20 | Speed: 30");
        System.out.println("   Special Skill: Shield Bash (Stuns enemy for 2 turns)");
        System.out.println("2. Wizard");
        System.out.println("   Attributes: HP: 200 | Attack: 50 | Defense: 10 | Speed: 20");
        System.out.println("   Special Skill: Arcane Blast (+10 Attack per kill)");
        System.out.print("Enter choice (1 or 2): ");
        int roleChoice = sc.nextInt();

        // How does BattleEngine set for the Player
        // if (roleChoice == 1) engine.setPlayer(new Player(new Warrior()));

        // 2. Item Selection
        System.out.println("\n--- SELECT YOUR ITEMS (Choose 2, duplicates allowed) ---");
        System.out.println("1. Potion (Heal 100 HP)");
        System.out.println("2. Power Stone (Free use of Special Skill without cooldown)");
        System.out.println("3. Smoke Bomb (Enemy attacks deal 0 damage for 2 turns)");

        for (int i = 1; i <= 2; i++) {
            System.out.print("Select Item " + i + " (1-3): ");
            int itemChoice = sc.nextInt();
            // Adding items to Player's inventory
            // if (itemChoice == 1) player.getInventory().add(new ItemPotion());
        }

        // 3. Difficulty Selection (not sure)
        System.out.println("\n--- SELECT DIFFICULTY ---");
        System.out.println("1. Easy (3 Goblins)");
        System.out.println("2. Medium (1 Goblin, 1 Wolf | Backup: 2 Wolves)");
        System.out.println("3. Hard (2 Goblins | Backup: 1 Goblin, 2 Wolves)");

        System.out.println("\nEnemy Stats:");
        System.out.println("- Goblin: HP: 55 | Attack: 35 | Defense: 15 | Speed: 25");
        System.out.println("- Wolf: HP: 40 | Attack: 45 | Defense: 5 | Speed: 35");
        System.out.print("Enter difficulty (1-3): ");
        int diffChoice = sc.nextInt();



        System.out.println("\nGame Initiation Complete! Battle Start!\n");
    }

    public void displayBattle() {

    }

    public void displayIfGameEnd(boolean didPlayerWin) {

    }

    public IAction inputPlayerAction(Player player) {
        return null;
    }
}
