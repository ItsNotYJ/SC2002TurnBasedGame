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

    public void setStatusEffect(IStatusEffect effectInterface) {
        this.activeEffects.add(effectInterface);
        effectInterface.applyEffect(this);
    }

    public void setSkipTurn(boolean skipped) {
        this.isTurnSkipped = skipped;
    }

    public void updateStatusEffect() {
    ArrayList<IStatusEffect> toRemove = new ArrayList<>();

    for (IStatusEffect effect : activeEffects) {
        if (effect.isEffectExpired()) {
            effect.removeEffect(this);
            toRemove.add(effect);
        }
    }

    activeEffects.removeAll(toRemove);
    }

    public void takeDamage(int damageAmt) {
        this.currentHP -= damageAmt;
        if (this.currentHP < 0) {
            this.currentHP = 0;
    }
    }

    public void healHP(int healAmt) {
        this.currentHP += healAmt;
        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public boolean isTurnSkipped() {
        return isTurnSkipped;
    }

    public void decreaseCooldown() {
        if (this.skillCooldown > 0) {
            this.skillCooldown--;
    }
    }

    public void resetCooldown() {
        this.skillCooldown = 3; //putting 3 rn
    }
}
