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

    // TODO: To implement based on given player role
    public void useSpecialSkill(Combatant enemy, BattleEngine engine) {

    }

    // IAction Method Overriding
    @Override
    public void executeTurn(Combatant user, Combatant target, BattleEngine engine) {

    }

    // IStatusEffect Method Overriding
    @Override
    public void applyEffect(Combatant target) {

    }

    @Override
    public void removeEffect(Combatant target) {

    }

    @Override
    public int getEffectDuration() {
        return 0;
    }

    @Override
    public boolean isEffectExpired() {
        return false;
    }

    @Override
    public void decreaseDuration() { decreaseSkillCooldown(); }
}
