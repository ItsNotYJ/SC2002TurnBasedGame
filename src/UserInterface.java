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

        System.out.println("Welcome to the game! Press <Enter> to start!");
        sc.nextLine();

        System.out.println("Initializing...\n");

        System.out.println("Player Setup:");
        System.out.printf("1. Warrior | Attack: %d | Health: %d | Defense: %d | Speed: %d\n",
                displayWarrior.getAttack(), displayWarrior.getMaxHP(),
                displayWarrior.getDefense(), displayWarrior.getSpeed());
        System.out.printf("2. Wizard | Attack: %d | Health: %d | Defense: %d | Speed: %d\n",
                displayWizard.getAttack(), displayWizard.getMaxHP(),
                displayWizard.getDefense(), displayWizard.getSpeed());

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

        System.out.println();

        // Initialize the player selection for inventory items (2 items, can allow duplicates)
        System.out.println("Inventory Setup:");
        System.out.println("1. Potion - Heals 100HP");
        System.out.println("2. Power Stone - Free extra use of special skill");
        System.out.println("3. Smoke Bomb - Enemy deals 0 damage this turn and the following turn");
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
        System.out.println("Game Difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Difficult");

        // Initialize the player selection for player role (Wizard / Warrior)
        int diffSelect = 0;
        do {
            System.out.print("Select your role: ");
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
