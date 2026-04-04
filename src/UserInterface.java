import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private BattleEngine engine;

    public UserInterface() {
        this.sc = new Scanner(System.in);
        this.engine = new BattleEngine();
    }

    public BattleEngine getEngine() {
        return engine;
    }

    // We use this method as the initial start up and loading screen for the player
    public void initGame() {
        WarriorRole displayWarrior = new WarriorRole();
        WizardRole displayWizard = new WizardRole();
        EnemyGoblin displayGoblin = new EnemyGoblin();
        EnemyWolf displayWolf = new EnemyWolf();

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

        System.out.println("- Goblin");
        System.out.printf("   Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n",
                displayGoblin.getMaxHP(), displayGoblin.getAttack(),
                displayGoblin.getDefense(), displayGoblin.getSpeed());

        System.out.println("- Wolf");
        System.out.printf("   Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n",
                displayWolf.getMaxHP(), displayWolf.getAttack(),
                displayWolf.getDefense(), displayWolf.getSpeed());

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
        // Loop through engine.getActiveCombatants() and print their current status
        // e.g., Name, HP, and active status effects
        System.out.println("\n--- CURRENT BATTLE STATUS ---");
        for (Combatant c : engine.getActiveCombatants()) {
            System.out.println(c.getCombatantName() + " | HP: " + c.getCurrentHP() + "/" + c.getMaxHP());
        }
    }

    public void displayIfGameEnd(boolean didPlayerWin) {
        System.out.println("\n========================================");

        // WORKAROUND: We must search the combatant list to find the Player and count live enemies
        int finalPlayerHP = 0;
        int enemiesRemaining = 0;

        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Player) {
                // We found the player! Grab their HP.
                finalPlayerHP = c.getCurrentHP();
            } else if (c instanceof Enemy && c.isAlive()) {
                // We found a living enemy, count them.
                enemiesRemaining++;
            }
        }

        if (didPlayerWin) {
            System.out.println("Congratulations, you have defeated all your enemies.");
            // Note: We cannot display Total Rounds without a getter in BattleEngine
            System.out.println("Statistics: Remaining HP: " + finalPlayerHP +
                    " | Total Rounds: N/A (Hidden in Engine)");
        } else {
            System.out.println("Defeated. Don't give up, try again!");
            System.out.println("Statistics: Enemies remaining: " + enemiesRemaining +
                    " | Total Rounds Survived: N/A (Hidden in Engine)");
        }
        System.out.println("========================================\n");
    }

    public IAction inputPlayerAction(Player player) {
        // This method works perfectly without touching BattleEngine because
        // the engine passes the 'player' object directly to this method as a parameter!

        System.out.println("\n--- IT IS YOUR TURN ---");
        System.out.println("Choose your action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.println("4. Special Skill");
        System.out.print("Enter action (1-4): ");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                return new ActionBasicAttack();
            case 2:
                return new ActionDefend();
            case 3:
                ArrayList<Item> inventory = player.getInventory();
                if (inventory.isEmpty()) {
                    System.out.println("You have no items left! Defaulting to Basic Attack.");
                    return new ActionBasicAttack();
                }
                System.out.println("Select an item to use:");
                for (int i = 0; i < inventory.size(); i++) {
                    System.out.println((i + 1) + ". " + inventory.get(i).getItemName());
                }
                int itemIndex = sc.nextInt() - 1;

                // Returns the specific ItemUse action with the chosen item
                // Uncomment this once the ItemUse class has its constructor ready
                // return new ItemUse(inventory.get(itemIndex));
                return null;
            case 4:
                return new ActionSpecialSkill();
            default:
                System.out.println("Invalid choice. Defaulting to Basic Attack.");
                return new ActionBasicAttack();
        }
    }
}
