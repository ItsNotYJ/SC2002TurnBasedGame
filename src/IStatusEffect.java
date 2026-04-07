public interface IStatusEffect {
    String getEffectName();
    void applyEffect(Combatant target);
    void removeEffect(Combatant target);
    int getEffectDuration();
    boolean isEffectExpired();
    void decreaseDuration();
}