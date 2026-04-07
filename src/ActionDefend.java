public class ActionDefend implements IAction {
    public ActionDefend() { }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        System.out.println("\n" +user.getCombatantName() + " assumes a defensive stance!");

        user.setStatusEffect(new EffectDefend());
        return this;
    }
}
