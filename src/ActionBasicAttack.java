public class ActionBasicAttack implements IAction {
    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        int damage = Math.max(0, user.getAttack() - target.getDefense());
        target.takeDamage(damage);

        return this;
    }
}