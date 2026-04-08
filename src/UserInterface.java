import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private BattleEngine engine;
    private int originalRoleSelect;
    private ArrayList<Item> originalItems;
    private int originalDiffSelect;
    private ArrayList<String> battleLog;

    public UserInterface() {
        this.sc = new Scanner(System.in);
        this.engine = new BattleEngine();
        this.battleLog = new ArrayList<>();
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

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║          TURN-BASED COMBAT GAME          ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("\nWelcome to the game! Press <Enter> to start!");
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
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println(  "║        Game initialized! Good luck!      ║");
        System.out.println(  "╚══════════════════════════════════════════╝\n");
    }
 
    private int initRole(WarriorRole displayWarrior, WizardRole displayWizard) {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│             SELECT YOUR ROLE             │");
        System.out.println("└──────────────────────────────────────────┘");
        System.out.println("\n[1] Warrior");
        System.out.printf("    HP: %-3d | ATK: %-3d | DEF: %-3d | SPD: %-3d\n",
                displayWarrior.getMaxHP(), displayWarrior.getAttack(),
                displayWarrior.getDefense(), displayWarrior.getSpeed());
        System.out.println("    Skill: Shield Bash (Stuns enemy for 2 turns)\n");

        System.out.println("[2] Wizard");
        System.out.printf("    HP: %-3d | ATK: %-3d | DEF: %-3d | SPD: %-3d\n",
                displayWizard.getMaxHP(), displayWizard.getAttack(),
                displayWizard.getDefense(), displayWizard.getSpeed());
        System.out.println("    Skill: Arcane Blast (+10 Attack per kill)\n");

        // Initialize the player selection for player role (Wizard / Warrior)
        int roleSelect = 0;
        do {
            System.out.print("-> Select your role (1-2): ");
            while (!sc.hasNextInt()) {
                System.out.println("[!] Invalid input. Please enter a number.\n");
                System.out.print("-> Select your role (1-2): ");
                sc.next();
            }
            roleSelect = sc.nextInt();

            if (roleSelect < 1 || roleSelect > 2) {
                System.out.println("[!] Invalid selection. Please choose 1 or 2.\n");
            } else {
                switch (roleSelect) {
                    case 1 -> System.out.println("-> You have chosen the Warrior role!");
                    case 2 -> System.out.println("-> You have chosen the Wizard role!");
                }
            }

            // We exit the do-while loop once the user chooses 1 / 2 for the player role
        } while (roleSelect != 1 && roleSelect != 2);

        return roleSelect;
    }

    private ArrayList<Item> initInventory() {
        // Initialize the player selection for inventory items (2 items, can allow duplicates)
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println(  "│       SELECT YOUR ITEMS (Choose 2)       │");
        System.out.println(  "└──────────────────────────────────────────┘");
        System.out.println("(Duplicates allowed)\n");
        System.out.println("[1] Potion      - Heal 100 HP");
        System.out.println("[2] Power Stone - Free Special Skill (no cooldown)");
        System.out.println("[3] Smoke Bomb  - Enemy deals 0 damage for 2 turns\n");
        ArrayList<Item> selectedItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int itemSelect = 0;
            do {
                System.out.print("-> Select item " + (i + 1) + " (1-3): ");
                while (!sc.hasNextInt()) {
                    System.out.println("[!] Invalid input. Please enter a number.\n");
                    System.out.print("-> Select item " + (i + 1) + " (1-3): ");
                    sc.next();
                }
                itemSelect = sc.nextInt();

                if (itemSelect < 1 || itemSelect > 3)
                    System.out.println("[!] Invalid selection. Please choose 1, 2, or 3.\n");
                else {
                    switch (itemSelect) {
                        case 1:
                            selectedItems.add(new ItemPotion());
                            System.out.println("-> Potion added!");
                            break;
                        case 2:
                            selectedItems.add(new ItemPowerStone());
                            System.out.println("-> Power Stone added!");
                            break;
                        case 3:
                            selectedItems.add(new ItemSmokeBomb());
                            System.out.println("-> Smoke Bomb added!");
                            break;
                    }
                }
                // We exit the do-while loop once the user chooses 1 / 2 for the player role
            } while (itemSelect != 1 && itemSelect != 2 && itemSelect != 3);

            System.out.println();
        }

        System.out.println("\n─────────────────────────────────────────");
        System.out.printf("Selected Items: %s, %s\n",
                selectedItems.get(0).getItemName(), selectedItems.get(1).getItemName());
        System.out.println("─────────────────────────────────────────\n");
        
        return selectedItems;
    }

    private int initDifficulty(EnemyGoblin displayGoblin, EnemyWolf displayWolf) {
        // We initialize the game difficulty here
        // 3. Difficulty Selection
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│             SELECT DIFFICULTY            │");
        System.out.println("└──────────────────────────────────────────┘");
        System.out.println("\n[1] Easy   - 3 Goblins");
        System.out.println("[2] Medium - 1 Goblin, 1 Wolf | Backup: 2 Wolves");
        System.out.println("[3] Hard   - 2 Goblins | Backup: 1 Goblin, 2 Wolves");

        System.out.println("\n─── Enemy Stats ───");

        System.out.println("\n[X] Goblin");
        System.out.printf("    HP: %-3d | ATK: %-3d | DEF: %-3d | SPD: %-3d\n",
                displayGoblin.getMaxHP(), displayGoblin.getAttack(),
                displayGoblin.getDefense(), displayGoblin.getSpeed());

        System.out.println("\n[X] Wolf");
        System.out.printf("    HP: %-3d | ATK: %-3d | DEF: %-3d | SPD: %-3d\n\n",
                displayWolf.getMaxHP(), displayWolf.getAttack(),
                displayWolf.getDefense(), displayWolf.getSpeed());

        // Initialize the player selection for player role (Wizard / Warrior)
        int diffSelect = 0;
        do {
            System.out.print("-> Select difficulty (1-3): ");
            while (!sc.hasNextInt()) {
                System.out.println("[!] Invalid input. Please enter a number.\n");
                System.out.print("-> Select difficulty (1-3): ");
                sc.next();
            }
            diffSelect = sc.nextInt();

            if (diffSelect < 1 || diffSelect > 3)
                System.out.println("[!] Invalid selection. Please choose 1, 2, or 3.\n");
            else {
                switch (diffSelect) {
                    case 1:
                        System.out.println("-> Difficulty set to Easy!");
                        break;
                    case 2:
                        System.out.println("-> Difficulty set to Medium!");
                        break;
                    case 3:
                        System.out.println("-> Difficulty set to Hard!");
                }
            }
            // We exit the do-while loop once the user chooses 1 / 2 for the player role
        } while (diffSelect != 1 && diffSelect != 2 && diffSelect != 3);

        return diffSelect;
    }

    public void displayBattle() {
        // Loop through engine.getActiveCombatants() and print their current status
        // e.g., Name, HP, and active status effects
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println(  "│          CURRENT BATTLE STATUS           │");
        System.out.println(  "└──────────────────────────────────────────┘\n");

        for (Combatant c : engine.getActiveCombatants()) {
            System.out.printf("  %-12s │ HP: %3d/%3d\n", c.getCombatantName(), c.getCurrentHP(), c.getMaxHP());
        }
        
        System.out.println("\n────────────────────────────────────────────");
    }

    public void displayIfGameEnd(boolean didPlayerWin) {
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
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println(  "║                 VICTORY!                 ║");
            System.out.println(  "╚══════════════════════════════════════════╝");
            System.out.println("\nCongratulations! You defeated all enemies!\n");
            System.out.println("─── Statistics ───");
            System.out.printf("Remaining HP:  %d\n", finalPlayerHP);
            System.out.printf("Total Rounds:  %d\n", engine.getRoundCounter());
        } else {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println(  "║                 DEFEATED                 ║");
            System.out.println(  "╚══════════════════════════════════════════╝");
            System.out.println("\nDon't give up! You can try again!\n");
            System.out.println("─── Statistics ───");
            System.out.printf("Enemies Remaining:     %d\n", enemiesRemaining);
            System.out.printf("Total Rounds Survived: %d\n", engine.getRoundCounter());
        }
        System.out.println("────────────────────────────────────────────\n");

        printBattleLog();
    }

    public IAction inputPlayerAction(Player player) {
        displayEnemiesInField();
        displayPlayerStatus(player);
        
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println(  "│                YOUR TURN                 │");
        System.out.println(  "└──────────────────────────────────────────┘");
        System.out.println("\n[1] Basic Attack");
        System.out.println("[2] Defend");
        System.out.println("[3] Use Item");
        System.out.println("[4] Special Skill");
        
        while (true) {
            if (player.getSkillCooldown() > 0) {
                System.out.println("\n[!] Special Skill on cooldown: " + player.getSkillCooldown() + " rounds remaining");
            } else {
                System.out.println("\n-> Special Skill is ready!");
            }

            // player input validation
            System.out.print("\n-> Choose action (1-4): ");
            while (!sc.hasNextInt()) {
                System.out.println("[!] Invalid input. Please enter a number.\n");
                System.out.print("-> Choose action (1-4): ");
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
                        System.out.println("[!] No items remaining! Choose a different action.");
                        continue;
                    }

                    displayPlayerInventory(player);

                    int itemUseSelectIndex = -1;
                    do {
                        System.out.print("\n-> Select item (1-" + inventory.size() + "): ");
                        while (!sc.hasNextInt()) {
                            System.out.println("[!] Invalid input. Please enter a number.\n");
                            System.out.print("-> Select item (1-" + inventory.size() + "): ");
                            sc.next();
                        }
                        
                        itemUseSelectIndex = sc.nextInt();

                        if (itemUseSelectIndex < 1 || itemUseSelectIndex > inventory.size())
                            System.out.println("[!] Invalid selection.");
                        else {
                            System.out.println("-> Using: " + inventory.get(itemUseSelectIndex - 1).getItemName());
                        }
                    } while (itemUseSelectIndex < 1 || itemUseSelectIndex > inventory.size());

                    return new ActionItemUse(inventory.get(itemUseSelectIndex - 1));
                case 4:
                    // We default to the basic attack if the player's skill is still on cooldown
                    if (player.getSkillCooldown() > 0) {
                        System.out.println("[!] Special Skill on cooldown: " + player.getSkillCooldown() + " rounds remaining!");
                        continue;
                    }

                    return new ActionSpecialSkill();
                default:
                    System.out.println("[!] Invalid choice. Please select 1-4.");
            }
        }
    }

    public void displayEnemiesInField() {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println(  "│           ENEMIES IN THE FIELD           │");
            System.out.println(  "└──────────────────────────────────────────┘\n");
            int count = 1;
            for (Combatant c : engine.getActiveCombatants()) {
                if (c instanceof Enemy && c.isAlive()) {
                    System.out.printf("[%d] %-12s │ HP: %3d/%3d\n", count, c.getCombatantName(), c.getCurrentHP(), c.getMaxHP());
                    displayStatusEffects(c);
                    count++;
                }
            }
            System.out.println("────────────────────────────────────────────");
    }

    public void displayPlayerStatus(Player player) {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println(  "│               YOUR STATUS                │");
        System.out.println(  "└──────────────────────────────────────────┘\n");
        System.out.printf("HP:      %3d/%3d\n", player.getCurrentHP(), player.getMaxHP());
        System.out.printf("Attack:  %3d\n", player.getAttack());
        System.out.printf("Defense: %3d\n", player.getDefense());
        System.out.printf("Speed:   %3d\n\n", player.getSpeed());
        System.out.println("─── Inventory ───");
        if (player.getInventory().isEmpty()) {
            System.out.println("(empty)");
        } else {
            for (Item item : player.getInventory()) {
                System.out.println("->" + item.getItemName());
            }
        }
        System.out.println();
        displayStatusEffects(player);
        System.out.println("────────────────────────────────────────────");
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
            System.out.print("\n-> Select target (1-" + enemies.size() + "): ");
            while (!sc.hasNextInt()) {
                System.out.println("[!] Invalid input. Please enter a number.\n");
                System.out.print("-> Select target (1-" + enemies.size() + "): ");
                sc.next();
            }
            targetIndex = sc.nextInt();

            if (targetIndex < 1 || targetIndex > enemies.size())
                System.out.println("[!] Invalid selection.");
            else {
                System.out.println("-> Target: " + enemies.get(targetIndex - 1).getCombatantName());
            }
        } while (targetIndex < 1 || targetIndex > enemies.size());

        Combatant target = enemies.get(targetIndex - 1);

        return target;
    }

    public void printRoundSummary(int currentRound) {
        String roundSummary = buildRoundSummaryString(currentRound);
        battleLog.add(roundSummary);
    }

    public void resetBattleLog() {
        battleLog.clear();
    }

    public void printBattleLog() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println(  "│                BATTLE LOG                │");
        System.out.println(  "└──────────────────────────────────────────┘\n");

        if (battleLog.isEmpty()) {
            System.out.println("No rounds were logged.");
            System.out.println("\n────────────────────────────────────────────\n");
            return;
        }

        for (String roundSummary : battleLog) {
            System.out.print(roundSummary);
        }

        System.out.println("────────────────────────────────────────────\n");
    }

    private String buildRoundSummaryString(int currentRound) {
        StringBuilder summary = new StringBuilder();
        summary.append(String.format("Round %-3d | ", currentRound));

        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Player) {
                summary.append(String.format("PLAYER %s HP:%d/%d", c.getCombatantName(), c.getCurrentHP(), c.getMaxHP()));
                summary.append(" | ");
                break;
            }
        }

        summary.append("ENEMIES: ");
        boolean hasEnemy = false;
        for (Combatant c : engine.getActiveCombatants()) {
            if (c instanceof Enemy) {
                hasEnemy = true;
                if (c.isAlive()) {
                    summary.append(String.format("%s(%d/%d) ", c.getCombatantName(), c.getCurrentHP(), c.getMaxHP()));
                } else {
                    summary.append(String.format("%s(DEAD) ", c.getCombatantName()));
                }
            }
        }

        if (!hasEnemy) {
            summary.append("None");
        }

        summary.append("\n");
        return summary.toString();
    }

    public void displayPlayerInventory(Player player) {
        System.out.println("\n─── Your Inventory ───\n");

        ArrayList<Item> inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + inventory.get(i).getItemName());
        }

        System.out.println();
    }

    public void displayTurnOrder(ArrayList<Combatant> turnOrder) {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println(  "│                TURN ORDER                │");
        System.out.println(  "└──────────────────────────────────────────┘\n");

        for (int i = 0, count = 1; i < turnOrder.size(); i++) {
            Combatant c = turnOrder.get(i);

            if (c.isAlive()) {
                System.out.printf("[%d] %-15s (Speed: %d)\n", count, c.getCombatantName(), c.getSpeed());
                count++;
            }
        }
        System.out.println("\n────────────────────────────────────────────");
    }

    public int checkIfRestartGame() {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println(  "│               PLAY AGAIN?                │");
        System.out.println(  "└──────────────────────────────────────────┘\n");
        System.out.println("[1] Replay with same settings");
        System.out.println("[2] Start a new game");
        System.out.println("[3] Exit");

        System.out.print("\n-> Select option (1-3): ");
        while (!sc.hasNext()) {
            System.out.println("[!] Invalid input. Please enter a number.\n");
            System.out.print("-> Select option (1-3): ");
            sc.next();
        }
        int restart = sc.nextInt();

        if (restart < 1 || restart > 3) {
            System.out.println("[!] Invalid selection. Please choose 1, 2, or 3.\n");
            return checkIfRestartGame();
        }

        switch (restart) {
            case 1:
                System.out.println("\n-> Restarting with same settings...\n");
                return 1;
            case 2:
                System.out.println("\n-> Starting new game...\n");
                return 2;
            case 3:
                System.out.println("\n══════════════════════════════════════════");
                System.out.println("      Thank you for playing! Goodbye!");
                System.out.println("══════════════════════════════════════════\n");
                return 3;
            default:
                System.out.println("[!] Invalid selection.");
        }

        return 3;
    }

    public void displayStatusEffects(Combatant c) {
        // We show the active status effects if its not empty
        if (c instanceof Player && c.getActiveEffects().isEmpty()) {
            System.out.println("Status: None\n");
        } else if (c instanceof Enemy && c.getActiveEffects().isEmpty()) {
            System.out.println("Status: None\n");
        } else {
            System.out.print("Status: ");
            for (IStatusEffect effect : c.getActiveEffects()) {
                System.out.printf("[%s (%d)] ", effect.getEffectName(), effect.getEffectDuration());
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

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println(  "║    Game reset with original settings!    ║");
        System.out.println(  "╚══════════════════════════════════════════╝\n");
    }
}