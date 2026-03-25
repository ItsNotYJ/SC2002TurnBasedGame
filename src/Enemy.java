import java.util.ArrayList;

public class Enemy extends Combatant {
    private IEnemyStrategy strategy;

    public Enemy(String name, int attack, int defense, int speed, int maxHP, IEnemyStrategy strategy) {
        super(name, speed, defense, attack, maxHP);

        this.strategy = strategy;
    }

    public IAction executeTurn(ArrayList<Combatant> combatants) {
        return this.strategy.executeAction(this, combatants);
    }

    @Override
    public void execute(Combatant user, Combatant target, BattleEngine engine) {

    }

    @Override
    public void applyEffect(Combatant target) {

    }

    @Override
    public void removeEffect(Combatant target) {

    }

    @Override
    public int getEffectDuration() {
        return 0;
    }

    @Override
    public boolean isEffectExpired() {
        return false;
    }
}
