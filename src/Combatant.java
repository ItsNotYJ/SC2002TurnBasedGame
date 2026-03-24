import java.util.ArrayList;

interface IStatusEffect {
    String effectName = null;

    static String getEffectName() {
        return effectName;
    }

    static void applyEffect() { }
    static void removeEffect() { }
    static void getEffectDuration() { }
    static void isEffectExpired() { }
}

public abstract class Combatant {
    private String combatantName;
    private int currentHP;
    private int maxHP;
    private int attack;
    private int defense;
    private int speed;
    private int skillCooldown;
    private boolean isTurnSkipped;
    private ArrayList<IStatusEffect> activeEffects;

    public Combatant() { }

    public Combatant(String combatantName, ArrayList<IStatusEffect> activeEffects, boolean isTurnSkipped, int skillCooldown, int speed, int defense, int attack, int maxHP, int currentHP) {
        this.combatantName = combatantName;
        this.activeEffects = activeEffects;
        this.isTurnSkipped = isTurnSkipped;
        this.skillCooldown = skillCooldown;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
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

    // TODO: Add method code after initializing files, might have to change the argument (Tentative)
    public void setStatusEffect(IStatusEffect effectInterface) {
        this.activeEffects.add(null);
    }

    public void setSkipTurn(boolean skipped) {
        this.isTurnSkipped = skipped;
    }

    // TODO: Add method code after initializing files
    public void updateStatusEffect() {

    }

    public abstract void takeDamage(int damageAmt);

    public abstract void healHP(int healAmt);

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
