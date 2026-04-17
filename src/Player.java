import java.util.ArrayList;

public class Player extends Combatant {
    private PlayerRole selectedRole;
    private ArrayList<Item> inventory;

    public PlayerRole getSelectedRole() {
        return this.selectedRole;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    // Abstract constructor
    public Player(PlayerRole selectedRole, ArrayList<Item> inventory) {
        // When the player first starts the game, initial default state values
        super(selectedRole.getClassRole(), selectedRole.getSpeed(), selectedRole.getDefense(),
                selectedRole.getAttack(), selectedRole.getMaxHP());

        this.selectedRole = selectedRole;
        this.inventory = inventory;
    }

    public void removeItem(Item item) {
        inventory.remove(item); // Might wanna test this if it works
    }

    // Mainly to use for the Power Stone item
    public void triggerSpecialSkill(Combatant target, BattleEngine engine) {
        this.selectedRole.doSpecialSkill(this, target, engine);
    }

    public void useSpecialSkill(Combatant enemy, BattleEngine engine) {
        if (this.getSkillCooldown() == 0) {
            this.selectedRole.doSpecialSkill(this, enemy, engine);
            this.resetCooldown();
        } else {
            System.out.println("\nYour special skill is on cooldown for " + this.getSkillCooldown() + " more turns!");
        }
    }
}
