public class EffectDefend implements IStatusEffect {
    public EffectDefend() { }

    @Override
    public void applyEffect(Combatant target) {

    }

    @Override
    public void removeEffect(Combatant target) {

    }

    @Override
    public int getEffectDuration() {
        return 2;
    }

    @Override
    public boolean isEffectExpired() {
        return false;
    }
}
