public class ActionDefend implements IAction {
    public ActionDefend() { }

    @Override
    public IAction executeTurn(Combatant user, Combatant target, BattleEngine engine) {
        System.out.println("\n" +user.getCombatantName() + " assumes a defensive stance!");

        // We want to check if the player already is defending
        for (IStatusEffect e : user.getActiveEffects()) {
            if (e instanceof EffectDefend effect) {
                // Our team decided to refresh the duration of the defend effect rather than wasting the player's action
                effect.refreshDuration();
                System.out.println("Defensive stance is refreshed! Duration reset to 2 turns.");
                return this;
            }
        }

        // only add new effect if no existing defend
        user.setStatusEffect(new EffectDefend());
        return this;
    }
}
