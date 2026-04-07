public class EffectDefend implements IStatusEffect {
    private final String effectName = "Defend";
    private int duration = 2;

    @Override
    public String getEffectName() {
        return effectName;
    }

    @Override
    public void applyEffect(Combatant target) {
        target.setDefense(target.getDefense() + 10);
        System.out.println("\n" + target.getCombatantName() + " braces for impact! Defense increased by 10.");
    }

    @Override
    public void removeEffect(Combatant target) {
        target.setDefense(target.getDefense() - 10);
        System.out.println(target.getCombatantName() + "'s defensive stance ends.");
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

    public void refreshDuration() {
        duration = 2;
    }
}
