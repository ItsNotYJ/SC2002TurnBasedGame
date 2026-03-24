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

    public Player() { }

    public Player(boolean isTurnSkipped, int skillCooldown, int speed,
                  int defense, int attack, int maxHP, int currentHP,
                  PlayerRole selectedRole, ArrayList<Item> inventory) {
        super(selectedRole.getClassRole(), null, isTurnSkipped,
                skillCooldown, speed, defense, attack, maxHP, currentHP);

        this.selectedRole = selectedRole;
        this.inventory = inventory;
    }

    public void removeItem(Item item) {
        inventory.remove(item); // Might wanna test this if it works
    }

    // TODO: To implement based on given player role
    public void useSpecialSkill(Combatant enemy, BattleEngine engine) {

    }

    @Override
    public void takeDamage(int damageAmt) {

    }

    @Override
    public void healHP(int healAmt) {

    }
}
