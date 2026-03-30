public class ActionBasicAttack implements IAction {
    public ActionBasicAttack() { }

    @Override
    public void execute(Combatant user, Combatant target, BattleEngine engine) {
        int damage = Math.max(0, user.getAttack() - target.getDefense());
        target.takeDamage(damage);

        System.out.println(user.getCombatantName() + " used Basic Attack on " + target.getCombatantName() + " for " + damage + " damage!");
    }
}
