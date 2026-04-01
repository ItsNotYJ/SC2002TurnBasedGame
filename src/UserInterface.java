import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private BattleEngine engine;

    public UserInterface() {
        this.sc = new Scanner(System.in);
        this.engine = new BattleEngine();
    }

    // We use this method as the initial start up and loading screen for the player
    public void initGame() {
        WarriorRole displayWarrior = new WarriorRole();
        WizardRole displayWizard = new WizardRole();

        System.out.println("========================================");
        System.out.println("       TURN-BASED COMBAT ARENA");
        System.out.println("========================================");
        System.out.println("Welcome to the game! Press <Enter> to start!");
        sc.nextLine();

        System.out.println("Loading Game...\n");

        // 1. Role Selection
        System.out.println("--- SELECT YOUR ROLE ---");
        System.out.println("1. Warrior");
        System.out.printf("   Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n",
                displayWarrior.getMaxHP(), displayWarrior.getAttack(),
                displayWarrior.getDefense(), displayWarrior.getSpeed());
        System.out.println("   Special Skill: Shield Bash (Stuns enemy for 2 turns)");

        System.out.println("2. Wizard");
        System.out.printf("   Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n",
                displayWizard.getMaxHP(), displayWizard.getAttack(),
                displayWizard.getDefense(), displayWizard.getSpeed());
        System.out.println("   Special Skill: Arcane Blast (+10 Attack per kill)");

        // Initialize the player selection for player role (Wizard / Warrior)
        int roleSelect = 0;
        do {
            System.out.print("Select your role: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid selection. Please try again!");
                sc.next();
            }
            roleSelect = sc.nextInt();

            if (roleSelect < 1 || roleSelect > 2)
                System.out.println("Your selection is invalid, try again!\n");
            else {
                switch (roleSelect) {
                    case 1:
                        System.out.println("You have chosen the Warrior role!");
                        break;
                    case 2:
                        System.out.println("You have chosen the Wizard role!");
                        break;
                }
            }

            // We exit the do-while loop once the user chooses 1 / 2 for the player role
        } while (roleSelect != 1 && roleSelect != 2);

        // Initialize the player selection for inventory items (2 items, can allow duplicates)
        // 2. Item Selection
        System.out.println("\n--- SELECT YOUR ITEMS (Choose 2, duplicates allowed) ---");
        System.out.println("1. Potion (Heal 100 HP)");
        System.out.println("2. Power Stone (Free use of Special Skill without cooldown)");
        System.out.println("3. Smoke Bomb (Enemy attacks deal 0 damage for 2 turns)");
        ArrayList<Item> selectedItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int itemSelect = 0;
            do {
                System.out.print("Select item " + (i + 1) + ": ");
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid selection. Please try again!");
                    sc.next();
                }
                itemSelect = sc.nextInt();

                if (itemSelect < 1 || itemSelect > 3)
                    System.out.println("Your selection is invalid, try again!\n");
                else {
                    switch (itemSelect) {
                        case 1:
                            selectedItems.add(new ItemPotion());
                            System.out.println("You have chosen Potion!");
                            break;
                        case 2:
                            selectedItems.add(new ItemPowerStone());
                            System.out.println("You have chosen Power Stone!");
                            break;
                        case 3:
                            selectedItems.add(new ItemSmokeBomb());
                            System.out.println("You have chosen Smoke Bomb!");
                            break;
                    }
                }
                // We exit the do-while loop once the user chooses 1 / 2 for the player role
            } while (itemSelect != 1 && itemSelect != 2 && itemSelect != 3);

            System.out.println();
        }

        // We initialize the game difficulty here
        // 3. Difficulty Selection
        System.out.println("--- SELECT DIFFICULTY ---");
        System.out.println("1. Easy (3 Goblins)");
        System.out.println("2. Medium (1 Goblin, 1 Wolf | Backup: 2 Wolves)");
        System.out.println("3. Hard (2 Goblins | Backup: 1 Goblin, 2 Wolves)");

        System.out.println("\nEnemy Stats:");
        System.out.println("- Goblin: HP: 55 | Attack: 35 | Defense: 15 | Speed: 25");
        System.out.println("- Wolf: HP: 40 | Attack: 45 | Defense: 5 | Speed: 35");

        // Initialize the player selection for player role (Wizard / Warrior)
        int diffSelect = 0;
        do {
            System.out.print("Select your difficulty: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid selection. Please try again!");
                sc.next();
            }
            diffSelect = sc.nextInt();

            if (diffSelect < 1 || diffSelect > 3)
                System.out.println("Your selection is invalid, try again!\n");
            else {
                switch (diffSelect) {
                    case 1:
                        System.out.println("You have chosen the Easy difficulty!");
                        break;
                    case 2:
                        System.out.println("You have chosen the Medium difficulty!");
                        break;
                    case 3:
                        System.out.println("You have chosen the Hard difficulty!");
                }
            }
            // We exit the do-while loop once the user chooses 1 / 2 for the player role
        } while (diffSelect != 1 && diffSelect != 2 && diffSelect != 3);

        // Now we setup the player and battle engine to complete the init
        Player newPlayer = switch (roleSelect) {
            case 1 -> new Player(displayWarrior, selectedItems);
            case 2 -> new Player(displayWizard, selectedItems);
            default -> null;
        };
        engine.setPlayer(newPlayer);

        Difficulty diff = switch (diffSelect) {
            case 1 -> new DifficultyEasy();
            case 2 -> new DifficultyMedium();
            case 3 -> new DifficultyHard();
            default -> null;
        };
        engine.setDifficulty(diff);

        // End init
        System.out.println();
        System.out.println("Game initialized successfully! Good luck and have fun!");
    }

    public void displayBattle() {

    }

    public void displayIfGameEnd(boolean didPlayerWin) {
        
    }

    public IAction inputPlayerAction(Player player) {
        return null;
    }
}
