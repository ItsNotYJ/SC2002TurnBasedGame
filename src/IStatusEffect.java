public interface IStatusEffect {
    void applyEffect(Combatant target);
    void removeEffect(Combatant target);
    int getEffectDuration();
    boolean isEffectExpired();
}