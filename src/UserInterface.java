import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private BattleEngine engine;
    private int originalRoleSelect;
    private ArrayList<Item> originalItems;
    private int originalDiffSelect;

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
        EnemyGoblin displayGoblin = new EnemyGoblin("Goblin");
        EnemyWolf displayWolf = new EnemyWolf("Wolf");

        System.out.println("========================================");
        System.out.println("       TURN-BASED COMBAT ARENA");
        System.out.println("========================================");
        System.out.println("Welcome to the game! Press <Enter> to start!");
        sc.nextLine();

        System.out.println("Loading Game...\n");

        int roleSelect = initRole(displayWarrior, displayWizard);
        ArrayList<Item> selectedItems = initInventory();
        int diffSelect = initDifficulty(displayGoblin, displayWolf);

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
        engine.setUserInterface(this);

        // We save the original settings for when the player wants to replay the game with the same settings
        this.originalRoleSelect = roleSelect;
        this.originalItems = selectedItems;
        this.originalDiffSelect = diffSelect;

        // End init
        System.out.println();
        System.out.println("Game initialized successfully! Good luck and have fun!\n\n");
    }

    private int initRole(WarriorRole displayWarrior, WizardRole displayWizard) {
        System.out.println("=========== SELECT YOUR ROLE ===========");
        System.out.println("1. Warrior");
        System.out.printf("   Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n",
                displayWarrior.getMaxHP(), displayWarrior.getAttack(),
                displayWarrior.getDefense(), displayWarrior.getSpeed());
        System.out.println("   Special Skill: Shield Bash (Stuns enemy for 2 turns)\n");

        System.out.println("2. Wizard");
        System.out.printf("   Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n",
                displayWizard.getMaxHP(), displayWizard.getAttack(),
                displayWizard.getDefense(), displayWizard.getSpeed());
        System.out.println("   Special Skill: Arcane Blast (+10 Attack per kill)\n");

        // Initialize the player selection for player role (Wizard / Warrior)
        int roleSelect = 0;
        do {
            System.out.print("Select your role: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid selection. Please try again!\n");
                System.out.print("Select your role: ");
                sc.next();
            }
            roleSelect = sc.nextInt();

            if (roleSelect < 1 || roleSelect > 2) {
                System.out.println("Invalid selection. Please try again!\n");
            } else {
                switch (roleSelect) {
                    case 1 -> System.out.println("You have chosen the Warrior role!");
                    case 2 -> System.out.println("You have chosen the Wizard role!");
                }
            }

            // We exit the do-while loop once the user chooses 1 / 2 for the player role
        } while (roleSelect != 1 && roleSelect != 2);

        return roleSelect;
    }

    private ArrayList<Item> initInventory() {
        // Initialize the player selection for inventory items (2 items, can allow duplicates)
        // 2. Item Selection
        System.out.println("\n=========== SELECT YOUR ITEMS (Choose 2, duplicates allowed) ===========");
        System.out.println("1. Potion (Heal 100 HP)");
        System.out.println("2. Power Stone (Free use of Special Skill without cooldown)");
        System.out.println("3. Smoke Bomb (Enemy attacks deal 0 damage for 2 turns)\n");
        ArrayList<Item> selectedItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int itemSelect = 0;
            do {
                System.out.print("Select item " + (i + 1) + ": ");
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid selection. Please try again!\n");
                    System.out.print("Select item " + (i + 1) + ": ");
                    sc.next();
                }
                itemSelect = sc.nextInt();

                if (itemSelect < 1 || itemSelect > 3)
                    System.out.println("Invalid selection. Please try again!\n");
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

        System.out.printf("You have selected the following items:\n1. %s\n2. %s\n\n",
                selectedItems.get(0).getItemName(), selectedItems.get(1).getItemName());
        
        return selectedItems;
    }

    private int initDifficulty(EnemyGoblin displayGoblin, EnemyWolf displayWolf) {
        // We initialize the game difficulty here
        // 3. Difficulty Selection
        System.out.println("=========== SELECT DIFFICULTY ===========");
        System.out.println("1. Easy (3 Goblins)");
        System.out.println("2. Medium (1 Goblin, 1 Wolf | Backup: 2 Wolves)");
        System.out.println("3. Hard (2 Goblins | Backup: 1 Goblin, 2 Wolves)");

        System.out.println("\nEnemy Stats:");

        System.out.println("- Goblin");
        System.out.printf("  Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n",
                displayGoblin.getMaxHP(), displayGoblin.getAttack(),
                displayGoblin.getDefense(), displayGoblin.getSpeed());

        System.out.println("- Wolf");
        System.out.printf("  Attributes: HP: %d | Attack: %d | Defense: %d | Speed: %d\n\n",
                displayWolf.getMaxHP(), displayWolf.getAttack(),
                displayWolf.getDefense(), displayWolf.getSpeed());

        // Initialize the player selection for player role (Wizard / Warrior)
        int diffSelect = 0;
        do {
            System.out.print("Select your difficulty: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid selection. Please try again!\n");
                System.out.print("Select your difficulty: ");
                sc.next();
            }
            diffSelect = sc.nextInt();

            if (diffSelect < 1 || diffSelect > 3)
                System.out.println("Invalid selection. Please try again!\n");
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

        return diffSelect;
    }

    public void displayBattle() {
        // Loop through engine.getActiveCombatants() and print their current status
        // e.g., Name, HP, and active status effects
        System.out.println("\n=========== CURRENT BATTLE STATUS ===========\n");

        for (Combatant c : engine.getActiveCombatants()) {
            System.out.println(c.getCombatantName() + " | HP: " + c.getCurrentHP() + "/" + c.getMaxHP());
        }
        
        System.out.println("\n=============================================");
    }

    public void displayIfGameEnd(boolean didPlayerWin) {
        System.out.println("\n========================================");

        // Summary stats
        int finalPlayerHP = 0;
        int enemiesRemaining = 0;

        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Player) {
                finalPlayerHP = c.getCurrentHP();
            } else if (c instanceof Enemy && c.isAlive()) {
                enemiesRemaining++;
            }
        }

        // Switch between either win or loss
        if (didPlayerWin) {
            System.out.println("Congratulations, you have defeated all your enemies.");
            // Note: We cannot display Total Rounds without a getter in BattleEngine
            System.out.printf("Statistics: Remaining HP: %d | Total Rounds: %d\n",
                            finalPlayerHP, engine.getRoundCounter());
        } else {
            System.out.println("Defeated. Don't give up, you can try again!");
            System.out.printf("Statistics: Enemies remaining: %d | Total Rounds Survived: %d\n", enemiesRemaining, engine.getRoundCounter());
        }

        System.out.println("========================================\n");
    }

    public IAction inputPlayerAction(Player player) {
        displayEnemiesInField();
        displayPlayerStatus(player);
        
        System.out.println("\n=========== IT IS YOUR TURN ===========");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.println("4. Special Skill");
        
        while (true) {
            if (player.getSkillCooldown() > 0) {
                System.out.println("\nYour Special Skill is currently on cooldown for " + player.getSkillCooldown() + " more rounds.");
            } else {
                System.out.println("\nYour Special Skill is ready to use!");
            }

            // player input validation
            System.out.print("Choose your action: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid selection. Please try again!\n");
                System.out.print("Choose your action: ");
                sc.next();
            }
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    return new ActionBasicAttack();
                case 2:
                    return new ActionDefend();
                case 3:
                    ArrayList<Item> inventory = player.getInventory();
                    if (inventory.isEmpty()) {
                        System.out.println("You have no items left! Please choose a different action.");
                        continue;
                    }

                    displayPlayerInventory(player);

                    int itemUseSelectIndex = -1;
                    do {
                        System.out.print("\nSelect an item to use: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("\nInvalid selection. Please try again!\n");
                            System.out.print("Select an item to use: ");
                            sc.next();
                        }
                        
                        itemUseSelectIndex = sc.nextInt();

                        if (itemUseSelectIndex < 1 || itemUseSelectIndex > inventory.size())
                            System.out.println("\nInvalid selection. Please try again!");
                        else {
                            System.out.println("You have chosen to use: " + inventory.get(itemUseSelectIndex - 1).getItemName());
                        }
                    } while (itemUseSelectIndex < 1 || itemUseSelectIndex > inventory.size());

                    return new ActionItemUse(inventory.get(itemUseSelectIndex - 1));
                case 4:
                    // We default to the basic attack if the player's skill is still on cooldown
                    if (player.getSkillCooldown() > 0) {
                        System.out.println("Your Special Skill is currently on cooldown for " + player.getSkillCooldown() + " more rounds! Please choose a different action.");
                        continue;
                    }

                    return new ActionSpecialSkill();
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    public void displayEnemiesInField() {
            System.out.println("\n=========== ENEMIES IN THE FIELD ===========\n");
            int count = 1;
            for (Combatant c : engine.getActiveCombatants()) {
                if (c instanceof Enemy && c.isAlive()) {
                    System.out.println(count + ". " + c.getCombatantName() + " | HP: " + c.getCurrentHP() + "/" + c.getMaxHP());
                    displayStatusEffects(c);
                    count++;
                }
            }
            System.out.println("============================================");
    }

    public void displayPlayerStatus(Player player) {
        System.out.println("\n=========== YOUR STATUS ===========\n");
        System.out.println("HP: " + player.getCurrentHP() + "/" + player.getMaxHP());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Speed: " + player.getSpeed());
        System.out.println("Inventory:");
        for (Item item : player.getInventory()) {
            System.out.println("- " + item.getItemName());
        }
        displayStatusEffects(player);
        System.out.println("===================================");
    }

    public Combatant inputPlayerTarget() {
        ArrayList<Combatant> enemies = new ArrayList<>();
        for (Combatant c : engine.getActiveCombatants()) {
            // Check if enemy still alive
            if (c instanceof Enemy && c.isAlive()) {
                enemies.add(c);
            }
        }

        int targetIndex = 0;
        do {
            System.out.print("\nSelect a target: ");
            while (!sc.hasNextInt()) {
                System.out.println("\nInvalid selection. Please try again!\n");
                System.out.print("Select a target: ");
                sc.next();
            }
            targetIndex = sc.nextInt();

            if (targetIndex < 1 || targetIndex > enemies.size())
                System.out.println("\nInvalid selection. Please try again!");
            else {
                System.out.println("You have chosen to attack: " + enemies.get(targetIndex - 1).getCombatantName());
            }
        } while (targetIndex < 1 || targetIndex > enemies.size());

        Combatant target = enemies.get(targetIndex - 1);

        return target;
    }

    public void printRoundSummary(int currentRound) {
        System.out.printf("\n=========== Round %d ===========\n\n", currentRound);
        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Player && c.isAlive()) {
                System.out.printf("PLAYER: %s, HP: %d\n", c.getCombatantName(), c.getCurrentHP());
                displayStatusEffects(c);

            } else if (c instanceof Enemy && c.isAlive()) {
                System.out.printf("ENEMY: %s, HP: %d\n", c.getCombatantName(), c.getCurrentHP());
                displayStatusEffects(c);

            } else {
                System.out.printf("DEAD: %s\n", c.getCombatantName());
            }
        }
        System.out.println("\n===============================");
    }

    public void displayPlayerInventory(Player player) {
        System.out.println("\n=========== YOUR INVENTORY ===========\n");

        ArrayList<Item> inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getItemName());
        }

        System.out.println("\n====================================");
    }

    public void displayTurnOrder(ArrayList<Combatant> turnOrder) {
        System.out.println("\n=========== TURN ORDER ===========\n");

        for (int i = 0, count = 1; i < turnOrder.size(); i++) {
            Combatant c = turnOrder.get(i);

            if (c.isAlive()) {
                System.out.println((count) + ". " + c.getCombatantName() + " (Speed: " + c.getSpeed() + ")");
                count++;
            }
        }
        System.out.println("\n================================");
    }

    public int checkIfRestartGame() {
        System.out.println("\n=========== RESTART GAME? ===========\n");
        System.out.println("1. Replay with same settings");
        System.out.println("2. Start a new game");
        System.out.println("3. Exit");

        System.out.print("\nSelect an option: ");
        while (!sc.hasNext()) {
            System.out.println("\nInvalid selection. Please try again!\n");
            System.out.print("Select an option: ");
            sc.next();
        }
        int restart = sc.nextInt();

        if (restart < 1 || restart > 3) {
            System.out.println("\nInvalid selection. Please try again!\n");
            return checkIfRestartGame();
        }

        switch (restart) {
            case 1:
                System.out.println("Restarting the game...\n");
                return 1;
            case 2:
                System.out.println("Starting a new game...\n");
                return 2;
            case 3:
                System.out.println("Thank you for playing! Goodbye!");
                return 3;
            default:
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
        }

        return 3;
    }

    public void displayStatusEffects(Combatant c) {
        // We show the active status effects if its not empty
        if (c instanceof Player && c.getActiveEffects().isEmpty()) { // Show for player status if no active effects
            System.out.println("You have no active status effects.\n");
        } else if (c instanceof Enemy && c.getActiveEffects().isEmpty()) {
            System.out.println("The enemy has no active status effects.\n");
        } else { // This is the default case for both player and enemy
            System.out.print("Active Status Effects: ");
            for (IStatusEffect effect : c.getActiveEffects()) {
                System.out.printf("[" + effect.getEffectName() + " (" + effect.getEffectDuration() + ")]");
            }
            System.out.println("\n");
        }
    }

    public void resetGame() {
        // We reset the game by reusing the original settings that the player chose during init
        Player resetPlayer = null;
        switch (originalRoleSelect) {
            case 1:
                resetPlayer = new Player(new WarriorRole(), originalItems);
                break;
            case 2:
                resetPlayer = new Player(new WizardRole(), originalItems);
                break;
            default:
                break;
        };
        engine.setPlayer(resetPlayer);

        Difficulty resetDiff = null;
        switch (originalDiffSelect) {
            case 1:
                resetDiff = new DifficultyEasy();
                break;
            case 2:
                resetDiff = new DifficultyMedium();
                break;
            case 3:
                resetDiff = new DifficultyHard();
                break;
            default:
                break;
        };
        engine.setDifficulty(resetDiff);

        System.out.println("Game reset successfully with your original settings! Good luck and have fun!\n\n");
    }
}