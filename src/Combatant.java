import java.util.ArrayList;

public abstract class Combatant {
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

    public void setStatusEffect(IStatusEffect effectInterface) {
        activeEffects.add(effectInterface);
        effectInterface.applyEffect(this);
    }

    public void setSkipTurn(boolean skipped) {
        isTurnSkipped = skipped;
    }

    public void updateStatusEffect() {
        ArrayList<IStatusEffect> toRemove = new ArrayList<>();

        for (IStatusEffect effect : activeEffects) {
            // If the effect has yet to expire, we decrease the duration by 1
            effect.decreaseDuration();

            if (effect.isEffectExpired()) {
                effect.removeEffect(this);
                toRemove.add(effect);

                System.out.println(effect.getEffectName() + " has expired for " + this.getCombatantName() + "!");
            }
        }

        activeEffects.removeAll(toRemove);
    }

    public void takeDamage(int damageAmt) {
        currentHP -= damageAmt;
        if (currentHP < 0) {
            currentHP = 0;
        }
    }

    public void healHP(int healAmt) {
        currentHP += healAmt;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public boolean isTurnSkipped() {
        return isTurnSkipped;
    }

    public void decreaseCooldown() {
        if (skillCooldown > 0)
            skillCooldown--;
    }

    public void resetCooldown() {
        skillCooldown = 3;
    }
}
