
public class WarriorRole extends PlayerRole {

    public WarriorRole() {
        super("Warrior", 30, 40, 260, 20);
    }

    @Override
    public void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine) {
        // We prevent stun stacking by checking if the enemy is already stunned
        for (IStatusEffect effect : enemy.getActiveEffects()) {
            if (effect instanceof EffectStun) {
                System.out.println(enemy.getCombatantName() + " is already stunned! Shield Bash has no additional effect.");
                return;
            }
        }

        // deal basic attack damage
        int damage = Math.max(0, player.getAttack() - enemy.getDefense());
        enemy.takeDamage(damage);
        System.out.println("Warrior uses Shield Bash on " + enemy.getCombatantName() + " for " + damage + " damage!");

        enemy.setStatusEffect(new EffectStun());

        System.out.println(enemy.getCombatantName() + " is stunned!");
    }
}
