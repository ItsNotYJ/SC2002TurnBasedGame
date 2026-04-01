public class ActionBasicAttack implements IAction {
    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        int effectiveDamage = user.getAttack() - target.getDefense();

        if (effectiveDamage < 0) {
            effectiveDamage = 0;
        }

        target.takeDamage(effectiveDamage);
        return this;
    }
}