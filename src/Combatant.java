import java.util.ArrayList;

public abstract class Combatant implements IStatusEffect, IAction {
    private final String combatantName;
    private int currentHP;
    private int maxHP;
    private int attack;
    private int defense;
    private int speed;
    private int skillCooldown;
    private boolean isTurnSkipped;
    private ArrayList<IStatusEffect> activeEffects;

    public Combatant(String combatantName, int speed, int defense, int attack, int maxHP) {
        this.activeEffects = new ArrayList<>();
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

    public ArrayList<IStatusEffect> getActiveEffects() { return activeEffects; }

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

    public void setStatusEffect(IStatusEffect effect) {
        activeEffects.add(effect);
    }

    public void setSkipTurn(boolean skipped) {
        isTurnSkipped = skipped;
    }

    // We apply each activeEffect to the combatant
    public void updateStatusEffect() {
        java.util.Iterator<IStatusEffect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            IStatusEffect e = iterator.next();
            if (!e.isEffectExpired()) {
                e.applyEffect(this);
            } else {
                iterator.remove();
            }
        }
    }

    public void takeDamage(int damageAmt) {
        // To ensure that the HP will always be 0 and above using the Math.max method
        int effectiveDmg = Math.max(0, damageAmt);
        currentHP = Math.max(0, currentHP - effectiveDmg);
    }

    public void healHP(int healAmt) {
        if (currentHP + healAmt > maxHP)
            currentHP = maxHP;
        else
            currentHP += healAmt;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public boolean isTurnSkipped() {
        return isTurnSkipped;
    }

    public void decreaseSkillCooldown() {
        skillCooldown--;
    }

    public void resetSkillCooldown() {
        skillCooldown = 0;
    }
}
