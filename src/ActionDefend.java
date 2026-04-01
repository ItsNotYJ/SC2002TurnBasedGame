public class ActionDefend implements IAction {
    public ActionDefend() { }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        // If the player chooses to defend for his action, we create a new status effect for the player
        user.setStatusEffect(new EffectDefend());
        return this;
    }
}
