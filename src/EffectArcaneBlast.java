public class EffectArcaneBlast implements IStatusEffect {
    private int duration = 99999;

    @Override
    public void applyEffect(Combatant target) {
        target.setAttack(target.getAttack() + 10);
        System.out.println(target.getCombatantName() + " surges with arcane energy! Attack boosted by 10.");
    }

    @Override
    public void removeEffect(Combatant target) {
        target.setAttack(target.getAttack() - 10);
    }

    @Override
    public void decreaseDuration() { duration--; }

    @Override
    public int getEffectDuration() {
        return duration;
    }

    @Override
    public boolean isEffectExpired() {
        return duration <= 0;
    }
}
