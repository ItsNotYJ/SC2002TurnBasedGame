public class ActionSpecialSkill implements IAction {

    public ActionSpecialSkill() { }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        return new ActionSpecialSkill();
    }
}
