
public class WarriorRole extends PlayerRole {

    public WarriorRole() {
        super("Warrior", 30, 40, 260, 20);
    }

    @Override
    public void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine) {
        // deal basic attack damage
        int damage = Math.max(0, player.getAttack() - enemy.getDefense());
        enemy.takeDamage(damage);
        System.out.println("Warrior uses Shield Bash on " + enemy.getCombatantName() + " for " + damage + " damage!");

        enemy.setStatusEffect(new EffectStun());

        System.out.println(enemy.getCombatantName() + " is stunned!");

    }
}
