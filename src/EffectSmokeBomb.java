public class EffectSmokeBomb implements IStatusEffect {
   private int duration = 2; //lasts for current and next turn

    @Override
    public void applyEffect(Combatant target) {
        //to notify console
        System.out.println(target.getCombatantName() + " is hidden in smoke and will take 0 damage!");
    }

    @Override
    public void removeEffect(Combatant target) {
        System.out.println("The smoke around " + target.getCombatantName() + " clears.");
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
