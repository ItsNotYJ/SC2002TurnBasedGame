
public class WarriorRole extends PlayerRole {

    public WarriorRole() {
        super("Warrior", 30, 40, 260, 20);
    }

    @Override
    public void doSpecialSkill(Player player, Combatant enemy, BattleEngine engine) {
        int effectiveDamage = Math.max(0, player.getAttack() - enemy.getDefense());
        enemy.takeDamage(effectiveDamage);
        enemy.setStatusEffect(new EffectStun());
    }
}
