public class EffectDefend implements IStatusEffect {
    public EffectDefend() { }

    @Override
    public void applyEffect(Combatant target) {
        if (isEffectExpired())
            target.setDefense(target.getDefense() + 10);
    }

    @Override
    public void removeEffect(Combatant target) {
        if (!isEffectExpired())
            target.setDefense(target.getDefense() - 10);
    }

    @Override
    public int getEffectDuration() {
        return 2;
    }

    @Override
    public boolean isEffectExpired() {
        return getEffectDuration() == 0;
    }
}
