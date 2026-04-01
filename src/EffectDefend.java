public class EffectDefend implements IStatusEffect {
    private int duration = 2;

    @Override
    public void applyEffect(Combatant target) {
        for (IStatusEffect e : target.getActiveEffects()) {
            if (e instanceof EffectDefend) {
                System.out.println("You already have a defend status active!");
                return;
            }
        }

        target.setDefense(target.getDefense() + 10);
        System.out.println(target.getCombatantName() + " braces for impact! Defense increased by 10.");
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
}
