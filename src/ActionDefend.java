public class ActionDefend implements IAction {
    public ActionDefend() { }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        return new ActionDefend();
    }
}
