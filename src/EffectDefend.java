public class EffectDefend implements IStatusEffect {
    public EffectDefend() { }

    @Override
    public void applyEffect(Combatant target) {
        target.setDefense(target.getDefense() + 10);
    }

    @Override
    public void removeEffect(Combatant target) {
        target.setDefense(target.getDefense() - 10);
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
