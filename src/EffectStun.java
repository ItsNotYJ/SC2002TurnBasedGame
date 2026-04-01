public class EffectStun implements IStatusEffect {
    private int duration = 2;

    @Override
    public void applyEffect(Combatant target) {
        // force the target to skip their turn
        target.setSkipTurn(true);

        System.out.println(target.getCombatantName() + " is stunned and cannot move!");
    }

    @Override
    public void removeEffect(Combatant target) {
        // allow the target to move again once the stun wears off
        target.setSkipTurn(false);
        System.out.println(target.getCombatantName() + " is no longer stunned.");
    }
    @Override
    public void decreaseDuration() {
        duration--;
    }

    @Override
    public int getEffectDuration() {
        return duration;
    }

    @Override
    public boolean isEffectExpired() {
        return duration <= 0;
    }
}
