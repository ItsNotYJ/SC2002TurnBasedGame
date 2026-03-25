import java.util.ArrayList;

public abstract class PlayerRole {
    private String classRole;
    private int maxHP;
    private int attack;
    private int defense;
    private int speed;

    public PlayerRole(String classRole, int speed, int attack, int maxHP, int defense) {
        this.classRole = classRole;
        this.speed = speed;
        this.attack = attack;
        this.maxHP = maxHP;
        this.defense = defense;
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

    public String getClassRole() {
        return this.classRole;
    }

    public abstract void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine);
}
