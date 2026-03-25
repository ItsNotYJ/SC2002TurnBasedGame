import java.util.ArrayList;

public abstract class Combatant implements IStatusEffect, IAction {
    private String combatantName;
    private int currentHP;
    private int maxHP;
    private int attack;
    private int defense;
    private int speed;
    private int skillCooldown;
    private boolean isTurnSkipped;
    private ArrayList<IStatusEffect> activeEffects;

    public Combatant(String combatantName, int speed, int defense, int attack, int maxHP) {
        this.activeEffects = new ArrayList<IStatusEffect>();
        this.isTurnSkipped = false;
        this.skillCooldown = 0;

        this.combatantName = combatantName;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }

    public String getCombatantName() {
        return combatantName;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSkillCooldown() {
        return skillCooldown;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    // TODO: Add method code after initializing files
    public void setStatusEffect(IStatusEffect effectInterface) {
        this.activeEffects.add(null);
    }

    public void setSkipTurn(boolean skipped) {
        this.isTurnSkipped = skipped;
    }

    // TODO: Add method code after initializing files
    public void updateStatusEffect() {

    }

    public void takeDamage(int damageAmt) {

    }

    public void healHP(int healAmt) {

    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public boolean isTurnSkipped() {
        return isTurnSkipped;
    }

    // TODO: Both cooldown methods from interface?
    public void decreaseCooldown() {

    }

    public void resetCooldown() {

    }
}
