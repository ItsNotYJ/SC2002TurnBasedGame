public class ActionBasicAttack implements IAction {
    public ActionBasicAttack() { }

    @Override
    public void executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        target.takeDamage(user.getAttack() - target.getDefense());
    }
}
