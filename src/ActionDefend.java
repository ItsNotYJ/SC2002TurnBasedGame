public class ActionDefend implements IAction {
    public ActionDefend() { }

    @Override
    public void execute(Combatant user, Combatant target, BattleEngine engine) {
        System.out.println(user.getCombatantName() + " assumes a defensive stance!");

        //after harini done:
        //user.setStatusEffect(new EffectDefend())
    }
}
