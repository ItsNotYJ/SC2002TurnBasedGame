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

    public void useSpecialSkill(Combatant enemy, BattleEngine engine) {
        if (this.getSkillCooldown() == 0) {
            this.selectedRole.doSpecialSkill(this, enemy, engine);
            this.resetCooldown();
        }
    }

    // IAction Method Overriding
    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        return null;
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
    public void decreaseDuration() { decreaseCooldown(); }
}
